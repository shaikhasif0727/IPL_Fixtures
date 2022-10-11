package com.ipl_fixtures.utils

import com.ipl_fixtures.R
import com.ipl_fixtures.models.TeamData
import java.util.*

class TeamsData {


   companion object {
       fun getTeamList(): List<TeamData> {
           val teamsList:MutableList<TeamData> = ArrayList()

           teamsList.add(TeamData("Mumbai Indians", R.drawable.ic_ball_throw))
           teamsList.add(TeamData("Sunrisers Hyderabad",R.drawable.ic_ball_throw))
           teamsList.add(TeamData("Royal Challengers Bangalore",R.drawable.ic_ball_throw))
           teamsList.add(TeamData("Rajasthan Royals",R.drawable.ic_ball_throw))
           teamsList.add(TeamData("Chennai Super Kings",R.drawable.ic_ball_throw))
           teamsList.add(TeamData("Kolkata Knight Riders",R.drawable.ic_ball_throw))
           teamsList.add(TeamData("Delhi Capitals",R.drawable.ic_ball_throw))
           teamsList.add(TeamData("Kings XI Punjab",R.drawable.ic_ball_throw))
           return teamsList;
       }
   }

}