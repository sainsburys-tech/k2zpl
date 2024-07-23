package info.mking.k2zpl.command

import info.mking.k2zpl.command.options.ZplLineColor

internal data class GraphicEllipse(
    val width: Int,
    val height: Int,
    val thickness: Int,
    val color: ZplLineColor? = null
) : ZplCommand {
    init {
        require(width in 3..4095) { "Width must be between 3 and 4095" }
        require(height in 3..4095) { "Height must be between 3 and 4095" }
        require(thickness in 2..4095) { "Thickness must be between 2 and 4095" }
    }

    override val command: CharSequence = "^GE"
    override val parameters: LinkedHashMap<CharSequence, Any?> = buildLinkedMap {
        putAll(mapOf("w" to width, "h" to height, "t" to thickness))
        if (color != null) put("c", color.code)
    }
}