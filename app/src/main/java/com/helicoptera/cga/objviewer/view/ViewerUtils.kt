package com.helicoptera.cga.objviewer.view

class ViewerUtils {
    companion object {

        fun processProjection(
            matrix: Array<FloatArray>,
            width: Float,
            height: Float,
            zNear: Float,
            zFar: Float
        ): Array<FloatArray> {
            val projectionMatrix = Matrix.projection(width, height, zNear, zFar)

            return matrix * projectionMatrix
        }

        fun processViewport(
            matrix: Array<FloatArray>,
            width: Float,
            height: Float,
            xMin: Float,
            yMin: Float
        ): Array<FloatArray> {
            val projectionMatrix = Matrix.viewport(width, height, xMin, yMin)

            return matrix * projectionMatrix
        }
    }
}