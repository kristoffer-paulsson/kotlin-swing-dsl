import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DataBindingTest {

    private lateinit var property: BoundProperty<String>

    @BeforeEach
    fun setUp() {
        property = BoundProperty("Initial Value")
    }

    @Test
    fun testBoundPropertyCreation() {
        assertEquals("Initial Value", property.value)
    }

    @Test
    fun testSetValue() {
        property.setValue("New Value")
        assertEquals("New Value", property.value)
    }

    @Test
    fun testListenerFunctionality() {
        var listenerCalled = false
        property.addListener { listenerCalled = true }
        property.setValue("Another Value")
        assertEquals(true, listenerCalled)
    }

    @Test
    fun testMultipleListeners() {
        var firstListenerCalled = false
        var secondListenerCalled = false
        property.addListener { firstListenerCalled = true }
        property.addListener { secondListenerCalled = true }
        property.setValue("New Value Again")
        assertEquals(true, firstListenerCalled)
        assertEquals(true, secondListenerCalled)
    }
}