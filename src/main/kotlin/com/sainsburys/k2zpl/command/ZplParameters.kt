package com.sainsburys.k2zpl.command

class ZplParameters(map: Map<CharSequence, Any?>) : Iterable<Map.Entry<CharSequence, Any?>> {
    constructor(vararg pairs: Pair<CharSequence, Any?>) : this(mapOf(*pairs))
    private val _parameters: LinkedHashMap<CharSequence, Any?> = LinkedHashMap(map)
    operator fun get(key: CharSequence) = _parameters[key]
    override fun iterator(): Iterator<Map.Entry<CharSequence, Any?>> =
        _parameters.iterator()
}

fun zplParameters(vararg pairs: Pair<CharSequence, Any?>) = ZplParameters(*pairs)
fun zplParameters(builderBlock: MutableMap<CharSequence, Any?>.() -> Unit) = ZplParameters(buildMap(builderBlock))