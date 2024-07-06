package command

import command.options.ZplMediaMode
import command.options.ZplPreprintedLabelHandling

internal data class MediaMode(val mediaMode: ZplMediaMode, val preprintedLabelHandling: ZplPreprintedLabelHandling) :
    ZplCommand {
    override val command: String = "^MM"
    override val parameters: Map<String, Any?> =
        mapOf("mode" to mediaMode.value, "preprinted" to preprintedLabelHandling.value)
}

