package com.ipl_fixtures.ui.teamsList

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ipl_fixtures.databinding.ItemTeamsBinding
import com.ipl_fixtures.domain.model.IPLTeamsListing
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*

class TeamsListAdapter(@ApplicationContext val context: Context ?= null): RecyclerView.Adapter<TeamsListAdapter.myViewHolder>() {

    private var teamList: List<IPLTeamsListing> = ArrayList()


    fun setDataonList(list: List<IPLTeamsListing>) {
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
            fun bind(teamData: IPLTeamsListing){
                binding.tvCricketTeam.text = teamData.teamName
                Log.d("TeamListAdapter",""+teamData.teamImage)
                binding.ivTeamlogo.setImageDrawable(context?.getDrawable(teamData.teamImage))
            }
    }
}