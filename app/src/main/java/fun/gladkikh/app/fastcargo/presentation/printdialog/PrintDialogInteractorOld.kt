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
class PrintDialogInteractorOld @Inject constructor() : ViewModel(), PrintDialogContractOld.Interactor {

    private val commandEvent = SingleLiveEvent<PrintDialogContractOld.Interactor.Command>()
    private val count = MutableLiveData<Int>()
    private val stateEvent = SingleLiveEvent<PrintDialogContractOld.Interactor.State>()

    val date = MutableLiveData<Date>()

    val dateCreate = Date()

    init {
        date.value = Date()
    }

    override fun getCommandEvent(): LiveData<PrintDialogContractOld.Interactor.Command> {
        return commandEvent
    }

    override fun issueCommand(command: PrintDialogContractOld.Interactor.Command) {
        commandEvent.value = command
    }

    override fun getCountEvent(): LiveData<Int> {
        return count
    }

    override fun setCount(password: Int) {
        count.value = count.value?:0 + password
    }

    override fun setState(state: PrintDialogContractOld.Interactor.State) {
        stateEvent.value = state
    }

    override fun getStateEvent(): LiveData<PrintDialogContractOld.Interactor.State> {
        return stateEvent
    }
}