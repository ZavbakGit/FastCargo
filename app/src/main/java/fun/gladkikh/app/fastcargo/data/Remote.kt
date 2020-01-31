package `fun`.gladkikh.app.fastcargo.data

import `fun`.gladkikh.app.fastcargo.common.type.Either
import `fun`.gladkikh.app.fastcargo.common.type.Failure

interface Remote {
    fun request(user:String,password:String,data: String): Either<Failure, String>
}