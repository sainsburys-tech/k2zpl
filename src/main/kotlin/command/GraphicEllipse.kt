package command

import command.options.ZplLineColor

internal data class GraphicEllipse(val width: Int, val height: Int, val thickness: Int, val color: ZplLineColor? = null) :
    ZplCommand {
    init {
        require(width in 3..4095) { "Width must be between 3 and 4095" }
        require(height in 3..4095) { "Height must be between 3 and 4095" }
        require(thickness in 2..4095) { "Thickness must be between 2 and 4095" }
    }

    override val command: String = "^GE"
    override val parameters: Map<String, Any?> =
        mutableMapOf<String, Any?>("w" to width, "h" to height, "t" to thickness).apply {
            if (color != null) this["c"] = color.code
        }
}