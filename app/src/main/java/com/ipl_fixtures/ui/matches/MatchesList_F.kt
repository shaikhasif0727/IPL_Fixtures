package com.ipl_fixtures.ui.matches

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ipl_fixtures.R
import com.ipl_fixtures.databinding.FragmentMatchesListBinding
import com.ipl_fixtures.models.MatchData
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MatchesList_F : Fragment() {

    private var _binding: FragmentMatchesListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MatchesAdapter
    val fixturesViewModel by viewModels<FixturesViewModel>()
    private var cricketTeamsData: MutableList<MatchData> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMatchesListBinding.inflate(inflater, container, false)
//        fixturesViewModel = ViewModelProvider(this).get(FixturesViewModel::class.java)
        adapter = MatchesAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.tvToolbarHeading.text = getString(R.string.headingMatches)
        fixturesViewModel.loadMainMatchesList()
        binding.btnSimulate.setOnClickListener {
            fixturesViewModel.setWinTeamsListing(fixturesViewModel.getWinningTeam(cricketTeamsData))
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