package info.mking.k2zpl.command

import info.mking.k2zpl.command.options.ZplFieldOrientation

internal data class FieldOrigin(val x: Int, val y: Int, val alignment: ZplFieldOrientation? = null) :
    ZplCommand {
    override val command: String = "^FO"
    override val parameters: Map<String, Any?> = mutableMapOf<String, Any?>("x" to x, "y" to y).apply {
        if (alignment != null) this["a"] = alignment.code
    }
}