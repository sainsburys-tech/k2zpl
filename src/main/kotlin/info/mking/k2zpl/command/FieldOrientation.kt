package info.mking.k2zpl.command

import info.mking.k2zpl.command.options.ZplFieldOrientation

internal data class FieldOrientation(val orientation: ZplFieldOrientation) : ZplCommand {
    override val command: CharSequence = "^FW"
    override val parameters: LinkedHashMap<CharSequence, Any?> = linkedMapOf("o" to orientation.code)
}