package info.mking.k2zpl.command

import info.mking.k2zpl.command.options.ZplFieldOrientation

internal data class FontAt(val orientation: ZplFieldOrientation, val height: Int, val width: Int, val path: String) :
    ZplCommand {
    init {
        require(height in 10..32000) { "Height must be between 10 and 32000" }
        require(width in 10..32000) { "Width must be between 10 and 32000" }
    }

    override val command: CharSequence = "^A@"
    override val parameters: LinkedHashMap<CharSequence, Any?> =
        linkedMapOf("o" to orientation.code, "h" to height, "w" to width, "p" to path)
}