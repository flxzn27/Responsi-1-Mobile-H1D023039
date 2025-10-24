package com.alfan.responsi1

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfan.responsi1.data.model.Player
import com.alfan.responsi1.databinding.ActivitySquadBinding
import com.alfan.responsi1.ui.adapter.OnPlayerClickListener
import com.alfan.responsi1.ui.adapter.PlayerAdapter
import com.alfan.responsi1.viewmodel.MainViewModel
import com.alfan.responsi1.ui.fragment.PlayerDetailFragment

class SquadActivity : AppCompatActivity(), OnPlayerClickListener {

    private lateinit var binding: ActivitySquadBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var playerAdapter: PlayerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySquadBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

    override fun onPlayerClick(player: Player) {
        val fragment = PlayerDetailFragment(
            name = player.name ?: "N/A",
            position = player.position ?: "N/A",
            dob = player.dateOfBirth ?: "N/A",
            nationality = player.nationality ?: "N/A"
        )
        fragment.show(supportFragmentManager, "PlayerDetailFragmentTag")
    }
}
