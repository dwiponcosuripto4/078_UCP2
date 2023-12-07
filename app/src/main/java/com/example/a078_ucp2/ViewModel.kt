package com.example.a078_ucp2

import androidx.lifecycle.ViewModel
import com.example.a078_ucp2.data.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ViewModel : ViewModel() {
    private val _stateUI = MutableStateFlow(UIState())
    val stateUI: StateFlow<UIState> = _stateUI.asStateFlow()

    fun setData(listData: MutableList<String>){
        _stateUI.update { stateSaatIni ->
            stateSaatIni.copy(
                namamhs = listData[0],
                nim = listData[1],
                konsen = listData[2],
                judulsk = listData[3]
            )
        }
    }
    fun setDosen(dosenPilihan: String) {
        _stateUI.update { stateSaatIni ->
            stateSaatIni.copy(dosen = dosenPilihan)
        }
    }
    fun resetOrder(){
        _stateUI.value = UIState()
    }

}