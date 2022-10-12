package com.ipl_fixtures.models

import java.util.*

data class MatchData(var team1Data: TeamData,
                     var team2Data: TeamData,
                     val matchDate: String = Date().toString(),
                     var winTeam: Int = -1
)
