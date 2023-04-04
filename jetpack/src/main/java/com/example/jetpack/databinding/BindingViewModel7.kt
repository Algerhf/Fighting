package com.example.jetpack.databinding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *
 * @author hufan
 * 创建日期：2023/4/3 17:11
 * 描述：
 *
 */
class BindingViewModel7 : ViewModel() {

    val aTeamScore = MutableLiveData(0)
    val bTeamScore = MutableLiveData(0)
    var mLastATeamScore = 0
    var mLastBTeamScore = 0

    fun addATeamScore(score: Int) {
        saveLastScore()
        val currentAScore = aTeamScore.value ?: 0
        aTeamScore.postValue(currentAScore + score)
    }

    fun addBTeamScore(score: Int) {
        saveLastScore()
        val currentBScore = bTeamScore.value ?: 0
        bTeamScore.postValue(currentBScore + score)
    }

    fun undo() {
        aTeamScore.postValue(mLastATeamScore)
        bTeamScore.postValue(mLastBTeamScore)
    }

    fun resetScore() {
        aTeamScore.postValue(0)
        bTeamScore.postValue(0)
    }

    private fun saveLastScore() {
        mLastATeamScore = aTeamScore.value ?: 0
        mLastBTeamScore = aTeamScore.value ?: 0
    }

}