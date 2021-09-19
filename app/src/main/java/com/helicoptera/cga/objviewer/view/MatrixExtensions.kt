package com.helicoptera.cga.objviewer.view

operator fun Array<FloatArray>.times(secondMatrix: Array<FloatArray>): Array<FloatArray> {
    val product = Array(this.size) { FloatArray(secondMatrix[0].size) }
    for (i in this.indices) {
        for (j in secondMatrix[0].indices) {
            for (k in secondMatrix.indices) {
                product[i][j] += this[i][k] * secondMatrix[k][j]
            }
        }
    }

    return product
}