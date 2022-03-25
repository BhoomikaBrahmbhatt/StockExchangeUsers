package com.android.demo.stockexchangeusers.repository

import com.google.gson.annotations.SerializedName



data class UsersResponse(

    @SerializedName("items"           ) var items: ArrayList<Items> = arrayListOf(),
    @SerializedName("has_more"        ) var hasMore: Boolean?         = null,
    @SerializedName("quota_max"       ) var quotaMax: Int?             = null,
    @SerializedName("quota_remaining" ) var quotaRemaining: Int?             = null

)
data class BadgeCounts (

    @SerializedName("bronze" ) var bronze : Int? = null,
    @SerializedName("silver" ) var silver : Int? = null,
    @SerializedName("gold"   ) var gold   : Int? = null

)
data class Items (

    @SerializedName("badge_counts"  ) var badgeCounts  : BadgeCounts? = BadgeCounts(),
    @SerializedName("reputation"    ) var reputation   : Int?         = null,
    @SerializedName("creation_date" ) var creationDate : Int?         = null,
    @SerializedName("user_id"       ) var userId       : Int?         = null,
    @SerializedName("location"      ) var location     : String?      = null,
    @SerializedName("profile_image" ) var profileImage : String?      = null,
    @SerializedName("display_name"  ) var displayName  : String?      = null
)