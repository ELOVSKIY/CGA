package com.helicoptera.cga.parser.model

data class Polygon(
    val vertexes: List<Vertex>
) : Cloneable {
    public override fun clone(): Polygon {
        return Polygon(vertexes.map { vertex -> vertex.clone() })
    }
}