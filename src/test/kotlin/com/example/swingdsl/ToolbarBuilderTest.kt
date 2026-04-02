import org.junit.jupiter.api.Test
import javax.swing.JButton
import javax.swing.JToolBar
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ToolbarBuilderTest {

    @Test
    fun `test ToolbarBuilder creation`() {
        val toolbarBuilder = ToolbarBuilder()
        assertNotNull(toolbarBuilder)
    }

    @Test
    fun `test toolbar DSL build`() {
        val toolbar = toolbar {}
        assertNotNull(toolbar)
    }

    @Test
    fun `test button addition`() {
        val toolbar = toolbar {
            button("Button 1")
            button("Button 2")
        }
        assertEquals(toolbar.componentCount, 2)
    }

    @Test
    fun `test separator functionality`() {
        val toolbar = toolbar {
            button("Button 1")
            separator()
            button("Button 2")
        }
        assertEquals(toolbar.componentCount, 3)
    }

    @Test
    fun `test spacer functionality`() {
        val toolbar = toolbar {
            button("Button 1")
            spacer()
            button("Button 2")
        }
        assertEquals(toolbar.componentCount, 3)
        assertEquals(toolbar.components[1].preferredSize.width, 20) // Assuming spacer width is set to 20
    }
}
