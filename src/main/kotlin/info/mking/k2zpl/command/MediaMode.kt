package info.mking.k2zpl.command

import info.mking.k2zpl.builder.ZplBuilder
import info.mking.k2zpl.builder.command
import info.mking.k2zpl.builder.toZplYesNo
import info.mking.k2zpl.command.options.ZplMediaMode
import info.mking.k2zpl.command.options.ZplYesNo

internal data class MediaMode(
    val mediaMode: ZplMediaMode,
    val prePeelSelect: ZplYesNo
) : ZplCommand {
    override val command: CharSequence = "^MM"
    override val parameters: LinkedHashMap<CharSequence, Any?> =
        linkedMapOf("m" to mediaMode.value, "p" to prePeelSelect)
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
