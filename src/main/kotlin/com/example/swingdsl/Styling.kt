package com.example.swingdsl

import javax.swing.*
import java.awt.*

// Color schemes
sealed class ColorScheme(val primary: Color, val secondary: Color, val background: Color)
object LightColorScheme : ColorScheme(Color(255, 255, 255), Color(0, 0, 0), Color(240, 240, 240))
object DarkColorScheme : ColorScheme(Color(50, 50, 50), Color(200, 200, 200), Color(30, 30, 30))

// Font schemes
sealed class FontScheme(val titleFont: Font, val bodyFont: Font)
object LightFontScheme : FontScheme(Font("Arial", Font.PLAIN, 12), Font("Arial", Font.ITALIC, 12))
object DarkFontScheme : FontScheme(Font("Arial", Font.PLAIN, 12), Font("Arial", Font.BOLD, 12))

// Sizing schemes
sealed class SizingScheme(val padding: Int, val margin: Int)
object LightSizingScheme : SizingScheme(5, 10)
object DarkSizingScheme : SizingScheme(10, 5)

// Theme data class
data class Theme(val colorScheme: ColorScheme, val fontScheme: FontScheme, val sizingScheme: SizingScheme)

// Light and dark theme functions
fun createLightTheme(): Theme {
    return Theme(LightColorScheme, LightFontScheme, LightSizingScheme)
}

fun createDarkTheme(): Theme {
    return Theme(DarkColorScheme, DarkFontScheme, DarkSizingScheme)
}

// ThemeManager singleton
object ThemeManager {
    private var currentTheme: Theme = createLightTheme()

    fun setTheme(theme: Theme) {
        currentTheme = theme
    }

    fun getTheme(): Theme = currentTheme
}

// ComponentStyleBuilder
class ComponentStyleBuilder {
    private val styles = mutableMapOf<JComponent, Theme>()

    fun style(component: JComponent, theme: Theme) {
        styles[component] = theme
        applyStyles(component)
    }

    private fun applyStyles(component: JComponent) {
        val theme = styles[component] ?: return
        component.background = theme.colorScheme.background
        component.font = theme.fontScheme.bodyFont
        // Additional styling logic here
    }
}

// componentStyle DSL function
fun JComponent.componentStyle(init: ComponentStyleBuilder.() -> Unit) {
    val builder = ComponentStyleBuilder()
    init(builder)
}    
