package com.ipl_fixtures.domain.model

// 0 = lose 1 = winner
data class IPLTeamsListing(val teamName: String, val teamImage: Int, var isWinner: Int = -1)
