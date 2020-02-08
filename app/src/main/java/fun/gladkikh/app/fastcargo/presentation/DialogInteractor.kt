package `fun`.gladkikh.app.fastcargo.presentation

import `fun`.gladkikh.app.fastcargo.common.type.SingleLiveEvent
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class DialogInteractor<T,C> @Inject constructor():ViewModel(){
    val toDialog = SingleLiveEvent<T>()
    val fromDialog = SingleLiveEvent<C>()
}