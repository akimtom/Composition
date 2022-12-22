package com.example.composition.prisentation

import android.app.Application
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.*
import com.example.composition.R
import com.example.composition.data.GameRepositoryImpl
import com.example.composition.domain.entity.GameResult
import com.example.composition.domain.entity.GameSittings
import com.example.composition.domain.entity.Level
import com.example.composition.domain.entity.Question
import com.example.composition.domain.UseCases.GenerateQuestionUseCase
import com.example.composition.domain.UseCases.GetGameSettingsUseCase

class GameViewModel(
    private val application: Application,
    private val level:Level):ViewModel() {

    private val repository = GameRepositoryImpl

    private val generateQuestionUseCase = GenerateQuestionUseCase(repository)
    private val getGameSettingsUseCase = GetGameSettingsUseCase(repository)

    private lateinit var gameSettings: GameSittings


    private val _minPercentOfRightAnswers = MutableLiveData<Int>()
    val minPercentOfRightAnswers: LiveData<Int>
        get() = _minPercentOfRightAnswers

    private val _leftFormattedTime = MutableLiveData<String>()
    val leftFormattedTime: LiveData<String>
        get() = _leftFormattedTime

    private val _question = MutableLiveData<Question>()
    val question: LiveData<Question>
        get() = _question

    private val _gameResult = MutableLiveData<GameResult>()
    val gameResult: LiveData<GameResult>
        get() = _gameResult

    private val _progressedAnswers = MutableLiveData<String>()
    val progressedAnswers: LiveData<String>
        get() = _progressedAnswers

    private val _percentOfRightAnswers = MutableLiveData<Int>()
    val percentOfRightAnswers: LiveData<Int>
        get() = _percentOfRightAnswers

    private val _enoughCountOfRightAnswers = MutableLiveData<Boolean> ()
    val enoughCountOfRightAnswers : LiveData<Boolean>
    get() = _enoughCountOfRightAnswers
    private val _enoughPresentOfRightAnswers = MutableLiveData<Boolean> ()
    val enoughPresentOfRightAnswers : LiveData<Boolean>
        get() = _enoughPresentOfRightAnswers

    private var timer: CountDownTimer? = null
    private var countOfRightAnswers = 0
    private var countOfWrongAnswers = 0

    init {
        startGame()
    }

   private fun startGame() {
        setupGameSettings(level)
        startTimer()
        generateQuestion()
        updateProgress()
    }

    fun chooseAnswer(answer: Int) {
        if (gameResult.value != null) {
            return
        }
        checkAnswer(answer)
        updateProgress()
        getPercentOfRightAnswers()
        generateQuestion()

    }

    private fun setupGameSettings(level: Level) {

        gameSettings = getGameSettingsUseCase(level)
        _minPercentOfRightAnswers.value = gameSettings.minPercentOfRightAnswers
    }

    private fun checkAnswer(answer: Int) {
        val rightAnswer = question.value?.rightAnswer

        Log.d("AnswerR",rightAnswer.toString())
        Log.d("AnswerR",answer.toString())
        if (answer == rightAnswer) {


            countOfRightAnswers++
        } else {
            countOfWrongAnswers++
        }
    }

    private fun startTimer() {
        timer = object : CountDownTimer(
            gameSettings.gameTimeInSecond * MILLIS_IN_SECONDS,
            MILLIS_IN_SECONDS
        ) {
            override fun onTick(millisUntilFinished: Long) {
                _leftFormattedTime.value = getFormattedLeftTime(millisUntilFinished)
            }

            override fun onFinish() {
                finishGame()
            }
        }
        timer?.start()
    }

    private fun updateProgress()
    {
        val percent = calculatePercentOfRightAnswers()
        _percentOfRightAnswers.value = percent
        _progressedAnswers.value = String.format(application.resources.getString(R.string.progress_answers),
            countOfRightAnswers, gameSettings.minCountOfRightAnswers)
        _enoughCountOfRightAnswers.value = countOfRightAnswers>=gameSettings.minCountOfRightAnswers
        _enoughPresentOfRightAnswers.value = percent >= gameSettings.minPercentOfRightAnswers
    }
    private fun calculatePercentOfRightAnswers():Int
    {
        if(countOfRightAnswers==0)
        {
            return 0
        }
        return ((countOfRightAnswers/countOfWrongAnswers.toDouble())*100).toInt()
    }
    private fun generateQuestion() {
        _question.value = generateQuestionUseCase(gameSettings.maxSumValue)
    }

    private fun finishGame() {
        _leftFormattedTime.value = getFormattedLeftTime(0)
        _gameResult.value = getGameResult()
    }

    private fun getGameResult(): GameResult {
        val percentOfRightAnswers = getPercentOfRightAnswers()
        val enoughPercentage = percentOfRightAnswers > gameSettings.minPercentOfRightAnswers
        val enoughRightAnswers = countOfRightAnswers >= gameSettings.minCountOfRightAnswers
        val winner = enoughPercentage && enoughRightAnswers
        val countOfQuestions = countOfRightAnswers + countOfWrongAnswers
        return GameResult(winner, countOfRightAnswers, countOfQuestions, gameSettings)
    }

    private fun getPercentOfRightAnswers(): Int {
        val countOfQuestions = countOfRightAnswers + countOfWrongAnswers
        val percentOfRightAnswers = if (countOfQuestions > 0) {
            ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
        } else {
            0
        }
        _percentOfRightAnswers.value = percentOfRightAnswers
        return percentOfRightAnswers
    }

    private fun getFormattedLeftTime(millisUntilFinished: Long): String {
        val seconds = (millisUntilFinished / MILLIS_IN_SECONDS).toInt()
        val minutes = seconds / SECONDS_IN_MINUTE
        return String.format("%02d:%02d", minutes, seconds)
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

    companion object {

        private const val MILLIS_IN_SECONDS = 1000L
        private const val SECONDS_IN_MINUTE = 60
    }

}