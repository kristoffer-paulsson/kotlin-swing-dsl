import org.junit.jupiter.api.Test
import kotlin.test.assertFails
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ValidationTest {

    @Test
    fun `test notEmpty validator`() {
        val notEmpty = { value: String -> value.isNotEmpty() }
        assertTrue(notEmpty("Hello"))
        assertFails { notEmpty("") }
    }

    @Test
    fun `test email validator`() {
        val email = { value: String -> value.matches(Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$")) }
        assertTrue(email("test@example.com"))
        assertFails { email("invalid-email") }
    }

    @Test
    fun `test lengthBetween validator`() {
        val lengthBetween = { value: String, min: Int, max: Int -> value.length in min..max }
        assertTrue(lengthBetween("Hello", 1, 10))
        assertFails { lengthBetween("Hi", 3, 5) }
    }

    @Test
    fun `test rangeInt validator`() {
        val rangeInt = { value: Int, min: Int, max: Int -> value in min..max }
        assertTrue(rangeInt(5, 1, 10))
        assertFails { rangeInt(15, 1, 10) }
    }

    @Test
    fun `test positive validator`() {
        val positive = { value: Int -> value > 0 }
        assertTrue(positive(1))
        assertFails { positive(-1) }
    }

    @Test
    fun `test pattern validator`() {
        val pattern = { value: String, regex: Regex -> value.matches(regex) }
        assertTrue(pattern("abc123", Regex("^[a-zA-Z0-9]+$")))
        assertFails { pattern("abc!123", Regex("^[a-zA-Z0-9]+$")) }
    }
}
