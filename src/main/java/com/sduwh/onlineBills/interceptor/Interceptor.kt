package com.sduwh.onlineBills.interceptor

import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import java.util.logging.Logger
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

open class LoginInterceptor: HandlerInterceptor {
    var logger: Logger = Logger.getLogger("LoginInterceptor")
    override fun preHandle(request: HttpServletRequest?, response: HttpServletResponse?, handler: Any?): Boolean {
        logger.info("[${request!!.method}] ${request.requestURI} Start here.")
        if(request.getSession(true).getAttribute("user") == null) {
            response!!.sendRedirect("/")
            return false
        }
        return true
    }

    override fun postHandle(request: HttpServletRequest?, response: HttpServletResponse?, handler: Any?, modelAndView: ModelAndView?) {
        logger.info("[${request!!.method}] ${request.requestURI} End.")
    }
}