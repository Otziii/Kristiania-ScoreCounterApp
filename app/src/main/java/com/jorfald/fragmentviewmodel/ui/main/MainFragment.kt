package com.jorfald.fragmentviewmodel.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jorfald.fragmentviewmodel.R
import com.jorfald.fragmentviewmodel.Team

class MainFragment : Fragment() {
    private lateinit var viewModel: MainViewModel

    private lateinit var blueTeamScoreLabel: TextView
    private lateinit var redTeamScoreLabel: TextView
    private lateinit var blueTeamScoreButton: Button
    private lateinit var blueTeamSubtractButton: Button
    private lateinit var redTeamScoreButton: Button
    private lateinit var redTeamSubtractButton: Button
    private lateinit var newGameButton: Button
    private lateinit var victoryText: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)

        blueTeamScoreLabel = view.findViewById(R.id.blue_team_score_label)
        redTeamScoreLabel = view.findViewById(R.id.red_team_score_label)
        blueTeamScoreButton = view.findViewById(R.id.blue_team_score_button)
        blueTeamSubtractButton = view.findViewById(R.id.blue_team_subtract_button)
        redTeamScoreButton = view.findViewById(R.id.red_team_score_button)
        redTeamSubtractButton = view.findViewById(R.id.red_team_subtract_button)
        newGameButton = view.findViewById(R.id.new_game_button)
        victoryText = view.findViewById(R.id.victory_text)
        victoryText.visibility = View.GONE

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        bindButtons()
    }

    private fun bindButtons() {
        blueTeamScoreButton.setOnClickListener {
            viewModel.blueTeamScore += 1
            updateLabels()
            checkWinner()
        }

        blueTeamSubtractButton.setOnClickListener {
            if (viewModel.blueTeamScore > 0) {
                viewModel.blueTeamScore -= 1
                updateLabels()
            }
        }

        redTeamScoreButton.setOnClickListener {
            viewModel.redTeamScore += 1
            updateLabels()
            checkWinner()
        }

        redTeamSubtractButton.setOnClickListener {
            if (viewModel.redTeamScore > 0) {
                viewModel.redTeamScore -= 1
                updateLabels()
            }
        }

        newGameButton.setOnClickListener {
            viewModel.blueTeamScore = 0
            viewModel.redTeamScore = 0

            updateLabels()
            victoryText.visibility = View.GONE
            blueTeamScoreButton.isEnabled = true
            blueTeamSubtractButton.isEnabled = true
            redTeamScoreButton.isEnabled = true
            redTeamSubtractButton.isEnabled = true
        }
    }

    private fun updateLabels() {
        blueTeamScoreLabel.text = viewModel.blueTeamScore.toString()
        redTeamScoreLabel.text = viewModel.redTeamScore.toString()
    }

    private fun checkWinner() {
        val winner = viewModel.checkWinner()

        if (winner != null) {
            if (winner == Team.RED) {
                victoryText.text = "Red team won!"
            } else {
                victoryText.text = "Blue team won!"
            }

            victoryText.visibility = View.VISIBLE

            blueTeamScoreButton.isEnabled = false
            blueTeamSubtractButton.isEnabled = false
            redTeamScoreButton.isEnabled = false
            redTeamSubtractButton.isEnabled = false
        } else {
            victoryText.visibility = View.GONE
        }
    }
}