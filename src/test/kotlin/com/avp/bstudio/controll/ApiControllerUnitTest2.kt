package com.avp.bstudio.controll

import com.avp.bstudio.service.SomeService
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@RunWith(SpringRunner::class)
@WebMvcTest(ApiController::class)
class ApiControllerUnitTest2
{
    @Autowired
    private lateinit var mvc: MockMvc

    @MockBean
    private lateinit var service: SomeService

    @Before
    fun setup()
    {

    }

    private val TEST_PATH_1 = "/api/test"
    private val OK: Int = 200

    @Test
    fun test1()
    {
        // preform
        BDDMockito.given(service.someMethod()).willReturn("TEST")

        // act
        mvc.perform(MockMvcRequestBuilders.get(TEST_PATH_1))
                .andExpect(MockMvcResultMatchers.status().`is`(OK))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("TEST")))

        // assert
    }
}