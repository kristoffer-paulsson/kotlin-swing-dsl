import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import com.example.swingdsl.DialogBuilder
import com.example.swingdsl.DialogResult

class DialogBuilderTest {

    @Test
    fun `test DialogBuilder creation`() {
        val dialogBuilder = DialogBuilder()
        assertNotNull(dialogBuilder, "DialogBuilder should be created successfully")
    }

    @Test
    fun `test DialogResult Confirmed`() {
        val result = DialogResult.Confirmed
        assertEquals(DialogResult.Confirmed, result, "Result should be Confirmed")
    }

    @Test
    fun `test DialogResult Canceled`() {
        val result = DialogResult.Canceled
        assertEquals(DialogResult.Canceled, result, "Result should be Canceled")
    }

    @Test
    fun `test DialogResult Input`() {
        val inputResult = DialogResult.Input("user input")
        assertEquals(DialogResult.Input("user input"), inputResult, "Result should be Input with the provided value")
    }

    @Test
    fun `test message setting`() {
        val dialogBuilder = DialogBuilder()
        dialogBuilder.setMessage("This is a test message")
        assertEquals("This is a test message", dialogBuilder.getMessage(), "The message should be set correctly")
    }
}
