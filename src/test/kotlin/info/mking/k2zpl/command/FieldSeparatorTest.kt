package info.mking.k2zpl.command

import info.mking.k2zpl.k2zpl
import info.mking.k2zpl.testBuildString
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class FieldSeparatorTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerLeaf

    describe("FieldSeparator") {
        it("outputs correct command") {
            FieldSeparator.testBuildString() shouldBe "^FS"
        }
    }
    describe("fieldSeparator extension function") {
        it("outputs correct command") {
            val result = k2zpl {
                fieldSeparator()
            }
            result shouldBe "^FS\n"
        }
    }
})