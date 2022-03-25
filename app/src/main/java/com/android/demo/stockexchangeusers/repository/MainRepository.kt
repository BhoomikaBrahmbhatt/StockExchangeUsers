package com.android.demo.stockexchangeusers.repository

import com.android.demo.stockexchangeusers.network.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getAllUsers( page:Int,
                             pagesize:Int,
                             order:String,
                             sort:String,
                             site:String,
    filter:String) = retrofitService.getAllUsers(page, pagesize, order, sort, site,filter)

    suspend fun getTagdetails(userId : Int,
                              site:String
    ) = retrofitService.getTagDetail(userId, site)

}