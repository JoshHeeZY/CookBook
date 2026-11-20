package com.joshuahee.cookbook

import com.joshuahee.cookbook.data.formatCookTime
import com.joshuahee.cookbook.ui.screens.toLines
import org.junit.Assert.assertEquals
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun formatCookTime_formatsMinutesAndHours() {
        assertEquals("25 min", formatCookTime(25))
        assertEquals("1 h", formatCookTime(60))
        assertEquals("1 h 15 min", formatCookTime(75))
    }

    @Test
    fun toLines_dropsBlankLinesBulletsAndNumbers() {
        val input = "- 2 eggs\n\n• 1 cup flour\n3) Pinch of salt\n  Milk  "
        assertEquals(
            listOf("2 eggs", "1 cup flour", "Pinch of salt", "Milk"),
            input.toLines()
        )
    }
}
