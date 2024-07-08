package info.mking.k2zpl.command

import info.mking.k2zpl.command.options.ZplLineColor

internal data class GraphicBox(
    val width: Int,
    val height: Int,
    val thickness: Int,
    val color: ZplLineColor? = null,
    val rounding: Int? = null
) : ZplCommand {
    init {
        require(width in 1..32000) { "Width must be between 1 and 32000" }
        require(height in 1..32000) { "Height must be between 1 and 32000" }
        require(thickness in 1..32000) { "Thickness must be between 1 and 32000" }
    }

    override val command: CharSequence = "^GB"
    override val parameters: Map<CharSequence, Any?> =
        buildMap {
            putAll(mapOf("w" to width, "h" to height, "t" to thickness))

            if (color != null) put("c", color.code)
            if (rounding != null) put("r", rounding)
        }
}