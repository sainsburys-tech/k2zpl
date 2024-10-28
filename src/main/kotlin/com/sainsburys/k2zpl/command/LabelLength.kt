package com.sainsburys.k2zpl.command

import com.sainsburys.k2zpl.builder.ZplBuilder
import com.sainsburys.k2zpl.command.options.ZplLabelLength

internal data class LabelLength(val length: Int) : ZplCommand {
    init {
        require(length in 1..32000) { "Length must be between 1 and 32000" }
    }

    override val command: CharSequence = "^LL"
    override val parameters: ZplParameters = zplParameters("l" to length)
}

/**
 * Sets the length of the label using Int.
 * @param length The length of the label.
 */
fun ZplBuilder.labelLength(length: Int) {
    labelLength(ZplLabelLength.Exact(length))
}

/**
 * Sets the length of the label using ZplLabelLength.
 * @param length The length of the label:
 *   - [ZplLabelLength.Exact] the exact value
 *   - [ZplLabelLength.CurrentPosition] use [ZplBuilder.verticalPosition]
 */
fun ZplBuilder.labelLength(length: ZplLabelLength) {
    when (length) {
        ZplLabelLength.CurrentPosition -> command { LabelLength(verticalPosition) }
        is ZplLabelLength.Exact -> command(LabelLength(length.value))
    }
}

/**
 * Sets the length of the label by lambda
 * This allows for lazy evaluation of the length.
 * @param length lambda with context of the current [ZplBuilder]
 * that returns an Int.
 */
fun ZplBuilder.labelLength(length: ZplBuilder.() -> ZplLabelLength) {
    command {
        when (val result = length.invoke(this)) {
            ZplLabelLength.CurrentPosition -> verticalPosition
            is ZplLabelLength.Exact -> result.value
        }.let {
            LabelLength(it)
        }
    }
}

