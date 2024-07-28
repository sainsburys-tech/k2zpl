package info.mking.k2zpl.command

import info.mking.k2zpl.command.options.ZplFieldOrientation

internal data class BarCode(
    val type: BarcodeType = BarcodeType.CODE_39,
    val orientation: ZplFieldOrientation = ZplFieldOrientation.NORMAL,
    val checkDigit: Boolean,
    val height: Int,
    val line: Int,
    val lineAbove: Boolean
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

    enum class BarcodeType {
        CODE_39
    }
}
