package com.helicoptera.cga.parser.model

import android.renderscript.Matrix4f

data class VertexCoordinates(
    val x: Float,
    val y: Float,
    val z: Float,
    val w: Float
) : Cloneable{
    public override fun clone(): VertexCoordinates {
        return VertexCoordinates(x, y, z, w)
    }
    fun transform(matrix: Matrix4f) : VertexCoordinates{
        return VertexCoordinates(
            (x.toDouble() * matrix.get(1, 1).toDouble() + y.toDouble() * matrix.get(2, 1).toDouble() + z.toDouble() * matrix.get(3, 1).toDouble() + w.toDouble() * matrix.get(4, 1).toDouble()).toFloat(),
            (x.toDouble() * matrix.get(1, 2).toDouble() + y.toDouble() * matrix.get(2, 2).toDouble() + z.toDouble() * matrix.get(3, 2).toDouble() + w.toDouble() * matrix.get(4, 2).toDouble()).toFloat(),
            (x.toDouble() * matrix.get(1, 3).toDouble() + y.toDouble() * matrix.get(2, 3).toDouble() + z.toDouble() * matrix.get(3, 3).toDouble() + w.toDouble() * matrix.get(4, 3).toDouble()).toFloat(),
            (x.toDouble() * matrix.get(1, 4).toDouble() + y.toDouble() * matrix.get(2, 4).toDouble() + z.toDouble() * matrix.get(3, 4).toDouble() + w.toDouble() * matrix.get(4, 4).toDouble()).toFloat()
        )
    }
}