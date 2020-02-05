package `fun`.gladkikh.app.fastcargo.ui.common

import `fun`.gladkikh.app.fastcargo.ui.login.LoginActivity
import `fun`.gladkikh.app.fastcargo.ui.main.MainActivity
import `fun`.gladkikh.app.fastcargo.ui.settings.SettingsActivity
import `fun`.gladkikh.app.fastcargo.Constants.COMMAND_OPEN_LOGIN
import `fun`.gladkikh.app.fastcargo.Constants.COMMAND_OPEN_MAIN
import `fun`.gladkikh.app.fastcargo.Constants.COMMAND_OPEN_ROUTE
import `fun`.gladkikh.app.fastcargo.Constants.COMMAND_OPEN_SETTINGS
import `fun`.gladkikh.app.fastcargo.ui.route.RouteActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle


class Navigator(val context: Context) {

    fun openForm(openForm: OpenFormCommand){
        when(openForm.id){
            COMMAND_OPEN_SETTINGS ->  context.startActivity<SettingsActivity>()
            COMMAND_OPEN_LOGIN ->  context.startActivity<LoginActivity>()
            COMMAND_OPEN_MAIN ->  context.startActivity<MainActivity>(true)
            COMMAND_OPEN_ROUTE ->  context.startActivity<RouteActivity>(true)
        }
    }

    private inline fun <reified T> Context.startActivity(newTask: Boolean = false, args: Bundle? = null) {
        this.startActivity(Intent(this, T::class.java).apply {
            if (newTask) {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }
            putExtra("args", args)
        })
    }
}


