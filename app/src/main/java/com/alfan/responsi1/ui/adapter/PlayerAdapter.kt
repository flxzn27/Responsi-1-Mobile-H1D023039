package com.alfan.responsi1.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.alfan.responsi1.R
import com.alfan.responsi1.data.model.Player
import com.alfan.responsi1.databinding.ItemPlayerBinding

interface OnPlayerClickListener {
    fun onPlayerClick(player: Player)
}

class PlayerAdapter(private var players: List<Player>, private val listener: OnPlayerClickListener) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    inner class PlayerViewHolder(val binding: ItemPlayerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val binding = ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return players.size
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = players[position]

        holder.binding.tvPlayerName.text = player.name ?: "N/A"
        holder.binding.tvPlayerNationality.text = player.nationality ?: "N/A"

        val playerCategory = when (player.position) {
            "Goalkeeper" -> "Goalkeeper"
            "Defence", "Centre-Back", "Left-Back", "Right-Back" -> "Defender"
            "Central Midfield", "Defensive Midfield", "Attacking Midfield" -> "Midfielder"
            "Offence", "Right Winger", "Centre-Forward" -> "Forward"
            else -> "Unknown"
        }

        val colorRes = when (playerCategory) {
            "Goalkeeper" -> R.color.kuning
            "Defender" -> R.color.biru
            "Midfielder" -> R.color.hijau
            "Forward" -> R.color.merah
            else -> R.color.abu_abu
        }

        holder.binding.cardPlayer.setCardBackgroundColor(
            ContextCompat.getColor(holder.itemView.context, colorRes)
        )

        holder.binding.root.setOnClickListener {
            listener.onPlayerClick(player)
        }
    }

    fun setData(newPlayers: List<Player>) {
        players = newPlayers
        notifyDataSetChanged()
    }
}
