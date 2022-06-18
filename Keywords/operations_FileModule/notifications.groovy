package operations_FileModule
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.aventstack.extentreports.Status




class notifications {

	def result
	@Keyword
	def getNotifications(String mgs,extentTest) {

		extentTest.log(Status.PASS, 'in notifications')
		TestObject newNotification= new TestObject('objectName')
		String xpath_newNotification = "//span[(contains(text(), '"+mgs+"'))]"
		newNotification.addProperty('xpath', ConditionType.EQUALS, xpath_newNotification)
		result=WebUI.waitForElementPresent(newNotification, 3, FailureHandling.CONTINUE_ON_FAILURE)

		extentTest.log(Status.PASS, 'xpath -'+xpath_newNotification)
		extentTest.log(Status.PASS, 'result - '+result )
	}


	/**
	 * Get all rows of HTML table
	 * @param table Katalon test object represent for HTML table
	 * @param outerTagName outer tag name of TR tag, usually is TBODY
	 * @return All rows inside HTML table
	 */
	@Keyword
	def List<WebElement> getHtmlTableRows(TestObject table, String outerTagName) {
		WebElement mailList = WebUiBuiltInKeywords.findWebElement(table)
		List<WebElement> selectedRows = mailList.findElements(By.xpath("./" + outerTagName + "/tr"))
		return selectedRows
	}
}