@file:Suppress("UNUSED")

package com.sainsburys.k2zpl.command.options

internal enum class ZplYesNo(val value: String) {
    YES("Y"),
    NO("N");

    override fun toString(): String {
        return value
    }
}