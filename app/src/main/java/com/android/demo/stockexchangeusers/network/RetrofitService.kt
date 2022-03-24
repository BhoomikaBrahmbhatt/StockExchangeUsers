package com.android.demo.stockexchangeusers.network

import com.android.demo.stockexchangeusers.repository.AllApi.BASE_URL
import com.android.demo.stockexchangeusers.repository.AllApi.USER_LIST
import com.android.demo.stockexchangeusers.repository.UsersResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
// @GET("users/{user_id}/playlists")
//  Call<List<Playlist> getUserPlaylists(@Path(value = "user_id", encoded = true) String userId);
//}

    //page=1&pagesize=20&order=asc&sort=name&site=stackoverflow
    @GET(USER_LIST)
    suspend fun getAllUsers(@Query("page") page:Int,
                            @Query("pagesize") pagesize:Int,
                            @Query("order") order:String,
                            @Query("sort") sort:String,
                            @Query("site") site:String,
    @Query("filter") filter:String) : Response<UsersResponse>

    companion object {
        var retrofitService: RetrofitService? = null
        fun getInstance() : RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }

    }

}