import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys


import com.aventstack.extentreports.ExtentReports
import com.aventstack.extentreports.ExtentTest
import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status

import com.kms.katalon.core.exception.StepErrorException
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI


import internal.GlobalVariable as GlobalVariable

//====================================================================================
def extentTest=GlobalVariable.G_ExtentTest
//=====================================================================================

CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//=====================================================================================
def navLocation =CustomKeywords.'generateFilePath.filePath.execLocation'()
def location=navLocation+'/ForJM/InputDeck'
println('##################################################################')
println (location)
println('##################################################################')
//=====================================================================================
def NewApp
def ss
def msg
String idForCntxtMn = 'Add as ' + FileArg
TestObject newRFBContextMnOption
try {

	WebUI.delay(3)
	def jobsTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/TitleLink_Jobs'), 10,extentTest,'JobsTab')
	println(jobsTab)
	if (jobsTab) {
		WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
	}
	extentTest.log(Status.PASS, 'navigated to jobs tab')

	TestObject newAppObj =  WebUI.modifyObjectProperty(findTestObject('NewJobPage/AppList_ShellScript'),  'id', 'equals', AppName, true)

	WebUI.click(newAppObj)

	extentTest.log(Status.PASS, 'navigated to job submission page ')
	for (int x=0;x<4;x++)
	{
		
		switch (x)
		{
			case 0:
				InputFile='Running.sh'
				NewApp='shellscript'
				idForCntxtMn = 'Add as Job Script'
				println(InputFile+" -- "+NewApp)
				msg= 'Submitting Running jobs '
				def testcaseName=msg
				
				break;
			case 1:
				InputFile='box.fem'
				NewApp='shellscript'
				idForCntxtMn = 'Add as Job Script'
				println(InputFile+" -- "+NewApp)
				msg='Submitting Failed jobs '
				def testcaseName=msg
				
				break

			case 2:
				InputFile='bar.fem'
				NewApp='optistruct'
				idForCntxtMn = 'Add as Input File'
				println(InputFile+" -- "+NewApp)
				msg='Submitting Completed jobs '
				def testcaseName=msg
				
				break

			case 3:
				InputFile='CUBE_0000.rad'
				NewApp='radioss-smp'
				idForCntxtMn = 'Add as Starter file'
				println(InputFile+" -- "+NewApp)
				msg='Submitting Queues jobs '
				def testcaseName=msg
				
				break
		}



		TestObject LeftNavAppIdentifier = CustomKeywords.'buildTestObj.CreateTestObjJobs.leftNavAppIdentifier'( NewApp)
		WebUI.click(LeftNavAppIdentifier)
		extentTest.log(Status.PASS, 'loaded job submission form for - '+NewApp)
		WebUI.delay(2)
		WebUI.scrollToElement(findTestObject('Object Repository/JobSubmissionForm/Link_ResetLink'), 3,  FailureHandling.STOP_ON_FAILURE)
		WebUI.click(findTestObject('Object Repository/JobSubmissionForm/Link_ResetLink'))
		WebUI.click(findTestObject('Object Repository/JobMonitoringPage/button_Yes'))

		WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
		WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), location)
		WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))

		extentTest.log(Status.PASS, 'navigated to - '+location+' in JS-RFB')
		CustomKeywords.'operations_JobsModule.JobSubmissions.JSAllFileds'('NCPU', '2', extentTest)

		if(NewApp=='radioss-smp')
		{
			WebUI.click(findTestObject('JobSubmissionForm/List_NCPUS'))
			WebUI.setText(findTestObject('JobSubmissionForm/List_NCPUS'),'200')
			extentTest.log(Status.PASS, 'Changed the NCPU value to - 200 for Radioss-SMP')

			WebUI.click(findTestObject('JobSubmissionForm/RadioBtn_All Fields'))
			WebUI.scrollToElement(findTestObject('JobSubmissionForm/label_Queue'), 5)
			WebUI.click(findTestObject('WIP/div_workq'))
			TestObject newQueueObj = WebUI.modifyObjectProperty(findTestObject('JobSubmissionForm/dropDown_version'), 'text', 'equals','compute', true)
			WebUI.mouseOver(newQueueObj)
			WebUI.click(newQueueObj)
			extentTest.log(Status.PASS, 'Changed the queue to - compute')
		}


		WebUI.modifyObjectProperty(findTestObject('JobSubmissionForm/File_InputFile') , 'text', 'equals',InputFile, true)
		WebUI.waitForElementPresent(findTestObject('Object Repository/JobSubmissionForm/textBx_file_filter'), 5)
		WebUI.click(findTestObject('Object Repository/JobSubmissionForm/textBx_file_filter'))
		WebUI.setText(findTestObject('Object Repository/JobSubmissionForm/textBx_file_filter'), InputFile)
		WebUI.sendKeys(findTestObject('JobSubmissionForm/textBx_file_filter'), Keys.chord(Keys.ENTER))
		extentTest.log(Status.PASS, 'Searched for input	  file - '+InputFile)
		WebUI.delay(3)
		TestObject newFileObj =  WebUI.modifyObjectProperty(findTestObject('JobSubmissionForm/File_InputFile'), 'text', 'equals',InputFile, true)
		WebUI.click(newFileObj)
		WebUI.rightClick(newFileObj)
		extentTest.log(Status.PASS, 'Right Clicked on  Input file ' + InputFile)
		WebUI.delay(2)
		newRFBContextMnOption = WebUI.modifyObjectProperty(findTestObject('Object Repository/NewJobPage/ContextMenu_RFB_FilePicker'),'id', 'equals', idForCntxtMn, true)
		WebUI.click(newRFBContextMnOption)
		extentTest.log(Status.PASS, 'Clicked on context menu - ' + idForCntxtMn)


		for (int i =0 ; i<4 ;i++) { def submitBtn =
			CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(  findTestObject('JobSubmissionForm/button_Submit_Job'),10, extentTest, 'Submit Button')
			if (submitBtn) {
				WebUI.click(findTestObject('JobSubmissionForm/button_Submit_Job'))
				extentTest.log(Status.PASS, 'Clicked on Submit Button ')
				ss=msg+'submitCount-'+i
						}
			WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobSubmission'), 5)
			def jobText =  WebUI.getText(findTestObject('Notificactions/Notification_JobSubmission'))
			extentTest.log(Status.PASS, 'Notification Generated')
			def jobID=CustomKeywords.'operations_JobsModule.GetJobRowDetails.getJobID'( jobText)
			extentTest.log(Status.PASS, msg+'Job Number - '+i+' Job ID - ' +  jobID)
			ss=msg+'Job Number - '+i+' Job ID - ' +  jobID
		}
	

	}
	CustomKeywords.'preReq.jobMonitorigColFilter.addColumn'(extentTest)


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