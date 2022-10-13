package com.ipl_fixtures.ui.winTeams

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ipl_fixtures.R
import com.ipl_fixtures.databinding.FragmentWinResultBinding
import com.ipl_fixtures.models.MatchData
import com.ipl_fixtures.ui.FixturesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WinResult_F : Fragment() {

   private var _binding: FragmentWinResultBinding ?= null
   private val binding get() = _binding !!
    private val fixturesViewModel: FixturesViewModel by activityViewModels()
    private lateinit var adapter: WinTeamsListAdapter
    lateinit var cricketTeamsData: MutableList<MatchData>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWinResultBinding.inflate(inflater,container,false)
        cricketTeamsData = fixturesViewModel.getParseTeamList()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.tvToolbarHeading.setText(getString(R.string.heading_ipl_winner))
        binding.btnRestart.setOnClickListener {
            findNavController().navigate(R.id.action_winResult_F_to_teamList_F)
        }

        adapter = WinTeamsListAdapter(context)
        binding.recWinList.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        binding.recWinList.adapter = adapter

        adapter.setDataonList(fixturesViewModel.getGameWinningTeams(cricketTeamsData))
    }

}