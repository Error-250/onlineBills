package com.sduwh.onlineBills.controller

import com.sduwh.onlineBills.domain.Role
import com.sduwh.onlineBills.domain.User
import com.sduwh.onlineBills.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
class UserController {
    @Autowired
    lateinit var userService: UserService

    @RequestMapping("/users", method = [RequestMethod.GET])
    fun findUsers(@RequestParam("user_name", defaultValue = "") userName: String,
                  httpServletRequest: HttpServletRequest): List<User> {
        val user = httpServletRequest.getSession(true).getAttribute("user") as User
        println(userName)
        return when(user.role) {
            Role.Admin -> userService.findUsers(userName)
            Role.User -> ArrayList()
        }
    }

    @RequestMapping("/users",method = [RequestMethod.PUT])
    fun updateUser(@RequestParam("password") password: String,
                   httpServletRequest: HttpServletRequest) {
        val user = httpServletRequest.getSession(true).getAttribute("user") as User
        when(user.role) {
            Role.Admin -> userService.updatePassword(user.id!!, password)
            Role.User -> {}
        }
    }
}