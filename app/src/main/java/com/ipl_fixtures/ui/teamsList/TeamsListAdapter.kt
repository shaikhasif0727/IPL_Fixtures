package com.ipl_fixtures.ui.teamsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ipl_fixtures.databinding.ItemFixtureBinding
import com.ipl_fixtures.databinding.ItemTeamsBinding
import com.ipl_fixtures.models.MatchData
import com.ipl_fixtures.models.TeamData
import java.util.*

class TeamsListAdapter: RecyclerView.Adapter<TeamsListAdapter.myViewHolder>() {

    private var teamList: List<TeamData> = ArrayList()


    fun setDataonList(list: List<TeamData>) {
        this.teamList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val binding = ItemTeamsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return myViewHolder(binding)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val match = teamList.get(position)
        match?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int {
        return teamList?.let { teamList.size } ?: 0
    }


    inner class myViewHolder(private val binding: ItemTeamsBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(teamData: TeamData){
                binding.tvCricketTeam.text = teamData.teamName
            }
    }
}