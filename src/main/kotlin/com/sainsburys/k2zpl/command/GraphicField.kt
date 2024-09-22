package com.sainsburys.k2zpl.command

import com.sainsburys.k2zpl.builder.ZplBuilder
import com.sainsburys.k2zpl.command.options.ZplCompressionType

internal data class GraphicField(
    val format: ZplCompressionType,
    val dataBytes: Int,
    val totalBytes: Int,
    val rowBytes: Int,
    val data: String
) : ZplCommand {
    init {
        require(dataBytes in 1..999999) { "Data bytes must be between 1 and 999999" }
        require(totalBytes in 1..999999) { "Total bytes must be between 1 and 999999" }
        require(rowBytes in 1..32000) { "Row bytes must be between 1 and 32000" }
    }

    override val command: CharSequence = "^GF"
    override val parameters: ZplParameters = zplParameters(
        "f" to format,
        "db" to dataBytes,
        "tb" to totalBytes,
        "rb" to rowBytes,
        "d" to data
    )
}

/**
 * Draws a graphic field.
 * @param format The format of the graphic field.
 * @param dataBytes The number of data bytes.
 * @param totalBytes The total number of bytes.
 * @param rowBytes The number of bytes per row.
 * @param data The data for the graphic field.
 */
fun ZplBuilder.graphicField(format: ZplCompressionType = ZplCompressionType.ASCII, dataBytes: Int, totalBytes: Int, rowBytes: Int, data: String) {
    command(
        GraphicField(
            format = format,
            dataBytes = dataBytes,
            totalBytes = totalBytes,
            rowBytes = rowBytes,
            data = data
        )
    )
}