package com.alfan.responsi1

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.alfan.responsi1.databinding.ActivityCoachBinding
import com.alfan.responsi1.viewmodel.MainViewModel

class CoachActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoachBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivCoach.setImageResource(R.drawable.coach)

        viewModel.fetchTeamDetails(66)
        observeCoachData()

        binding.btnBackCoach.setOnClickListener {
            finish()
        }
    }

    private fun observeCoachData() {
        viewModel.teamDetails.observe(this) { teamResponse ->
            teamResponse?.coach?.let { coachData ->
                binding.tvCoachName.text = coachData.name ?: "N/A"
                binding.tvCoachDob.text = coachData.dateOfBirth ?: "N/A"
                binding.tvCoachNationality.text = coachData.nationality ?: "N/A"
            }
        }
    }
}
