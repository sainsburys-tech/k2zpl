package com.sainsburys.k2zpl.command

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import io.mockk.verify

class LazyCommandTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerLeaf

    val mockZplCommand = mockk<ZplCommand>(relaxed = true)

    describe("LazyCommand") {
        it("has no command") {
            val lazyCommand = LazyCommand { mockZplCommand }
            lazyCommand.command shouldBe ""
        }
        it("invokes build against lambda") {
            val lambda: () -> ZplCommand = {
                mockZplCommand
            }
            val lazyCommand = LazyCommand(lambda)
            val stringBuilder: StringBuilder = StringBuilder()
            lazyCommand.build(stringBuilder)
            verify { mockZplCommand.build(stringBuilder) }
        }
    }
})