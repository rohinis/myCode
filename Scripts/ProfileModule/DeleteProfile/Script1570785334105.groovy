import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.aventstack.extentreports.ExtentReports
import com.aventstack.extentreports.ExtentTest
import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status
import com.kms.katalon.core.exception.StepErrorException
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable


//====================================================================================
def extentTest=GlobalVariable.G_ExtentTest
//=====================================================================================


CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//=====================================================================================
def navLocation = CustomKeywords.'generateFilePath.filePath.execLocation'()
def location = navLocation + '/FilesModule/FileOps/'
//=====================================================================================
def isElementPresnt

WebUI.delay(2)
try
{

	def jobsTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('NewJobPage/AppList_ShellScript'),
			20,extentTest,'App def')

	if (jobsTab) {
		WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
	}

	WebUI.delay(2)

	TestObject newAppObj = WebUI.modifyObjectProperty(findTestObject('NewJobPage/AppList_ShellScript'), 'id', 'equals', AppName, true)

	WebUI.click(newAppObj)
	extentTest.log(Status.PASS, 'Navigated to Job Submission form for - '+AppName )

	WebUI.delay(2)

	TestObject LeftNavAppIdentifier = CustomKeywords.'buildTestObj.CreateTestObjJobs.myLeftNavAppIdentifier'(proName)
	WebUI.click(findTestObject('Object Repository/NewJobPage/GenericProfile'))
	def isProfilePersent = WebUI.verifyElementPresent(LeftNavAppIdentifier, 5)

	if (isProfilePersent) {
		WebUI.click(LeftNavAppIdentifier)
		isElementPresnt=WebUI.verifyElementPresent(findTestObject('Object Repository/JobSubmissionForm/Title_Reset'),3,FailureHandling.CONTINUE_ON_FAILURE)
		if(isElementPresnt)
		{
			WebUI.verifyElementPresent(findTestObject('Object Repository/JobSubmissionForm/Text_Reset'),3)
			WebUI.click(findTestObject('Object Repository/JobMonitoringPage/button_Yes'))
		}

	}
	WebUI.delay(2)

	def deleteOption = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('ProfileOptions/Icon_Delete_Profile'),5,extentTest,'DeleteOption')
	extentTest.log(Status.PASS, 'Loaded Profile -  '+proName )

	if (deleteOption) {
		extentTest.log(Status.PASS, 'Test to verify delete option exists - Pass ')

		WebUI.mouseOver(findTestObject('ProfileOptions/Icon_Delete_Profile'))
		WebUI.delay(2)
		WebUI.click(findTestObject('ProfileOptions/Icon_Delete_Profile'))
		extentTest.log(Status.PASS, 'Clicked on Delete ')
		WebUI.delay(3)
		WebUI.click(findTestObject('GenericObjects/btn_Yes'))
		WebUI.delay(2)
	}

	WebUI.click(findTestObject('Object Repository/NewJobPage/GenericProfile'))

	def result = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(LeftNavAppIdentifier,5,extentTest,'deletedProfile')
	if (result)
	{
		extentTest.log(Status.FAIL,'Profile not deleted')
		extentTest.log(Status.FAIL, ( TestCaseName) + ' :: failed')

	}
	else {
		extentTest.log(Status.PASS, 'Deleted Profile - '+proName )
		extentTest.log(Status.PASS, ('Verified ::  ' + TestCaseName) + ' :: Sucessfully')

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



