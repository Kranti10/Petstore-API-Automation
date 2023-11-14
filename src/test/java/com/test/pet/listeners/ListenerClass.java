package com.test.pet.listeners;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


/*
 * Listener class which is implementing ITestListener and hence we can use this to dynamically create reports, write logs.
 */
public class ListenerClass implements ITestListener{
	
	private static String TestcaseName;

	

	public static String getTestcaseName() {
		return TestcaseName;
	}

	public static void setTestcaseName(String testcaseName) {
		TestcaseName = testcaseName;
	}

	public void onTestStart(ITestResult result) {
		TestcaseName =result.getMethod().getDescription();
		setTestcaseName(TestcaseName);

	System.out.println(TestcaseName+ " is started successfully");
		
	}

	public void onTestSuccess(ITestResult result) {

		System.out.println(result.getMethod().getDescription()+ " test case is passed");

	}

	public void onTestFailure(ITestResult result) {

		System.out.println(result.getMethod().getDescription()+ " is failed");
		System.out.println(result.getThrowable().toString());

		
	}

	public void onTestSkipped(ITestResult result) {

		System.out.println(result.getMethod().getDescription()+ " is skipped");
		

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("event trigger for onTestFailedButWithinSuccessPercentage");

	}

	public void onStart(ITestContext context) {

		System.out.println("event trigger for onStart");
	}





}
