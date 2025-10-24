package com.alfan.responsi1

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.alfan.responsi1.databinding.ActivityCoachBinding // Ganti dengan package name Anda
import com.alfan.responsi1.viewmodel.MainViewModel // Ganti juga ini

class CoachActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoachBinding

    // Ambil instance ViewModel yang sama dengan MainActivity
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set gambar secara manual karena tidak dari API
        binding.ivCoach.setImageResource(R.drawable.coach)

        // Amati LiveData dari ViewModel
        viewModel.fetchTeamDetails(66)
        observeCoachData()

        // Atur tombol kembali
        binding.btnBackCoach.setOnClickListener {
            finish()
        }
    }

    private fun observeCoachData() {
        // Karena fetchTeamDetails sudah dipanggil di MainActivity,
        // kita hanya perlu mengamati perubahannya di sini.
        viewModel.teamDetails.observe(this) { teamResponse ->
            // Pastikan teamResponse dan coach tidak null
            teamResponse?.coach?.let { coachData ->
                binding.tvCoachName.text = coachData.name ?: "N/A" // Gunakan 'name' dari API coach
                binding.tvCoachDob.text = coachData.dateOfBirth ?: "N/A"
                binding.tvCoachNationality.text = coachData.nationality ?: "N/A"
            }
        }
    }
}