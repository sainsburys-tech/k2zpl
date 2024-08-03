@file:Suppress("UNUSED")

package com.sainsburys.k2zpl.command.options

enum class ZplMediaMode(val value: Char) {
    TEAR_OFF('T'),
    PEEL_OFF('P'),
    REWIND('R'),
    APPLICATOR('A'),
    CUTTER('C'),
    DELAYED_CUT('D');

    override fun toString(): String {
        return value.toString()
    }
}