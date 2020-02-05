package `fun`.gladkikh.app.fastcargo.common.presentation

import `fun`.gladkikh.app.fastcargo.common.type.Failure
import `fun`.gladkikh.app.fastcargo.common.type.SingleLiveEvent
import `fun`.gladkikh.app.fastcargo.ui.common.Command
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



abstract class BaseViewModel : ViewModel() {

    protected var failureData = SingleLiveEvent<Failure>()
    protected var progressData: MutableLiveData<Boolean> = MutableLiveData()
    protected var command = SingleLiveEvent<Command>()

    fun getCommand():LiveData<Command> = command
    fun getError():LiveData<Failure> = failureData
    fun getProgressData():LiveData<Boolean> = progressData

    protected fun handleFailure(failure: Failure) {
        this.failureData.postValue(failure)
        updateProgress(false)
    }

    protected fun updateProgress(progress: Boolean) {
        this.progressData.postValue(progress)
    }
}