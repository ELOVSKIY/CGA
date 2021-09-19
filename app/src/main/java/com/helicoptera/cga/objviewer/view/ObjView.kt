package com.helicoptera.cga.objviewer.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.renderscript.Matrix4f
import android.util.AttributeSet
import android.view.View
import com.helicoptera.cga.parser.model.Obj
import com.helicoptera.cga.parser.model.Vector

class ObjView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private val target = Vector(0F, 0F, 0F)
    private var up = Vector(0F, 1F, 0F)
    private var eye = Vector(0F, 1F, 0F)

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

        val localObject = obj
        if (localObject != null) {
            val model = localObject.clone()
            model.apply {
                var viewerMatrix = Matrix.viewer(eye, target, up)
                var projectionMatrix =
                    Matrix.projection(width.toFloat(), height.toFloat(), Z_NEAR, Z_FAR)
                var viewPortMatrix =
                    Matrix.viewport(width.toFloat(), height.toFloat(), X_MIN, Y_MIN)

                for (vertexIndex in vertexesCoordinates.indices) {
                    vertexesCoordinates[vertexIndex] = vertexesCoordinates[vertexIndex].transform(viewerMatrix)
                    vertexesCoordinates[vertexIndex] = vertexesCoordinates[vertexIndex].transform(projectionMatrix)
                    vertexesCoordinates[vertexIndex] = vertexesCoordinates[vertexIndex].transform(viewPortMatrix)
//                    vertexesCoordinates[vertexIndex] /= cModel.vertices[i].W;
                }
            }
        }
    }


    private fun drawVector(canvas: Canvas?, vector: Array<FloatArray>) {
        canvas?.drawCircle(
            vector[0][0], vector[0][1], 5F, paint
        )
    }

    companion object {
        private const val Z_NEAR = 0F
        private const val Z_FAR = 5F
        private const val X_MIN = 100F
        private const val Y_MIN = 100F
    }
}