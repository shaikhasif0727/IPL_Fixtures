package com.ipl_fixtures.ui.matches

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ipl_fixtures.models.MatchData
import com.ipl_fixtures.models.TeamData
import com.ipl_fixtures.repository.FixturesRepository
import com.ipl_fixtures.utils.TeamsData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class FixturesViewModel @Inject constructor(
     val fixturesRepository: FixturesRepository
     ) : ViewModel() {

    private lateinit var matcheslist: List<MatchData>

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

    fun getWinningTeam(matcheslist : List<MatchData>) : List<TeamData>
    {
        val winningTeam: MutableList<TeamData> = ArrayList()

        for (teams in matcheslist)
        {
            val wining = (0..1).random()
            teams.winTeam = wining
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

}