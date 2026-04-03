package  com.example.swingdl

// CustomComponents.kt

interface CustomComponent {
    fun render(): String
}

class SearchBoxComponent : CustomComponent {
    override fun render(): String {
        return "<input type='text' class='search-box'/>"
    }
}

class CardComponent(val title: String, val content: String) : CustomComponent {
    override fun render(): String {
        return "<div class='card'><h2>$title</h2><p>$content</p></div>"
    }
}

class FormFieldComponent(val label: String, val inputType: String) : CustomComponent {
    override fun render(): String {
        return "<label>$label</label><input type='$inputType' class='form-field'/>"
    }
}

class SectionComponent(val title: String, val components: List<CustomComponent>) : CustomComponent {
    override fun render(): String {
        val renderedComponents = components.joinToString(separator = "\n") { it.render() }
        return "<section><h2>$title</h2>$renderedComponents</section>"
    }
}