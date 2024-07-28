package info.mking.k2zpl.command

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

class PrintWidthTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerLeaf

    val printWidth = PrintWidth(
        width = 640
    )

    describe("PrintWidth") {
        it("outputs correct command") {
            printWidth.testBuildString() shouldBe "^PW640"
        }
        it("requires valid parameters") {
            table(headers("width"),
                row(0),
                row(32001)
            ).forAll {
                shouldThrow<IllegalArgumentException> {
                    printWidth.copy(width = it)
                }
            }
        }
    }
    describe("printWidth extension function") {
        it("outputs the correct command") {
            val result = k2zpl {
                printWidth(640)
            }
            result shouldBe "^PW640\n"
        }
    }
})