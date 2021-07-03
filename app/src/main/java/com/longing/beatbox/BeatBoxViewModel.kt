package com.longing.beatbox

import android.content.res.AssetManager
import androidx.lifecycle.ViewModel

class BeatBoxViewModel : ViewModel() {
    var beatBox: BeatBox? = null

    fun initializeBeatBox(assets: AssetManager) {
        if (beatBox == null) {
            beatBox = BeatBox(assets)
        }

    }

    override fun onCleared() {
        super.onCleared()
        beatBox?.release()
    }
}