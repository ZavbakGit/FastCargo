package `fun`.gladkikh.app.fastcargo.presentation.test

import `fun`.gladkikh.app.fastcargo.common.presentation.BaseViewModel
import `fun`.gladkikh.app.fastcargo.common.type.Failure
import `fun`.gladkikh.app.fastcargo.common.type.None
import `fun`.gladkikh.app.fastcargo.domain.entity.AccountEntity
import `fun`.gladkikh.app.fastcargo.domain.entity.SettingsEntity
import `fun`.gladkikh.app.fastcargo.domain.usecase.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import java.util.*
import javax.inject.Inject

class TestViewModel @Inject constructor(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val saveSettingsUseCase: SaveSettingsUseCase,
    private val saveAccountUseCase: SaveAccountUseCase,
    private val getAccountUseCase: GetAccountUseCase,
    private val loginAccountUseCase: LoginAccountUseCase
) : BaseViewModel() {
    private val message = MutableLiveData<String>()
    fun getMessage(): LiveData<String> = message

    fun getSettings() {
        getSettingsUseCase(None(), viewModelScope) { either ->
            either.either(::handleError) {
                handleMessage(it.toString())
            }
        }
    }

    fun saveAccount() {
        val id = (0..10).random().toString()
        saveAccountUseCase(AccountEntity(id,"Name $id", Date(),id),viewModelScope){either ->
            either.either(::handleError) {
                handleMessage("Сохранили Аккаунт")
            }
        }
    }

    fun getAccount() {
        getAccountUseCase(None(),viewModelScope){either ->
            either.either(::handleError) {
                handleMessage(it.toString())
            }
        }
    }

    fun saveTestSettings(){
        val settings =  SettingsEntity(
            login1C = "Админ",
            password1C = "111",
            host = "http://172.31.255.150//UT/hs/api/",
            date = Date(),
            pdt = 1
        )
        saveSettingsUseCase(settings,viewModelScope){either ->
            either.either(::handleError) {
                handleMessage("Сохранили!")
            }
        }
    }

    fun login(){
        loginAccountUseCase("111",viewModelScope){either ->
            either.either(::handleError) {
                handleMessage(it.toString())
            }
        }
    }

    private fun handleMessage(str: String) {
        message.postValue(str)
    }

    private fun handleError(failure: Failure) {
        failureData.postValue(failure)
    }

}