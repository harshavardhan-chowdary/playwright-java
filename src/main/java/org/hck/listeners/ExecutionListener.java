package org.hck.listeners;

import org.testng.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExecutionListener implements ITestListener, ISuiteListener {

    private final int passedCount = 0;
    private final int failedCount = 0;
    private final int skippedCount = 0;

    @Override
    public void onStart(ISuite suite) {
        logTestExecutionStart(suite);
    }

    @Override
    public void onFinish(ISuite suite) {
        logTestingCompleted(suite);
    }

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }

    private void logTestExecutionStart(ISuite suite) {
        int totalTests = suite.getAllMethods().size();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());

        printSeparator();
        printColoredLine("Testing started at suite level...", ConsoleColor.GREEN);
        printSeparator();
        printColoredLine("Suite name: " + suite.getName(), ConsoleColor.CYAN);
        printColoredLine("Number of tests to execute: " + totalTests, ConsoleColor.CYAN);
        printColoredLine("Date and time: " + currentDateTime, ConsoleColor.CYAN);
        printSeparator();
    }

    private void logTestingCompleted(ISuite suite) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());

        printSeparator();
        printColoredLine("Testing completed at suite level...", ConsoleColor.GREEN);
        printSeparator();
        printColoredLine("Suite name: " + suite.getName(), ConsoleColor.CYAN);
        printColoredLine("Time: " + currentDateTime, ConsoleColor.CYAN);
        printColoredLine(
                "Execution status: " + passedCount + " - PASS, " + failedCount + " - FAILED, " + skippedCount + " - Skipped",
                ConsoleColor.CYAN
        );
        printSeparator();
    }

    private void printSeparator() {
        System.out.println("**********");
    }

    private void printColoredLine(String message, ConsoleColor color) {
        System.out.println(color.getCode() + message + ConsoleColor.RESET.getCode());
    }

    // Enum for ANSI color codes
    private enum ConsoleColor {
        RESET("\u001B[0m"),
        GREEN("\u001B[32m"),
        CYAN("\u001B[36m");

        private final String code;

        ConsoleColor(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }
}
