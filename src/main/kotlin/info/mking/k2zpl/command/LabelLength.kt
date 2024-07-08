package info.mking.k2zpl.command

internal data class LabelLength(val length: Int) : ZplCommand {
    init {
        require(length in 1..32000) { "Length must be between 1 and 32000" }
    }

    override val command: CharSequence = "^LL"
    override val parameters: Map<CharSequence, Any?> = mapOf("l" to length)
}