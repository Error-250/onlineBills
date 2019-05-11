package com.sduwh.onlineBills.domain

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Date

enum class Role {
    Admin,
    User
}

data class User(
        val id: Long? = null,
        val username: String? = null,
        val password: String? = null,
        val role: Role = Role.User
)

data class Bill(
        val id: Long? = null,
        @JsonProperty("account_id")
        val accountId: Long? = null,
        val money: Double? = null,
        val info: String? = null,
        val date: Date? = null
)

data class RestfulResponse (
        val success: Boolean,
        val message: String? = null,
        val data: Any? = null
)

data class Config (
        val id:      Long?   = null,
        val appKey:  String? = null,
        val key:     String? = null,
        val value:   String? = null,
        val comment: String? = null
)