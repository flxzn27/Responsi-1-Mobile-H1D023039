package com.alfan.responsi1.viewmodel

import android.util.Log // <-- Pastikan ini di-import
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alfan.responsi1.data.model.TeamResponse
import com.alfan.responsi1.data.network.RetrofitInstance
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    // LiveData untuk menyimpan seluruh data tim
    private val _teamDetails = MutableLiveData<TeamResponse>()
    val teamDetails: LiveData<TeamResponse> = _teamDetails

    // LiveData untuk status loading
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    // === TAMBAHKAN BLOK INIT ===
    init {
        Log.d("ViewModel", "ViewModel Initialized") // Log saat ViewModel dibuat
    }
    // ============================

    fun fetchTeamDetails(teamId: Int) {
        _isLoading.value = true
        // === TAMBAHKAN LOG DI SINI ===
        Log.d("ViewModel", "Starting API call for team ID: $teamId")
        // ============================
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getTeamDetails(teamId)
                if (response.isSuccessful) {
                    _teamDetails.value = response.body()
                    // === TAMBAHKAN LOG SUKSES ===
                    Log.d("ViewModel", "API Success: Data received - ${response.body()}")
                    // ============================
                } else {
                    // === TAMBAHKAN LOG ERROR API ===
                    Log.e("ViewModel", "API Error: Code=${response.code()}, Message=${response.message()}")
                    // ============================
                    // Handle error (misal: post pesan error ke LiveData lain)
                }
            } catch (e: Exception) {
                // === TAMBAHKAN LOG EXCEPTION ===
                Log.e("ViewModel", "API Exception: ${e.message}", e)
                // ============================
                // Handle exception (misal: post pesan error ke LiveData lain)
            } finally {
                _isLoading.value = false
            }
        }
    }
}