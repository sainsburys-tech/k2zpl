package info.mking.k2zpl.command

import info.mking.k2zpl.command.options.ZplFieldOrientation

internal data class FieldOrigin(val x: Int, val y: Int, val alignment: ZplFieldOrientation? = null) :
    ZplCommand {
    override val command: CharSequence = "^FO"
    override val parameters: LinkedHashMap<CharSequence, Any?> = buildLinkedMap {
        putAll(linkedMapOf("x" to x, "y" to y))
        if (alignment != null) put("a", alignment.code)
    }
}