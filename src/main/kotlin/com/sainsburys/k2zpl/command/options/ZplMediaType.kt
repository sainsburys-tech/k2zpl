package com.sainsburys.k2zpl.command.options

enum class ZplMediaType(val value: String) {
    THERMAL_TRANSFER("T"),
    DIRECT_TRANSFER("D");

    override fun toString(): String {
        return value
    }
}