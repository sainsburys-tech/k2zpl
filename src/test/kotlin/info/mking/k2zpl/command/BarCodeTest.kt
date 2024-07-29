package info.mking.k2zpl.command

import info.mking.k2zpl.command.options.ZplBarcodeType
import info.mking.k2zpl.command.options.ZplFieldOrientation
import info.mking.k2zpl.command.options.ZplYesNo
import info.mking.k2zpl.k2zpl
import info.mking.k2zpl.testBuildString
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class BarCodeTest : DescribeSpec({

    isolationMode = IsolationMode.InstancePerLeaf

    val barCode = BarCode(
        type = ZplBarcodeType.CODE_39,
        orientation = ZplFieldOrientation.NORMAL,
        checkDigit = ZplYesNo.NO,
        height = 10,
        line = 7,
        lineAbove = ZplYesNo.YES
    )

    describe("Barcode") {
        it("outputs correct command") {
            val result = barCode.testBuildString()
            result shouldBe "^B1N,N,10,7,Y"
        }
        it("uses orientation parameter properly") {
            ZplFieldOrientation.entries.forEach {
                barCode.copy(orientation = it).testBuildString() shouldBe "^B1${it.code},N,10,7,Y"
            }
        }
        it("uses checkDigit parameter properly") {
            ZplYesNo.entries.forEach {
                barCode.copy(checkDigit = it).testBuildString() shouldBe "^B1N,${it},10,7,Y"
            }
        }
        it("uses lineAbove parameter properly") {
            ZplYesNo.entries.forEach {
                barCode.copy(lineAbove = it).testBuildString() shouldBe "^B1N,N,10,7,${it}"
            }
        }
        it("requires valid parameters") {
            table(
                headers("height", "line"),
                row(32001, 1),
                row(0, 1),
                row(1, 0),
                row(1, 8)
            ).forAll { height, line ->
                shouldThrow<IllegalArgumentException> {
                    barCode.copy(
                        height = height,
                        line = line,
                    )
                }
            }
        }

    }
    describe("barcode extension") {
        it("outputs the correct command") {
            val result = k2zpl {
                barcode(
                    data = "1234567890",
                    x = 10,
                    y = 10,
                    height = 10,
                    lineThickness = 7,
                    barcodeType = ZplBarcodeType.CODE_39,
                    orientation = ZplFieldOrientation.NORMAL,
                    checkDigit = false,
                    lineAbove = true
                )
            }
            result shouldBe """
                ^FO10,10,0
                ^B1N,N,10,7,Y
                ^FD1234567890
                ^FS
                
            """.trimIndent()
        }
        it("uses default values") {
            val result = k2zpl {
                barcode(data = "1234567890", x = 10, y = 10, height = 10, lineThickness = 7)
            }
            result shouldBe """
                ^FO10,10,0
                ^B1N,N,10,7,N
                ^FD1234567890
                ^FS
                
            """.trimIndent()
        }
    }
})
