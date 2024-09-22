package com.sainsburys.k2zpl.command

class ZplParameters(vararg pairs: Pair<CharSequence, Any?>) {
    private val _parameters: LinkedHashMap<CharSequence, Any?> = linkedMapOf(*pairs)
    val values get() = _parameters.values
    operator fun get(key: CharSequence) = _parameters[key]
}

fun zplParameters(vararg pairs: Pair<CharSequence, Any?>) = ZplParameters(*pairs)
