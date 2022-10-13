package com.ipl_fixtures.ui.teamsList.menu

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import com.ipl_fixtures.R
import com.ipl_fixtures.databinding.DialogAddteamBinding
import com.ipl_fixtures.domain.model.IPLTeamsListing

open class DialogAddTeam(val context: Context ?= null) {

    init {
        initDialog()
    }

    lateinit var dialog : Dialog
    private var _binding: DialogAddteamBinding?= null
    private val binding get() = _binding !!

    private fun initDialog() : DialogAddTeam {
        dialog = Dialog(context!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setTitle("")
        _binding = DialogAddteamBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(binding.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        binding.ivClose.setOnClickListener { dismiss() }
        binding.btnYes.setOnClickListener {
            val inputText = binding.etInput.text.toString()
            if(!inputText.isEmpty())
            {
                dismiss()
                addTeam(IPLTeamsListing(inputText,R.drawable.ic_ball_throw))
            }

        }

        return this
    }

    public fun show(){
        dialog?.let {
            it.show()
        }
    }

    public fun dismiss(){
        dialog?.let {
            it.dismiss()
        }
    }

    open fun addTeam(teams: IPLTeamsListing) {}
}