package info.mking.k2zpl.command

import info.mking.k2zpl.builder.ZplBuilder
import info.mking.k2zpl.builder.command

internal data class LabelHome(val x: Int, val y: Int) : ZplCommand {
    override val command: CharSequence = "^LH"
    override val parameters: LinkedHashMap<CharSequence, Any?> = linkedMapOf("x" to x, "y" to y)
}

/**
 * Sets the label home position.
 * @param x The x-coordinate of the label home.
 * @param y The y-coordinate of the label home.
 */
fun ZplBuilder.labelHome(x: Int, y: Int) {
    command(LabelHome(x = x, y = y))
}