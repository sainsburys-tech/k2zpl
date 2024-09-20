package com.sainsburys.k2zpl.command

import java.util.Collections.unmodifiableMap

abstract class ZplCommand(
    parameters: List<Pair<CharSequence, Any?>> = emptyList()
) {
    val parameters: Map<CharSequence, Any?> = unmodifiableMap(parameters.toMap())

    abstract val command: CharSequence

    open fun build(stringBuilder: StringBuilder) = stringBuilder.apply {
        append(command)
        with(parameters.values.iterator()) {
            if (hasNext()) {
                nextNotNull { append(it.toString()) }
            }
            while (hasNext()) {
                nextNotNull {
                    if (length > command.length) {
                        append(',')
                    }
                    append(it.toString())
                }
            }
        }
    }
}

private fun <T> Iterator<T>.nextNotNull(block: (T) -> Unit) {
    next()?.let { block(it) }
}
