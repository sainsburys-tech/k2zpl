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
 * Creates a Code 39 barcode marker
 * @param data data encoded in the barcode
 * @param x horizontal position
 * @param y vertical position
 * @param barcodeType Barcode type
 * @param orientation The orientation of the barcode.
 * @param checkDigit Whether to include a check digit.
 * @param height The height of the barcode.
 * @param lineThickness The line thickness of the barcode.
 * @param lineAbove Whether to include a line above the barcode.
 */
fun ZplBuilder.barcode(
    data: String,
    x: Int,
    y: Int,
    height: Int,
    lineThickness: Int,
    barcodeType: ZplBarcodeType = ZplBarcodeType.CODE_39,
    orientation: ZplFieldOrientation = ZplFieldOrientation.NORMAL,
    checkDigit: Boolean = false,
    lineAbove: Boolean = false
) {
    fieldOrigin(x, y)
    command(
        BarCode(
            type = barcodeType,
            orientation = orientation,
            checkDigit = checkDigit.toZplYesNo(),
            height = height,
            line = lineThickness,
            lineAbove = lineAbove.toZplYesNo()
        )
    )
    fieldData(data)
    fieldSeparator()
}
