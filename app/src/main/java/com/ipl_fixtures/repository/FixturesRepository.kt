package com.ipl_fixtures.repository

import androidx.lifecycle.LiveData
import com.ipl_fixtures.models.MatchData
import com.ipl_fixtures.models.TeamData

interface FixturesRepository {

 suspend fun getTeamsList() : List<MatchData>

 fun getTeamspairs(matchesList: List<TeamData>) : List<MatchData>

}