package com.example.composition.domain.Entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable
@Parcelize
data class GameSittings(
    val maxSumValue:Int,
    val minCountOfRightAnswers:Int,
    val minPercentOfRightAnswers:Int,
    val gameTimeInSecond:Int
): Parcelable {
}