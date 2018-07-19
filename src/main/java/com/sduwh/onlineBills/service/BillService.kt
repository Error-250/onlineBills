package com.sduwh.onlineBills.service

import com.sduwh.onlineBills.domain.Bill
import com.sduwh.onlineBills.mapper.BillMapper
import org.springframework.beans.factory.annotation.Autowired
import java.text.SimpleDateFormat
import java.util.*

interface BillService {
    fun findBillByMonth(userId: Long, year: Int, month: Int): List<Bill>
    fun findYearsById(userId: Long): Int
    fun addBill(bill: Bill)
    fun summaryMonthly(userId: Long, year: Int): List<Map<String, Any>>
    fun summaryYearly(userId: Long, option: Int): List<Map<String, Any>>
    fun summaryByComments(userId: Long, year: Int, month: Int): List<Map<String, Any>>
    fun getBillTags(userId: Long): List<String>
}

class BillServiceImpl: BillService {
    @Autowired
    lateinit var billMapper: BillMapper

    override fun findBillByMonth(userId: Long, year: Int, month: Int): List<Bill> {
        var calendar = Calendar.getInstance(TimeZone.getDefault())
        // 日历对象的月份是从0开始的
        calendar.set(year, month - 1, 1)
        return billMapper.findBillThisMonth(userId, calendar.time)
    }

    override fun findYearsById(userId: Long): Int {
        return billMapper.findYearsById(userId)
    }

    override fun addBill(bill: Bill) {
        billMapper.addBill(bill)
    }

    override fun summaryMonthly(userId: Long, year: Int): List<Map<String, Any>> {
        return billMapper.summaryMonthly(userId, year)
    }

    override fun summaryYearly(userId: Long, option: Int): List<Map<String, Any>> {
        return billMapper.summaryYearly(userId, option)
    }

    override fun summaryByComments(userId: Long, year: Int, month: Int): List<Map<String, Any>> {
        return billMapper.summaryByComments(userId, year, month)
    }

    override fun getBillTags(userId: Long): List<String> {
        return billMapper.findBillTags(userId)
    }

}
