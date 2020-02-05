package `fun`.gladkikh.app.fastcargo.ui.login

import `fun`.gladkikh.app.fastcargo.App
import `fun`.gladkikh.app.fastcargo.R
import `fun`.gladkikh.app.fastcargo.common.type.Failure
import `fun`.gladkikh.app.fastcargo.common.ui.BaseActivity
import `fun`.gladkikh.app.fastcargo.common.ui.ext.onEvent
import `fun`.gladkikh.app.fastcargo.presentation.test.LoginViewModel
import `fun`.gladkikh.app.fastcargo.ui.common.Command
import `fun`.gladkikh.app.fastcargo.ui.common.OpenFormCommand
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.login_activity.*

class LoginActivity:BaseActivity(){
    override val contentId = R.layout.login_activity

    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)

        viewModel = viewModel {
            onEvent(getError(), ::handleFailure)
            onEvent(getCommand(),::handlerCommand)
            onEvent(getMessage(),::handlerMessage)
        }

        btLogin.setOnClickListener {
            viewModel.login(edPassword.text.toString())
        }

        btUnlogin.setOnClickListener {
            viewModel.unlogin()
        }

        btOpenSettings.setOnClickListener {
            viewModel.openSettings()
        }

        btTestConnect.setOnClickListener {
            viewModel.testConnect()
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.onStrat()
    }


    private fun handlerCommand(command: Command?){
        when(command){
            is OpenFormCommand -> openForm(command)
        }
    }
    private fun handlerMessage(str: String?) {
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show()
    }

    private fun handleFailure(failure: Failure?) {
        Toast.makeText(this,"Error:" + failure?.message?:"",Toast.LENGTH_SHORT).show()
    }
}