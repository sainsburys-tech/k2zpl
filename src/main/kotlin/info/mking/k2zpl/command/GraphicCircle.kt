package info.mking.k2zpl.command

import info.mking.k2zpl.command.options.ZplLineColor

internal data class GraphicCircle(
    val diameter: Int,
    val thickness: Int,
    val color: ZplLineColor? = null
) : ZplCommand {
    init {
        require(diameter in 3..4095) { "Diameter must be between 3 and 4095" }
        require(thickness in 2..4095) { "Thickness must be between 2 and 4095" }
    }

    override val command: CharSequence = "^GC"
    override val parameters: LinkedHashMap<CharSequence, Any?> = buildLinkedMap {
        putAll(mapOf("d" to diameter, "t" to thickness))
        if (color != null) put("c", color.code)
    }
}