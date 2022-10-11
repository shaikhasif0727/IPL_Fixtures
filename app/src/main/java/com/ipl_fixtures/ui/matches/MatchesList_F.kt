package com.ipl_fixtures.ui.matches

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ipl_fixtures.R
import com.ipl_fixtures.databinding.FragmentMatchesListBinding
import com.ipl_fixtures.models.MatchData
import com.ipl_fixtures.ui.FixturesViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MatchesList_F : Fragment() {

    private var _binding: FragmentMatchesListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MatchesAdapter
    private val fixturesViewModel: FixturesViewModel by activityViewModels()
    lateinit var cricketTeamsData: MutableList<MatchData>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMatchesListBinding.inflate(inflater, container, false)
        cricketTeamsData = fixturesViewModel.getParseTeamList()
        adapter = MatchesAdapter(context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.tvToolbarHeading.text = getString(R.string.headingMatches)
        fixturesViewModel.loadMainMatchesList()
        binding.btnSimulate.setOnClickListener {
            manageSimulateAction()
        }

        binding.recMatchesList.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        binding.recMatchesList.adapter = adapter


        fixturesViewModel.getMainMatchesList().observe(
            viewLifecycleOwner,{
                cricketTeamsData.clear()
                cricketTeamsData.addAll(it)
                adapter.setDataonList(cricketTeamsData)
                manageBottomButtons()
            }
        )

    }

    private fun manageSimulateAction() {
        cricketTeamsData.size.let { it ->
            if(it <= 1)
            {
                findNavController().navigate(R.id.action_matchesList_F_to_winResult_F)
            }
            else
                fixturesViewModel.setWinTeamsListing(fixturesViewModel.getWinningTeam(cricketTeamsData))

        }
    }

    private fun manageBottomButtons() {
        val buttonText = cricketTeamsData.size.let { it ->
            if(it <= 1)
                getString(R.string.txt_simulate_end)
            else
                getString(R.string.txt_simulate)
        }
        binding.btnSimulate.text = buttonText
    }

}