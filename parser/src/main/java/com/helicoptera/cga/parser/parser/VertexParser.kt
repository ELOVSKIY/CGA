package com.helicoptera.cga.parser.parser

import com.helicoptera.cga.parser.entity.VertexEntity
import com.helicoptera.cga.parser.exception.ParseException

internal class VertexParser {

    internal fun parseVertex(source: String) : VertexEntity {
        val values = source.split(VALUES_DELIMITER)
        try {
            val vertexCoordinatesIndex = values[0].toInt()
            val textureVertexIndex =
                if (values.size > 1 && values[1].isNotEmpty()) values[1].toInt() else null
            val normalVectorIndex = if (values.size > 2) values[2].toInt() else null

            return VertexEntity(vertexCoordinatesIndex, textureVertexIndex, normalVectorIndex)
        } catch (e: Exception) {
            throw ParseException("Invalid vertex value: $source")
        }
    }

    companion object {
        private const val VALUES_DELIMITER = "/"
    }
}