# Responsi 1 Mobile (H1D023039) - Aplikasi Info Klub Sepakbola

Aplikasi Android sederhana yang dibuat untuk memenuhi tugas Responsi 1 Praktikum Pemrograman Mobile. Aplikasi ini menampilkan informasi tentang klub sepakbola Manchester United FC menggunakan API dari football-data.org.

## Informasi Praktikan

* *Nama:* Alfan Fauzan Ridlo
* *NIM:* H1D023039 
* *Shift Baru:* B 
* *Shift Asal:* C 

## Video Demo Aplikasi 

Berikut adalah video demo aplikasi yang menunjukkan fungsionalitas utama dan tampilan ikon aplikasi:
![Demo Aplikasi Responsi 1](Demo%20Aplikasi.gif)

## Penjelasan Alur Data (API Call -> Display)

Aplikasi ini menggunakan arsitektur MVVM (Model-View-ViewModel) untuk memisahkan logika tampilan, data, dan proses bisnis. Berikut alur datanya:

1.  *Inisiasi (MainActivity)*:
    * Saat MainActivity dibuka, instance MainViewModel dibuat (atau diambil jika sudah ada) menggunakan by viewModels().
    * MainActivity memanggil fungsi viewModel.fetchTeamDetails(66) untuk meminta data tim Manchester United (ID 66).

2.  *Panggilan API (MainViewModel)*:
    * Fungsi fetchTeamDetails di MainViewModel berjalan di dalam viewModelScope (menggunakan Kotlin Coroutines untuk operasi asynchronous).
    * RetrofitInstance.api.getTeamDetails(66) dipanggil. Ini menggunakan Retrofit untuk membuat request HTTP GET ke endpoint https://api.football-data.org/v4/teams/66 dengan menyertakan header X-Auth-Token.

3.  *Respons & Parsing (Retrofit/Gson)*:
    * API football-data.org merespons dengan data JSON.
    * Retrofit, dengan bantuan GsonConverterFactory, secara otomatis mem-parsing JSON tersebut menjadi objek Kotlin TeamResponse (beserta objek Coach dan List<Player> di dalamnya).

4.  *Pembaruan LiveData (MainViewModel)*:
    * Jika respons API berhasil (response.isSuccessful), MainViewModel memperbarui nilai _teamDetails (sebuah MutableLiveData) dengan objek TeamResponse yang diterima.
    * Jika terjadi error (API error atau exception jaringan/parsing), pesan error dicatat ke Logcat.

5.  *Observasi & Pembaruan UI (Activities)*:
    * MainActivity, CoachActivity, dan SquadActivity masing-masing mengamati (observe) teamDetails (LiveData) dari instance MainViewModel yang sama (atau instance baru jika fetch dipanggil ulang di activity tersebut).
    * Ketika nilai teamDetails berubah (karena data API berhasil diterima), lambda di dalam blok observe akan dieksekusi:
        * Di MainActivity: TextView nama klub diperbarui.
        * Di CoachActivity: Data dari teamResponse.coach digunakan untuk mengisi TextView nama, tanggal lahir, dan kebangsaan pelatih.
        * Di SquadActivity: Daftar pemain (teamResponse.squad) diambil dan dikirim ke PlayerAdapter melalui fungsi adapter.setData(squadList).

6.  *Tampilan RecyclerView (PlayerAdapter)*:
    * PlayerAdapter menerima daftar Player baru.
    * notifyDataSetChanged() dipanggil untuk memberitahu RecyclerView agar me-refresh tampilannya.
    * Untuk setiap pemain dalam daftar, onBindViewHolder dipanggil:
        * Data pemain (nama, kebangsaan) diisi ke TextView di layout item_player.xml.
        * Posisi pemain dari API (player.position) dipetakan ke dalam kategori (Goalkeeper, Defender, Midfielder, Forward).
        * Berdasarkan kategori, warna background MaterialCardView diatur menggunakan ContextCompat.getColor().

7.  *Interaksi Fragment (SquadActivity -> PlayerDetailFragment)*:
    * PlayerAdapter memiliki OnClickListener pada setiap item.
    * Ketika item pemain diklik, listener.onPlayerClick(player) dipanggil.
    * SquadActivity (yang mengimplementasikan OnPlayerClickListener) menerima objek player yang diklik.
    * SquadActivity membuat instance PlayerDetailFragment, mengirimkan data pemain yang relevan (nama, posisi, dll.) melalui constructor Fragment.
    * fragment.show() dipanggil untuk menampilkan BottomSheetDialogFragment.
    * PlayerDetailFragment, dalam onViewCreated, menggunakan data yang diterima dari constructor untuk mengisi TextView detail pemain.
