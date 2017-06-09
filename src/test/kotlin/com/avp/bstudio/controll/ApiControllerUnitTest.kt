package com.avp.bstudio.controll

import com.avp.bstudio.model.dto.Portfolio
import com.avp.bstudio.service.PortfolioService
import com.avp.bstudio.service.SomeService
import com.google.common.collect.ImmutableList
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders

import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.Matchers.*
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8
import org.springframework.test.web.servlet.MvcResult
import org.springframework.boot.test.json.JacksonTester
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.IOException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.core.type.TypeReference


class ApiControllerUnitTest
{
    @InjectMocks
    private lateinit var ctrl: ApiController

    private lateinit var mvc: MockMvc

    @Mock
    private lateinit var portService: PortfolioService

    private lateinit var json: JacksonTester<Portfolio>

    @Before
    fun setup()
    {
        MockitoAnnotations.initMocks(this)

        mvc = MockMvcBuilders.standaloneSetup(ctrl).build()

        // Possibly configure the mapper
        JacksonTester.initFields(this, ObjectMapper())
    }

    private val GET_PORTFOLIOS_PATH = "/api/portfolio"
    private val OK: Int = 200
    private val PORTFOLIO_1 = Portfolio(1, "Manicure", "Franch manicure")
    private val PORTFOLIO_2 = Portfolio(2, "Pedicure", "Fixing legs")
    private val PORTFOLIO_LIST = ImmutableList.of(PORTFOLIO_1, PORTFOLIO_2)

    @Test
    @Throws(Exception::class)
    fun getPortfolios_WhenSuccess_ShouldReturnList()
    {
        given(portService.getPortfolios()).willReturn(PORTFOLIO_LIST)

        val mvcResult = mvc.perform(get(GET_PORTFOLIOS_PATH))
                .andExpect(status().isOk)
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andReturn()

        val jsonString = mvcResult.response.contentAsString

        //assertThat(this.json.parse(jsonString)).isEqualTo(PORTFOLIO_LIST)

        //assertThat(this.json.parseObject(jsonString).).isEqualTo("Ford")

        //val mapper = ObjectMapper()

        //val constructCollectionType = mapper.typeFactory.constructCollectionType(List::class.java, Portfolio::class.java)

        //val list = mapper.readValue(json, constructCollectionType as TypeReference<List<Portfolio>>)

        val list = mapFromJson(jsonString, Array<Portfolio>::class.java)
    }

    @Throws(JsonParseException::class, JsonMappingException::class, IOException::class)
    protected fun <T> mapFromJson(json: String, clazz: Class<T>): T
    {
        val mapper = ObjectMapper()
        return mapper.readValue(json, clazz)
    }
}