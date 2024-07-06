package command

internal data class LabelShift(val shift: Int) : ZplCommand {
    override val command: String = "^LS"
    override val parameters: Map<String, Any?> = mapOf("s" to shift)
}