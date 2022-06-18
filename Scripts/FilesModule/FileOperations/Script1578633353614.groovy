import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys

import com.aventstack.extentreports.ExtentReports
import com.aventstack.extentreports.ExtentTest
import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status
import com.kms.katalon.core.exception.StepErrorException
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

//====================================================================================
def extentTest=GlobalVariable.G_ExtentTest
//=====================================================================================
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//=====================================================================================
TestObject newFileObj

def navLocation = CustomKeywords.'generateFilePath.filePath.execLocation'()
def location
def result
def fileItem
if (TestOperation.contains('icon')){
	location = navLocation + '/FilesModule/FileOpsIcons/'
}
else{
	location = navLocation + '/FilesModule/FileOps/'
}

if (TestCaseName.contains('tile view')) {
	newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_TileView'), 'title', 'equals', fileName,	true )
} else {
	newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'title', 'equals', fileName, true)
}

try {
	def filesTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/FilesTab_disabled'),
			2, extentTest, 'Files Tab')

	if (filesTab)	{
		WebUI.click(findTestObject('GenericObjects/TitleLink_Files'))
	}

	extentTest.log(Status.PASS, 'Navigated to Files Tab')
	WebUI.delay(2)

	println ("==============================================")
	println(TestCaseName)
	println ("==============================================")

	CustomKeywords.'operations_FileModule.ChangeView.changePageView'(TestCaseName, extentTest)

	if (TestCaseName.contains('Upload'))
	{
		result = CustomKeywords.'operations_FileModule.fileOperations.executeFileOperations'(TestOperation, TestCaseName,
				extentTest)
		fileItem = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(newFileObj, 20, extentTest, fileName)
		println(fileItem)
		if (fileItem) {
			result=true
			extentTest.log(Status.PASS, 'Verified file  ' + fileName + ' uploaded sucessfully')
		}
	}
	else
	{

		WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
		WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), location)
		WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
		extentTest.log(Status.PASS, 'Navigated to ' + location)
		WebUI.click(findTestObject('FilesPage/FilesSearch_filter'))
		WebUI.waitForElementVisible(findTestObject('FilesPage/FilesSearch_filter'), 2)
		println(fileName)
		WebUI.setText(findTestObject('FilesPage/FilesSearch_filter'), fileName)
		extentTest.log(Status.PASS, (('Looking for file - ' + fileName) + ' to perfrom operation - ') + TestOperation)
		WebUI.sendKeys(findTestObject('JobDetailsPage/TextBx_DetailsFilter'), Keys.chord(Keys.ENTER))
		extentTest.log(Status.PASS, 'Found File  - ' + fileName)
		fileItem = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(newFileObj, 20, extentTest, fileName)
		println(fileItem)
		if (fileItem) {
			if (TestOperation.contains('icon'))
			{
				WebUI.waitForElementPresent(newFileObj, 3)
				WebUI.click(newFileObj)
				extentTest.log(Status.PASS, 'Clicked on file ' + fileName)
				result = CustomKeywords.'operations_FileModule.fileOperations_Icon.executeFileOperations'(TestOperation, TestCaseName,extentTest)
			}
			else
			{
				WebUI.waitForElementPresent(newFileObj, 3)
				WebUI.click(newFileObj)
				extentTest.log(Status.PASS, 'Clicked on file ' + fileName)
				WebUI.rightClick(newFileObj)
				extentTest.log(Status.PASS, 'Right Clicked File to invoke context menu on  - ' + fileName)
				result = CustomKeywords.'operations_FileModule.fileOperations.executeFileOperations'(TestOperation, TestCaseName,
						extentTest)
			}

		}
	}



	if (result) {
		extentTest.log(Status.PASS, ('Verified - ' + TestCaseName) + '  Sucessfully')
	} else {
		extentTest.log(Status.FAIL, TestCaseName + ' - failed')
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