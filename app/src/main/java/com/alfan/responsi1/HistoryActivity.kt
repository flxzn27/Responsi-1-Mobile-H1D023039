package com.alfan.responsi1 // Ganti dengan package name Anda

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alfan.responsi1.databinding.ActivityHistoryBinding // Ganti dengan package name Anda

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Atur listener untuk tombol kembali
        binding.btnBackHistory.setOnClickListener {
            finish() // Menutup Activity saat ini dan kembali ke Activity sebelumnya (MainActivity)
        }
    }
}