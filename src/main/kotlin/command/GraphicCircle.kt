package command

import command.options.ZplLineColor

internal data class GraphicCircle(val diameter: Int, val thickness: Int, val color: ZplLineColor? = null) : ZplCommand {
    init {
        require(diameter in 3..4095) { "Diameter must be between 3 and 4095" }
        require(thickness in 2..4095) { "Thickness must be between 2 and 4095" }
    }

    override val command: String = "^GC"
    override val parameters: Map<String, Any?> = mutableMapOf<String, Any?>("d" to diameter, "t" to thickness).apply {
        if (color != null) this["c"] = color.code
    }
}