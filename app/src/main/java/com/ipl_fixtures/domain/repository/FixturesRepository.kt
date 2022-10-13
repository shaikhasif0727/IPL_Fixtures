package com.ipl_fixtures.domain.repository

import com.ipl_fixtures.domain.model.MatchData
import com.ipl_fixtures.domain.model.IPLTeamsListing
import com.ipl_fixtures.utils.Resource
import kotlinx.coroutines.flow.Flow

interface FixturesRepository {

 suspend fun getTeamsList(
  fetchFromRemote: Boolean,
  query: String
 ) : Flow<Resource<List<IPLTeamsListing>>>

 suspend fun getMatchParseTeamsList() : List<MatchData>

 fun getTeamspairs(matchesList: List<IPLTeamsListing>) : List<MatchData>

 suspend fun addTeam(teams: IPLTeamsListing)

}