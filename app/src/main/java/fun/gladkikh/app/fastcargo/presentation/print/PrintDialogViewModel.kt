package `fun`.gladkikh.app.fastcargo.presentation.print

import `fun`.gladkikh.app.fastcargo.common.presentation.BaseViewModel
import `fun`.gladkikh.app.fastcargo.common.type.SingleLiveEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class PrintDialogViewModel @Inject constructor() : BaseViewModel(), PrintDialogContract {

    private val stateDialog = MutableLiveData<PrintDialogContract.State>()
    private val countDialog = MutableLiveData<Int>()
    private val barcodeDialog = MutableLiveData<String>()
    private val productDialog = MutableLiveData<String>()
    private val commandDialog = SingleLiveEvent<PrintDialogContract.Command>()


    override fun getStateEvent(): LiveData<PrintDialogContract.State> {
        return stateDialog
    }

    override fun getCountEvent(): MutableLiveData<Int> {
        return countDialog
    }

    override fun getBarcode(): LiveData<String> {
        return barcodeDialog
    }

    override fun getProduct(): LiveData<String> {
        return productDialog
    }

    override fun setCommandEvent(command: PrintDialogContract.Command) {
        commandDialog.value  = command
    }

    fun setState(sate: PrintDialogContract.State) {
        stateDialog.value = sate
    }

    fun setCount(count: Int) {
        countDialog.value = count
    }


}