package `fun`.gladkikh.app.fastcargo.ui.route

import `fun`.gladkikh.app.fastcargo.App
import `fun`.gladkikh.app.fastcargo.R
import `fun`.gladkikh.app.fastcargo.common.MyLog
import `fun`.gladkikh.app.fastcargo.common.type.Failure
import `fun`.gladkikh.app.fastcargo.common.ui.BaseActivity
import `fun`.gladkikh.app.fastcargo.common.ui.ext.onEvent
import `fun`.gladkikh.app.fastcargo.presentation.RouteViewModel
import `fun`.gladkikh.app.fastcargo.ui.common.Command
import `fun`.gladkikh.app.fastcargo.ui.common.OpenFormCommand
import android.os.Bundle

class RouteActivity : BaseActivity(){
    override val contentId = R.layout.route_activity

    lateinit var viewModel: RouteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)

        viewModel = viewModel {
            onEvent(getError(), ::handleFailure)
            onEvent(getCommand(),::handlerCommand)
        }


    }

    override fun onResume() {
        super.onResume()
        viewModel.start()
    }



    private fun handlerCommand(command:Command?){
        when(command){
            is OpenFormCommand -> openForm(command)
        }
    }

    private fun handleFailure(failure: Failure?) {
        MyLog.logD(failure?.message?:"")
    }

}