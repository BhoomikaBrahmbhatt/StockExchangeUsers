package com.android.demo.stockexchangeusers.network

import com.android.demo.stockexchangeusers.repository.AllApi.BASE_URL
import com.android.demo.stockexchangeusers.repository.AllApi.TAG_DETAIL
import com.android.demo.stockexchangeusers.repository.AllApi.USER_LIST
import com.android.demo.stockexchangeusers.repository.TagsResponse
import com.android.demo.stockexchangeusers.repository.UsersResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET(USER_LIST)
    suspend fun getAllUsers(@Query("page") page:Int,
                            @Query("pagesize") pagesize:Int,
                            @Query("order") order:String,
                            @Query("sort") sort:String,
                            @Query("site") site:String,
    @Query("filter") filter:String) : Response<UsersResponse>


    @GET(TAG_DETAIL)
    suspend fun getTagDetail( @Path("user_id") userid: Int,
                              @Query("site") site: String) : Response<TagsResponse>
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