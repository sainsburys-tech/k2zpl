package command

import command.options.ZplLineColor

internal data class GraphicBox(
    val width: Int, val height: Int, val thickness: Int, val color: ZplLineColor? = null, val rounding: Int? = null
) : ZplCommand {
    init {
        require(width in 1..32000) { "Width must be between 1 and 32000" }
        require(height in 1..32000) { "Height must be between 1 and 32000" }
        require(thickness in 1..32000) { "Thickness must be between 1 and 32000" }
    }

    override val command: String = "^GB"
    override val parameters: Map<String, Any?> =
        mutableMapOf<String, Any?>("w" to width, "h" to height, "t" to thickness).apply {
            if (color != null) this["c"] = color.code
            if (rounding != null) this["r"] = rounding
        }
}