package operations_FileModule

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import org.openqa.selenium.remote.RemoteWebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class fileviewerRunningJob {


	def executeFileOperations(WebDriver katalonWebDriver,String Operation,String TestCaseName ,extentTest) {
		boolean result=false
		//def Status = com.relevantcodes.extentreports.Status
		println ("Control in Keyword --- "+ Operation )
		WebUI.delay(2)
		switch (Operation) {

			case 'EditSave':
				def myXpath="//div[@id='ace-editor']//textarea"
				def myLineXpath='//div[@class="ace_line_group"]'
				WebUI.click(findTestObject('Object Repository/JobDetailsPage/Icon_EditFile'))
				extentTest.log(Status.PASS, 'Click on file edit icon')
				WebUI.delay(3)
				WebElement textEle=katalonWebDriver.findElement(By.xpath(myXpath))

				List<WebElement> linesBeforeEdit = katalonWebDriver.findElements(By.xpath(myLineXpath))
				def num=linesBeforeEdit.size()
			//	extentTest.log(Status.PASS, 'linesBeforeEdit - '+ num)

				RemoteWebElement ele = textEle
				ele.sendKeys("new line added")
				ele.sendKeys('\n')
				extentTest.log(Status.PASS, 'Added new line into file')
				WebUI.click(findTestObject('Object Repository/FilesPage/btn_Save_fileEditor'))
				extentTest.log(Status.PASS, 'Saved the file ')

				WebUI.click(findTestObject('Object Repository/JobDetailsPage/Icon_EditFile'))
				extentTest.log(Status.PASS, 'Click on file edit icon again to check added lines ')
				WebUI.delay(3)
				ele.sendKeys("new line added one")
				ele.sendKeys('\n')
				WebUI.delay(3)
				ele.sendKeys("new line added two")
				ele.sendKeys('\n')
				WebUI.delay(3)
				List<WebElement> linesAfterEdit = katalonWebDriver.findElements(By.xpath(myLineXpath))
				def num3=linesAfterEdit.size()

			//	extentTest.log(Status.PASS, 'linesAfterEdit - '+ num3)

				if(num3>num) {
					extentTest.log(Status.PASS, 'New  lines added in file')
					result=true
				}
				else {
					extentTest.log(Status.FAIL, 'New line not added in file')

					result=false
				}
				extentTest.log(Status.PASS, 'Added new line into file')
				WebUI.click(findTestObject('Object Repository/FilesPage/btn_Save_fileEditor'))
				extentTest.log(Status.PASS, 'Saved the file ')
				return result
				break

		}
	}
}
