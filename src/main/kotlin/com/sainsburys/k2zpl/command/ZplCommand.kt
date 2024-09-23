package com.sainsburys.k2zpl.command

interface ZplCommand {
    val command: CharSequence
    val parameters: ZplParameters get() = zplParameters()
    fun build(stringBuilder: StringBuilder) = stringBuilder.apply {
        append(command)
        parameters
            .asSequence()
            .mapNotNull { it.value?.toString() }
            .filter(String::isNotBlank)
            .joinTo(this, separator = ",")
    }
}
