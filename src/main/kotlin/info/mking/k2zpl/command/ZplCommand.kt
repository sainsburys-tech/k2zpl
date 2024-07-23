package info.mking.k2zpl.command

interface ZplCommand {
    val command: CharSequence
    val parameters: LinkedHashMap<CharSequence, Any?> get() = linkedMapOf()
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

internal fun <K, V> buildLinkedMap(block: LinkedHashMap<K, V>.() -> Unit) =
    linkedMapOf<K, V>().apply(block)