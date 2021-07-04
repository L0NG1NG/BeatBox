package com.longing.beatbox

import android.content.res.AssetManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BeatBoxViewModel(val assets: AssetManager) : ViewModel() {
    val beatBox = BeatBox(assets)

    override fun onCleared() {
        super.onCleared()
        beatBox.release()
    }
}

class BeatBoxViewModelFactory(val assets: AssetManager) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BeatBoxViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BeatBoxViewModel(assets) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}