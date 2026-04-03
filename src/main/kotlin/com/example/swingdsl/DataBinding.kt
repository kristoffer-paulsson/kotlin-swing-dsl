package com.example.swingdsl

// Represents a bound property that can notify listeners about changes.
class BoundProperty<T>(private var value: T) {
    private val listeners = mutableListOf<(T) -> Unit>()

    var property: T
        get() = value
        set(newValue) {
            value = newValue
            notifyListeners() // Notify listeners about the change.
        }

    fun bind(listener: (T) -> Unit) {
        listeners.add(listener)
    }

    private fun notifyListeners() {
        for (listener in listeners) {
            listener(value)
        }
    }
}

// Handles the binding between two properties.
class PropertyBinding<T>(private val source: BoundProperty<T>, private val target: BoundProperty<T>) {
    init {
        source.bind { target.property = it } // Update target when source changes.
        target.bind { source.property = it } // Update source when target changes (two-way binding).
    }
}

// Represents the data model containing properties that can be bound to UI components.
class DataModel { 
    val name = BoundProperty("")
    val age = BoundProperty(0)
    // Add other properties as needed.
}

// Represents a bound component such as a JTextField that can be bound to a property.
class BoundComponent<T>(private val property: BoundProperty<T>, private val updateUI: (T) -> Unit) {
    init {
        property.bind { updateUI(it) } // Update UI when property changes.
    }
}

// Usage example:
/*
val model = DataModel()
val nameField = BoundComponent(model.name) { newValue -> println("Name updated: $newValue") }
val ageField = BoundComponent(model.age) { newValue -> println("Age updated: $newValue") }

model.name.property = "Kristoffer"
model.age.property = 30
*/
