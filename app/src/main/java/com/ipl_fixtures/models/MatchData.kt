package com.ipl_fixtures.models

import java.util.*

data class MatchData(val team1Data: TeamData,
                     val team2Data: TeamData,
                     val matchDate: String = Date().toString(),
                     var winTeam: Int = -1
)
