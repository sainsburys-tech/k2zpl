package info.mking.k2zpl.command

interface ZplCommand {
    val command: CharSequence
    val parameters: LinkedHashMap<CharSequence, Any?> get() = linkedMapOf()
    fun build(stringBuilder: StringBuilder) = stringBuilder.apply {
        append(command)
        with(parameters.values.iterator()) {
            if (hasNext()) append(next().toString())
            while (hasNext()) {
                append(',')
                append(next().toString())
            }
        }
    }
}

fun <K, V> buildLinkedMap(block: LinkedHashMap<K, V>.() -> Unit) =
    linkedMapOf<K, V>().apply(block)