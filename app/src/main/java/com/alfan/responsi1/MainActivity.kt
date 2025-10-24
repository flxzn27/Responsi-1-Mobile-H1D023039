package com.alfan.responsi1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.alfan.responsi1.databinding.ActivityMainBinding
import com.alfan.responsi1.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MainActivity", "onCreate START")
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("MainActivity", "Calling fetchTeamDetails(66)")
        viewModel.fetchTeamDetails(66)

        setupNavigation()
        observeTeamName()
    }

    private fun setupNavigation() {
        binding.btnHistory.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }

        binding.btnCoach.setOnClickListener {
            startActivity(Intent(this, CoachActivity::class.java))
        }

        binding.btnSquad.setOnClickListener {
            startActivity(Intent(this, SquadActivity::class.java))
        }
    }

    private fun observeTeamName() {
        viewModel.teamDetails.observe(this) { team ->
            Log.d("MainActivity", "Observed team details in MainActivity: $team")
            team?.let {
                binding.tvClubName.text = it.name
            }
        }
    }
}
