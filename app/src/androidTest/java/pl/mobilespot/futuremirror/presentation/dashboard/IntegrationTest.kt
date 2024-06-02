package pl.mobilespot.futuremirror.presentation.dashboard

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pl.mobilespot.futuremirror.core.MainActivity
import pl.mobilespot.futuremirror.testing.CommonTags.NAMES_COUNTER
import pl.mobilespot.futuremirror.testing.CommonTags.SEARCH_NAME

@RunWith(AndroidJUnit4::class)
@LargeTest
class IntegrationTest {
    @get:Rule
    val activityRule = createAndroidComposeRule<MainActivity>()

    private val unselect = "Unselect"
    private val settings = "Settings"
    private val search = "Search"
    private val news = "News"
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

    @Test
    fun `click settings should show names counter`() {
        activityRule.apply {
            onNodeWithText(settings).performClick()
            onNodeWithText(unselect).assertDoesNotExist()

            onNodeWithTag(NAMES_COUNTER).assertTextEquals("2")
        }
    }

    @Test
    fun `click search should open search name screen with empty search bar`() {
        activityRule.apply {
            onNodeWithText(search).performClick()

            onNodeWithTag(SEARCH_NAME).assertTextEquals("")
        }
    }

    @Ignore("wait for debounce effect")
    @Test
    fun `search Bo should found Bogdan`() {
        activityRule.apply {
            onNodeWithText(search).performClick()
            onNodeWithTag(SEARCH_NAME).assertIsDisplayed()
            onNodeWithTag(SEARCH_NAME).performTextInput("Bo")

            onNodeWithText("Bogdan").assertIsDisplayed()
            onNodeWithText("Unknown").assertIsNotDisplayed()
        }
    }
}
