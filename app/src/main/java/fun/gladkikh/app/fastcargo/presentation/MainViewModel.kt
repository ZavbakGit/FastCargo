package `fun`.gladkikh.app.fastcargo.presentation

import `fun`.gladkikh.app.fastcargo.Constants
import `fun`.gladkikh.app.fastcargo.common.presentation.BaseViewModel
import `fun`.gladkikh.app.fastcargo.common.type.Failure
import `fun`.gladkikh.app.fastcargo.common.type.None
import `fun`.gladkikh.app.fastcargo.domain.entity.AccountEntity
import `fun`.gladkikh.app.fastcargo.domain.usecase.GetAccountUseCase
import `fun`.gladkikh.app.fastcargo.domain.usecase.RemoveAccountUseCase
import `fun`.gladkikh.app.fastcargo.ui.common.OpenFormCommand
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getAccountUseCase: GetAccountUseCase,
    private val removeAccountUseCase: RemoveAccountUseCase
) :
    BaseViewModel() {

    private val message = MutableLiveData<String>()
    fun getMessage(): LiveData<String> = message

    private val accountEntity = MutableLiveData<AccountEntity>()
    fun getAccount(): LiveData<AccountEntity> = accountEntity

    fun onStrat() {

        getAccountUseCase(None(), viewModelScope) {
            it.either(::handleError, ::handlerGetAccount)
        }
    }

    fun unlogin() {
        removeAccountUseCase(None(),viewModelScope){
            it.either(::handleError,::handlerUnlogin)
        }
    }

    private fun handlerUnlogin(none:None) {
        command.postValue(OpenFormCommand(Constants.COMMAND_OPEN_ROUTE))
    }


    fun openSettings() {
        command.postValue(OpenFormCommand(Constants.COMMAND_OPEN_SETTINGS))
    }


    fun handlerGetAccount(account: AccountEntity) {
        accountEntity.postValue(account)
    }

    private fun handleError(failure: Failure) {
        failureData.postValue(failure)
    }




}