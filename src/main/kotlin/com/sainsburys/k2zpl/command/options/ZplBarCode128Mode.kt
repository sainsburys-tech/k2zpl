package com.sainsburys.k2zpl.command.options

enum class ZplBarCode128Mode(val value: String) {
    NONE("N"),
    UCC("U"),
    AUTOMATIC("A"),
    UCC_EAN("D");

    override fun toString(): String {
        return value
    }
}