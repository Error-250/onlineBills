package com.sduwh.onlineBills.service

import com.sduwh.onlineBills.domain.User
import com.sduwh.onlineBills.mapper.LoginMapper
import com.sduwh.onlineBills.mapper.UserMapper
import org.springframework.beans.factory.annotation.Autowired

interface UserService {
    fun login(username: String, password: String): Boolean
    fun checkUname(username: String): Boolean
    fun findOne(username: String): User
    fun findUserById(userId: Long): User
    fun addUser(username: String, password: String)
}

class UserServiceImpl: UserService {
    @Autowired
    lateinit var loginMapper: LoginMapper
    @Autowired
    lateinit var userMapper: UserMapper

    override fun login(username: String, password: String): Boolean {
        return loginMapper.findOne(username, password) > 0
    }

    override fun checkUname(username: String): Boolean {
        return loginMapper.findUname(username) == 0
    }

    override fun findOne(username: String): User {
        return userMapper.findOne(username)
    }

    override fun findUserById(userId: Long): User {
        return userMapper.findUserById(userId)
    }

    override fun addUser(username: String, password: String) {
        userMapper.insert(username, password)
    }
}