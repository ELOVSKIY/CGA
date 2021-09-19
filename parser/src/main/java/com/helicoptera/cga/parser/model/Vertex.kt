package com.helicoptera.cga.parser.model

data class Vertex(
    val vertexCoordinates: VertexCoordinates,
    val textureVertex: TextureVertex? = null,
    val normalVector: Vector? = null
)