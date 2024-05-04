package pl.mobilespot.futuremirror.presentation.search

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import pl.mobilespot.futuremirror.namedays.NameDay

class SearchScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val noResult = "No result"
    @Test
    fun `show no result when there is any result`() {
        composeTestRule.setContent {
            SearchScreen()
        }

        composeTestRule.onNodeWithText(noResult).assertIsDisplayed()
    }

    @Test
    fun `do not show no result when there is result`() {
        composeTestRule.setContent {
            SearchScreen(listOf(NameDay.raw))
        }

        composeTestRule.onNodeWithText(noResult).assertDoesNotExist()
    }
}
