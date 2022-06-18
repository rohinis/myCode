package operations_FileModule

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.aventstack.extentreports.Status

public class getRowDetails {

	@Keyword
	def getSearchResutls(WebDriver katalonWebDriver,extentTest,searchStr,TestCaseName){
		String myXpath=null;
		String myText=null;
		WebUI.delay(5)
		//def Status = com.relevantcodes.extentreports.Status
		def result
		String item=null

		if(TestCaseName.contains('tile view')) {
			myXpath="//label[@id='file_text']" // and contains (@title,'"+searchStr+"')]"
			item =" grid element on files page "
		}
		else {
			myXpath="//div[@col-id='name']"
			item =" row in files table "
		}

		def emptyMsgPresent = WebUI.verifyElementPresent( findTestObject('Object Repository/FilesPage/Label_FolderEmpty'), 3, FailureHandling.CONTINUE_ON_FAILURE)

		if(emptyMsgPresent) {
			println("empty msg found")
			result=false
		}
		else {
			println("empty msg not found ")
			List<WebElement> listElement = katalonWebDriver.findElements(By.xpath(myXpath))
			println("-------------------------------------")
			println listElement.size()
			println("-------------------------------------")

			for(int i =0;i<listElement.size();i++) {
				RemoteWebElement ele = listElement.get(i)
				myText=ele.getText()
				println (ele.getText())
				if(myText.toLowerCase().contains(searchStr.toLowerCase())) {
					println("PASS")
					extentTest.log(Status.PASS, 'Current'+item +' - '+i + '- file name - '+myText)
					result=true
				}
				else {
					println("FAIL")
					result=false
				}
			}
		}
		return result
	}


	@Keyword
	def getFileLine(WebDriver katalonWebDriver,extentTest) {
		String myText
		def Status = com.relevantcodes.extentreports.Status
		def myXpath="//div[@id='brace-editor']//textarea"
		List<WebElement> listElement = katalonWebDriver.findElements(By.xpath(myXpath))
		def num=listElement.size()
		num=num-1
		println("-------------------------------------")
		println listElement.size()
		println("-------------------------------------")

		/*for(int i =0;i<listElement.size();i++) {
		 RemoteWebElement ele = listElement.get(i)
		 myText=ele.getText()
		 println (ele.getText())
		 }*/
		println("----------------------- "+num+" -----------------------------")
		RemoteWebElement ele = listElement.get(num)
		ele.sendKeys("new line added")
		ele.sendKeys('\n')
	}

	@Keyword
	def getFilePage(WebDriver katalonWebDriver,extentTest,TestCaseName) {
		String myXpath=null;
		String myText=null;
		WebUI.delay(5)
		def Status = com.relevantcodes.extentreports.Status
		def result
		String item=null

		if(TestCaseName.contains('tile view')) {
			myXpath="//label[@id='file_text']" // and contains (@title,'"+searchStr+"')]"
			item =" grid element on files page "
		}
		else {
			myXpath="//div[@col-id='name']"
			item =" row in files table "
		}


		List<WebElement> listElement = katalonWebDriver.findElements(By.xpath(myXpath))
		println("-------------------------------------")
		println listElement.size()
		println("-------------------------------------")
		def x=listElement.size()
		extentTest.log(Status.PASS, ' Number of files listied on this page - '+x)

		RemoteWebElement ele = listElement.get(1)
		myText=ele.getText()
		extentTest.log(Status.PASS, 'Name of first file - '+myText)
		x=x-1
		RemoteWebElement ele1 = listElement.get(x)
		myText=ele1.getText()
		extentTest.log(Status.PASS, 'Name of last file - '+myText)
	}
}
