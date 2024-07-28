package info.mking.k2zpl.command

import info.mking.k2zpl.builder.ZplBuilder
import info.mking.k2zpl.builder.command
import info.mking.k2zpl.builder.toZplYesNo
import info.mking.k2zpl.command.options.ZplBarcodeType
import info.mking.k2zpl.command.options.ZplFieldOrientation
import info.mking.k2zpl.command.options.ZplYesNo

internal data class BarCode(
    val type: ZplBarcodeType,
    val orientation: ZplFieldOrientation,
    val checkDigit: ZplYesNo,
    val height: Int,
    val line: Int,
    val lineAbove: ZplYesNo
) : ZplCommand {
    init {
        require(height in 1..32000) { "Height must be between 1 and 32000" }
        require(line in 1..7) { "Line thickness must be between 1 and 7" }
    }

    override val command: CharSequence = "^B1"
    override val parameters: LinkedHashMap<CharSequence, Any?> = linkedMapOf(
        "o" to orientation.code,
        "c" to checkDigit.toString(),
        "h" to height,
        "l" to line,
        "la" to lineAbove.toString()
    )
}

/**
 * Creates a Code 39 barcode.
 * @param barcodeType Barcode type
 * @param orientation The orientation of the barcode.
 * @param checkDigit Whether to include a check digit.
 * @param height The height of the barcode.
 * @param line The line thickness of the barcode.
 * @param lineAbove Whether to include a line above the barcode.
 */
fun ZplBuilder.barcode(
    barcodeType: ZplBarcodeType = ZplBarcodeType.CODE_39,
    orientation: ZplFieldOrientation = ZplFieldOrientation.NORMAL,
    checkDigit: Boolean,
    height: Int,
    line: Int,
    lineAbove: Boolean
) {
    command(
        BarCode(
            type = barcodeType,
            orientation = orientation,
            checkDigit = checkDigit.toZplYesNo(),
            height = height,
            line = line,
            lineAbove = lineAbove.toZplYesNo()
        )
    )
}

