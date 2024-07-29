package info.mking.k2zpl.command

import info.mking.k2zpl.builder.ZplBuilder
import info.mking.k2zpl.command.options.ZplTextAlignment

internal data class FieldBlock(
    val width: Int,
    val maxLines: Int,
    val lineSpacing: Int,
    val alignment: ZplTextAlignment,
    val hangingIndent: Int
) : ZplCommand {
    init {
        require(width in 1..32000) { "Width must be between 1 and 32000" }
        require(maxLines in 1..9999) { "Lines must be between 1 and 9999" }
        require(lineSpacing in -9999..9999) { "Line spacing must be between -9999 and 9999" }
        require(hangingIndent in 0..9999) { "Hanging indent must be between 0 and 9999" }
    }

    override val command: CharSequence = "^FB"
    override val parameters: LinkedHashMap<CharSequence, Any?> = linkedMapOf(
        "w" to width,
        "l" to maxLines,
        "s" to lineSpacing,
        "a" to alignment.code,
        "h" to hangingIndent
    )
}

/**
 * Formats a text block.
 * @param width The width of the text block.
 * @param maxLines The number of lines in the text block.
 * @param lineSpacing The space between lines.
 * @param alignment The text alignment within the block.
 * @param hangingIndent The hanging indent for the block.
 */
fun ZplBuilder.fieldBlock(
    width: Int,
    maxLines: Int = 1,
    lineSpacing: Int = 0,
    alignment: ZplTextAlignment = ZplTextAlignment.LEFT,
    hangingIndent: Int = 0
) {
    command(
        FieldBlock(
            width = width,
            maxLines = maxLines,
            lineSpacing = lineSpacing,
            alignment = alignment,
            hangingIndent = hangingIndent
        )
    )
}
