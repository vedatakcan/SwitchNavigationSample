package com.vedatakcan.switchnavigationsample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppViewModel : ViewModel() {

    // Switch durumlarını tutan LiveData'lar

    private val _isEgoSwitchChecked = MutableLiveData(true) // Başlangıçta açık
    val isEgoSwitchChecked: LiveData<Boolean> = _isEgoSwitchChecked

    private val _isSettingsSwitchChecked = MutableLiveData(false)
    val isSettingsSwitchChecked: LiveData<Boolean> = _isSettingsSwitchChecked

    private val _isHelpSwitchChecked = MutableLiveData(false)
    val isHelpSwitchChecked: LiveData<Boolean> = _isHelpSwitchChecked

    private val _isNotificationsSwitchChecked = MutableLiveData(false)
    val isNotificationsSwitchChecked: LiveData<Boolean> = _isNotificationsSwitchChecked

    private val _isProfileSwitchChecked = MutableLiveData(false)
    val isProfileSwitchChecked: LiveData<Boolean> = _isProfileSwitchChecked

    private val _isSearchSwitchChecked = MutableLiveData(false)
    val isSearchSwitchChecked: LiveData<Boolean> = _isSearchSwitchChecked

    // Bottom Navigation'ın görünürlüğünü kontrol eden LiveData
    private val _isBottomNavVisible = MutableLiveData(false)
    val isBottomNavVisible: LiveData<Boolean> = _isBottomNavVisible

    // Switch durumlarını değiştiren fonksiyonlar
    fun setEgoSwitchChecked(isChecked: Boolean) {
        _isEgoSwitchChecked.value = isChecked

        // Ego switch açıldığında, diğer switch'leri kapat
        if (isChecked) {
            _isSettingsSwitchChecked.value = false
            _isHelpSwitchChecked.value = false
            _isNotificationsSwitchChecked.value = false
            _isProfileSwitchChecked.value = false
            _isSearchSwitchChecked.value = false
        }

        // Ego switch kapalı olduğunda, Bottom Navigation'ı kontrol et
        _isBottomNavVisible.value = !isChecked
    }

    fun setSettingsSwitchChecked(isChecked: Boolean) {
        if (_isEgoSwitchChecked.value == true) {
            // Ego switch açıkken diğer switch'lerin açılmasına izin verme
            _isSettingsSwitchChecked.value = false
        } else {
            _isSettingsSwitchChecked.value = isChecked
        }
    }

    fun setHelpSwitchChecked(isChecked: Boolean) {
        if (_isEgoSwitchChecked.value == true) {
            // Ego switch açıkken diğer switch'lerin açılmasına izin verme
            _isHelpSwitchChecked.value = false
        } else {
            _isHelpSwitchChecked.value = isChecked
        }
    }

    fun setNotificationsSwitchChecked(isChecked: Boolean) {
        if (_isEgoSwitchChecked.value == true) {
            // Ego switch açıkken diğer switch'lerin açılmasına izin verme
            _isNotificationsSwitchChecked.value = false
        } else {
            _isNotificationsSwitchChecked.value = isChecked
        }
    }

    fun setProfileSwitchChecked(isChecked: Boolean) {
        if (_isEgoSwitchChecked.value == true) {
            // Ego switch açıkken diğer switch'lerin açılmasına izin verme
            _isProfileSwitchChecked.value = false
        } else {
            _isProfileSwitchChecked.value = isChecked
        }
    }

    fun setSearchSwitchChecked(isChecked: Boolean) {
        if (_isEgoSwitchChecked.value == true) {
            // Ego switch açıkken diğer switch'lerin açılmasına izin verme
            _isSearchSwitchChecked.value = false
        } else {
            _isSearchSwitchChecked.value = isChecked
        }
    }
}
