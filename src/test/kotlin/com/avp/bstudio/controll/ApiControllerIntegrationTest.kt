package com.avp.bstudio.controll

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

import org.assertj.core.api.Assertions.assertThat
import java.net.URL
import kotlin.reflect.KClass

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class ApiControllerIntegrationTest
{
    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    private val TEST_PATH_1 = "/api/test"

    @Test
    fun createClient()
    {
        val responseEntity = restTemplate.getForEntity(TEST_PATH_1, String::class.java)    //(url, String)
        val msg = responseEntity.body

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(msg).isEqualTo("TEST")
    }
}
