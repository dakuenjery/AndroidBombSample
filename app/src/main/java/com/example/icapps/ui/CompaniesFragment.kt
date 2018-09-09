package com.example.AndroidBombSample.ui

import activitystarter.ActivityStarter
import activitystarter.Arg
import activitystarter.MakeActivityStarter
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.AndroidBombSample.Game
import com.example.AndroidBombSample.databinding.FragmentCompaniesBinding
import com.example.AndroidBombSample.databinding.FragmentGamesBinding
import com.example.AndroidBombSample.viewModel.CompaniesViewModel
import com.example.AndroidBombSample.viewModel.GamesViewModel
import com.marcinmoskala.activitystarter.argExtra
import org.koin.android.architecture.ext.viewModel

@MakeActivityStarter
class CompaniesFragment : Fragment() {

    @get:Arg val count: Int by argExtra()

    private val viewModel by viewModel<CompaniesViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentCompaniesBinding.inflate(inflater)!!
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        ActivityStarter.fill(this, arguments)
        viewModel.load(count)
    }
}