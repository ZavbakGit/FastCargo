package `fun`.gladkikh.app.fastcargo.presentation.test

import `fun`.gladkikh.app.fastcargo.App
import `fun`.gladkikh.app.fastcargo.common.presentation.BaseViewModel
import `fun`.gladkikh.app.fastcargo.common.type.Failure
import `fun`.gladkikh.app.fastcargo.common.type.None
import `fun`.gladkikh.app.fastcargo.domain.entity.AccountEntity
import `fun`.gladkikh.app.fastcargo.domain.entity.Printer
import `fun`.gladkikh.app.fastcargo.domain.entity.Settings
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
    private val testRemoteRequestUseCase: TestRemoteRequestUseCase,
    private val loginAccountUseCase: LoginAccountUseCase,
    private val checkSettingsUseCase: CheckSettingsUseCase,
    private val removeAccountUseCase: RemoveAccountUseCase,
    private val checkAccountUseCase: CheckAccountUseCase
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

      val entity =   AccountEntity(
            user = "Alex",
            password = "111",
            guid = "111",
            settings = Settings(
                name = "Хабаровск",
                guid = "111",
                listPrinter = listOf(
                    Printer(
                        name = "Принтер 1",
                        guid = "111",
                        current = false
                    ),
                    Printer(
                        name = "Принтер 2",
                        guid = "222",
                        current = true
                    )
                )
            )
        )

        saveAccountUseCase(entity, viewModelScope) { either ->
            either.either(::handleError) {
                handleMessage("Сохранили Аккаунт")
            }
        }
    }

    fun getAccount() {
        getAccountUseCase(None(), viewModelScope) { either ->
            either.either(::handleError) {
                handleMessage(it.toString())
            }
        }
    }

    fun saveTestSettings() {
        val settings = SettingsEntity(
            login1C = "Админ",
            password1C = "123",
            host = "http://172.31.255.150//UT/hs/api/",
            date = Date(),
            pdt = 1
        )
        App.initRequestRemote(settings.host!!)
        //ToDo Не забыть инициализировать

        saveSettingsUseCase(settings, viewModelScope) { either ->
            either.either(::handleError) {
                handleMessage("Сохранили!")
            }
        }
    }

    fun testRemoteRequest() {
        testRemoteRequestUseCase(None(), viewModelScope) { either ->
            either.either(::handleError) {
                handleMessage(it)
            }
        }
    }

    fun login1() {
        loginAccountUseCase("111", viewModelScope) { either ->
            either.either(::handleError) {
                handleMessage("Ок")
            }
        }
    }

    fun login2() {
        loginAccountUseCase("222", viewModelScope) { either ->
            either.either(::handleError) {
                handleMessage(it.toString())
            }
        }
    }

    fun login3() {
        loginAccountUseCase("333", viewModelScope) { either ->
            either.either(::handleError) {
                handleMessage(it.toString())
            }
        }
    }

    fun clearSettings() {
        val settings = SettingsEntity(
            login1C = "",
            password1C = "",
            host = "",
            date = Date(),
            pdt = 1
        )
        App.initRequestRemote(settings.host!!)
        //ToDo Не забыть инициализировать

        saveSettingsUseCase(settings, viewModelScope) { either ->
            either.either(::handleError) {
                handleMessage("Сохранили!")
            }
        }
    }

    fun checkSetting() {
        checkSettingsUseCase(None(), viewModelScope) { either ->
            either.either(::handleError) {
                handleMessage("Ок")
            }
        }
    }


    fun removeAccount(){
        removeAccountUseCase(None(),viewModelScope){ either ->
            either.either(::handleError) {
                handleMessage("Ок")
            }
        }
    }

    fun checkAccount() {
        checkAccountUseCase(None(), viewModelScope) { either ->
            either.either(::handleError) {
                handleMessage("Ок")
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