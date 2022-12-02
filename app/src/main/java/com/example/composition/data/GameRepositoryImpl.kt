package com.example.composition.data

import com.example.composition.domain.Entity.GameSittings
import com.example.composition.domain.Entity.Level
import com.example.composition.domain.Entity.Question
import com.example.composition.domain.repositories.GameRepository
import java.lang.Integer.max
import java.lang.Integer.min
import kotlin.random.Random

object GameRepositoryImpl: GameRepository {
  private  const val MIN_SUM_VALUE = 2
  private  const val MIN_VISIBLE_NUMBER_VALUE = 1
    override fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question {
        val sum  = Random.nextInt(MIN_SUM_VALUE,maxSumValue+1)
        val visibleNumber = Random.nextInt(MIN_VISIBLE_NUMBER_VALUE,sum)
        val options = HashSet<Int>()
        val rightAnswer = sum - visibleNumber
        options.add(rightAnswer)
        val from = max(rightAnswer-countOfOptions, MIN_VISIBLE_NUMBER_VALUE)
        val to = min(maxSumValue, rightAnswer+countOfOptions)
        while (options.size<=countOfOptions)
        {
            options.add(Random.nextInt(from,to))
        }
        return Question(sum,visibleNumber, options.toList())
    }

    override fun getGameSittings(level: Level): GameSittings {

        return when(level)
        {
            Level.TEST ->{ GameSittings(
                10,
                3,
                50,
                8
            )}
            Level.EASY -> {
                GameSittings(
                    10,
                    10,
                    70,
                    60
                )
            }
            Level.NORMAL -> {
                GameSittings(
                    20,
                    20,
                    80,
                    40
                )
            }
            Level.HARD -> {
                GameSittings(
                    30,
                    30,
                    90,
                    40
                )
            }
        }
    }
}