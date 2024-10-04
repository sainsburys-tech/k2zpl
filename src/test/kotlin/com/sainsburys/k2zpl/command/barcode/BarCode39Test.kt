package com.sainsburys.k2zpl.command.barcode

import com.sainsburys.k2zpl.command.options.ZplFieldOrientation
import com.sainsburys.k2zpl.command.options.ZplYesNo
import com.sainsburys.k2zpl.k2zpl
import com.sainsburys.k2zpl.testBuildString
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class BarCode39Test : DescribeSpec({

    isolationMode = IsolationMode.InstancePerLeaf

    val subject = BarCode39(
        orientation = ZplFieldOrientation.NORMAL,
        checkDigit = ZplYesNo.NO,
        height = 10,
        line = ZplYesNo.NO,
        lineAbove = ZplYesNo.NO
    )

    describe("Barcode39") {
        it("outputs correct command") {
            val result = subject.testBuildString()
            result shouldBe "^B3N,N,10,N,N"
        }
        it("uses orientation parameter properly") {
            ZplFieldOrientation.entries.forEach {
                subject.copy(orientation = it).testBuildString() shouldBe "^B3${it.code},N,10,N,N"
            }
        }
        it("uses height parameter properly") {
            subject.copy(height = 100).testBuildString() shouldBe "^B3N,N,100,N,N"
        }
        it("uses checkDigit parameter properly") {
            ZplYesNo.entries.forEach {
                subject.copy(checkDigit = it).testBuildString() shouldBe "^B3N,${it},10,N,N"
            }
        }
        it("uses line parameter properly") {
            ZplYesNo.entries.forEach {
                subject.copy(line = it).testBuildString() shouldBe "^B3N,N,10,${it},N"
            }
        }
        it("uses lineAbove parameter properly") {
            ZplYesNo.entries.forEach {
                subject.copy(lineAbove = it).testBuildString() shouldBe "^B3N,N,10,N,${it}"
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
    describe("barcode39 extension") {
        it("outputs the correct command") {
            val result = k2zpl {
                barcode39(
                    data = "1234567890",
                    x = 10,
                    y = 10,
                    height = 10,
                    checkDigit = false,
                    lineAbove = true
                )
            }
            result shouldBe """
                ^FO10,10,0
                ^B3N,N,10,N,Y
                ^FD1234567890
                ^FS
                
            """.trimIndent()
        }
        it("uses default values") {
            val result = k2zpl {
                barcode39(data = "1234567890", x = 10, y = 10, height = 10, interpretationLine = true)
            }
            result shouldBe """
                ^FO10,10,0
                ^B3N,N,10,Y,N
                ^FD1234567890
                ^FS
                
            """.trimIndent()
        }
    }
})
