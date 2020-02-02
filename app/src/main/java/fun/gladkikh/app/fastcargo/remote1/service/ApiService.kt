package `fun`.gladkikh.app.fastcargo.remote1.service

import `fun`.gladkikh.app.fastcargo.remote1.entity.RequestEntity
import `fun`.gladkikh.app.fastcargo.remote1.entity.ResponseEntity
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("host")
    fun getDataFromServer(
        @Header("Authorization") auth: String,
        @Body requestEntity: RequestEntity
    ): Call<ResponseEntity>
}