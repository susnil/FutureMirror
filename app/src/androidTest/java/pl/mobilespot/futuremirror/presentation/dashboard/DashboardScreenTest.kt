package pl.mobilespot.futuremirror.presentation.dashboard

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class DashboardScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun myTest() {
        composeTestRule.setContent {
            DashboardScreen(DashboardState.raw)
        }

        composeTestRule.onNodeWithText("1").performClick()

        composeTestRule.onNodeWithText("1").assertIsDisplayed()
    }
}