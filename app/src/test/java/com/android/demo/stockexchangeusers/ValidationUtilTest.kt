package com.android.demo.stockexchangeusers

import com.android.demo.stockexchangeusers.repository.BadgeCounts
import com.android.demo.stockexchangeusers.repository.Items
import com.android.demo.stockexchangeusers.utils.ValidationUtil
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidationUtilTest {

    @Test
    fun validateUserTest() {
        val itemUser = Items(BadgeCounts(8,1,0),35,12345678,12,"London",
        "https://www.gravatar.com/avatar/894891fa86576454f1cab28c28625425?s=256&d=identicon&r=PG",
        "Test User")
        assertEquals(true, ValidationUtil.validateList(itemUser))
    }

    @Test
    fun validateUserNameEmptyTest() {
        val itemUser = Items(BadgeCounts(8,1,0),35,12345678,12,"London",
            "https://www.gravatar.com/avatar/894891fa86576454f1cab28c28625425?s=256&d=identicon&r=PG",
            "")
        assertEquals(false, ValidationUtil.validateList(itemUser))
    }

}
