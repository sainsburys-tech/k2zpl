package info.mking.k2zpl

import info.mking.k2zpl.builder.ZplBuilder
import info.mking.k2zpl.builder.endFormat
import info.mking.k2zpl.builder.startFormat

fun zpl(init: ZplBuilder.() -> Unit) = ZplBuilder().apply(init).build()

fun label(init: ZplBuilder.() -> Unit) = ZplBuilder().apply {
    startFormat()
    init()
    endFormat()
}
