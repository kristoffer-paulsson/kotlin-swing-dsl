import javax.swing.*

class ToolbarBuilder {
    private val toolbar = JToolBar()

    fun button(name: String, action: () -> Unit) {
        val button = JButton(name)
        button.addActionListener { action() }
        toolbar.add(button)
    }

    fun separator() {
        toolbar.add(JSeparator())
    }

    fun spacer() {
        toolbar.add(Box.createHorizontalGlue())
    }

    fun build(): JToolBar {
        return toolbar
    }
}

fun toolbar(builderAction: ToolbarBuilder.() -> Unit): JToolBar {
    val builder = ToolbarBuilder()
    builder.builderAction()
    return builder.build()
}