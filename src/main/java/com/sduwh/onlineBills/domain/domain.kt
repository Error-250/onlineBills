package com.sduwh.onlineBills.domain

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Date


data class User constructor(
        val id:        Long?   = null,
        val username:  String? = null,
        val password:  String? = null
)

data class Bill(
        val id:        Long?   = null,
        @JsonProperty("account_id")
        val accountId: Long?   = null,
        val money:     Double? = null,
        val info:      String? = null,
        val date:      Date?   = null
)