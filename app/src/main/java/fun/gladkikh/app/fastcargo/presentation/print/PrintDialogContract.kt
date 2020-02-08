package `fun`.gladkikh.app.fastcargo.presentation.print

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface PrintDialogContract{
    fun getStateEvent(): LiveData<State>
    fun getCountEvent():MutableLiveData<Int>
    fun getBarcode(): LiveData<String>
    fun getProduct(): LiveData<String>
    fun setCommandEvent(command:Command)

    enum class State {
        ALIVE, DEAD
    }

    sealed class Command {
        class Open :Command()
        class Close : Command()
    }
}