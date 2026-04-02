import javax.swing.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class MenuBuilderTest {

    @Test
    fun testMenuCreation() {
        val menu = createMenu("File")
        assertNotNull(menu)
        assertEquals("File", menu.text)
    }

    @Test
    fun testMenuItemAddition() {
        val menu = createMenu("File")
        addItemToMenu(menu, "Open")
        assertEquals(1, menu.itemCount)
        assertEquals("Open", menu.getItem(0)?.text)
    }

    @Test
    fun testSubmenuCreation() {
        val menu = createMenu("File")
        val submenu = createMenu("Recent")
        addSubmenuToMenu(menu, submenu)
        assertEquals(1, menu.itemCount)
        assertEquals("Recent", menu.getItem(0)?.text)
    }

    @Test
    fun testSeparatorAddition() {
        val menu = createMenu("File")
        addItemToMenu(menu, "Open")
        addSeparatorToMenu(menu)
        addItemToMenu(menu, "Exit")
        assertEquals(3, menu.itemCount)
        assertEquals(JSeparator::class.java, menu.getItem(1)?.javaClass)
    }

    private fun createMenu(name: String): JMenu {
        return JMenu(name)
    }

    private fun addItemToMenu(menu: JMenu, itemName: String) {
        menu.add(JMenuItem(itemName))
    }

    private fun addSubmenuToMenu(menu: JMenu, submenu: JMenu) {
        menu.add(submenu)
    }

    private fun addSeparatorToMenu(menu: JMenu) {
        menu.add(JSeparator())
    }
}