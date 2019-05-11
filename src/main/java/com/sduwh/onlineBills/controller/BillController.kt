package com.sduwh.onlineBills.controller

import com.sduwh.onlineBills.domain.Bill
import com.sduwh.onlineBills.domain.User
import com.sduwh.onlineBills.service.BillService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.text.SimpleDateFormat
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
class BillController {
    @Autowired
    private
    lateinit var billService: BillService

    @RequestMapping("/search")
    fun search(@RequestParam("year") year: Int,
               @RequestParam("month") month: Int,
               httpServletRequest: HttpServletRequest): List<Bill> {
        val user = httpServletRequest.getSession(true).getAttribute("user") as User
        return billService.findBillByMonth(user.id!!, year, month)
    }

    @RequestMapping("/addBill", method = [RequestMethod.POST])
    fun addBill(@RequestParam("money") money: Double,
                @RequestParam("type") type: Int,
                @RequestParam("date") date: String,
                @RequestParam("info", defaultValue = "") info: String,
                httpServletRequest: HttpServletRequest,
                httpServletResponse: HttpServletResponse) {
        val user = httpServletRequest.getSession(true).getAttribute("user") as User
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        if(type == 0)
            billService.addBill(Bill(null, user.id!!, money * -1, info, simpleDateFormat.parse(date)))
        else
            billService.addBill(Bill(null, user.id, money, info, simpleDateFormat.parse(date)))
        httpServletResponse.sendRedirect("/addBill")
    }

    @RequestMapping("/summaryMonth")
    fun summaryByMonth(@RequestParam("year") year: Int,
                       httpServletRequest: HttpServletRequest): List<Map<String, Any>> {
        val user = httpServletRequest.getSession(true).getAttribute("user") as User
        return billService.summaryMonthly(user.id!!, year)
    }

    @RequestMapping("/summaryYearly")
    fun summaryYearly(@RequestParam("option") option: Int,
                      httpServletRequest: HttpServletRequest): List<Map<String, Any>> {
        val user = httpServletRequest.getSession(true).getAttribute("user") as User
        return billService.summaryYearly(user.id!!, option)
    }

    @RequestMapping("/summaryByComments")
    fun summaryByComments(@RequestParam("year") year: Int,
                          @RequestParam("month") month: Int,
                          httpServletRequest: HttpServletRequest): List<Map<String, Any>> {
        val user = httpServletRequest.getSession(true).getAttribute("user") as User
        return billService.summaryByComments(user.id!!, year, month)
    }
}