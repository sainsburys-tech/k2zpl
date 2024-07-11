@file:Suppress("UNUSED")

package info.mking.k2zpl

import info.mking.k2zpl.builder.ZplBuilder

fun k2zpl(init: ZplBuilder.() -> Unit) = ZplBuilder().apply(init).build()
