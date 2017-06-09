package com.avp.bstudio.model.dto

import lombok.NonNull

data class Portfolio(var id: Long, var title: String, var description: String)
{
    companion object Factory
    {
        fun fromEntity(): Portfolio = Portfolio(1, "", "")
    }
}