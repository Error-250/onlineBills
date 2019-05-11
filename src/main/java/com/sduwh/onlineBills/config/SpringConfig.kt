package com.sduwh.onlineBills.config

import com.sduwh.onlineBills.service.*
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

    @Bean
    open fun getOnlineConfigService(): OnlineConfigService {
        return LocalOnlineConfigService()
    }

}