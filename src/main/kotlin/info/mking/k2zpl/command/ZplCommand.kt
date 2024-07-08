package info.mking.k2zpl.command

interface ZplCommand {
    val command: CharSequence
    val parameters: Map<CharSequence, Any?> get() = emptyMap()
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