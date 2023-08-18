package com.paulo.motionanimation.shopCart.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    val isLoading  = MutableStateFlow(true)

    fun changeLoading(){
        viewModelScope.launch {
            delay(3000)
            isLoading.update { false }
        }

    }


}