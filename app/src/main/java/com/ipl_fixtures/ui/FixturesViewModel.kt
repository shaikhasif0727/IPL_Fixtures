package com.ipl_fixtures.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ipl_fixtures.models.MatchData
import com.ipl_fixtures.models.TeamData
import com.ipl_fixtures.repository.FixturesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*

import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class FixturesViewModel @Inject constructor(
     val fixturesRepository: FixturesRepository
     ) : ViewModel() {

    private lateinit var matcheslist: List<MatchData>
    private lateinit var winningTeam: MutableList<TeamData>
    private var cricketTeamsData: MutableList<MatchData> = ArrayList()


    private val _matchesLiveData = MutableLiveData<List<MatchData>>()
    val matchesLiveData get() = _matchesLiveData

    public fun loadMainMatchesList() {
        viewModelScope.launch {
            matcheslist = fixturesRepository.getTeamsList()
            matchesLiveData.postValue(matcheslist)
        }
    }

    public fun setWinTeamsListing(winTeamsList: List<TeamData>){
        matchesLiveData.postValue(fixturesRepository.getTeamspairs(winTeamsList))
    }

    public fun getMainMatchesList() : LiveData<List<MatchData>> {
        return matchesLiveData
    }

    fun getParseTeamList() = cricketTeamsData

    fun getWinningTeam(matcheslist : List<MatchData>) : List<TeamData>
    {
        winningTeam= ArrayList()

        for (teams in matcheslist)
        {
            val wining = (0..1).random()
            teams.winTeam = wining

            Log.d("FixturesViewModel","getWinningTeam : "+teams.winTeam)

            val winTeam = teams.winTeam.let { it ->
                if(it == 0)
                    teams.team1Data
                else
                    teams.team2Data
            }
            winningTeam.add(winTeam)
        }

        return winningTeam
    }

     fun getGameWinningTeams(matcheslist : List<MatchData>) : List<TeamData>{
        winningTeam= ArrayList()

        for (teams in matcheslist)
        {
            val wining = (0..1).random()
            teams.winTeam = wining

            Log.d("FixturesViewModel","getGameWinningTeams : "+teams.winTeam)

            winningTeam.add(teams.team1Data)
            winningTeam.add(teams.team2Data)

            teams.winTeam.let { it ->
                if(it == 1)
                {
                     Collections.reverse(winningTeam)
                }
            }
        }

        return winningTeam
    }


}