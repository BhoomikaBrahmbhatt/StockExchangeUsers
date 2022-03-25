package com.android.demo.stockexchangeusers

import com.android.demo.stockexchangeusers.network.RetrofitService
import com.android.demo.stockexchangeusers.repository.AllApi
import com.android.demo.stockexchangeusers.repository.AllApi.USER_LIST
import com.google.gson.Gson
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.MediaType

import org.hamcrest.CoreMatchers




class RetrofitServiceTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var apiService: RetrofitService
    lateinit var gson: Gson

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        gson = Gson()
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(RetrofitService::class.java)
    }


    @Test
    fun `get all users api test`() {
        runBlocking {
            val mockResponse = MockResponse()
            mockWebServer.enqueue(mockResponse.setBody("{}"))
            val response = apiService.getAllUsers(
                AllApi.PAGE,
                AllApi.PAGE_SIZE,
                AllApi.ORDER,
                AllApi.SORT,
                AllApi.SITE,
                AllApi.FILTER
            )
            val request = mockWebServer.takeRequest()
           // assertEquals(USER_LIST,request.getUR)
            assertEquals(true, response.body()?.items.isNullOrEmpty())


        }
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }


}