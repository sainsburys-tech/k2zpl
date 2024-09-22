package com.sainsburys.k2zpl.command

interface ZplCommand {
    val command: CharSequence
    val parameters: ZplParameters get() = zplParameters()
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
