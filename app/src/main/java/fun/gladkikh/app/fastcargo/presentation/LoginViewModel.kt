package `fun`.gladkikh.app.fastcargo.presentation
import `fun`.gladkikh.app.fastcargo.App
import `fun`.gladkikh.app.fastcargo.Constants
import `fun`.gladkikh.app.fastcargo.common.presentation.BaseViewModel
import `fun`.gladkikh.app.fastcargo.common.type.Failure
import `fun`.gladkikh.app.fastcargo.common.type.None
import `fun`.gladkikh.app.fastcargo.domain.entity.SettingsEntity
import `fun`.gladkikh.app.fastcargo.domain.usecase.GetSettingsUseCase
import `fun`.gladkikh.app.fastcargo.domain.usecase.LoginAccountUseCase
import `fun`.gladkikh.app.fastcargo.domain.usecase.RemoveAccountUseCase
import `fun`.gladkikh.app.fastcargo.domain.usecase.TestRemoteRequestUseCase
import `fun`.gladkikh.app.fastcargo.ui.common.OpenFormCommand
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginAccountUseCase: LoginAccountUseCase,
    private val removeAccountUseCase: RemoveAccountUseCase,
    private val getSettingsUseCase: GetSettingsUseCase,
    private val testRemoteRequestUseCase: TestRemoteRequestUseCase
) : BaseViewModel() {
    private val message = MutableLiveData<String>()
    fun getMessage(): LiveData<String> = message


    fun onStrat(){
        getSettingsUseCase(None(),viewModelScope){
            it.either(::handleError,::handlerGetSetting)
        }
    }


    fun login(password:String){
        loginAccountUseCase(password,viewModelScope){
            it.either(::handleError,::handleLogin)
        }
    }

    fun unlogin() {
        removeAccountUseCase(None(),viewModelScope){
            it.either(::handleError,::handleUnlogin)
        }
    }

    fun openSettings() {
        command.postValue(OpenFormCommand(Constants.COMMAND_OPEN_SETTINGS))
    }

    fun testConnect() {
        testRemoteRequestUseCase(None(),viewModelScope){
            it.either(::handleError,::handlerTestConnect)
        }
    }

    fun handlerTestConnect(str:String){
        message.postValue(str)
    }


    fun handlerGetSetting(settingsEntity: SettingsEntity){
        App.initRequestRemote(settingsEntity.host!!)
    }
    private fun handleUnlogin(none:None) {
        command.postValue(OpenFormCommand(Constants.COMMAND_OPEN_ROUTE))
    }


    private fun handleLogin(none:None) {
        command.postValue(OpenFormCommand(Constants.COMMAND_OPEN_MAIN))
    }

    private fun handleError(failure: Failure) {
        failureData.postValue(failure)
    }




}