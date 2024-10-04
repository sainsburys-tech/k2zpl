package com.sainsburys.k2zpl.command.barcode

import com.sainsburys.k2zpl.command.ZplCommand
import com.sainsburys.k2zpl.command.options.ZplFieldOrientation

internal abstract class BarCode : ZplCommand {
    abstract val orientation: ZplFieldOrientation
    abstract val height: Int
}