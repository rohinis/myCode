import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.support.events.EventFiringWebDriver

import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status

import com.kms.katalon.core.exception.StepErrorException
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable


//====================================================================================
WebDriver driver = DriverFactory.getWebDriver()
EventFiringWebDriver eventFiring = ((DriverFactory.getWebDriver()) as EventFiringWebDriver)
WebDriver wrappedWebDriver = eventFiring.getWrappedDriver()
RemoteWebDriver katalonWebDriver = ((wrappedWebDriver) as RemoteWebDriver)
//====================================================================================



//====================================================================================
def extentTest=GlobalVariable.G_ExtentTest
//=====================================================================================

TestCaseName='MultiFileOperation -'+TestCaseName
if (TestOperation.contains('icon')) {
	TestCaseName = (TestCaseName + ' thorugh top menu icons')
}



//Login call
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)


//updating location to navigate as per module

def result
def navLocation = CustomKeywords.'generateFilePath.filePath.execLocation'()
def location
def pasteLocation

TestObject newFileObj

if (TestOperation.contains('icon'))
{
	location = navLocation + '/MultipleFilesIcons'
}
else
{
	location = navLocation + '/MultipleFiles'
}
if (TestCaseName.contains('tile view'))
{
	if(TestCaseName.contains('Cut')||TestCaseName.contains('Copy'))
	{
		location = (location + '/TileViewCut')
	}
	else
	{
		location = (location + '/TileView')
	}
	newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_TileView'), 'title', 'equals', 'five.txt',
			true)
}
else
{
	pasteLocation = (location + '/ToPasteLV')
	if(TestCaseName.contains('Cut')||TestCaseName.contains('Copy'))
	{
		location = (location + '/ListViewCut')
	}
	else
	{
		location = (location + '/ListView')
	}
	newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'title', 'equals', 'five.txt',
			true)
}
println("================================================")
println("navlocation - "+ navLocation)
println("================================================")
println("location - "+location)
println("================================================")
println("pastelocation - "+pasteLocation)
println("================================================")

try {
	WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))
	extentTest.log(Status.PASS, 'Navigated to Files Tab')
	WebUI.delay(2)

	WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
	WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), location)
	WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
	extentTest.log(Status.PASS, 'Navigated to - ' + location)

	CustomKeywords.'operations_FileModule.ChangeView.changePageView'('list view', extentTest)

	// checking the first row element - if two.txt sorting by name to get five.txt at top
	String myXpath="//div[@col-id='name']"
	List<WebElement> listElement = katalonWebDriver.findElements(By.xpath(myXpath))
	println("================================================")
	println(listElement[1].getText())
	println("================================================")

	extentTest.log(Status.PASS,listElement[1].getText())
	if(listElement[1].getText().equals('five.txt'))
	{
		println("in order")
	}
	else{
		println("need to sort")
		WebUI.click(findTestObject('Object Repository/FilesPage/Label-FilesTable-Name'))
	}

	WebUI.click(findTestObject('FilesPage/SelectallFiles'))
	extentTest.log(Status.PASS, 'Clicked on Select All header check box ')

	//Changing view as per test case
	CustomKeywords.'operations_FileModule.ChangeView.changePageView'(TestCaseName, extentTest)

	//Calling keyword as pre TestOperation - icon or context menu
	if (TestOperation.contains('icon'))
	{
		result = CustomKeywords.'operations_FileModule.multifileOps.multiFileOperationsIcons'(TestOperation, TestCaseName, extentTest,
				katalonWebDriver)
	}
	else
	{
		def fileItem = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(newFileObj, 20, extentTest, 'five.txt')
		println(fileItem)
		if (fileItem) {
			WebUI.waitForElementPresent(newFileObj, 3)
			WebUI.scrollToElement(newFileObj, 3)
			WebUI.rightClick(newFileObj)
			extentTest.log(Status.PASS, 'Right Clicked File to invoke context menu on  ')
		}
		result = CustomKeywords.'operations_FileModule.multifileOpsCntxtMn.multiFileOperations'(TestOperation, TestCaseName,
				extentTest, katalonWebDriver)
	}


	if (result)
	{
		extentTest.log(Status.PASS, ('Verified - ' + TestCaseName) + '  Sucessfully')
	} else {
		extentTest.log(Status.FAIL, ( TestCaseName) + ' - failed')
	}

} 



catch (Exception ex) {
	println("From TC - "+GlobalVariable.G_ReportFolder )
		String screenShotPath = (('ExtentReports/' + TestCaseName) + GlobalVariable.G_Browser) + '.png'
	WebUI.takeScreenshot(screenShotPath)
	String p = (TestCaseName + GlobalVariable.G_Browser) + '.png'
	extentTest.log(Status.FAIL, ex)
	extentTest.fail(MediaEntityBuilder.createScreenCaptureFromPath(p).build())
}
catch (StepErrorException e) {
	String screenShotPath = (('ExtentReports/' +TestCaseName) + GlobalVariable.G_Browser) + '.png'
	WebUI.takeScreenshot(screenShotPath)
	String p = (TestCaseName + GlobalVariable.G_Browser) + '.png'
	extentTest.log(Status.FAIL, ex)
	extentTest.fail(MediaEntityBuilder.createScreenCaptureFromPath(p).build())


}
finally {
	extentTest.log(Status.PASS, 'Closing the browser after executinge test case - '+ TestCaseName)

}