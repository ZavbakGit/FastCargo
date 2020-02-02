package `fun`.gladkikh.app.fastcargo.remote1


import `fun`.gladkikh.app.fastcargo.common.type.Either
import `fun`.gladkikh.app.fastcargo.common.type.Failure



interface RequestRemote {
    fun request(user:String,password:String,data: String): Either<Failure, String>
}