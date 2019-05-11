package com.sduwh.onlineBills.controller

import com.sduwh.onlineBills.domain.Role
import com.sduwh.onlineBills.domain.User
import com.sduwh.onlineBills.service.BillService
import com.sduwh.onlineBills.service.OnlineConfigService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import java.util.Date
import java.util.Calendar
import javax.servlet.http.HttpServletRequest

@Controller
class ViewController {
    @Autowired
    private
    lateinit var billService: BillService

    @Autowired
    private lateinit var onlineConfigService: OnlineConfigService

    @RequestMapping("/")
    fun index(): String {
        return "index"
    }

    @RequestMapping("/register")
    fun registerView(): String {
        return "register"
    }

    @RequestMapping("/home")
    fun home(httpServletRequest: HttpServletRequest): String {
        val user = httpServletRequest.getSession(true).getAttribute("user") as User
        // 尚未登陆, 跳转首页
        val years: List<Int>?
        val yearnums = billService.findYearsById(user.id!!)
        val calender = Calendar.getInstance()
        calender.time = Date()
        val thisYear = calender.get(Calendar.YEAR)
        years = thisYear.downTo(thisYear - yearnums).asSequence().toList()
        // 向freemarker传参数
        httpServletRequest.setAttribute("years", years)
        return "home"
    }

    @RequestMapping("/addBill")
    fun viewAddBill(httpServletRequest: HttpServletRequest): String {
        val user = httpServletRequest.getSession(true).getAttribute("user") as User
        httpServletRequest.setAttribute("tags", billService.getBillTags(user.id!!))
        return "addBill"
    }

    @RequestMapping("/viewSummaryMonthly")
    fun viewSummaryMonthly(httpServletRequest: HttpServletRequest): String {
        val user = httpServletRequest.getSession(true).getAttribute("user") as User
        val years: List<Int>?
        val yearnums = billService.findYearsById(user.id!!)
        val calender = Calendar.getInstance()
        calender.time = Date()
        val thisYear = calender.get(Calendar.YEAR)
        years = thisYear.downTo(thisYear - yearnums).asSequence().toList()

        httpServletRequest.setAttribute("years", years)
        return "summaryMonthly"
    }

    @RequestMapping("/viewSummaryYearly")
    fun summaryYearly(httpServletRequest: HttpServletRequest): String {
        return "summaryYearly"
    }

    @RequestMapping("/viewSummaryByComments")
    fun summaryByComments(httpServletRequest: HttpServletRequest): String {
        val user = httpServletRequest.getSession(true).getAttribute("user") as User
        val years: List<Int>?
        val yearnums = billService.findYearsById(user.id!!)
        val calender = Calendar.getInstance()
        calender.time = Date()
        val thisYear = calender.get(Calendar.YEAR)
        years = thisYear.downTo(thisYear - yearnums).asSequence().toList()
        httpServletRequest.setAttribute("years", years)
        return "summaryByComments"
    }

    @RequestMapping("/viewUser")
    fun viewUser(httpServletRequest: HttpServletRequest): String {
        return "user"
    }

    @RequestMapping("/config")
    fun viewConfig(httpServletRequest: HttpServletRequest): String {
        val user = httpServletRequest.getSession(true).getAttribute("user") as User
        if (user.role == Role.Admin) {
            httpServletRequest.setAttribute("configs", onlineConfigService.getConfigs())
            return "config"
        }
        return "index"
    }
}