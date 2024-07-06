package command

import command.options.ZplDiagonalOrientation
import command.options.ZplLineColor

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

    override val command: String = "^GD"
    override val parameters: Map<String, Any?> =
        mutableMapOf<String, Any?>("w" to width, "h" to height, "t" to thickness).apply {
            if (color != null) this["c"] = color.code
            if (orientation != null) this["o"] = orientation.code
        }
}
