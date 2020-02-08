package `fun`.gladkikh.app.fastcargo.presentation.print

import `fun`.gladkikh.app.fastcargo.common.presentation.BaseViewModel
import `fun`.gladkikh.app.fastcargo.common.presentation.StateDialog
import `fun`.gladkikh.app.fastcargo.common.type.SingleLiveEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class MainPrintViewModel @Inject constructor():BaseViewModel(){

    private val stateDialog = MutableLiveData<StateDialog>()
    private val dialogBarcode = MutableLiveData<String>()
    private val dialogCount = MutableLiveData<Int>()

    private val startDialog = SingleLiveEvent<Boolean>()

    fun getstateDialog():LiveData<StateDialog> = stateDialog
    fun getdialogBarcode():LiveData<String> = dialogBarcode
    fun getdialogCount():LiveData<Int> = dialogCount
    fun getStartDialog():LiveData<Boolean> = startDialog


    fun setStateDialog(state: StateDialog){
        stateDialog.value = state
    }

    fun setBarcode(barcode:String){
        dialogBarcode.value = barcode
        startDialog.value = true
    }

    fun setCount(count:Int){
        dialogCount.value = count
    }



}