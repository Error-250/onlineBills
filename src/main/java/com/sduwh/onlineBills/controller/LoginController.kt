package com.sduwh.onlineBills.controller

import com.sduwh.onlineBills.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
class LoginController {
    @Autowired
    private
    lateinit var userService: UserService

    @RequestMapping("/login", method = [RequestMethod.POST])
    fun login(@RequestParam("username") username: String,
              @RequestParam("password") password: String,
              httpServletRequest: HttpServletRequest): Map<String, Boolean> {
        val result = HashMap<String, Boolean>()
        if(userService.login(username, password)) {
            result["login"] = true
            val user = userService.findOne(username)
            httpServletRequest.getSession(true).setAttribute("user", user)
        } else {
            result["login"] = false
        }
        return result
    }

    @RequestMapping("/checkUname", method = [RequestMethod.POST])
    private fun checkUname(@RequestParam("uname") uname: String): Boolean {
        return userService.checkUname(uname)
    }

    @RequestMapping("/regist", method = [RequestMethod.POST])
    fun regist(@RequestParam("username") username: String,
               @RequestParam("password") password: String): String{
        if (checkUname(username)) {
            userService.addUser(username, password)
            return "TRUE"
        }
        return "FALSE"
    }

    @RequestMapping("/signout")
    fun signOut(httpServletRequest: HttpServletRequest,
                httpServletResponse: HttpServletResponse) {
        httpServletRequest.getSession(true).removeAttribute("user")
        httpServletResponse.sendRedirect("/")
    }
}