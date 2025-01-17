package com.example.benchmark

import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This is an example startup benchmark.
 *
 * It navigates to the device's home screen, and launches the default activity.
 *
 * Before running this benchmark:
 * 1) switch your app's active build variant in the Studio (affects Studio runs only)
 * 2) add `<profileable android:shell="true" />` to your app's manifest, within the `<application>` tag
 *
 * Run this benchmark from Studio to see startup measurements, and captured system traces
 * for investigating your app's performance.
 */
@RunWith(AndroidJUnit4::class)
class ExampleStartupBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun startup() = benchmarkRule.measureRepeated(
        packageName = "com.example.highDemand_xml",
        metrics = listOf(StartupTimingMetric()),
        iterations = 10,
        startupMode = StartupMode.COLD
    ) {
        pressHome()
        startActivityAndWait()
    }
    @Test
    fun scrollPostTest() = benchmarkRule.measureRepeated(
        packageName = "com.example.highDemand_xml",
        metrics = listOf(FrameTimingMetric()),
        iterations = 30,
        startupMode = StartupMode.COLD
    ) {
        pressHome()
        startActivityAndWait()

        scrollPostList()
    }

    private fun MacrobenchmarkScope.scrollPostList() {
        val contentList = device.findObject(By.res("com.example.highDemand_xml:id/linearLayout2"))

        device.waitForIdle()

        contentList.setGestureMargin(device.displayWidth / 5)
        contentList.scroll(Direction.DOWN, 300f)

        device.waitForIdle()
    }

    @Test
    fun scrollRowTest() = benchmarkRule.measureRepeated(
        packageName = "com.example.highDemand_xml",
        metrics = listOf(FrameTimingMetric()),
        iterations = 30,
        startupMode = StartupMode.COLD
    ){
        pressHome()
        startActivityAndWait()
        scrollRowList()
    }

    private fun MacrobenchmarkScope.scrollRowList() {
        val contentList = device.findObject(By.res("com.example.highDemand_xml:id/linearLayout"))

        device.waitForIdle()

        contentList.setGestureMargin(device.displayWidth / 4)

        contentList.scroll(Direction.RIGHT, 300f)

        device.waitForIdle()
    }

    @Test
    fun openNav() = benchmarkRule.measureRepeated(
        packageName = "com.example.highDemand_xml",
        metrics = listOf(FrameTimingMetric()),
        iterations = 10,
        startupMode = StartupMode.COLD
    ){
        pressHome()
        startActivityAndWait()

        //Test to open navigation menu
        openNavigation()
    }
    private fun MacrobenchmarkScope.openNavigation() {
        val openNav = device.findObject(By.res("com.example.highDemand_xml:id/dropdown_button"))

        device.waitForIdle()

        openNav.click()

        device.waitForIdle()
    }

    @Test
    fun allTest() = benchmarkRule.measureRepeated(
        packageName = "com.example.highDemand_xml",
        metrics = listOf(FrameTimingMetric()),
        iterations = 1,
        startupMode = StartupMode.COLD
    ){
        pressHome()
        startActivityAndWait()
        scrollPostList()
        scrollRowList()
    }
}