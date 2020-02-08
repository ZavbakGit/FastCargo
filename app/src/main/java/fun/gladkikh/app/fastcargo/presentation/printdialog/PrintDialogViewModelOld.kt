package `fun`.gladkikh.app.fastcargo.presentation.printdialog

import `fun`.gladkikh.app.fastcargo.common.presentation.BaseViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class PrintDialogViewModelOld @Inject constructor() :BaseViewModel(),PrintDialogContractOld.ViewModel{

    private val countStream = MutableLiveData<Int>()
    private val messageStream = MutableLiveData<String>()

    override fun getCountStream(): MutableLiveData<Int> {
        return countStream
    }

    override fun getMessageStream(): LiveData<String> {
        return messageStream
    }

    override fun setMessage(message: String) {
        messageStream.value = message
    }

}