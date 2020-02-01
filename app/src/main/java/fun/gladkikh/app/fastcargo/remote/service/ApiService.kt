package `fun`.gladkikh.app.fastcargo.remote.service

import `fun`.gladkikh.app.fastcargo.remote.core.RequestEntity
import `fun`.gladkikh.app.fastcargo.remote.core.ResponseEntity
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("host")
    fun getDataFromServer(
        @Header("Authorization") auth: String,
        @Body requestEntity: RequestEntity
    ): Call<ResponseEntity>
}