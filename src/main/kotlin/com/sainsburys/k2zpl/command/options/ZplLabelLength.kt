@file:Suppress("UNUSED")

package com.sainsburys.k2zpl.command.options

sealed class ZplLabelLength {
    data class Exact(val value: Int) : ZplLabelLength()
    data object CurrentPosition : ZplLabelLength()
}
