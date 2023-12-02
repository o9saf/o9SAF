package com.o9.utilities;

import org.openqa.selenium.WebDriver;

public class O9Library {
	public int MAX_TIMEOUT_SECS;
	public int MAX_TIMEOUT_MILLI_SECS;
	public Browser broswer;
	public WebDriver driver;
	public String moduleName, launchBroswer, dataSheetsPath, objectRepoPath, reportPath, screeshotPath, downloadPath;
	public static String g_envURL, g_username, g_password, g_clientAdress, g_tenantName;
	public String[] args;
	
	public O9Library(String moduleName, String[] args) {
		this.moduleName = moduleName;
		this.args = args;
		
	}

	public void launchApplication() {
		this.broswer.getBroswer(this.launchBroswer);
		this.driver.manage().deleteAllCookies();
		//this.driver.manage().window().maximize();
	}

}
