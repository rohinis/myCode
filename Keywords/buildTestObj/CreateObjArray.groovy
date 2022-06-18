package buildTestObj

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.aventstack.extentreports.Status

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject



public class CreateObjArray {


	@Keyword
	def lines(WebDriver katalonWebDriver , extentTest  ) {

		WebUI.click(findTestObject('FilesPage/FileViewer_Edit'))
		List<WebElement> listElement = katalonWebDriver.findElements(By.xpath("//div[contains(@class, 'ace_line_group')]"))
		println listElement.size()
		extentTest.log(Status.PASS, 'Number of filterd jobs '+ listElement.size() )
		return true
	}


	@Keyword
	def grid(WebDriver katalonWebDriver , String dataAttribute , String FliterValue, extentTest  ) {

		def fail=false
		def Status = com.relevantcodes.extentreports.Status

		List<WebElement> listElement = katalonWebDriver.findElements(By.xpath("//div[contains(@id, '_row')]"))
		//List listElement = driver.findElements(By.xpath("//div[contains(@id, '_row')]"))
		//List listElement = ((RemoteWebDriver) (((EventFiringWebDriver) driver).findElements(By.xpath("//div[contains(@id, '_row')]"))))

		println listElement.size()
		extentTest.log(Status.PASS, 'Number of filterd jobs '+ listElement.size() )

		for(int i =0;i<listElement.size();i++) {
			RemoteWebElement ele = listElement.get(i)
			String eleID=ele.getAttribute("id")
			println eleID
			TestObject testObject = new TestObject();
			testObject.addProperty("id", ConditionType.EQUALS, eleID)
			WebUI.delay(2)
			WebUI.click(testObject)
			WebUI.delay(2)
			String data=WebUI.getAttribute(testObject, "data-node")
			String dataAttributeString='"'+dataAttribute+'":"'
			String[] splitAddress = data.split(dataAttributeString)
			String  jobState = splitAddress[1]
			String[] s1 = jobState.split('",')
			String CheckString=s1[0]

			if(CheckString.equalsIgnoreCase(FliterValue)) {
				extentTest.log(Status.PASS, 'Filter value matched for '+ i + ' row in jobs table')
			}
			else {
				fail=true
			}
		}

		return fail
	}
}