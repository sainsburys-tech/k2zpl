package info.mking.k2zpl.command

import info.mking.k2zpl.builder.ZplBuilder
import info.mking.k2zpl.builder.command
import info.mking.k2zpl.command.options.ZplFieldOrientation
import info.mking.k2zpl.command.options.ZplFont

internal data class Font(
    val font: ZplFont,
    val orientation: ZplFieldOrientation,
    val height: Int,
    val width: Int
) :
    ZplCommand {
    init {
        require(height in 10..32000) { "Height must be between 10 and 32000" }
        require(width in 10..32000) { "Width must be between 10 and 32000" }
    }

    override val command: CharSequence = "^A${font}"
    override val parameters: LinkedHashMap<CharSequence, Any?> =
        linkedMapOf("o" to orientation, "h" to height, "w" to width)
}


/**
 * Sets the font for text fields.
 * @param font The font to use.
 * @param height The height of the font.
 * @param width The width of the font.
 */
fun ZplBuilder.font(
    font: ZplFont,
    orientation: ZplFieldOrientation = ZplFieldOrientation.NORMAL,
    height: Int,
    width: Int
) {
    command(
        Font(
            font = font,
            orientation = orientation,
            height = height,
            width = width
        )
    )
}