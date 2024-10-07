package com.sainsburys.k2zpl.command.barcode

import com.sainsburys.k2zpl.command.options.ZplBarCode128Mode
import com.sainsburys.k2zpl.command.options.ZplFieldOrientation
import com.sainsburys.k2zpl.command.options.ZplYesNo
import com.sainsburys.k2zpl.k2zpl
import com.sainsburys.k2zpl.testBuildString
import com.sainsburys.k2zpl.toRows
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class BarCode128Test : DescribeSpec({
    isolationMode = IsolationMode.InstancePerLeaf

    val subject = BarCode128(
        orientation = ZplFieldOrientation.NORMAL,
        height = 10,
        line = ZplYesNo.NO,
        lineAbove = ZplYesNo.NO,
        checkDigit = ZplYesNo.NO,
        mode = ZplBarCode128Mode.NONE
    )
    
    describe("BarCode128") {
        it("outputs correct command") {
            val result = subject.testBuildString()
            result shouldBe "^BCN,10,N,N,N,N"
        }
        it("uses orientation parameter properly") {
            table(
                headers("orientation"),
                ZplFieldOrientation.entries.map(::row)
            ).forAll {
                subject.copy(orientation = it).testBuildString() shouldBe "^BC${it.code},10,N,N,N,N"
            }
        }
        it("uses height parameter properly") {
            subject.copy(height = 100).testBuildString() shouldBe "^BCN,100,N,N,N,N"
        }
        it("uses line parameter properly") {
            table(headers("line"), ZplYesNo.entries.toRows()).forAll {
                subject.copy(line = it).testBuildString() shouldBe "^BCN,10,${it},N,N,N"
            }
        }
        it("uses lineAbove parameter properly") {
            table(headers("lineAbove"), ZplYesNo.entries.toRows()).forAll {
                subject.copy(lineAbove = it).testBuildString() shouldBe "^BCN,10,N,${it},N,N"
            }
        }
        it("uses checkDigit parameter properly") {
            table(headers("checkDigit"), ZplYesNo.entries.toRows()).forAll {
                subject.copy(checkDigit = it).testBuildString() shouldBe "^BCN,10,N,N,${it},N"
            }
        }
        it("uses mode parameter properly") {
            table(headers("mode"), ZplBarCode128Mode.entries.toRows()).forAll {
                subject.copy(mode = it).testBuildString() shouldBe "^BCN,10,N,N,N,${it}"
            }
        }
        it("requires valid parameters") {
            table(
                headers("height"),
                row(32001),
                row(0),
            ).forAll { height ->
                shouldThrow<IllegalArgumentException> {
                    subject.copy(
                        height = height
                    )
                }
            }
        }
    }
    describe("barcode128 extension") {
        it("outputs the correct command") {
            val result = k2zpl {
                barcode128(
                    data = "1234567890",
                    x = 10,
                    y = 10,
                    height = 10,
                    checkDigit = true,
                    lineAbove = true,
                    interpretationLine = true,
                    mode = ZplBarCode128Mode.UCC
                )
            }
            result shouldBe """
                ^FO10,10,0
                ^BCN,10,Y,Y,Y,U
                ^FD1234567890
                ^FS
                
            """.trimIndent()
        }
        it("uses default values") {
            val result = k2zpl {
                barcode128(data = "1234567890", x = 10, y = 10, height = 10)
            }
            result shouldBe """
                ^FO10,10,0
                ^BCN,10,N,N,N,N
                ^FD1234567890
                ^FS
                
            """.trimIndent()
        }
    }
})