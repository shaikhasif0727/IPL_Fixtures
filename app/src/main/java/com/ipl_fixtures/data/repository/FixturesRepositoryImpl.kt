package com.ipl_fixtures.data.repository

import com.ipl_fixtures.data.local.IPLDatabase
import com.ipl_fixtures.data.mapper.toIPLListing
import com.ipl_fixtures.data.mapper.toIPLListingEntity
import com.ipl_fixtures.domain.model.MatchData
import com.ipl_fixtures.domain.model.IPLTeamsListing
import com.ipl_fixtures.domain.repository.FixturesRepository
import com.ipl_fixtures.utils.Resource
import com.ipl_fixtures.utils.TeamsData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FixturesRepositoryImpl @Inject constructor(
    private val db: IPLDatabase
) : FixturesRepository {

    private val dao = db.dao

    override suspend fun getTeamsList(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<IPLTeamsListing>>> {

        return flow {
            emit(Resource.Loading(true))
            val localListing = dao.searchIPLListing("")

            emit(Resource.Success(
                data = localListing.map { it.toIPLListing() }
            ))

            val isDbEmpty = localListing.isEmpty()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

            if(shouldJustLoadFromCache)
            {
                return@flow
            }

            val remoteListing = TeamsData.getTeamList()
            remoteListing?.let { listing ->
                dao.clearIPListing()
                dao.insertCompanyListing(
                    listing.map { it.toIPLListingEntity() })

                val localList = dao.searchIPLListing("")
                    .map { it.toIPLListing() }

                emit(Resource.Success(
                    data = localList
                ))
//                emit(Resource.Loading(false))
            }

        }
    }

    override suspend fun getMatchParseTeamsList(): List<MatchData> {
        val teamsListData = dao.searchIPLListing("").map { it.toIPLListing() }
        Collections.shuffle(teamsListData)
        return getTeamspairs(teamsListData)
    }

    override fun getTeamspairs(teamsListData : List<IPLTeamsListing>): List<MatchData>{

        var matchesList: MutableList<MatchData> = ArrayList()
        var j: Int = 0

        for (i in 1..(teamsListData.size/2))
        {
            val teamData1 = teamsListData.get(j)
            j++
            val teamData2 = teamsListData.get(j)
            val matchTeam = MatchData(teamData1,teamData2)
            matchesList.add(matchTeam)
            j++
        }

        return matchesList
    }

    override suspend fun addTeam(teams: IPLTeamsListing) = dao.insertCompanyListing(teams.toIPLListingEntity())
}