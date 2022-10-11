package com.ipl_fixtures.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ipl_fixtures.models.MatchData
import com.ipl_fixtures.models.TeamData
import com.ipl_fixtures.utils.TeamsData
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FixturesRepositoryImpl @Inject constructor() : FixturesRepository {


    override suspend fun getTeamsList(): List<MatchData> {
        val teamsListData = TeamsData.getTeamList()
        Collections.shuffle(teamsListData)
        return getTeamspairs(teamsListData)
    }

    override fun getTeamspairs(teamsListData : List<TeamData>): List<MatchData>{

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
}