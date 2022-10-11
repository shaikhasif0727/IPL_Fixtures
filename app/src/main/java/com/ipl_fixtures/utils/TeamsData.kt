package com.ipl_fixtures.utils

import com.ipl_fixtures.R
import com.ipl_fixtures.models.TeamData
import java.util.*

class TeamsData {


   companion object {
       fun getTeamList(): List<TeamData> {
           val teamsList:MutableList<TeamData> = ArrayList()

           teamsList.add(TeamData("Mumbai Indians", R.drawable.ic_mumbai_indian_logo))
           teamsList.add(TeamData("Sunrisers Hyderabad",R.drawable.ic_sunrisers_hyderabards))
           teamsList.add(TeamData("Royal Challengers Bangalore",R.drawable.ic_royal_chalangers_banglor_logo))
           teamsList.add(TeamData("Rajasthan Royals",R.drawable.ic_rajasthan_royals_logo))
           teamsList.add(TeamData("Chennai Super Kings",R.drawable.ic_chennai_super_kings_logo))
           teamsList.add(TeamData("Kolkata Knight Riders",R.drawable.ic_knight_kolkata_logo))
           teamsList.add(TeamData("Delhi Capitals",R.drawable.ic_dehli_capitals_logo))
           teamsList.add(TeamData("Kings XI Punjab",R.drawable.ic_kings_xl_punjab_logo))
           return teamsList;
       }
   }

}