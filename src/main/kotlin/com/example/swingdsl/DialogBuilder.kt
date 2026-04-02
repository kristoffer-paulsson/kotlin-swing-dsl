package com.example.swingdsl

sealed class DialogResult {
    object Confirmed : DialogResult()
    object Canceled : DialogResult()
    data class Input(val value: String) : DialogResult()
}

class DialogBuilder {
    fun message(text: String) {
        // Implementation for displaying a message dialog
    }

    fun infoDialog(text: String) {
        // Implementation for displaying an info dialog
    }

    fun warningDialog(text: String) {
        // Implementation for displaying a warning dialog
    }

    fun errorDialog(text: String) {
        // Implementation for displaying an error dialog
    }

    fun confirmDialog(text: String): DialogResult {
        // Implementation for displaying a confirmation dialog
        return DialogResult.Confirmed // Placeholder return
    }

    fun inputDialog(prompt: String): DialogResult {
        // Implementation for displaying an input dialog
        return DialogResult.Input("User Input") // Placeholder return
    }
}

fun dialogBuilder(block: DialogBuilder.() -> Unit) {
    val builder = DialogBuilder()
    builder.block()
}