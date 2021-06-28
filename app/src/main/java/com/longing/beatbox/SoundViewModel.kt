package com.longing.beatbox

import androidx.lifecycle.MutableLiveData

//class SoundViewModel : BaseObservable() {
//    var sound: Sound? = null
//        set(sound) {
//            field = sound
//            notifyChange()
//        }
//
//    @get:Bindable
//    val tile: String?
//        get() = sound?.name
//}


class SoundViewModel {

    val title: MutableLiveData<String?> = MutableLiveData()

    var sound: Sound? = null
        set(sound) {
            field = sound
            title.postValue(sound?.name)
        }
}