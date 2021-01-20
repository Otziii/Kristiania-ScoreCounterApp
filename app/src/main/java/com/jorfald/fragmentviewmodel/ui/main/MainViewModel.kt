package com.jorfald.fragmentviewmodel.ui.main

import androidx.lifecycle.ViewModel
import com.jorfald.fragmentviewmodel.Team

class MainViewModel : ViewModel() {
    private val winningScore = 10

    var blueTeamScore: Int = 0
    var redTeamScore: Int = 0

    fun checkWinner(): Team? {
        if (blueTeamScore >= winningScore) {
            return Team.BLUE
        } else if (redTeamScore >= winningScore) {
            return Team.RED
        } else {
            return null
        }
    }
}