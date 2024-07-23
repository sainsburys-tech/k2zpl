package info.mking.k2zpl.command

import info.mking.k2zpl.testBuildString
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class ZplCommandTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    describe("build") {
        it("outputs command") {
            ZplCommandWithoutParameters().testBuildString() shouldBe "^ZC"
        }
        it("outputs command with one parameter") {
            ZplCommandWithOneParameter().testBuildString() shouldBe "^ZCPvalue-one"
        }
        it("outputs command with two parameters") {
            ZplCommandWithMultipleParameters().testBuildString() shouldBe "^ZCPSvalue-one,value-two"
        }
    }
})

class ZplCommandWithoutParameters : ZplCommand {
    override val command = "^ZC"
}

class ZplCommandWithOneParameter : ZplCommand {
    override val command = "^ZCP"
    override val parameters: LinkedHashMap<CharSequence, Any?> = buildLinkedMap {
        putAll(mapOf("param-one" to "value-one"))
    }
}

class ZplCommandWithMultipleParameters : ZplCommand {
    override val command = "^ZCPS"
    override val parameters: LinkedHashMap<CharSequence, Any?> = buildLinkedMap {
        putAll(mapOf("param-one" to "value-one", "param-two" to "value-two"))
    }
}