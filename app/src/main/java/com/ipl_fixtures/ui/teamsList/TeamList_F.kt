package com.ipl_fixtures.ui.teamsList

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ipl_fixtures.R
import com.ipl_fixtures.databinding.FragmentTeamListBinding
import com.ipl_fixtures.ui.FixturesViewModel
import com.ipl_fixtures.utils.Resource
import com.ipl_fixtures.utils.TeamsData
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext


@AndroidEntryPoint
class TeamList_F : Fragment() {

    private var _binding: FragmentTeamListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: TeamsListAdapter
    private val fixtureViewModel : FixturesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTeamListBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnStartIPL.setOnClickListener {
            findNavController().navigate(R.id.action_teamList_F_to_matchesList_F)
        }
        adapter = TeamsListAdapter(context)
        binding.recTeamList.layoutManager = GridLayoutManager(context,2)
        binding.recTeamList.adapter = adapter

        fixtureViewModel.getIPLListing("").observe(
            viewLifecycleOwner, { response ->
                when(response)
                {
                    is Resource.Loading ->{}
                    is Resource.Success ->{ adapter.setDataonList(response.data!!) }
                    is Resource.Error -> {}
                }
            }
        )

    }

}