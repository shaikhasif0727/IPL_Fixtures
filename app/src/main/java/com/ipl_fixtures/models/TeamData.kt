package com.ipl_fixtures.models

// 0 = lose 1 = winner
data class TeamData(val teamName: String,val teamImage: Int,var isWinner: Int = -1)
