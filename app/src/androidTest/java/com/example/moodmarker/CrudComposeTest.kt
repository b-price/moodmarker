package com.example.moodmarker

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumentation test that exercises Create / Read / Update / Delete flows through the
 * Jetpack Compose UI. This uses testTags to find nodes. Replace the testTag strings below
 * ("addButton","titleInput","saveButton","deleteButton") with the actual testTags,
 * content descriptions, or texts used in the app.
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class CrudComposeTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun createReadUpdateDelete_flow() {
        // Wait for the UI to settle
        composeTestRule.waitForIdle()

        // ----- CREATE -----
        // Tap the add button. Replace selector if your app uses contentDescription or text
        composeTestRule.onNodeWithTag("addButton")
            .assertExists("Add button not found - update the testTag or matcher to match your UI")
            .performClick()

        // Type into title input field
        val title = "Test Mood"
        composeTestRule.onNodeWithTag("titleInput")
            .assertExists("Title input not found - update the testTag or matcher to match your UI")
            .performTextInput(title)

        // Save
        composeTestRule.onNodeWithTag("saveButton")
            .assertExists("Save button not found - update the testTag or matcher to match your UI")
            .performClick()

        // Verify item appears in list
        composeTestRule.onNodeWithText(title).assertIsDisplayed()

        // ----- UPDATE -----
        // Open the item (tap it) to edit
        composeTestRule.onNodeWithText(title)
            .assertExists()
            .performClick()

        // Replace text
        val updated = "Updated Mood"
        composeTestRule.onNodeWithTag("titleInput")
            .performTextReplacement(updated)

        composeTestRule.onNodeWithTag("saveButton").performClick()

        // Verify updated text shown and old text gone
        composeTestRule.onNodeWithText(updated).assertIsDisplayed()
        composeTestRule.onNodeWithText(title).assertDoesNotExist()

        // ----- DELETE -----
        // Open the item (if needed) and tap delete
        composeTestRule.onNodeWithText(updated)
            .assertExists()
            .performClick()

        composeTestRule.onNodeWithTag("deleteButton")
            .assertExists("Delete button not found - update the testTag or matcher to match your UI")
            .performClick()

        // If your app shows a confirmation dialog, uncomment or adapt the next lines:
        // composeTestRule.onNodeWithText("Delete").performClick()

        // Verify the item is gone
        composeTestRule.onNodeWithText(updated).assertDoesNotExist()
    }
}
