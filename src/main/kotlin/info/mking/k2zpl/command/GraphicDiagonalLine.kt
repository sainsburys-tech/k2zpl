package info.mking.k2zpl.command

import info.mking.k2zpl.command.options.ZplDiagonalOrientation
import info.mking.k2zpl.command.options.ZplLineColor

internal data class GraphicDiagonalLine(
    val width: Int,
    val height: Int,
    val thickness: Int,
    val color: ZplLineColor? = null,
    val orientation: ZplDiagonalOrientation? = null
) : ZplCommand {
    init {
        require(width in 3..32000) { "Width must be between 3 and 32000" }
        require(height in 3..32000) { "Height must be between 3 and 32000" }
        require(thickness in 1..32000) { "Thickness must be between 1 and 32000" }
    }

    override val command: CharSequence = "^GD"
    override val parameters: Map<CharSequence, Any?> = buildMap {
        putAll(mapOf("w" to width, "h" to height, "t" to thickness))
        if (color != null) put("c", color.code)
        if (orientation != null) put("o", orientation.code)
    }
}
