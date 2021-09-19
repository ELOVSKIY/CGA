package com.helicoptera.cga.objviewer.view

import android.renderscript.Matrix4f
import com.helicoptera.cga.parser.model.Vector
import kotlin.math.cos
import kotlin.math.sin

class Matrix {
    companion object {

        fun projection(
            width: Float,
            height: Float,
            zNear: Float,
            zFar: Float
        ): Matrix4f {
            return Matrix4f(
                floatArrayOf(
                    2 / width, 0F, 0F, 0F,
                    0F, 2 / height, 0F, 0F,
                    0F, 0F, 1 / (zNear - zFar), zFar / (zNear - zFar),
                    0F, 0F, 0F, 1F
                )
            )
        }


        fun viewport(
            width: Float,
            height: Float,
            xMin: Float,
            yMin: Float,
        ): Matrix4f {
            return Matrix4f(
                floatArrayOf(
                    width / 2, 0F, 0F, xMin + width / 2,
                    0F, height / 2, 0F, yMin + height / 2,
                    0F, 0F, 1F, 0F,
                    0F, 0F, 0F, 1F
                )
            )
        }

        fun viewer(
            eye: Vector,
            target: Vector,
            up: Vector,
        ): Matrix4f {
            val zAxis = (eye - target).normalize()
            val xAxis = up.cross(zAxis)
            val yAxis = zAxis.cross(xAxis).normalize()

            val viewerMatrix = Matrix4f(
                floatArrayOf(
                xAxis.x, xAxis.y, xAxis.z, 0F,
                yAxis.x, yAxis.y, yAxis.z, 0F,
                zAxis.x, zAxis.y, zAxis.z, 0F,
                0F, 0F, 0F, 1F
                )
            )

            return TODO()
        }

        fun scale(x: Float): Matrix4f {
            return Matrix4f(
                floatArrayOf(
                    x, 0F, 0F, 0F,
                    0F, x, 0F, 0F,
                    0F, 0F, x, 0F,
                    0F, 0F, 0F, 1F
                )
            )
        }

        fun move(vector: Vector): Matrix4f {
            return Matrix4f(
                floatArrayOf(
                    1F, 0F, 0F, vector.x,
                    0F, 1F, 0F, vector.y,
                    0F, 0F, 1F, vector.z,
                    0F, 0F, 0F, 1F
                )
            )
        }

        fun rotatex(degrees: Float): Matrix4f {
            val angle: Double = Math.PI * degrees / 180.0
            val sin = sin(angle).toFloat()
            val cos = cos(angle).toFloat()

            return Matrix4f(
                floatArrayOf(
                    1F, 0F, 0F, 0F,
                    0F, cos, -sin, 0F,
                    0F, sin, cos, 0F,
                    0F, 0F, 0F, 1F
                )
            )
        }

        fun rotateY(degrees: Float): Matrix4f {
            val angle: Double = Math.PI * degrees / 180.0
            val sin = sin(angle).toFloat()
            val cos = cos(angle).toFloat()

            return Matrix4f(
                floatArrayOf(
                    cos, 0F, sin, 0F,
                    0F, 1F, 0F, 0F,
                    -sin, 0F, cos, 0F,
                    0F, 0F, 0F, 1F
                )
            )
        }

        fun rotateZ(degrees: Float): Matrix4f {
            val angle: Double = Math.PI * degrees / 180.0
            val sin = sin(angle).toFloat()
            val cos = cos(angle).toFloat()

            return Matrix4f(
                floatArrayOf(
                    cos, -sin, 0F, 0F,
                    sin, cos, 0F, 0F,
                    0F, 0F, 1F, 0F,
                    0F, 0F, 0F, 1F
                )
            )
        }
    }
}