package info.mking.k2zpl.command

internal data class PrintWidth(val width: Int) : ZplCommand {
    init {
        require(width in 1..32000) { "Width must be between 1 and 32000" }
    }

    override val command: String = "^PW"
    override val parameters: Map<String, Any?> = mapOf("w" to width)
}