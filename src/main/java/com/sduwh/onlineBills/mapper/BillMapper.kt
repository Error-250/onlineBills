package com.sduwh.onlineBills.mapper

import com.sduwh.onlineBills.domain.Bill
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import java.util.Date

@Mapper
interface BillMapper {
    @Select("select * from account_bills where account_id = #{userId} " +
            "and date_format(date, \"%Y-%m\") = date_format(#{date}, \"%Y-%m\")")
    fun findBillThisMonth(@Param("userId") userId: Long,
                          @Param("date") date: Date): List<Bill>

    @Select("select case" +
            " when min(year(date)) is null then 0" +
            " else year(now()) - min(year(date)) end as yearNum" +
            " from account_bills where account_id = #{userId}")
    fun findYearsById(@Param("userId") userId: Long): Int

    @Insert("insert into account_bills(account_id, money, info, date)" +
            "values(#{accountId}, #{money}, #{info}, #{date});")
    fun addBill(bill: Bill)

    @Select("select month(date) as date, sum(money) as money from account_bills " +
            "where account_id = #{userId} and year(date) = #{year} group by month(date) order by date")
    fun summaryMonthly(@Param("userId") userId: Long,
                       @Param("year") year: Int): List<Map<String, Any>>

    @Select("<script>" +
            "select year(date) as date, sum(money) as money from account_bills " +
            "where account_id = #{userId} " +
            "<if test='option != 0'>and year(date) between year(now()) - 10 and year(now()) </if>" +
            " group by year(date) order by date" +
            "</script>")
    fun summaryYearly(@Param("userId") userId: Long,
                      @Param("option") option: Int): List<Map<String, Any>>

    @Select("<script>" +
            "select info, sum(money) as money from account_bills " +
            "where account_id = #{userId} " +
            "<if test='year != -1'>and year(date) = #{year}</if>" +
            "<if test='month != -1'>and month(date) = #{month}</if>" +
            " group by info" +
            " order by money" +
            "</script>")
    fun summaryByComments(@Param("userId") userId: Long,
                          @Param("year") year: Int,
                          @Param("month") month: Int): List<Map<String, Any>>

    @Select("select info from account_bills " +
            "where account_id = #{userId}" +
            " group by info order by count(id) desc limit 10")
    fun findBillTags(@Param("userId") userId: Long): List<String>
}