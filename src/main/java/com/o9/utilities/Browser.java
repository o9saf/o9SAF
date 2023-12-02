package com.o9.utilities;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONValue;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class Browser {
	private O9Library aut;

	public Browser(O9Library aut) {
		this.aut = aut;
	}

	public void closeTab() {
		try {
			this.aut.driver.close();
		} catch (Exception e) {
			//this.aut.log.fail("Exception is " + e + " in closeTab()");
			Assert.fail("Exception is " + e + " in closeTab()");
		} finally {

		}
	}

	public void closeBroswer() {
		try {
			this.aut.driver.quit();
		} catch (Exception e) {
			//this.aut.log.fail("Exception is " + e + " in closeBroswer()");
			Assert.fail("Exception is " + e + " in closeBroswer()");
		} finally {
			//this.aut.report.flushReport();
		}
	}

	private Map<String, Object> getClipBoardSettingsMap(int settingValue) {
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("last_modified", String.valueOf(System.currentTimeMillis()));
			map.put("setting", settingValue);
			Map<String, Object> cbPreference = new HashMap<>();
			cbPreference.put("[*.],*", map);
			String jsonStr = JSONValue.toJSONString(cbPreference);
			// this.aut.log.info("clipboardSettingJson: " + jsonStr);
			return cbPreference;
		} catch (Exception e) {
			//this.aut.log.fail("Exception is " + e + " in closeBroswer()");
			Assert.fail("Exception is " + e + " in closeBroswer()");
			return null;
		} finally {

		}

	}

	// need to add some arguments
	//run in headless mode
	public void launchChrome() {
		try {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			options.addArguments("--disable-popup-blocking");
			options.addArguments("--disable-web-security");
			options.addArguments("ignore-certificate-errors");
			options.addArguments("--start-maximized");
			if (System.getProperty("headlessMode")!=null && System.getProperty("headlessMode").equalsIgnoreCase("true")) {
				options.addArguments("--headless=new");
				options.addArguments("--window-size=1920,1080");
			}
			// options.setCapability("UNHANDLED_PROMPT_BEHAVIOUR", "accept");
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.content_settings.exceptions.clipboard", this.getClipBoardSettingsMap(1));
			// "profile.content_settings.exceptions.clipboard" :{"[*.],*":{"setting":1}}
			
			File dir = new File(this.aut.downloadPath);
			prefs.put("download.default_directory", dir.getAbsolutePath());
			options.setExperimentalOption("prefs", prefs);
			// prefs.put("download.default_directory", System.getProperty("user.dir") +
			// File.separator + "test-output"
			// + File.separator + "downloads");

			// prefs.put("download.directory_upgrade", true);
			// prefs.put("browser.set_download_behavior", "allow");
			// prefs.put("download.prompt_for_download", false);

			// prefs.put("download.default_directory", this.aut.reportPath + File.separator
			// + "downloadFiles");
			// prefs.put("download.default_directory", System.getProperty("user.dir") +
			// File.separator + "externalFiles"
			// + File.separator + "downloadFiles");
			// prefs.put("profile.default_content_settings.popups", 1);
			// prefs.put("download.prompt_for_download", false);
			// System.getProperty("user.dir");
			// app.log.info(prefs);
			// app.log.info(options);
			this.aut.driver = new ChromeDriver(options);
			//this.aut.driver = WebDriverManager.chromedriver().capabilities(options).create();
		} catch (Exception e) {
			//this.aut.log.fail("Exception is " + e + " in launchChrome()");
			Assert.fail("Exception is " + e + " in launchChrome()");
		} finally {

		}
	}

	private void launchFireFox() {
		try {
			this.aut.driver = new FirefoxDriver();
		} catch (Exception e) {
			//this.aut.log.fail("Exception is " + e + " in launchFireFox()");
			Assert.fail("Exception is " + e + " in launchFireFox()");
		} finally {

		}
	}

	private void launchEdge() {
		try {
			this.aut.driver = new EdgeDriver();
		} catch (Exception e) {
			//this.aut.log.fail("Exception is " + e + " in launchEdge()");
			Assert.fail("Exception is " + e + " in launchEdge()");
		} finally {

		}
	}

	private void launchIE() {
		try {
			this.aut.driver = new InternetExplorerDriver();
		} catch (Exception e) {
			//this.aut.log.fail("Exception is " + e + " in launchIE()");
			Assert.fail("Exception is " + e + " in launchIE()");
		} finally {

		}
	}

	public void getBroswer(String browser) {
		try {
			System.setProperty("webdriver.http.factory", "jdk-http-client");
			if ((browser.toUpperCase()).equals("CR") || (browser.toUpperCase()).equals("CHROME")) {
				this.launchChrome();
				//this.aut.log.info("Report path " + this.aut.reportPath);
			}
			if ((browser.toUpperCase()).equals("FF") || (browser.toUpperCase()).equals("FIREFOX")) {
				this.launchFireFox();
			}
			if ((browser.toUpperCase()).equals("IE") || (browser.toUpperCase()).equals("INTERNET_EXPLORER")) {
				this.launchIE();
			}
			if ((browser.toUpperCase()).equals("EC") || (browser.toUpperCase()).equals("EDGE")) {
				this.launchEdge();
			}
		} catch (Exception e) {
			//this.aut.log.fail("Exception is " + e + " in getBroswer()");
			Assert.fail("Exception is " + e + " in getBroswer()");
		} finally {

		}
	}

	public void closeTest() {
		try {
			if (this.aut.driver != null) {
				//this.aut.commonFunctions.logout();
			}
			//if (this.aut.log.isTestFailed) {
				//this.aut.log.fail("\"" + this.aut.moduleName + "\"" + " script execution failed.");
				//Assert.fail("\"" + this.aut.moduleName + "\"" + " script execution failed.");
			//}
		} catch (Exception e) {

		} finally {
			if (this.aut.driver != null) {
				this.aut.broswer.closeBroswer();
			} else {
				//this.aut.report.flushReport();
			}
		}
	}
}
