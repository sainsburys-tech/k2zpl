@file:Suppress("UNUSED")

package com.sainsburys.k2zpl.command.options

enum class ZplFont(val code: Char) {
    A('A'),
    B('B'),
    C('C'),
    D('D'),
    E('E'),
    F('F'),
    G('G'),
    H('H'),
    GS('0'); // Graphic Symbol font

    override fun toString(): String {
        return code.toString()
    }
}