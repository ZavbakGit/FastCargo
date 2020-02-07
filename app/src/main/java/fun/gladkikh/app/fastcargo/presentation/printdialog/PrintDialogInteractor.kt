package `fun`.gladkikh.app.fastcargo.presentation.printdialog

import `fun`.gladkikh.app.fastcargo.common.type.SingleLiveEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

import javax.inject.Inject

/**
 * @author erick
 * @since 2018-12-31
 */
class PrintDialogInteractor @Inject constructor() : ViewModel(), PrintDialogContract.Interactor {

    private val commandEvent = SingleLiveEvent<PrintDialogContract.Interactor.Command>()
    private val count = MutableLiveData<Int>()
    private val stateEvent = SingleLiveEvent<PrintDialogContract.Interactor.State>()

    val date = MutableLiveData<Date>()

    init {
        date.value = Date()
    }

    override fun getCommandEvent(): LiveData<PrintDialogContract.Interactor.Command> {
        return commandEvent
    }

    override fun issueCommand(command: PrintDialogContract.Interactor.Command) {
        commandEvent.value = command
    }

    override fun getCountEvent(): LiveData<Int> {
        return count
    }

    override fun setCount(password: Int) {
        count.value = count.value?:0 + password
    }

    override fun setState(state: PrintDialogContract.Interactor.State) {
        stateEvent.value = state
    }

    override fun getStateEvent(): LiveData<PrintDialogContract.Interactor.State> {
        return stateEvent
    }
}