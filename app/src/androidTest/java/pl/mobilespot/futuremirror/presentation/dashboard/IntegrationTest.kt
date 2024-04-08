package pl.mobilespot.futuremirror.presentation.dashboard

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pl.mobilespot.futuremirror.core.MainActivity

@RunWith(AndroidJUnit4::class)
@LargeTest
class IntegrationTest {
    @get:Rule
    val activityRule = createAndroidComposeRule<MainActivity>()

    private val unselect = "Unselect"
    private val day = "1"

    @Test
    fun `select a day should show unselect button`() {
        activityRule.apply {
            selectDay()
            onNodeWithText(unselect).assertIsDisplayed()
        }
    }

    @Test
    fun `click unselected should hide the button`() {
        activityRule.apply {
            selectDay()

            onNodeWithText(unselect).performClick()
            onNodeWithText(unselect).assertDoesNotExist()
        }
    }

    private fun AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>.selectDay() =
        onNodeWithText(day).performClick()

}