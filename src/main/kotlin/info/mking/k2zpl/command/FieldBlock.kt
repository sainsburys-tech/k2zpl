package info.mking.k2zpl.command

import info.mking.k2zpl.command.options.ZplTextAlignment

internal data class FieldBlock(
    val width: Int, val lines: Int, val lineSpacing: Int, val alignment: ZplTextAlignment, val hangingIndent: Int
) : ZplCommand {
    init {
        require(width in 1..32000) { "Width must be between 1 and 32000" }
        require(lines in 1..999) { "Lines must be between 1 and 999" }
        require(lineSpacing in 0..32000) { "Line spacing must be between 0 and 32000" }
        require(hangingIndent in 0..32000) { "Hanging indent must be between 0 and 32000" }
    }

    override val command: CharSequence = "^FB"
    override val parameters: Map<CharSequence, Any?> = mapOf(
        "w" to width, "l" to lines, "s" to lineSpacing, "a" to alignment.code, "h" to hangingIndent
    )
}