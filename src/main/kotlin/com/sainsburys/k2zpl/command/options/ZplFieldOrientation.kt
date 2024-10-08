@file:Suppress("UNUSED")

package com.sainsburys.k2zpl.command.options

enum class ZplFieldOrientation(val code: Char) {
    NORMAL('N'),
    ROTATED_90('R'),
    INVERTED('I'),
    BOTTOM_UP('B');

    override fun toString(): String {
        return code.toString()
    }
}