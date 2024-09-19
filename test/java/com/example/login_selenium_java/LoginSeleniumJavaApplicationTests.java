package com.example.login_selenium_java;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoginSeleniumJavaApplicationTests {
	private WebDriver driver;
	private ExtentReports extentReport;

	@BeforeEach
	void getUp() {

		driver = new ChromeDriver();
		extentReport = new ExtentReports();
	}

	@Test
	void loginTest() {
		ExtentSparkReporter spark = new ExtentSparkReporter("Reporte/login.html");
		extentReport.attachReporter(spark);

		ExtentTest testLog = extentReport.createTest("Login Test");

		driver.get("https://www.maximus.com.ar/LOGIN/login/maximus.aspx");
		driver.manage().window().maximize();
		driver.findElement(By.id("usuario")).sendKeys("admin");
		driver.findElement(By.id("clave")).sendKeys("clave");
		driver.findElement(By.xpath("//button[normalize-space()='LOGIN']")).click();

		if (driver.getPageSource().contains("Los datos ingresados son inválidos.")) {
			testLog.log(Status.FAIL, "Login fallido");
		} else {
			testLog.log(Status.PASS, "Login exitoso");
		}
		driver.close();
		extentReport.flush();
	}

}
