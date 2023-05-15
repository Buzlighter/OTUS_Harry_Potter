package com.otus.core_ui

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.core.animation.addPauseListener
import androidx.core.util.rangeTo
import kotlin.math.cos

class PotionView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attributeSet, defStyleAttr) {

    private var midX = 0F
    private var midY = 0F
    private val padding = 20F
    private val flaskRadius = 120F
    private val flaskNeckHeight = 2.5F * flaskRadius
    private var neckWidth = 0F

    private val flaskPath = Path()
    private val flaskPaint = Paint()

    private val potionPath = Path()
    private val potionPaint = Paint()

    private val startAnglePotionAnim = ValueAnimator.ofFloat(90F, -60F)
    private val sweepAnglePotionAnim = ValueAnimator.ofFloat(0F, 300F)
    private val fillingAnimSet = AnimatorSet()

    private var startAnglePotion = 90F
    private var sweepAnglePotion = 0F
    private val durationOfFillingAnim = 5000L
    private var quantityOfIngredients = 3
    private var pauseLvl = 300F / quantityOfIngredients

    init {
        flaskPaint.apply {
            isAntiAlias = true
            color = Color.BLACK
            style = Paint.Style.STROKE
            strokeWidth = 5F
        }

        potionPaint.apply {
            isAntiAlias = true
            color = Color.GREEN
            style = Paint.Style.FILL_AND_STROKE
        }

        startAnglePotionAnim.addUpdateListener { startAnglePotion = it.animatedValue as Float }
        sweepAnglePotionAnim.addUpdateListener {
            sweepAnglePotion = it.animatedValue as Float
            pauseFillingAnimation()
        }

        fillingAnimSet.apply {
            duration = durationOfFillingAnim
            playTogether(startAnglePotionAnim, sweepAnglePotionAnim)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec))
    }

    private fun measureWidth(widthMeasureSpec: Int): Int {
        val specMode = MeasureSpec.getMode(widthMeasureSpec)
        val measureSize = MeasureSpec.getSize(widthMeasureSpec)
        return if (specMode == MeasureSpec.EXACTLY) {
            measureSize
        } else {
            (2 * flaskRadius + 2 * padding).toInt()
        }
    }

    private fun measureHeight(heightMeasureSpec: Int): Int {
        val specMode = MeasureSpec.getMode(heightMeasureSpec)
        val measureSize = MeasureSpec.getSize(heightMeasureSpec)
        return if (specMode == MeasureSpec.EXACTLY) {
            measureSize
        } else {
            (2 * flaskRadius + flaskNeckHeight + 2 * padding).toInt()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        if (canvas == null) return
        midX = width / 2F
        midY = height / 2F
        neckWidth = 2 * cos(Math.toRadians(60.0)).toFloat() * flaskRadius

        drawFlask(canvas)
        drawPotion(canvas)
    }

    private fun drawFlask(canvas: Canvas) {
        flaskPath.arcTo(
            midX - flaskRadius,
            height - 2 * flaskRadius,
            midX + flaskRadius,
            height - padding,
            -60F,
            300F,
            false)

        flaskPath.rLineTo(0F, -flaskNeckHeight)
        flaskPath.rLineTo(neckWidth, 0F)

        canvas.drawPath(flaskPath, flaskPaint)
    }


    private fun drawPotion(canvas: Canvas) {
        invalidate()

        potionPath.arcTo(midX - flaskRadius,
            height - 2 * flaskRadius,
            midX + flaskRadius,
            height - padding,
            startAnglePotion,
            sweepAnglePotion,
            false)

        canvas.drawPath(potionPath, potionPaint)
    }

    fun startFillingAnimation() {
        if (fillingAnimSet.isPaused) {
            fillingAnimSet.resume()
        } else {
            fillingAnimSet.start()
        }
    }

    private fun pauseFillingAnimation() {
        if (sweepAnglePotion in pauseLvl..pauseLvl + 10) {
            fillingAnimSet.pause()
            pauseLvl += (300F / quantityOfIngredients)
        }
    }

    fun setIngredientsQuantity(number: Int) {
        pauseLvl = 300F / number
        quantityOfIngredients = number
    }
}