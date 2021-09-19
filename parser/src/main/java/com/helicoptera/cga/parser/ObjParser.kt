package com.helicoptera.cga.parser

import com.helicoptera.cga.parser.entity.PolygonEntity
import com.helicoptera.cga.parser.exception.ParseException
import com.helicoptera.cga.parser.mapper.PolygonMapper
import com.helicoptera.cga.parser.model.Vector
import com.helicoptera.cga.parser.model.Obj
import com.helicoptera.cga.parser.model.TextureVertex
import com.helicoptera.cga.parser.model.VertexCoordinates
import com.helicoptera.cga.parser.parser.NormalVectorParser
import com.helicoptera.cga.parser.parser.PolygonParser
import com.helicoptera.cga.parser.parser.TextureVertexParser
import com.helicoptera.cga.parser.parser.VertexCoordinatesParser

class ObjParser {

    private val normalVectorParser = NormalVectorParser()
    private val textureVertexParser = TextureVertexParser()
    private val vertexCoordinatesParser = VertexCoordinatesParser()
    private val polygonParser = PolygonParser()
    private val polygonMapper = PolygonMapper()

    fun parseObj(source: String): Obj {
        val normalVectors = mutableListOf<Vector>()
        val polygonsEntities = mutableListOf<PolygonEntity>()
        val texturesVertexes = mutableListOf<TextureVertex>()
        val vertexesCoordinates = mutableListOf<VertexCoordinates>()

        try {
            val items = source.split(VALUES_DELIMITER)
            for (item in items) {
                if (item[0] != COMMENT_PREFIX) {
                    val prefixDelimiterIndex = item.indexOf(PREFIX_DELIMITER)
                    val prefix = item.substring(0, prefixDelimiterIndex)
                    val value = item.substring(prefixDelimiterIndex + 1)
                    val processedValue = value.trim()
                    when (prefix) {
                        VERTEX_COORDINATES_PREFIX -> {
                            val vertexCoordinates =
                                vertexCoordinatesParser.parseVertexCoordinates(processedValue)
                            vertexesCoordinates.add(vertexCoordinates)
                        }
                        TEXTURE_VERTEX_PREFIX -> {
                            val textureVertex =
                                textureVertexParser.parseTextureVertex(processedValue)
                            texturesVertexes.add(textureVertex)
                        }
                        NORMAL_VECTOR_PREFIX -> {
                            val normalVector = normalVectorParser.parseNormalVector(processedValue)
                            normalVectors.add(normalVector)
                        }
                        POLYGON_PREFIX -> {
                            val polygonEntity = polygonParser.parsePolygon(processedValue)
                            polygonsEntities.add(polygonEntity)
                        }
                        else -> {
                            throw ParseException("Invalid object prefix: $prefix")
                        }
                    }
                }
            }

            val polygons = polygonsEntities.map {
                polygonMapper.convertPolygonEntityToModel(
                    it,
                    vertexesCoordinates,
                    texturesVertexes,
                    normalVectors
                )
            }
            return Obj(vertexesCoordinates, texturesVertexes, normalVectors, polygons)
        } catch (parseException: ParseException) {
            throw parseException
        }
    }

    companion object {
        private const val VERTEX_COORDINATES_PREFIX = "v"
        private const val TEXTURE_VERTEX_PREFIX = "tx"
        private const val NORMAL_VECTOR_PREFIX = "vn"
        private const val POLYGON_PREFIX = "f"
        private const val COMMENT_PREFIX = '#'
        private const val VALUES_DELIMITER = "\n"
        private const val PREFIX_DELIMITER = " "
    }
}