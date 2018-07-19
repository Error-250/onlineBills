package com.sduwh.onlineBills.mapper

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface LoginMapper {
    @Select("select count(1) from accounts where username=#{username} and userpass=MD5(#{password})")
    fun findOne(@Param("username") username: String,
                @Param("password") password: String): Int

    @Select("select count(1) from accounts where username=#{username}")
    fun findUname(@Param("username") username: String): Int
}