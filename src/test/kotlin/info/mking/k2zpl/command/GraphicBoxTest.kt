package info.mking.k2zpl.command

import info.mking.k2zpl.command.options.ZplLineColor
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

class GraphicBoxTest : DescribeSpec({

    isolationMode = IsolationMode.InstancePerTest

    describe("GraphicBox") {
        it("outputs correct command") {
            val graphicBox = GraphicBox(
                width = 100,
                height = 50,
                thickness = 2
            )
            val result = graphicBox.testBuildString()
            result shouldBe "^GB100,50,2,B,0"
        }
        it("updates color option") {
            val table = table(
                headers("color"),
                ZplLineColor.entries.map { row(it) }
            )
            table.forAll {
                val graphicBox = GraphicBox(
                    width = 100,
                    height = 50,
                    thickness = 2,
                    color = it
                )
                graphicBox.testBuildString() shouldBe "^GB100,50,2,${it.code},0"
            }
        }
        it("adds rounding option") {
            val graphicBox = GraphicBox(
                width = 100,
                height = 50,
                thickness = 2,
                rounding = 5
            )
            graphicBox.testBuildString() shouldBe "^GB100,50,2,B,5"
        }
        it("requires valid parameters") {
            table(
                headers("width", "height", "thickness", "rounding"),
                row(0, 1, 1, 1),
                row(32001, 1, 1, 1),
                row(1, 0, 1, 1),
                row(1, 32001, 1, 1),
                row(1, 1, 0, 1),
                row(1, 1, 32001, 1),
                row(1, 1, 1, -1),
                row(1, 1, 1, 9)
            ).forAll { width, height, thickness, rounding ->
                shouldThrow<IllegalArgumentException> {
                    GraphicBox(
                        width = width,
                        height = height,
                        thickness = thickness,
                        rounding = rounding
                    )
                }
            }
        }
    }
    describe("graphicBox extension") {
        it("has correct minimum argument output") {
            val result = k2zpl {
                graphicBox(100, 50)
            }
            result shouldBe "^GB100,50,1,B,0\n"
        }
        it("uses all passed arguments in output") {
            val result = k2zpl {
                graphicBox(100, 50, 10, ZplLineColor.WHITE, 3)
            }
            result shouldBe "^GB100,50,10,W,3\n"
        }
    }
    describe("line extension") {
        it("has correct minimum argument output") {
            val result = k2zpl {
                line(1000, 1)
            }
            result shouldBe "^GB1000,1,1,B,0\n"
        }
        it("uses all passed arguments in output") {
            val result = k2zpl {
                line(1000, 10, ZplLineColor.WHITE)
            }
            result shouldBe "^GB1000,10,10,W,0\n"
        }
        it("uses x and y parameters") {
            val result = k2zpl {
                line(x = 10, y = 100, width = 1000, thickness = 1)
            }
            result shouldBe "^FO10,100,0\n^GB1000,1,1,B,0\n^FS\n"
        }
    }
})