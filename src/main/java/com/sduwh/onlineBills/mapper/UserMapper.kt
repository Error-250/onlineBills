package com.sduwh.onlineBills.mapper

import com.sduwh.onlineBills.domain.User
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface UserMapper {
    @Select("select id, username, userpass as password from accounts where username=#{uname}")
    fun findOne(@Param("uname") uname: String): User

    @Select("select id, username, userpass as password from accounts where id = #{userId}")
    fun findUserById(@Param("userId") userId: Long): User

    @Insert("insert into accounts(username, userpass) values(#{username}, md5(#{password}))")
    fun insert(@Param("username") username: String,
               @Param("password") password: String)
}