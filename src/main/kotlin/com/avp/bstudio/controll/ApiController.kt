package com.avp.bstudio.controll

import com.avp.bstudio.service.PortfolioService
import com.avp.bstudio.service.SomeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = "/api")
class ApiController
{
    @Autowired lateinit var service: SomeService
    @Autowired lateinit var portService: PortfolioService

    @GetMapping("/test")
    fun test(): String = service.someMethod()

    @GetMapping("/portfolio")
    fun getPortfolios() = portService.getPortfolios()
}