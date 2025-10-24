package com.alfan.responsi1.ui.fragment // Ganti dengan package name Anda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alfan.responsi1.databinding.FragmentPlayerDetailBinding // Ganti package name
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

// 1. Ubah parent & tambahkan constructor
class PlayerDetailFragment(
    private val name: String,
    private val position: String,
    private val dob: String,
    private val nationality: String
) : BottomSheetDialogFragment() {

    // 2. Setup ViewBinding
    private var _binding: FragmentPlayerDetailBinding? = null
    private val binding get() = _binding!!

    // 3. Override onCreateView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayerDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    // 4. Override onViewCreated untuk set data
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Isi TextViews dengan data dari constructor
        binding.tvDetailPlayerName.text = name
        binding.tvDetailPlayerPosition.text = "Posisi: $position"
        binding.tvDetailPlayerDob.text = "Tanggal Lahir: $dob"
        binding.tvDetailPlayerNationality.text = "Kebangsaan: $nationality"
    }

    // 5. Override onDestroyView
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Hindari memory leak
    }

    // Kode bawaan seperti companion object dan newInstance() dihapus karena tidak dipakai
}