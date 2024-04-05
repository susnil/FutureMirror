package pl.mobilespot.futuremirror.presentation.dashboard

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test

class DashboardScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val unselect = "Unselect"
    private val day = "2"

    @Ignore("todo small test for internal screen testing")
    @Test
    fun `select a day should show unselect button`() {
        composeTestRule.setContent {
            DashboardScreen(DashboardState.raw, {}, {})
        }

        composeTestRule.onNodeWithText(day).performClick()

        composeTestRule.onNodeWithText(unselect).assertIsDisplayed()
    }
}