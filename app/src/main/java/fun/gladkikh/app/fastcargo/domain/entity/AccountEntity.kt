package `fun`.gladkikh.app.fastcargo.domain.entity

import com.google.gson.annotations.SerializedName
import java.util.*

data class AccountEntity(
    @SerializedName("user_id")
    var id: String?,
    var name: String?,
    @SerializedName("user_date")
    var date: Date?,
    var password: String?
)