package com.ipl_fixtures.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ipl_fixtures.domain.model.MatchData
import com.ipl_fixtures.domain.model.IPLTeamsListing
import com.ipl_fixtures.domain.repository.FixturesRepository
import com.ipl_fixtures.utils.Resource
import com.ipl_fixtures.utils.TeamsData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*

import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class FixturesViewModel @Inject constructor(
     val repository: FixturesRepository
     ) : ViewModel() {

    private lateinit var matcheslist: List<MatchData>
    private lateinit var looseTeamList: MutableList<IPLTeamsListing>
    private lateinit var winningTeamList: MutableList<IPLTeamsListing>
    private var cricketTeamsData: MutableList<MatchData> = ArrayList()
    private var isFinalMatch = false;


    private val _iplLiveData = MutableLiveData<Resource<List<IPLTeamsListing>>>()
//    val iplTeamListLiveData : LiveData<Resource<List<IPLTeamsListing>>>
    val iplTeamListLiveData get() = _iplLiveData

    private val _matchesLiveData = MutableLiveData<List<MatchData>>()
    val matchesLiveData get() = _matchesLiveData

    private val _looseTeammatchesLiveData = MutableLiveData<List<MatchData>>()
    val looseTeammatchesLiveData get() = _looseTeammatchesLiveData

    public fun getIPLListing(
        query: String = "",
        fetchFromRemote: Boolean = false
    ) : LiveData<Resource<List<IPLTeamsListing>>> {
        viewModelScope.launch {
            repository
                .getTeamsList(fetchFromRemote,query)
                .collect{ result ->
                    _iplLiveData.postValue(result)
                }
        }
        return iplTeamListLiveData
    }

    public fun loadMainMatchesList() {
        viewModelScope.launch {
            matcheslist = repository.getMatchParseTeamsList()
            matchesLiveData.postValue(matcheslist)
        }
    }

    public fun setWinTeamsListing(winTeamsList: List<IPLTeamsListing>){
        matchesLiveData.postValue(repository.getTeamspairs(winTeamsList))
    }


    public fun getMainMatchesList() : LiveData<List<MatchData>> {
        return matchesLiveData
    }

    public fun setLooseTeamsListing(looseTeamsList: List<IPLTeamsListing>){
        looseTeammatchesLiveData.postValue(repository.getTeamspairs(looseTeamsList))
    }

    public fun getLooseTeamMatchesList() : LiveData<List<MatchData>> {
        return looseTeammatchesLiveData
    }

    public fun getLooseTeamsList() = looseTeamList

    fun getParseTeamList() = cricketTeamsData

    fun getWinningTeam(matcheslist : List<MatchData>) : List<IPLTeamsListing>
    {
        winningTeamList= ArrayList()
        looseTeamList= ArrayList()

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
            val looseTeam = teams.winTeam.let { it ->
                if(it != 0)
                    teams.team1Data
                else
                    teams.team2Data
            }
            winTeam.isWinner = 1
            looseTeam.isWinner = 0
            winningTeamList.add(winTeam)
            looseTeamList.add(looseTeam)
        }

        return winningTeamList
    }

     fun getGameWinningTeams(matcheslist : List<MatchData>) : List<IPLTeamsListing>{
        winningTeamList= ArrayList()

        for (teams in matcheslist)
        {

            val wining = (0..1).random()
            teams.winTeam = wining

            Log.d("FixturesViewModel","getGameWinningTeams : "+teams.winTeam)

            if(teams.team1Data.isWinner == 0 && teams.team2Data.isWinner == 0)
            {
                if(teams.winTeam == 0)
                {
                    teams.team1Data.isWinner = 1
                    teams.team2Data.isWinner = 0
                }
                else
                {
                    teams.team1Data.isWinner = 0
                    teams.team2Data.isWinner = 1
                }
            }


            winningTeamList.apply {
                if(teams.team1Data.isWinner == 1)
                    add(teams.team1Data)
            }
            winningTeamList.apply {
                if(teams.team2Data.isWinner == 1)
                    add(teams.team2Data)
            }

            teams.winTeam.let { it ->
                if(it == 1)
                {
                     Collections.reverse(winningTeamList)
                }
            }
        }

        return winningTeamList
    }

    fun getIsFinalMatch() = isFinalMatch

    fun setIsFinalMatch(value : Boolean)
    {
        this.isFinalMatch = value
    }


}