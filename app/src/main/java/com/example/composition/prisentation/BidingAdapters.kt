package com.example.composition.prisentation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.databinding.BindingAdapter
import com.example.composition.R
import com.example.composition.domain.entity.GameResult

interface onOpitonClickLitener
{
    fun onOptionClick(option:Int)
}
@BindingAdapter("requireAnswers")
fun bindRequireAnswer(textView: TextView,count:Int){
    textView.text =
        String.format(textView.context.getString(R.string.required_score),count)

}
@BindingAdapter("scoreAnswers")
fun bindScore(textView: TextView,score:Int){
    textView.text =
        String.format(textView.context.getString(R.string.score_answers),score)

}
@BindingAdapter("requiredScore")
fun bindCountOfRightAnswers(textView: TextView,count:Int){
    textView.text =
        String.format(textView.context.getString(R.string.required_score),count)

}
@BindingAdapter("percentRA")
fun percentOfRightA(textView: TextView, gameResult: GameResult)
{
    val percentOfRightAnswers = ((gameResult.countRightAnswers/(gameResult.countOfAnswers).toDouble() )*100).toInt().toString()
    textView.text = String.format(textView.context.getString(R.string.score_percentage),percentOfRightAnswers)
}
@BindingAdapter("emoji_result")
fun bindEmoji(imageView: ImageView, isWinner:Boolean)
    {
        if(isWinner)
        {

            imageView.setImageResource(R.drawable.ic_smile)
        }
        else
        {
            imageView.setImageResource(R.drawable.ic_sad)
        }

    }
@BindingAdapter("aSum")
fun bindSumTv(textView: TextView,sum:Int)
{
    textView.text = sum.toString()
}

@BindingAdapter("lNumber")
fun bindLeftNumber(textView: TextView,lnumber:Int)
{
    textView.text = lnumber.toString()
}
@BindingAdapter("enoughCount")
fun bindEnoughColor(textView: TextView,state:Boolean,)
{

    textView.setTextColor(getColorByState(textView.context,state))
}
@BindingAdapter("enoughPercent")
fun bindPercentColor(progressBar: ProgressBar, enough:Boolean)
{
    val color = getColorByState(progressBar.context,enough)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

@BindingAdapter("onClickOptions")
fun onClickOptions(textView: TextView,clickListener:onOpitonClickLitener)
{
    textView.setOnClickListener { clickListener.onOptionClick(textView.text.toString().toInt()) }

}


private fun getColorByState( context: Context,state:Boolean ):Int
{
    val colorResId = if(state)
    {
        android.R.color.holo_green_light
    }
    else
    {
        android.R.color.holo_red_light
    }
    return  getColor(context,colorResId)
}