package com.android.demo.stockexchangeusers.repository

import com.google.gson.annotations.SerializedName


data class TagsResponse (
    @SerializedName("items"           ) var items          : ArrayList<Tags> = arrayListOf()
)

data class Tags (
    @SerializedName("answer_count"   ) var answerCount   : Int?    = null,
    @SerializedName("answer_score"   ) var answerScore   : Int?    = null,
    @SerializedName("question_count" ) var questionCount : Int?    = null,
    @SerializedName("question_score" ) var questionScore : Int?    = null,
    @SerializedName("tag_name"       ) var tagName       : String? = null
)