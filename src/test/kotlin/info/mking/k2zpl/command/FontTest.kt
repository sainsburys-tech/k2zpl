package info.mking.k2zpl.command

import info.mking.k2zpl.command.options.ZplFieldOrientation
import info.mking.k2zpl.command.options.ZplFont
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

class FontTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerLeaf

    val font = Font(font = ZplFont.A, orientation = ZplFieldOrientation.NORMAL, height = 30, width = 20)

    describe("Font") {
        it("outputs the correct command") {
            font.testBuildString() shouldBe "^AAN,30,20"
        }
        it("correctly uses font parameter") {
            ZplFont.entries.forEach {
                font.copy(font = it).testBuildString() shouldBe "^A${it}N,30,20"
            }
        }
        it("correctly uses orientation parameter") {
            ZplFieldOrientation.entries.forEach {
                font.copy(orientation = it).testBuildString() shouldBe "^AA${it},30,20"
            }
        }
        it("requires valid parameters") {
            table(
                headers("width", "height"),
                row(9, 10),
                row(32001, 10),
                row(10, 9),
                row(10, 32001)
            ).forAll { width, height ->
                shouldThrow<IllegalArgumentException> {
                    font.copy(width = width, height = height)
                }
            }
        }
    }
    describe("font extension function") {
        it("outputs the correct command") {
            val result = k2zpl {
                font(font = ZplFont.C, orientation = ZplFieldOrientation.INVERTED, height = 20, width = 15)
            }
            result shouldBe "^ACI,20,15\n"
        }
        it("uses default parameter") {
            val result = k2zpl {
                font(font = ZplFont.D, height = 10, width = 10)
            }
            result shouldBe "^ADN,10,10\n"
        }
    }
})