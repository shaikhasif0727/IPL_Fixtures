package com.ipl_fixtures.ui.winTeams

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ipl_fixtures.R
import com.ipl_fixtures.databinding.FragmentWinResultBinding

class WinResult_F : Fragment() {

   private var _binding: FragmentWinResultBinding ?= null
   private val binding get() = _binding !!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWinResultBinding.inflate(inflater,container,false)
        return binding.root
    }

}