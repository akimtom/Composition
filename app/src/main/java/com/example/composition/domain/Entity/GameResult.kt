package com.example.composition.domain.Entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable
@Parcelize
data class GameResult (
 val winner:Boolean,
 val countRightAnswers:Int,
 val countTotalAnswers: Int,
 val gameSittings: GameSittings
): Parcelable {


}