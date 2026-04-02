package com.example.swingdsl

class MenuBuilder {
    private val menuBar = MenuBar()

    fun menuBar(init: MenuBuilder.() -> Unit) {
        this.init() // Allow the DSL to give access to MenuBuilder
    }

    fun menu(name: String, init: MenuItemBuilder.() -> Unit) {
        val menuItemBuilder = MenuItemBuilder(name)
        menuItemBuilder.init() // Call the init function, allowing further configuration
        menuBar.add(menuItemBuilder.build()) // Build menu item and add to menu bar
    }

    fun build(): MenuBar {
        return menuBar
    }
}

class MenuItemBuilder(private val name: String) {
    private val items = mutableListOf<MenuItem>()

    fun item(name: String, init: MenuItemBuilder.() -> Unit = {}) {
        val subMenuItemBuilder = MenuItemBuilder(name)
        subMenuItemBuilder.init() // Call the init to allow further configuration
        items.add(subMenuItemBuilder.build()) // Build item and add to items list
    }

    fun build(): MenuItem {
        return Menu(name, items) // Return built menu item with nested items
    }
}

class MenuBar {
    private val menus = mutableListOf<MenuItem>()

    fun add(menuItem: MenuItem) {
        menus.add(menuItem) // Add menu item to menu bar
    }

    // Additional functionality for rendering or accessing the menu bar can be added here
}

open class MenuItem(val name: String)
class Menu(name: String, val items: List<MenuItem>) : MenuItem(name)