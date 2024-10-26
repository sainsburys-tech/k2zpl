package com.sainsburys.k2zpl.command

import com.sainsburys.k2zpl.command.options.ZplLabelLength
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

class LabelLengthTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerLeaf

    val labelLength = LabelLength(100)

    describe("LabelLength") {
        it("outputs correct command") {
            labelLength.testBuildString() shouldBe "^LL100"
        }
        it("requires valid parameters") {
            table(
                headers("length"),
                row(0),
                row(32001)
            ).forAll {
                shouldThrow<IllegalArgumentException> {
                    labelLength.copy(length = it)
                }
            }
        }
    }
    describe("labelLength extension function") {
        it("outputs correct command") {
            val result = k2zpl {
                labelLength(1000)
            }
            result shouldBe "^LL1000\n"
        }
        it("outputs correct command when using Exact") {
            val result = k2zpl {
                labelLength(ZplLabelLength.Exact(100))
            }
            result shouldBe "^LL100\n"
        }
        it("outputs correct command when using lambda Exact") {
            var size = 100
            val result = k2zpl {
                labelLength {
                    ZplLabelLength.Exact(size)
                }
                size += 100
            }
            result shouldBe "^LL200\n"
        }
        it("outputs correct command when using CurrentPosition") {
            val result = k2zpl {
                labelLength(ZplLabelLength.CurrentPosition)
                advancePosition(200)
            }
            result shouldBe "^LL200\n"
        }
        it("outputs correct command when using CurrentPosition with multiple advancePosition") {
            val result = k2zpl {
                advancePosition(200)
                labelLength(ZplLabelLength.CurrentPosition)
                advancePosition(200)
            }
            result shouldBe "^LL400\n"
        }
        it("outputs correct command when using lambda CurrentPosition") {
            val result = k2zpl {
                labelLength { ZplLabelLength.CurrentPosition }
                advancePosition(200)
            }
            result shouldBe "^LL200\n"
        }
    }
})