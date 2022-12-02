package com.example.composition.domain.Entity

data class GameSittings(
    val maxSumValue:Int,
    val minCountOfRightAnswers:Int,
    val minPercentOfRightAnswers:Int,
    val gameTimeInSecond:Int
) {
}