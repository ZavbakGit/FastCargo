package `fun`.gladkikh.app.fastcargo.presentation.printdialog


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


interface PrintDialogContract {
    interface ViewModel {
        fun getCountStream(): MutableLiveData<Int>
        fun getMessageStream(): LiveData<String>
        fun setMessage(message: String)
    }

    interface Interactor {
        interface Command {
            class ShowMessage(val message: String) : Command
            class Close : Command
        }

        enum class State {
            ALIVE, DEAD
        }

        fun getCommandEvent(): LiveData<Command>
        fun issueCommand(command: Command)
        fun getCountEvent(): LiveData<Int>
        fun setCount(count: Int)
        fun setState(state: State)
        fun getStateEvent(): LiveData<State>
    }
}