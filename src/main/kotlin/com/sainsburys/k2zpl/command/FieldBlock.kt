package com.sainsburys.k2zpl.command

import com.sainsburys.k2zpl.builder.ZplBuilder
import com.sainsburys.k2zpl.command.options.ZplTextAlignment

internal data class FieldBlock(
    val width: Int,
    val maxLines: Int,
    val lineSpacing: Int,
    val alignment: ZplTextAlignment,
    val hangingIndent: Int
) : ZplCommand(
    parameters = listOf(
        "w" to width,
        "l" to maxLines,
        "s" to lineSpacing,
        "a" to alignment.code,
        "h" to hangingIndent
    )
) {
    init {
        require(width in 1..32000) { "Width must be between 1 and 32000" }
        require(maxLines in 1..9999) { "Lines must be between 1 and 9999" }
        require(lineSpacing in -9999..9999) { "Line spacing must be between -9999 and 9999" }
        require(hangingIndent in 0..9999) { "Hanging indent must be between 0 and 9999" }
    }

    override val command: CharSequence = "^FB"
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

/**
 * Formats a text block.
 * @param x horizontal position of fieldBlock
 * @param y vertical position of fieldBlock
 * @param width The width of the text block.
 * @param data content of block. Any newlines are substituted with \&
 * @param maxLines The number of lines in the text block.
 * @param lineSpacing The space between lines.
 * @param alignment The text alignment within the block.
 * @param hangingIndent The hanging indent for the block.
 */
fun ZplBuilder.fieldBlock(
    x: Int,
    y: Int,
    width: Int,
    data: String,
    maxLines: Int = 1,
    lineSpacing: Int = 0,
    alignment: ZplTextAlignment = ZplTextAlignment.LEFT,
    hangingIndent: Int = 0
) {
    fieldOrigin(x = x, y = y)
    fieldBlock(
        width = width,
        maxLines = maxLines,
        lineSpacing = lineSpacing,
        alignment = alignment,
        hangingIndent = hangingIndent
    )
    fieldData(data)
    fieldSeparator()
}