package com.android.demo.stockexchangeusers.repository

object AllApi {
//https://api.stackexchange.com/2.3/users?page=1&pagesize=20&order=asc&sort=name&site=stackoverflow
    //https://api.stackexchange.com/2.3/users/818544/tags?order=desc&sort=popular&site=stackoverflow


    const val BASE_URL = "https://api.stackexchange.com/"

    private const val V1 = "2.3/"

    const val USER_LIST = V1 + "users"

    const val PAGE = 1
    const val PAGE_SIZE =20
    const val ORDER = "asc"
    const val SORT="name"
    const val SITE="stackoverflow"
    const val FILTER="!)sb2*WaUM7n8Ngu6X(P2"
}
