@file:Suppress("UNUSED")

package com.sainsburys.k2zpl

import com.sainsburys.k2zpl.builder.ZplBuilder

fun k2zpl(init: ZplBuilder.() -> Unit) = ZplBuilder().apply(init).build()
