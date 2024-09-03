import com.sainsburys.k2zpl.command.ZplCommand

/**
 * Allow for lazy evaluation of a ZplCommand. This works by
 * passing in a block that will be executed when [build] is called.
 */
internal class LazyCommand(private val commandBlock: () -> ZplCommand) : ZplCommand {
    override val command: CharSequence get() = ""

    override fun build(stringBuilder: StringBuilder): StringBuilder {
        return commandBlock.invoke().build(stringBuilder)
    }
}