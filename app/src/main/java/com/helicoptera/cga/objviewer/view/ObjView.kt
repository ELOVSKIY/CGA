package com.helicoptera.cga.objviewer.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.helicoptera.cga.parser.model.Obj
import com.helicoptera.cga.parser.model.Vertex

class ObjView @JvmOverloads constructor (
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    var obj: Obj? = null
        get
        set(value) {
            field = value
            requestLayout()
        }

    private val paint = Paint().apply {
        color = Color.BLACK
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        obj?.apply {
            polygons.forEach { polygon ->
                polygon.vertexes.forEach { vertex ->
                    processValues(canvas, vertex)
                }
            }
        }
    }

    private fun processValues(canvas: Canvas?, vertex: Vertex) {
        val coordinates = vertex.vertexCoordinates
        var processedVector = arrayOf(floatArrayOf(coordinates.x, coordinates.y, coordinates.z, coordinates.w))

        val width = width.toFloat()
        val height = height.toFloat()
        processedVector = ViewerUtils
            .processViewport(processedVector, width, height, X_MIN, Y_MIN)
        processedVector = ViewerUtils.processProjection(processedVector, width,
            height, Z_NEAR, Z_FAR)
        drawVector(canvas, processedVector)
    }

    private fun drawVector(canvas: Canvas?, vector: Array<FloatArray>) {
        canvas?.drawCircle(
            vector[0][0], vector[0][1], 5F, paint)
    }

    companion object {
        private const val Z_NEAR = 0F
        private const val Z_FAR = 5F
        private const val X_MIN = 100F
        private const val Y_MIN = 100F
    }
}