package info.mking.k2zpl.command

import info.mking.k2zpl.command.options.ZplTextAlignment
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

class FieldBlockTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerLeaf

    val fieldBlock = FieldBlock(
        width = 10,
        maxLines = 1,
        lineSpacing = 10,
        alignment = ZplTextAlignment.LEFT,
        hangingIndent = 0
    )

    describe("FieldBlock") {
        it("outputs correct command") {
            fieldBlock.testBuildString() shouldBe "^FB10,1,10,L,0"
        }
        it("requires valid parameters") {
            table(
                headers("width", "maxLines", "lineSpacing", "hangingIndent"),
                row(0, 1, 1, 0),
                row(32001, 1, 1, 0),
                row(1, 0, 1, 0),
                row(1, 10000, 1, 0),
                row(1, 1, -10000, 0),
                row(1, 1, 10000, 0),
                row(1, 1, 1, -1),
                row(1, 1, 1, 10000)
            ).forAll { width, maxLines, lineSpacing, hangingIndent ->
                shouldThrow<IllegalArgumentException> {
                    fieldBlock.copy(
                        width = width,
                        maxLines = maxLines,
                        lineSpacing = lineSpacing,
                        hangingIndent = hangingIndent
                    )
                }
            }
        }
        it("uses alignment parameter correctly") {
            ZplTextAlignment.entries.forEach {
                fieldBlock.copy(alignment = it).testBuildString() shouldBe "^FB10,1,10,$it,0"
            }
        }
    }
    describe("fieldBlock extension function") {
        it("outputs correct command") {
            val result = k2zpl {
                fieldBlock(width = 100, maxLines = 10, lineSpacing = 10, alignment = ZplTextAlignment.CENTER, hangingIndent = 10)
            }
            result shouldBe "^FB100,10,10,C,10\n"
        }
        it("outputs correct command with default parameters") {
            val result = k2zpl {
                fieldBlock(width = 100)
            }
            result shouldBe "^FB100,1,0,L,0\n"
        }
        it("outputs correct command with x, y and data") {
            val result = k2zpl {
                fieldBlock(x = 10, y = 10, width = 640, data = "some-data", maxLines = 1, lineSpacing = 10, alignment = ZplTextAlignment.LEFT)
            }
            result shouldBe """
                ^FO10,10,0
                ^FB640,1,10,L,0
                ^FDsome-data
                ^FS
                
                """.trimIndent()
        }
    }
})