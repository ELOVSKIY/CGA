package com.helicoptera.cga.parser.entity

internal data class VertexEntity(
    val vertexCoordinatesIndex: Int,
    val textureVertexIndex: Int? = null,
    val normalVectorIndex: Int? = null
)