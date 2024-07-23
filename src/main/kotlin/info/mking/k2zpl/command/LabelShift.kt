package info.mking.k2zpl.command

internal data class LabelShift(val shift: Int) : ZplCommand {
    override val command: CharSequence = "^LS"
    override val parameters: LinkedHashMap<CharSequence, Any?> = linkedMapOf("s" to shift)
}