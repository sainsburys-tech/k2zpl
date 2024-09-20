package com.sainsburys.k2zpl.command

import com.sainsburys.k2zpl.builder.ZplBuilder
import com.sainsburys.k2zpl.builder.toZplYesNo
import com.sainsburys.k2zpl.command.options.ZplMediaMode
import com.sainsburys.k2zpl.command.options.ZplYesNo

internal data class MediaMode(
    val mediaMode: ZplMediaMode,
    val prePeelSelect: ZplYesNo
) : ZplCommand(
    parameters = listOf("m" to mediaMode, "p" to prePeelSelect)
) {
    override val command: CharSequence = "^MM"
}

/**
 * Sets Media Mode
 */
fun ZplBuilder.mediaMode(
    mediaMode: ZplMediaMode,
    prePeel: Boolean = false
) {
    command(
        MediaMode(
            mediaMode = mediaMode,
            prePeelSelect = prePeel.toZplYesNo()
        )
    )
}
