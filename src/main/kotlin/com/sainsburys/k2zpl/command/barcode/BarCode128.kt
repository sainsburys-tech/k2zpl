package com.sainsburys.k2zpl.command.barcode

import com.sainsburys.k2zpl.builder.ZplBuilder
import com.sainsburys.k2zpl.builder.toZplYesNo
import com.sainsburys.k2zpl.command.ZplParameters
import com.sainsburys.k2zpl.command.fieldData
import com.sainsburys.k2zpl.command.fieldOrigin
import com.sainsburys.k2zpl.command.fieldSeparator
import com.sainsburys.k2zpl.command.options.ZplBarCode128Mode
import com.sainsburys.k2zpl.command.options.ZplFieldOrientation
import com.sainsburys.k2zpl.command.options.ZplYesNo
import com.sainsburys.k2zpl.command.zplParameters

internal data class BarCode128(
    override val orientation: ZplFieldOrientation,
    override val height: Int,
    val line: ZplYesNo,
    val lineAbove: ZplYesNo,
    val checkDigit: ZplYesNo,
    val mode: ZplBarCode128Mode
) : BarCode() {

    init {
        require(height in 1..32000) { "Height must be between 1 and 32000" }
    }

    override val command = "^BC"

    override val parameters: ZplParameters = zplParameters(
        "o" to orientation.code,
        "h" to height,
        "l" to line,
        "la" to lineAbove.toString(),
        "c" to checkDigit.toString(),
        "m" to mode.toString()
    )
}

/**
 * Creates a Code 128 barcode marker
 * @param data data encoded in the barcode
 * @param x horizontal position
 * @param y vertical position
 * @param orientation The orientation of the barcode.
 * @param height The height of the barcode.
 * @param interpretationLine print interpretation line
 * @param lineAbove print interpretation line above code
 * @param checkDigit UCC check digit
 * @param mode barcode mode
 */
fun ZplBuilder.barcode128(
    data: String,
    x: Int,
    y: Int,
    orientation: ZplFieldOrientation = ZplFieldOrientation.NORMAL,
    height: Int,
    interpretationLine: Boolean = false,
    lineAbove: Boolean = false,
    checkDigit: Boolean = false,
    mode: ZplBarCode128Mode = ZplBarCode128Mode.NONE
) {
    fieldOrigin(x, y)
    command(
        BarCode128(
            orientation = orientation,
            height = height,
            line = interpretationLine.toZplYesNo(),
            lineAbove = lineAbove.toZplYesNo(),
            checkDigit = checkDigit.toZplYesNo(),
            mode = mode
        )
    )
    fieldData(data)
    fieldSeparator()
}
