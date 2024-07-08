package info.mking.k2zpl.command

import info.mking.k2zpl.command.options.ZplMediaMode
import info.mking.k2zpl.command.options.ZplPreprintedLabelHandling

internal data class MediaMode(val mediaMode: ZplMediaMode, val preprintedLabelHandling: ZplPreprintedLabelHandling) :
    ZplCommand {
    override val command: CharSequence = "^MM"
    override val parameters: Map<CharSequence, Any?> =
        mapOf("mode" to mediaMode.value, "preprinted" to preprintedLabelHandling.value)
}

