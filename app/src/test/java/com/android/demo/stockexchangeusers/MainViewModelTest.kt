package com.android.demo.stockexchangeusers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.demo.stockexchangeusers.network.RetrofitService
import com.android.demo.stockexchangeusers.repository.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MainViewModelTest {


    private val testDispatcher = TestCoroutineDispatcher()
    lateinit var mainViewModel: MainViewModel
    lateinit var mainRepository: MainRepository
    var userList = ArrayList<Items>()
    var emptyuserList = ArrayList<Items>()

    @Mock
    lateinit var apiService: RetrofitService

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()



    private val itemUser  = Items(
        BadgeCounts(8,1,0),35,12345678,12,"London",
        "https://www.gravatar.com/avatar/894891fa86576454f1cab28c28625425?s=256&d=identicon&r=PG",
        "Test User")

    lateinit var userResponse : UsersResponse
    lateinit var userEmptyResponse : UsersResponse
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        mainRepository = MainRepository(apiService)
        mainViewModel = MainViewModel(mainRepository)
         userList.add(itemUser)
         userResponse = UsersResponse(userList , true,300,299 )
        userEmptyResponse= UsersResponse(emptyuserList , false,300,299 )
    }

    @Test
    fun getAllUsersTest() {
        runBlocking {
            Mockito.`when`(mainRepository.getAllUsers(
                AllApi.PAGE,
                AllApi.PAGE_SIZE,
                AllApi.ORDER,
                AllApi.SORT,
                AllApi.SITE,
                AllApi.FILTER
            ))
                .thenReturn(Response.success(userResponse))

            mainViewModel.getAllUsers(
                AllApi.PAGE,
                AllApi.PAGE_SIZE,
                AllApi.ORDER,
                AllApi.SORT,
                AllApi.SITE,
                AllApi.FILTER
            )
            val result = mainViewModel.usersList.getOrAwaitValue()
            assertEquals( userList, result)
        }
    }


    @Test
    fun `empty users list test`() {
        runBlocking {
            Mockito.`when`(mainRepository.getAllUsers(
                AllApi.PAGE,
                AllApi.PAGE_SIZE,
                AllApi.ORDER,
                AllApi.SORT,
                AllApi.SITE,
                AllApi.FILTER
            ))
                .thenReturn(Response.success(userEmptyResponse))
            mainViewModel.getAllUsers(
                AllApi.PAGE,
                AllApi.PAGE_SIZE,
                AllApi.ORDER,
                AllApi.SORT,
                AllApi.SITE,
                AllApi.FILTER
            )
            val result = mainViewModel.usersList.getOrAwaitValue()
            assertEquals(emptyuserList, result)
        }
    }

}