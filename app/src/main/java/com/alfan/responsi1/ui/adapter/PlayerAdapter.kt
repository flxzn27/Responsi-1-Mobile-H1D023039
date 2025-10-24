package com.alfan.responsi1.ui.adapter // Ganti dengan package name Anda

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.alfan.responsi1.R // Import R dari package utama Anda
import com.alfan.responsi1.data.model.Player // Import data class Player Anda
import com.alfan.responsi1.databinding.ItemPlayerBinding // Import binding untuk item_player.xml

interface OnPlayerClickListener {
    fun onPlayerClick(player: Player) // Fungsi yang akan dipanggil saat item diklik
}
class PlayerAdapter(private var players: List<Player>, private val listener: OnPlayerClickListener) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    // ViewHolder untuk menyimpan referensi view item
    inner class PlayerViewHolder(val binding: ItemPlayerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        // Membuat binding untuk layout item_player.xml
        val binding = ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        // Mengembalikan jumlah pemain dalam list
        return players.size
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = players[position]

        // Set data ke TextViews
        holder.binding.tvPlayerName.text = player.name ?: "N/A"
        holder.binding.tvPlayerNationality.text = player.nationality ?: "N/A"

        // === Logika Pemetaan Posisi ke Kategori ===
        val playerCategory = when (player.position) {
            "Goalkeeper" -> "Goalkeeper"
            "Defence", "Centre-Back", "Left-Back", "Right-Back" -> "Defender"
            "Central Midfield", "Defensive Midfield", "Attacking Midfield" -> "Midfielder"
            "Offence", "Right Winger", "Centre-Forward" -> "Forward"
            else -> "Unknown" // Kategori default jika posisi tidak dikenal
        }

        // === Logika Penentuan Warna Berdasarkan Kategori ===
        val colorRes = when (playerCategory) {
            "Goalkeeper" -> R.color.kuning // Pastikan warna ini ada di colors.xml
            "Defender" -> R.color.biru
            "Midfielder" -> R.color.hijau
            "Forward" -> R.color.merah
            else -> R.color.abu_abu // Warna default untuk 'Unknown'
        }

        // Terapkan warna background ke CardView
        holder.binding.cardPlayer.setCardBackgroundColor(
            ContextCompat.getColor(holder.itemView.context, colorRes)
        )

        holder.binding.root.setOnClickListener {
            listener.onPlayerClick(player) // Panggil fungsi interface saat item diklik
        }
    }

    // Fungsi untuk mengupdate data di adapter
    fun setData(newPlayers: List<Player>) {
        players = newPlayers
        notifyDataSetChanged() // Memberi tahu RecyclerView data berubah
    }
}