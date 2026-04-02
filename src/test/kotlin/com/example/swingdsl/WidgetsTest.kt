import javax.swing.*
import kotlin.test.*

class WidgetsTest {

    @Test
    fun testJTextFieldCreation() {
        val textField = JTextField("Sample Text")
        assertEquals("Sample Text", textField.text)
        assertEquals(20, textField.columns)
    }

    @Test
    fun testJButtonCreation() {
        val button = JButton("Click Me")
        assertEquals("Click Me", button.text)
    }

    @Test
    fun testJCheckBoxCreation() {
        val checkBox = JCheckBox("Accept Terms")
        assertEquals("Accept Terms", checkBox.text)
        assertFalse(checkBox.isSelected)
        checkBox.isSelected = true
        assertTrue(checkBox.isSelected)
    }

    @Test
    fun testJComboBoxCreation() {
        val comboBox = JComboBox(arrayOf("Option 1", "Option 2"))
        assertEquals("Option 1", comboBox.selectedItem)
        comboBox.selectedIndex = 1
        assertEquals("Option 2", comboBox.selectedItem)
    }

    @Test
    fun testJLabelCreation() {
        val label = JLabel("This is a label")
        assertEquals("This is a label", label.text)
        assertEquals(JLabel.CENTER, label.horizontalAlignment)
    }
}