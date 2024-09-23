package com.sainsburys.k2zpl.command

interface ZplCommand {
    val command: CharSequence
    val parameters: ZplParameters get() = zplParameters()
    fun build(stringBuilder: StringBuilder) = stringBuilder.apply {
        append(command)
        with(parameters.iterator()) {
            if (hasNext()) {
                next().value?.let { append(it.toString()) }
            }
            while (hasNext()) {
                next().value?.let {
                    if (length > command.length) {
                        append(',')
                    }
                    append(it.toString())
                }
            }
        }
    }
}
