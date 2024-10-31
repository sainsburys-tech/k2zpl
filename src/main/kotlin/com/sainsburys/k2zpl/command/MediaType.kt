package com.sainsburys.k2zpl.command

import com.sainsburys.k2zpl.builder.ZplBuilder
import com.sainsburys.k2zpl.command.options.ZplMediaType

internal data class MediaType(
    val type: ZplMediaType,
) : ZplCommand {
    override val command = "^MT"
    override val parameters: ZplParameters = zplParameters(
        "t" to type
    )
}

/**
 * Sets the media type for the label.
 * Allows to set thermal vs. ribbon.
 *
 * The ^MT command selects the type of media being used in the printer.
 *
 * @param type The media type of the printer.
 *
 * [ZplMediaType.THERMAL_TRANSFER] – this media uses a high-carbon black or colored ribbon. The ink on the ribbon is bonded to the media.
 *
 * [ZplMediaType.DIRECT_TRANSFER] – this media is heat sensitive and requires no ribbon.
 *
 */
fun ZplBuilder.mediaType(type: ZplMediaType) {
    command(MediaType(type))
}