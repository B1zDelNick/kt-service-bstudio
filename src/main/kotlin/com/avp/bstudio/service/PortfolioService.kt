package com.avp.bstudio.service

import com.avp.bstudio.model.dto.Portfolio

interface PortfolioService
{
    fun getPortfolios(): List<Portfolio>
}