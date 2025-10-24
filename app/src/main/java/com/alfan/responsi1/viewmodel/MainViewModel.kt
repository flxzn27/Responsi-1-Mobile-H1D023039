package com.alfan.responsi1.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alfan.responsi1.data.model.TeamResponse
import com.alfan.responsi1.data.network.RetrofitInstance
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _teamDetails = MutableLiveData<TeamResponse>()
    val teamDetails: LiveData<TeamResponse> = _teamDetails

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        Log.d("ViewModel", "ViewModel Initialized")
    }

    fun fetchTeamDetails(teamId: Int) {
        _isLoading.value = true
        Log.d("ViewModel", "Starting API call for team ID: $teamId")
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getTeamDetails(teamId)
                if (response.isSuccessful) {
                    _teamDetails.value = response.body()
                    Log.d("ViewModel", "API Success: Data received - ${response.body()}")
                } else {
                    Log.e("ViewModel", "API Error: Code=${response.code()}, Message=${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("ViewModel", "API Exception: ${e.message}", e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}
