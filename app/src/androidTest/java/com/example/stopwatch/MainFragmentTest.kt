package com.example.stopwatch

import android.content.Context
import android.widget.TextView
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.*
import com.example.stopwatch.view.MainFragment
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainFragmentTest {
    private lateinit var scenario: FragmentScenario<MainFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer()
    }

    @Test
    fun mainFragmentIsNotNull() {
        scenario.onFragment() {
            assertNotNull(it)
        }
    }

    @Test
    fun mainFragmentTextTimeIsOnStart() {
        onView(withId(R.id.text_time)).check(matches(withText("00:00:000")))
    }

    @Test
    fun mainFragmentClickToStart() {
        onView(withId(R.id.button_start)).perform(click())
        onView(withId(R.id.button_pause)).perform((click()))
        scenario.onFragment() {
            val textTime = it.activity?.findViewById<TextView>(R.id.text_time)?.text
            assertNotEquals(textTime, "00:00:000")
        }
    }

    @Test
    fun mainFragmentClickToStop() {
        onView(withId(R.id.button_start)).perform(click())
        onView(withId(R.id.button_pause)).perform((click()))
        onView(withId(R.id.button_stop)).perform(click())
        onView(withId(R.id.text_time)).check(matches(withText("00:00:000")))
    }

    @After
    fun close() {
        scenario.close()
    }
}