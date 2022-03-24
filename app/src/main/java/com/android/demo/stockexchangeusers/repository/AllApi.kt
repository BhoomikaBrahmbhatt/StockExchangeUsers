package com.android.demo.stockexchangeusers.repository

object AllApi {

    const val BASE_URL = "https://api.stackexchange.com/"

    private const val V1 = "2.3/"

    const val USER_LIST = V1 + "users"
    const val TAG_DETAIL = V1 + "users/{user_id}/top-tags"

    const val USER_DATA = "userdata"

    const val PAGE = 1
    const val PAGE_SIZE =50
    const val ORDER = "asc"
    const val SORT="name"
    const val SITE="stackoverflow"
    const val FILTER="!)sb2*WaUM7n8Ngu6X(P2"
}
