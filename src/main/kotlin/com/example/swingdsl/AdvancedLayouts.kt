package  com.example.swingdsl

import java.awt.Insets

data class GridConstraints(
    val gridx: Int = 0,
    val gridy: Int = 0,
    val gridwidth: Int = 1,
    val gridheight: Int = 1,
    val weightx: Double = 0.0,
    val weighty: Double = 0.0,
    val anchor: Int = 0,
    val fill: Int = 0,
    val ipadx: Int = 0,
    val ipady: Int = 0,
    val insets: Insets = Insets(0, 0, 0, 0)
)

class FormLayoutBuilder {
    private val constraints = mutableListOf<GridConstraints>()

    fun cell(constraints: GridConstraints.() -> Unit) {
        this.constraints.add(GridConstraints().apply(constraints))
    }

    fun build(): List<GridConstraints> {
        return constraints
    }
}

class ColumnLayoutBuilder {
    private val constraints = mutableListOf<GridConstraints>()

    fun column(constraints: GridConstraints.() -> Unit) {
        this.constraints.add(GridConstraints().apply(constraints))
    }

    fun build(): List<GridConstraints> {
        return constraints
    }
}

class RowLayoutBuilder {
    private val constraints = mutableListOf<GridConstraints>()

    fun row(constraints: GridConstraints.() -> Unit) {
        this.constraints.add(GridConstraints().apply(constraints))
    }

    fun build(): List<GridConstraints> {
        return constraints
    }
}

fun formLayout(init: FormLayoutBuilder.() -> Unit): List<GridConstraints> {
    val builder = FormLayoutBuilder()
    builder.init()
    return builder.build()
}

fun columnLayout(init: ColumnLayoutBuilder.() -> Unit): List<GridConstraints> {
    val builder = ColumnLayoutBuilder()
    builder.init()
    return builder.build()
}

fun rowLayout(init: RowLayoutBuilder.() -> Unit): List<GridConstraints> {
    val builder = RowLayoutBuilder()
    builder.init()
    return builder.build()
}