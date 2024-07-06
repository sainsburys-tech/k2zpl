package command

import command.options.ZplFont

internal data class Font(val font: ZplFont, val height: Int, val width: Int) : ZplCommand {
    init {
        require(height in 10..32000) { "Height must be between 10 and 32000" }
        require(width in 10..32000) { "Width must be between 10 and 32000" }
    }

    override val command: String = "^A"
    override val parameters: Map<String, Any?> = mapOf("f" to font.code, "h" to height, "w" to width)
}