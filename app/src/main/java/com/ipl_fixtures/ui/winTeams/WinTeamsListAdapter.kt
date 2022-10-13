package com.ipl_fixtures.ui.winTeams

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ipl_fixtures.databinding.ItemTeamwinBinding
import com.ipl_fixtures.domain.model.IPLTeamsListing
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*

class WinTeamsListAdapter(@ApplicationContext val context: Context ?= null): RecyclerView.Adapter<WinTeamsListAdapter.myViewHolder>() {

    private var teamList: List<IPLTeamsListing> = ArrayList()


    fun setDataonList(list: List<IPLTeamsListing>) {
        this.teamList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val binding = ItemTeamwinBinding.inflate(LayoutInflater.from(parent.context),parent,false)
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


    inner class myViewHolder(private val binding: ItemTeamwinBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(teamData: IPLTeamsListing){
                binding.tvCricketTeam.text = teamData.teamName
                Log.d("TeamListAdapter",""+teamData.teamImage)
                binding.ivTeamlogo.setImageDrawable(context?.getDrawable(teamData.teamImage))
                binding.tvWinningPosition.text = "${adapterPosition+1}st Position"
                binding.ivWinnerIcon.visibility = adapterPosition.let {
                    when(it)
                    {
                        0 -> View.VISIBLE
                        1 -> View.GONE
                        else -> View.GONE
                    }
                }
            }
    }
}