package com.sainsburys.k2zpl.command.barcode

import com.sainsburys.k2zpl.builder.ZplBuilder
import com.sainsburys.k2zpl.builder.toZplYesNo
import com.sainsburys.k2zpl.command.ZplParameters
import com.sainsburys.k2zpl.command.fieldData
import com.sainsburys.k2zpl.command.fieldOrigin
import com.sainsburys.k2zpl.command.fieldSeparator
import com.sainsburys.k2zpl.command.options.ZplFieldOrientation
import com.sainsburys.k2zpl.command.options.ZplYesNo
import com.sainsburys.k2zpl.command.zplParameters

internal data class BarCode39(
    override val height: Int,
    override val orientation: ZplFieldOrientation,
    val checkDigit: ZplYesNo,
    val line: ZplYesNo,
    val lineAbove: ZplYesNo,
) : BarCode() {

    init {
        require(height in 1..32000) { "Height must be between 1 and 32000" }
    }

    override val command = "^B3"

    override val parameters: ZplParameters = zplParameters(
        "o" to orientation.code,
        "c" to checkDigit.toString(),
        "h" to height,
        "l" to line.toString(),
        "la" to lineAbove.toString()
    )
}

/**
 * Creates a Code 39 barcode marker
 * @param data data encoded in the barcode
 * @param x horizontal position
 * @param y vertical position
 * @param orientation The orientation of the barcode.
 * @param checkDigit Mod-43 check digit
 * @param height The height of the barcode.
 * @param interpretationLine print interpretation line
 * @param lineAbove print interpretation line above code
 */
fun ZplBuilder.barcode39(
    data: String,
    x: Int,
    y: Int,
    orientation: ZplFieldOrientation = ZplFieldOrientation.NORMAL,
    checkDigit: Boolean = false,
    height: Int,
    interpretationLine: Boolean = false,
    lineAbove: Boolean = false
) {
    fieldOrigin(x, y)
    command(
        BarCode39(
            orientation = orientation,
            checkDigit = checkDigit.toZplYesNo(),
            height = height,
            line = interpretationLine.toZplYesNo(),
            lineAbove = lineAbove.toZplYesNo()
        )
    )
    fieldData(data)
    fieldSeparator()
}
