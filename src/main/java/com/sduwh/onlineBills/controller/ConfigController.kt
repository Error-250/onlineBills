package com.sduwh.onlineBills.controller

import com.sduwh.onlineBills.domain.Config
import com.sduwh.onlineBills.domain.RestfulResponse
import com.sduwh.onlineBills.service.OnlineConfigService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class ConfigController {
    @Autowired
    lateinit var onlineConfigService: OnlineConfigService

    @RequestMapping("/disableConfig", method = [RequestMethod.PUT])
    fun disableConfig(@RequestBody config: Config): RestfulResponse {
        onlineConfigService.updateConfig(config)
        return RestfulResponse(false, "test")
    }
}