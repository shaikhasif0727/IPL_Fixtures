package com.ipl_fixtures.utils

import com.ipl_fixtures.R
import com.ipl_fixtures.domain.model.IPLTeamsListing
import java.util.*

class TeamsData {


   companion object {
       fun getTeamList(): List<IPLTeamsListing> {
           val teamsList:MutableList<IPLTeamsListing> = ArrayList()

           teamsList.add(IPLTeamsListing("Mumbai Indians", R.drawable.ic_mumbai_indian_logo))
           teamsList.add(IPLTeamsListing("Sunrisers Hyderabad",R.drawable.ic_sunrisers_hyderabards))
           teamsList.add(IPLTeamsListing("Royal Challengers Bangalore",R.drawable.ic_royal_chalangers_banglor_logo))
           teamsList.add(IPLTeamsListing("Rajasthan Royals",R.drawable.ic_rajasthan_royals_logo))
           teamsList.add(IPLTeamsListing("Chennai Super Kings",R.drawable.ic_chennai_super_kings_logo))
           teamsList.add(IPLTeamsListing("Kolkata Knight Riders",R.drawable.ic_knight_kolkata_logo))
           teamsList.add(IPLTeamsListing("Delhi Capitals",R.drawable.ic_dehli_capitals_logo))
           teamsList.add(IPLTeamsListing("Kings XI Punjab",R.drawable.ic_kings_xl_punjab_logo))
           return teamsList;
       }
   }

}