package `fun`.gladkikh.app.fastcargo.domain.entity

import com.google.gson.annotations.SerializedName

data class AccountEntity(
    val user: String?,
    val guid: String?,
    val password: String?,
    var settings: Settings?
)

data class Settings(
    val name: String?,
    val guid: String?,
    @SerializedName("list_printer")
    val listPrinter: List<Printer>
)

data class Printer(
    val name: String?,
    val guid: String?,
    val current: Boolean?
)