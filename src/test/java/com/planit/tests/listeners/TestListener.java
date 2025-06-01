package com.planit.tests.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
	private static final Logger logger = LogManager.getLogger(TestListener.class);

	@Override
	public void onTestStart(ITestResult result) {
		logger.info("🟡 Test started: " + result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		logger.info("✅ Test passed: " + result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		logger.error("❌ Test failed: " + result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logger.warn("⏭️ Test skipped: " + result.getMethod().getMethodName());
	}

	@Override
	public void onStart(ITestContext context) {
		logger.info("=== Test Suite started ===");
	}

	@Override
	public void onFinish(ITestContext context) {
		logger.info("=== Test Suite finished ===");
	} 
}
