package com.sainsburys.k2zpl.command

interface ZplCommand {
    val command: CharSequence
    val parameters: Map<CharSequence, Any?> get() = linkedMapOf()
    fun build(stringBuilder: StringBuilder) = stringBuilder.apply {
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

/**
 * A shortcut to adding parameters that helps to enforce use of [LinkedHashMap]
 * so that entry order is preserved.
 */
internal fun <K, V> ZplCommand.addParameters(vararg pairs: Pair<K, V>) = linkedMapOf(*pairs)
