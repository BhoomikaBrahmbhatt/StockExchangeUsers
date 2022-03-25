package com.android.demo.stockexchangeusers

import com.android.demo.stockexchangeusers.network.RetrofitService
import com.android.demo.stockexchangeusers.repository.AllApi
import com.android.demo.stockexchangeusers.repository.MainRepository
import com.android.demo.stockexchangeusers.repository.UsersResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@RunWith(JUnit4::class)
class MainRepositoryTest {

    lateinit var mainRepository: MainRepository

    @Mock
    lateinit var apiService: RetrofitService

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mainRepository = MainRepository(apiService)
    }

    @Test
    fun `get all userslist test`() {
        runBlocking {
            val responsedata = apiService.getAllUsers(
                AllApi.PAGE,
                AllApi.PAGE_SIZE,
                AllApi.ORDER,
                AllApi.SORT,
                AllApi.SITE,
                AllApi.FILTER
            )
            Mockito.`when`(responsedata).thenReturn(Response.success(UsersResponse()))
            val response = mainRepository.getAllUsers(
                AllApi.PAGE,
                AllApi.PAGE_SIZE,
                AllApi.ORDER,
                AllApi.SORT,
                AllApi.SITE,
                AllApi.FILTER
            )
            assertEquals(UsersResponse(), response.body())
        }

    }

}