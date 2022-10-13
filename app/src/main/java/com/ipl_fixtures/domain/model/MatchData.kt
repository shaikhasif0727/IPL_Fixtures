package com.ipl_fixtures.domain.model

import java.util.*

data class MatchData(var team1Data: IPLTeamsListing,
                     var team2Data: IPLTeamsListing,
                     val matchDate: String = Date().toString(),
                     var winTeam: Int = -1
)
