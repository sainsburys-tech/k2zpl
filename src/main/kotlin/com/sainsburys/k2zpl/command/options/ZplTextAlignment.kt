@file:Suppress("UNUSED")

package com.sainsburys.k2zpl.command.options

enum class ZplTextAlignment(val code: Char) {
    LEFT('L'),
    CENTER('C'),
    RIGHT('R'),
    JUSTIFY('J');

    override fun toString(): String {
        return code.toString()
    }
}