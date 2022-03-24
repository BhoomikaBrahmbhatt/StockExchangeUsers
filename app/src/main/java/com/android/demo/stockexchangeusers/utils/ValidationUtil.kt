package com.android.demo.stockexchangeusers.utils

import com.android.demo.stockexchangeusers.repository.Items

object ValidationUtil {

    fun validateList(items: Items) : Boolean {
        if (items.displayName?.isNotEmpty() == true) {
            return true
        }
        return false
    }
}