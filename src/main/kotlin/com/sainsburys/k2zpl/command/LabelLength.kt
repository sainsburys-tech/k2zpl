package com.sainsburys.k2zpl.command

import com.sainsburys.k2zpl.builder.ZplBuilder

internal data class LabelLength(val length: Int) : ZplCommand {
    init {
        require(length in 1..32000) { "Length must be between 1 and 32000" }
    }

    override val command: CharSequence = "^LL"
    override val parameters: LinkedHashMap<CharSequence, Any?> = linkedMapOf("l" to length)
}

/**
 * Sets the length of the label.
 * @param length The length of the label.
 */
fun ZplBuilder.labelLength(length: Int) {
    command(LabelLength(length))
}

/**
 * Sets the length of the label by lambda
 * This allows for lazy evaluation of the length.
 * @param length lambda with context of the current [ZplBuilder]
 */
fun ZplBuilder.labelLength(length: ZplBuilder.() -> Int) {
    command {
        LabelLength(length.invoke(this))
    }
}