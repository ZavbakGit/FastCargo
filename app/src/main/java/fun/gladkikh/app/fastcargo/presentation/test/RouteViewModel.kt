package `fun`.gladkikh.app.fastcargo.presentation.test

import `fun`.gladkikh.app.fastcargo.common.presentation.BaseViewModel
import `fun`.gladkikh.app.fastcargo.common.type.None
import `fun`.gladkikh.app.fastcargo.domain.usecase.CheckAccountUseCase
import `fun`.gladkikh.app.fastcargo.domain.usecase.CheckSettingsUseCase
import `fun`.gladkikh.app.fastcargo.ui.common.OpenFormCommand
import `fun`.gladkikh.app.fastcargo.Constants.COMMAND_OPEN_LOGIN
import `fun`.gladkikh.app.fastcargo.Constants.COMMAND_OPEN_MAIN
import `fun`.gladkikh.app.fastcargo.Constants.COMMAND_OPEN_SETTINGS
import androidx.lifecycle.viewModelScope
import javax.inject.Inject

class RouteViewModel @Inject constructor(
    private val checkSettingsUseCase: CheckSettingsUseCase,
    private val checkAccountUseCase: CheckAccountUseCase
) : BaseViewModel() {

    fun start() {
        checkSettingsUseCase(None(),viewModelScope){
            if (it.isLeft){
                //Открываем настройки
                command.postValue(OpenFormCommand(COMMAND_OPEN_SETTINGS))
            }else{
                checkAccountUseCase(None(),viewModelScope){
                    if (it.isLeft){
                        command.postValue(OpenFormCommand(COMMAND_OPEN_LOGIN))
                    }else{
                        command.postValue(OpenFormCommand(COMMAND_OPEN_MAIN))
                    }

                }
            }
        }
    }

}