package com.sduwh.onlineBills.config

import com.sduwh.onlineBills.service.BillService
import com.sduwh.onlineBills.service.BillServiceImpl
import com.sduwh.onlineBills.service.UserService
import com.sduwh.onlineBills.service.UserServiceImpl
import org.springframework.boot.SpringBootConfiguration
import org.springframework.context.annotation.Bean

@SpringBootConfiguration
open class SpringConfig {
    @Bean
    open fun getUserService(): UserService {
        return UserServiceImpl()
    }

    @Bean
    open fun getBillService(): BillService {
        return BillServiceImpl()
    }

}