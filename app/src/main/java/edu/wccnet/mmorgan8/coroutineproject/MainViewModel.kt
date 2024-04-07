package edu.wccnet.mmorgan8.coroutineproject

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    companion object {
        var cardText = mutableListOf<String>()
    }


    fun addCardText(output: String) {
        cardText.add(output)
    }
}