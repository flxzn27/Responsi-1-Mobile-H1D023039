package com.alfan.responsi1.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alfan.responsi1.databinding.FragmentPlayerDetailBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PlayerDetailFragment(
    private val name: String,
    private val position: String,
    private val dob: String,
    private val nationality: String
) : BottomSheetDialogFragment() {

    private var _binding: FragmentPlayerDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayerDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvDetailPlayerName.text = name
        binding.tvDetailPlayerPosition.text = "Posisi: $position"
        binding.tvDetailPlayerDob.text = "Tanggal Lahir: $dob"
        binding.tvDetailPlayerNationality.text = "Kebangsaan: $nationality"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
