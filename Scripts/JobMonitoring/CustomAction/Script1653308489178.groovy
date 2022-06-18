import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
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

def result
WebUI.delay(2)
try
{
	WebUI.delay(2)
	def jobsTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('NewJobPage/AppList_ShellScript'),
			20,extentTest,'App def')

	if (jobsTab) {
		WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
	}


	extentTest.log(Status.PASS, 'Navigated to Jobs Tab')


	WebUI.delay(2)
	WebUI.click(findTestObject('Object Repository/JobMonitoringPage/a_Reset'))


	WebUI.click(findTestObject('JobMonitoringPage/JM_SearchBox'))
	WebUI.sendKeys(findTestObject('JobMonitoringPage/JM_SearchBox'), 'CustomActions')

	TestObject newJobFilter = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/label_jobState'), 'text', 'equals',
			jobState, true)

	WebUI.click(newJobFilter)

	WebUI.delay(2)
	extentTest.log(Status.PASS, 'Clicked on job with state  - ' + jobState)

	println jobState
	TestObject newJobRow = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/div_Completed'), 'title', 'equals',	jobState, true)
	WebUI.rightClick(newJobRow)

	WebUI.click(findTestObject('JobMonitoringPage/ViewDetails_Jobs'))
	extentTest.log(Status.PASS, 'Click on view details job')


	switch(userChoice)
	{
		case 'Input':
			WebUI.click(findTestObject('JobMonitoringPage/InputFolder'))
		//WebUI.rightClick(findTestObject('JobMonitoringPage/OutputFolder_File'))

			extentTest.log(Status.PASS, 'Click on Input Folder')
			break;

		case 'Output':
			WebUI.click(findTestObject('JobMonitoringPage/OutputFolder'))
			extentTest.log(Status.PASS, 'Click on Output Folder')
			WebUI.waitForElementVisible(findTestObject('JobMonitoringPage/OutputFolder'), 5)
		//WebUI.rightClick(findTestObject('JobMonitoringPage/OutputFolder_File'))
			break;

		case 'Running':
			WebUI.click(findTestObject('JobMonitoringPage/RunningFolder'))
			extentTest.log(Status.PASS, 'Click on Running Folder')
		//WebUI.rightClick(findTestObject('JobMonitoringPage/RunningFolder_File'))

			break;
	}

	WebUI.click(findTestObject('Object Repository/JobDetailsPage/Btn_CustomActions'))
	extentTest.log(Status.PASS, 'Clicked on CustomAction button ')
	WebUI.click(findTestObject('Object Repository/JobDetailsPage/Btn_SendSignal') )
	extentTest.log(Status.PASS, 'Clicked on Send Signal Drop down ')
	WebUI.delay(2)

	WebUI.click(findTestObject('Object Repository/JobMonitoringPage/Ok_btn'))
	extentTest.log(Status.PASS, 'Clicked on RUN Btn')

	String res=WebUI.getAttribute(	findTestObject('Object Repository/JobDetailsPage/txt_CustomActionOutput'), 'textContent')

	println(res)

	if(res.contains('STOP'))
	{
		extentTest.log(Status.PASS, 'Executed STOP action ')
		extentTest.log(Status.PASS, 'Verifed msg present on page - '+res)
	}



	if(res)
	{
		extentTest.log(Status.PASS, ' Verified custom action for job state - '+ jobState+'in '+userChoice +' folder')
	}
	else
	{
		extentTest.log(Status.FAIL,'some exception')
	}


	if (GlobalVariable.G_Browser == 'Edge') {
		WebUI.callTestCase(findTestCase('Generic/Logout'), [:], FailureHandling.STOP_ON_FAILURE)
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