package demo

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebElement

import com.kms.katalon.core.annotation.Keyword
import com.aventstack.extentreports.Status



public class AuditLog {

	@Keyword
	def auditLogs(WebDriver katalonWebDriver , extentTest) {
		String [] tabHeader = [
			'time',
			'username',
			'ipaddress',
			'entityAction',
			'entityType' ,
			'entityName',
			'result'
		]
		String [] tabHeaderLabel = [
			'time',
			'username',
			'ipaddress',
			'action',
			'Type' ,
			'name',
			'status'
		]

		int i =0
		for (String name:tabHeader) {
			String header=tabHeader[i]
			String x1="//div[@col-id='"
			String x2 ="']"
			String myXpath=x1+header+x2


			List<WebElement> listElement = katalonWebDriver.findElements(By.xpath(myXpath))
			println("-------------------------------------")
			println listElement.size()
			if(listElement.size() > 0) {
				println("-------------------------------------")
				def x=listElement.size()
				extentTest.log(Status.PASS, "value for "+tabHeaderLabel[i]  +"--- "+header)
				RemoteWebElement ele = listElement.get(1)
				String myText=ele.getText()
				extentTest.log(Status.PASS, "text --- "+myText)
				i++
			}
			else {
				WebUI.delay(2)
			}
		}
	}
}

