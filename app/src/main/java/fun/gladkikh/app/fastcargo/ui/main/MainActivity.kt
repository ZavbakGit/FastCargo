package `fun`.gladkikh.app.fastcargo.ui.main

import `fun`.gladkikh.app.fastcargo.App
import `fun`.gladkikh.app.fastcargo.R
import `fun`.gladkikh.app.fastcargo.common.type.Failure
import `fun`.gladkikh.app.fastcargo.common.ui.BaseActivity
import `fun`.gladkikh.app.fastcargo.common.ui.ext.onEvent
import `fun`.gladkikh.app.fastcargo.domain.entity.AccountEntity
import `fun`.gladkikh.app.fastcargo.presentation.MainViewModel
import `fun`.gladkikh.app.fastcargo.ui.common.Command
import `fun`.gladkikh.app.fastcargo.ui.common.OpenFormCommand
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity:BaseActivity(){
    override val contentId = R.layout.main_activity

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)

        viewModel = viewModel {
            onEvent(getError(), ::handleFailure)
            onEvent(getCommand(),::handlerCommand)
            onEvent(getMessage(),::handlerMessage)
            onEvent(getAccount(),::handlerAccount)
        }


        btOpenSettings.setOnClickListener {
            viewModel.openSettings()
        }

        btUnlogin.setOnClickListener {
            viewModel.unlogin()
        }

    }


    override fun onResume() {
        super.onResume()
        viewModel.onStrat()
    }


    private fun handlerAccount(accountEntity: AccountEntity?){
        tvUser.text = accountEntity?.user?:""
    }


    private fun handlerCommand(command: Command?){
        when(command){
            is OpenFormCommand -> openForm(command)
        }
    }
    private fun handlerMessage(str: String?) {
        Toast.makeText(this,str, Toast.LENGTH_SHORT).show()
    }

    private fun handleFailure(failure: Failure?) {
        Toast.makeText(this,"Error:" + failure?.message?:"", Toast.LENGTH_SHORT).show()
    }
}