package com.avp.bstudio.service

import com.avp.bstudio.model.dto.Portfolio
import org.springframework.stereotype.Service

@Service("portService")
class PortfolioServieImpl : PortfolioService
{
    override fun getPortfolios(): List<Portfolio>
    {
        return emptyList()
    }
}