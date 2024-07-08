package info.mking.k2zpl.command

internal data class LabelShift(val shift: Int) : ZplCommand {
    override val command: CharSequence = "^LS"
    override val parameters: Map<CharSequence, Any?> = mapOf("s" to shift)
}