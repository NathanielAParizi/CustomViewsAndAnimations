package com.example.customviewsandanimations

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CircleView(context: Context, attributes: AttributeSet?, defStyleAttr : Int)
    : View(context, attributes, defStyleAttr) {

    constructor(context : Context)
            : this(context,null)
    constructor(context : Context, attributes : AttributeSet?)
            : this(context, attributes, 0)


    val typedArray = context.obtainStyledAttributes(attributes, R.styleable.CircleView)
    var radius : Int = typedArray.getInt(R.styleable.CircleView_radius,30)
    val fillColor : Int = typedArray.getInt(R.styleable.CircleView_fillColor,0)
    val xPosition : Int = typedArray.getInt(R.styleable.CircleView_xPosition, 50)
    val yPosition : Int =  typedArray.getInt(R.styleable.CircleView_xPosition, 50)
    val isVisible : Boolean = true

    // Rendering the view didctated by what is defined on the canvas
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val paint = Paint()
        paint.color = fillColor
        if(radius > 500){
            radius = 500
        }

        canvas?.drawCircle(xPosition.toFloat(), yPosition.toFloat(), radius.toFloat(),paint)


    }

    // Mkaing considerations for layout size (screen size)
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

//    override fun onClick(p0 : View?){
//        isVisible = isVisible.not()
//    }

}