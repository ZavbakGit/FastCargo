package `fun`.gladkikh.app.fastcargo.ui.common

sealed class Command
data class OpenFormCommand(val id:String, val data:Any? = null):Command()