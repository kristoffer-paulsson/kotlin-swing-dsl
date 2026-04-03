package com.example.swingdsl

import javax.swing.*
import kotlin.test.Test


public object MenuEngine {
    public fun build(menuBar: MenuBar): JMenuBar {
        return JMenuBar().apply {
            menuBar.sub.forEach { menuBarItem ->
                add(JMenu(menuBarItem.name).apply {
                    menuBarItem.sub.forEach { menuItem ->
                        when (menuItem.name) {
                            "---" -> add(JSeparator())
                            else -> add(JMenuItem(menuItem.name))
                        }
                    }
                })
            }
        }
    }
}

interface ActionComp {
    var onAction: () -> (Unit)
}

interface NamedComp {
    var name: String
}

interface HierarchyComp<E> {
    var sub: MutableList<E>
    fun add(thing: E) { sub.add(thing) }
}

data class MenuItem(
    override var name: String = "",
    override var onAction: () -> Unit = {}
) : NamedComp, ActionComp

data class MenuBarItem(
    override var name: String = "",
    override var sub: MutableList<MenuItem> = mutableListOf()
) : NamedComp, HierarchyComp<MenuItem>

data class MenuBar(override var sub: MutableList<MenuBarItem> = mutableListOf()) : HierarchyComp<MenuBarItem>

fun MenuBarItem.separator() = item("---")
fun MenuBarItem.item(name: String) = item { this.name = name}
fun MenuBarItem.item(name: String, onAction: () -> Unit) = item {
    this.name = name
    this.onAction = onAction
}
fun MenuBarItem.item(setup: MenuItem.() -> Unit) = add(MenuItem().apply(setup))
fun MenuBar.menu(name: String, setup: MenuBarItem.() -> Unit) {
    menu {
        setup()
        this.name = name
    }
}
fun MenuBar.menu(setup: MenuBarItem.() -> Unit) = add(MenuBarItem().apply(setup))
fun menuBar(setup: MenuBar.() -> Unit): MenuBar {
    return MenuBar().apply(setup)
}

class App : JFrame() {
    init {
        setTitle("Example")
        setDefaultCloseOperation(EXIT_ON_CLOSE)

        jMenuBar = MenuEngine.build(implMenuBar())

        extendedState = MAXIMIZED_BOTH

        setLocationRelativeTo(null)
        setDefaultLookAndFeelDecorated(true)
        setVisible(true)
    }

    private fun implMenuBar() = menuBar {
        menu("File") {
            item("New")
            item("Open")
            item("Save")
            separator()
            item("Close") {
                this@App.dispose()
            }
        }
        menu("Edit") {
            item("Cut")
            item("Copy")
            item("Paste")
        }
         menu("Help") {
             item("About")
         }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SwingUtilities.invokeLater(Runnable { App() })
        }
    }
}

class SwingDslTest {
    @Test
    fun testSwingDsl() {

    }
}