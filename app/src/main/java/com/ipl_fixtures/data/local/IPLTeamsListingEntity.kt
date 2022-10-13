package com.ipl_fixtures.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class IPLTeamsListingEntity(
    val name: String,
    val symbol: Int,
    @PrimaryKey val id: Int ?= null
)
