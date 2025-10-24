package com.alfan.responsi1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alfan.responsi1.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBackHistory.setOnClickListener {
            finish()
        }
    }
}
