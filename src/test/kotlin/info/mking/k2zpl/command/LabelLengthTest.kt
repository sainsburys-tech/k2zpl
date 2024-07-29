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
    }
})