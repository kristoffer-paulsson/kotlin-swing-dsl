package com.example.swingdsl

import java.awt.*
import javax.swing.*

class PanelBuilder(private val parent: Container) {
    private val layout = GridBagLayout()
    private val constraints = GridBagConstraints()

    init {
        parent.layout = layout
    }

    fun addComponent(component: Component, gridX: Int, gridY: Int, gridWidth: Int = 1, gridHeight: Int = 1) {
        constraints.gridx = gridX
        constraints.gridy = gridY
        constraints.gridwidth = gridWidth
        constraints.gridheight = gridHeight
        layout.setConstraints(component, constraints)
        parent.add(component)
    }
}