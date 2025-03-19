package com.vedatakcan.switchnavigationsample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppViewModel : ViewModel() {

    private val _switchStates = MutableLiveData<Map<String, Boolean>>()
    val switchStates: LiveData<Map<String, Boolean>> = _switchStates

    private val _bottomMenuItems = MutableLiveData<List<String>>()
    val bottomMenuItems: LiveData<List<String>> = _bottomMenuItems

    private val maxMenuItems = 5 // En fazla 5 menü eklenebilir


    init {
        // Varsayılan switch ve menü durumu (Ego açık, diğerleri kapalı)
        _switchStates.value = mapOf(
            "switchEgo" to true,
            "switchSettings" to false,
            "switchHelp" to false,
            "switchNotifications" to false,
            "switchProfile" to false,
            "switchSearch" to false
        )
        _bottomMenuItems.value = listOf() // Başlangıçta boş olacak
    }

    // Switch durumunu güncelle
    fun updateSwitchState(key: String, state: Boolean) {
        val updateStates = _switchStates.value?.toMutableMap() ?: mutableMapOf()

        updateStates[key] = state

        // Eğer kapalıysa diğer switchler açılabilir
        if (key == "switchEgo" && !state) {
            updateStates?.keys?.forEach { if (it != "switchEgo") updateStates[it] = true }
            _bottomMenuItems.value = listOf("home") // Bottom menü de home eklenir
        }

        _switchStates.value = updateStates
    }

    // Buttom menüye eleman ekleme
    fun addMenuItem(item: String){
        val currentItems = _bottomMenuItems.value ?: listOf()

        if (!currentItems.contains(item) && currentItems.size < maxMenuItems) {
            _bottomMenuItems.value = currentItems + item
        }
    }

}