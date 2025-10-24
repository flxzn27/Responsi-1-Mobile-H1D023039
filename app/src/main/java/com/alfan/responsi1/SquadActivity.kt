package com.alfan.responsi1 // Ganti dengan package name Anda

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfan.responsi1.data.model.Player // Import data class Player
import com.alfan.responsi1.databinding.ActivitySquadBinding // Ganti package name
import com.alfan.responsi1.ui.adapter.OnPlayerClickListener
import com.alfan.responsi1.ui.adapter.PlayerAdapter // Ganti package name
import com.alfan.responsi1.viewmodel.MainViewModel // Ganti package name
import com.alfan.responsi1.ui.fragment.PlayerDetailFragment // Import fragment

// === Implementasikan Interface ===
class SquadActivity : AppCompatActivity(), OnPlayerClickListener {

    private lateinit var binding: ActivitySquadBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var playerAdapter: PlayerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySquadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // === Modifikasi inisialisasi Adapter ===
        // Kirim 'this' (SquadActivity) sebagai listener
        playerAdapter = PlayerAdapter(emptyList(), this)

        setupRecyclerView()

        viewModel.fetchTeamDetails(66)
        observeSquadData()

        binding.btnBackSquad.setOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {
        binding.rvPlayers.apply {
            layoutManager = LinearLayoutManager(this@SquadActivity)
            adapter = playerAdapter
        }
    }

    private fun observeSquadData() {
        viewModel.teamDetails.observe(this) { teamResponse ->
            val squadList: List<Player> = teamResponse?.squad ?: emptyList()
            playerAdapter.setData(squadList)
        }
    }

    // === Implementasi fungsi dari Interface ===
    override fun onPlayerClick(player: Player) {
        // Buat instance fragment dengan data pemain yang diklik
        val fragment = PlayerDetailFragment(
            name = player.name ?: "N/A",
            position = player.position ?: "N/A",
            dob = player.dateOfBirth ?: "N/A",
            nationality = player.nationality ?: "N/A"
        )
        // Tampilkan BottomSheetDialogFragment [cite: 1570-1571]
        fragment.show(supportFragmentManager, "PlayerDetailFragmentTag")
    }
}