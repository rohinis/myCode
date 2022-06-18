
package funtionsForEdge

import java.awt.Robot
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.awt.event.KeyEvent as KeyEvent

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver as WebDriver

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI


public class EdgeFunctions {


	@Keyword
	def setTestToField(String inputText , TestObject to) {
		int i
		Robot robot = new Robot()
		robot.keyPress(KeyEvent.VK_CONTROL)
		robot.keyPress(KeyEvent.VK_A)
		robot.keyRelease(KeyEvent.VK_CONTROL)
		robot.keyRelease(KeyEvent.VK_A)
		robot.keyPress(KeyEvent.VK_SPACE)
		robot.keyRelease(KeyEvent.VK_SPACE)
		sleep(300)
		for (i = 0; i < inputText.length(); i++) {
			String Char = inputText.charAt(i)

			println('Send: ' + Char)

			WebUI.sendKeys(to, Char)

			Thread.sleep(500)
		}
	}

	@Keyword
	def uploadFileEdge (TestObject to, String filePath) {

		println ("from uploadEdge")
		WebUI.click(to)
		println("clicked to ")
		StringSelection ss = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Robot robot = new Robot();
		println("Done sleeping")
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_TAB);

		robot.keyRelease(KeyEvent.VK_TAB)
		robot.keyRelease(KeyEvent.VK_ALT)
		/*		robot.keyPress(KeyEvent.VK_ENTER);
		 robot.keyRelease(KeyEvent.VK_ENTER);*/
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		sleep(500);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}






	@Keyword
	def uploadSelenium ( String cssPath) {

		println ("from selenium")

		WebDriver driver = DriverFactory.getWebDriver();
		//driver.findElement(By.cssSelector("id#file_upload_element_fileview")).sendKeys("c:\\bar.fem")
		driver.findElement(By.xpath('//input[@id=\'file_upload_element_fileview\']')).sendKeys("c:\\bar.fem")

		/*	String nameValue=driver.findElement(By.xpath('//input[@id=\'file_upload_element_fileview\']'))
		 WebElement ele=driver.findElement(By.xpath('//input[@id=\'file_upload_element_fileview\']'))
		 ele.sendKeys("c:\bar.fem")
		 println nameValue
		 JavascriptExecutor jse = (JavascriptExecutor) driver;
		 jse.executeScript("document.getElementById('file_upload_element_fileview').focus();")
		 Actions actions = new Actions(driver)
		 actions.moveToElement(driver.findElement(By.xpath('//input[@id=\'file_upload_element_fileview\']')))
		 actions.sendKeys("c:\\bar.fem")
		 actions.build().perform();
		 */
	}
}
