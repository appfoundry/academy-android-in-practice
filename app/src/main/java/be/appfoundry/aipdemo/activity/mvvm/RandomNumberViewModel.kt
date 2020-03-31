package be.appfoundry.aipdemo.activity.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class RandomNumberViewModel : ViewModel() {

    val number = MutableLiveData<Int>()

    fun generateRandom() {
        number.value = Random().nextInt()
    }
}