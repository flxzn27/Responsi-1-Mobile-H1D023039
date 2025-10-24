package com.alfan.responsi1

import android.content.Intent
import android.os.Bundle
import android.util.Log // <-- Pastikan ini di-import
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.alfan.responsi1.databinding.ActivityMainBinding
import com.alfan.responsi1.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    // 1. Inisialisasi ViewBinding
    private lateinit var binding: ActivityMainBinding

    // 2. Inisialisasi ViewModel
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        // === TAMBAHKAN LOG START ===
        Log.d("MainActivity", "onCreate START")
        // ==========================
        super.onCreate(savedInstanceState)

        // 3. Setup ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//         4. Panggil API saat Activity dibuat
//         Kita panggil sekali di sini, agar datanya bisa dipakai di halaman lain
//         === TAMBAHKAN LOG SEBELUM FETCH ===
        Log.d("MainActivity", "Calling fetchTeamDetails(66)")
        // ===================================
        viewModel.fetchTeamDetails(66) // ID 66 untuk Manchester United

        // 5. Atur listener untuk tombol-tombol
        setupNavigation()

        // 6. (Opsional) Amati LiveData untuk update UI di halaman ini
        observeTeamName()
    }

    private fun setupNavigation() {
        // Atur OnClickListener untuk setiap tombol

        binding.btnHistory.setOnClickListener {
            // Intent Eksplisit untuk pindah ke HistoryActivity
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

        binding.btnCoach.setOnClickListener {
            // Intent Eksplisit untuk pindah ke CoachActivity
            val intent = Intent(this, CoachActivity::class.java)
            startActivity(intent)
        }

        binding.btnSquad.setOnClickListener {
            // Intent Eksplisit untuk pindah ke SquadActivity
            val intent = Intent(this, SquadActivity::class.java)
            startActivity(intent)
        }
    }

    private fun observeTeamName() {
        // Amati LiveData 'teamDetails' dari ViewModel
        viewModel.teamDetails.observe(this) { team ->
            // === TAMBAHKAN LOG DI OBSERVER ===
            Log.d("MainActivity", "Observed team details in MainActivity: $team")
            // ==================================
            // 'team' bisa jadi null saat pertama kali, jadi kita cek
            team?.let {
                binding.tvClubName.text = it.name // Update TextView dengan nama dari API
            }
        }
    }
}