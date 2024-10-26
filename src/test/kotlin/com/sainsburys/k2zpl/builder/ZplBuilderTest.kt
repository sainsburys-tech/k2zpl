package com.sainsburys.k2zpl.builder

import com.sainsburys.k2zpl.command.ZplCommand
import com.sainsburys.k2zpl.command.options.ZplDpiSetting
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import io.mockk.verify
import kotlin.math.roundToInt

class ZplBuilderTest : DescribeSpec({

    isolationMode = IsolationMode.InstancePerTest
    
    val mockZplCommand: ZplCommand = mockk(relaxed = true)

    val subject = ZplBuilder()

    describe("addCommand") {
        it("should call build on the passed command") {
            subject.command(mockZplCommand)
            subject.build()
            verify { mockZplCommand.build(ofType()) }
        }
        it("should used the pass string command") {
            subject.command("another-command")
            subject.build() shouldBe "another-command\n"
        }
        it("should add lambda command") {
            subject.command {
                mockZplCommand
            }
            subject.build()
            verify { mockZplCommand.build(ofType()) }
        }
    }
    describe("dpiSetting") {
        it("throws an appropriate exception when Unset") {
            shouldThrow<IllegalStateException> { subject.dpiSetting }
        }
        it("accepts the value set") {
            subject.dpiSetting = ZplDpiSetting.DPI_203
            subject.dpiSetting shouldBe ZplDpiSetting.DPI_203
        }
    }
    describe("advancePosition") {
        it("defaults to zero") {
            subject.verticalPosition shouldBe 0
        }
        it("adds value to the vertical position") {
            subject.advancePosition(100)
            subject.verticalPosition shouldBe 100
        }
        it("adds cm value to the vertical position") {
            subject.verticalPosition shouldBe 100
        }
    }
    describe("Int mm extension") {
        it("throws an appropriate exception when no ZplDpiSetting") {
            shouldThrow<IllegalStateException> {
                subject.apply { 1.mm }
            }
        }
        it("translates the correct amount to dots") {
            val table = table(
                headers("dpi", "num", "expected"),
                (ZplDpiSetting.entries - ZplDpiSetting.Unset).flatMap { dpi ->
                    (1..10).map { row(dpi, it, (it * dpi.dotsPerMm).roundToInt()) }
                }
            )
            subject.apply {
                table.forAll { zplDpiSetting, num, expected ->
                    subject.dpiSetting = zplDpiSetting
                    num.mm shouldBe expected
                }
            }
        }
    }
    describe("Int cm extension") {
        val table = table(
            headers("dpi", "num", "expected"),
            (ZplDpiSetting.entries - ZplDpiSetting.Unset).flatMap { dpi ->
                (1..10).map { row(dpi, it, (it * 10 * dpi.dotsPerMm).roundToInt()) }
            }
        )
        it("throws an appropriate exception when no ZplDpiSetting") {
            shouldThrow<IllegalStateException> {
                subject.apply { 1.cm }
            }
        }
        it("translates the correct amount to dots") {
            subject.apply {
                table.forAll { zplDpiSetting, num, expected ->
                    subject.dpiSetting = zplDpiSetting
                    num.cm shouldBe expected
                }
            }
        }
    }
    describe("Int inches extension") {
        it("throws an appropriate exception when no ZplDpiSetting") {
            shouldThrow<IllegalStateException> {
                subject.apply { 1.inches }
            }
        }
        it("translates the correct amount to dots") {
            val table = table(
                headers("dpi", "num", "expected"),
                (ZplDpiSetting.entries - ZplDpiSetting.Unset).flatMap { dpi ->
                    (1..10).map { row(dpi, it, it * dpi.dpi) }
                }
            )
            subject.apply {
                table.forAll { zplDpiSetting, num, expected ->
                    subject.dpiSetting = zplDpiSetting
                    num.inches shouldBe expected
                }
            }
        }
    }

})