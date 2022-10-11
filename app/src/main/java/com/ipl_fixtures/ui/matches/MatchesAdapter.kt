package com.ipl_fixtures.ui.matches

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ipl_fixtures.databinding.ItemFixtureBinding
import com.ipl_fixtures.models.MatchData
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*

class MatchesAdapter(@ApplicationContext val context: Context ?= null): RecyclerView.Adapter<MatchesAdapter.myViewHolder>() {

    private var matchesList: List<MatchData> = ArrayList()


    fun setDataonList(list: List<MatchData>) {
        this.matchesList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val binding = ItemFixtureBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return myViewHolder(binding)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val match = matchesList.get(position)
        match?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int {
        return matchesList?.let { matchesList.size } ?: 0
    }


    inner class myViewHolder(private val binding: ItemFixtureBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(matchData: MatchData){
                val team1 = matchData.team1Data
                val team2 = matchData.team2Data
                binding.tvTeam1.text = team1.teamName
                binding.tvTeam2.text = team2.teamName
                binding.tvDate.text = matchData.matchDate

                binding.ivTeamlogo1.setImageDrawable(context?.getDrawable(team1.teamImage))
                binding.ivTeamlogo2.setImageDrawable(context?.getDrawable(team2.teamImage))
            }
    }
}