package com.sainsburys.k2zpl.command

import com.sainsburys.k2zpl.builder.ZplBuilder

internal data class LabelHome(val x: Int, val y: Int) : ZplCommand(
    parameters = listOf("x" to x, "y" to y)
) {
    override val command: CharSequence = "^LH"
}

/**
 * Sets the label home position.
 * @param x The x-coordinate of the label home.
 * @param y The y-coordinate of the label home.
 */
fun ZplBuilder.labelHome(x: Int, y: Int) {
    command(LabelHome(x = x, y = y))
}