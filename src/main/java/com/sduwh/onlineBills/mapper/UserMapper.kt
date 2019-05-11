package com.sduwh.onlineBills.mapper

import com.sduwh.onlineBills.domain.User
import org.apache.ibatis.annotations.*

@Mapper
interface UserMapper {
    @Select("select id, username, '' as password, role from accounts where username=#{uname}")
    fun findOne(@Param("uname") uname: String): User

    @Select("select id, username, '' as password, role from accounts where id = #{userId}")
    fun findUserById(@Param("userId") userId: Long): User

    @Insert("insert into accounts(username, userpass) values(#{username}, md5(#{password}))")
    fun insert(@Param("username") username: String,
               @Param("password") password: String)

    @Select("select id, username, '' as password, role from accounts" +
            " where role = 'User' and username like concat(concat('%',#{username}),'%')")
    fun findUsers(username: String): List<User>

    @Update("update accounts set userpass = #{password} where id = #{userId}")
    fun update(@Param("userId") userId: Long,
               @Param("password") password: String)
}