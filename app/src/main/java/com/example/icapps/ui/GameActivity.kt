package com.example.AndroidBombSample.ui

import activitystarter.ActivityStarter
import activitystarter.Arg
import activitystarter.MakeActivityStarter
import android.os.Bundle
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import com.example.AndroidBombSample.R
import com.example.AndroidBombSample.databinding.ActivityGameBinding
import com.example.AndroidBombSample.viewModel.GameViewModel
import com.marcinmoskala.activitystarter.argExtra

import org.koin.android.architecture.ext.viewModel

@MakeActivityStarter
class GameActivity : AppCompatActivity() {

    @get:Arg val gameId: Int by argExtra()

    val viewModel: GameViewModel by viewModel()
    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_game)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        ActivityStarter.fill(this, savedInstanceState)

        viewModel.loadGameData(gameId)
    }
}
