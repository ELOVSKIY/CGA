package com.helicoptera.cga.parser.mapper

import com.helicoptera.cga.parser.entity.PolygonEntity
import com.helicoptera.cga.parser.model.*

internal class PolygonMapper {

    internal fun convertPolygonEntityToModel(
        polygonEntity: PolygonEntity,
        vertexesCoordinates: List<VertexCoordinates>,
        textureVertexes: List<TextureVertex>,
        normalVectors: List<Vector>
    ): Polygon {
        val vertexes = polygonEntity.vertexes.map {
            it.run {
                val vertexCoordinates = if (vertexCoordinatesIndex > 0) vertexesCoordinates[vertexCoordinatesIndex - 1]
                else vertexesCoordinates[vertexesCoordinates.lastIndex + vertexCoordinatesIndex + 1]
                val textureVertex =
                    if (textureVertexIndex != null) if (textureVertexIndex > 0) textureVertexes[textureVertexIndex- 1]
                    else textureVertexes[textureVertexes.lastIndex + textureVertexIndex + 1] else null
                val normalVector =
                    if (normalVectorIndex != null) if (normalVectorIndex > 0) normalVectors[normalVectorIndex - 1]
                    else normalVectors[normalVectors.lastIndex + normalVectorIndex + 1] else null

                return@run Vertex(vertexCoordinates, textureVertex, normalVector)
            }
        }

        return Polygon(vertexes)
    }
}