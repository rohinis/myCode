import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.remote.RemoteWebElement
import org.openqa.selenium.support.events.EventFiringWebDriver

import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.exception.StepErrorException
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable


//====================================================================================
WebDriver driver = DriverFactory.getWebDriver()
EventFiringWebDriver eventFiring = ((DriverFactory.getWebDriver()) as EventFiringWebDriver)
WebDriver wrappedWebDriver = eventFiring.getWrappedDriver()
RemoteWebDriver katalonWebDriver = ((wrappedWebDriver) as RemoteWebDriver)
//====================================================================================

//====================================================================================
def extentTest=GlobalVariable.G_ExtentTest
//=====================================================================================

//CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//=====================================================================================
def navLocation = CustomKeywords.'generateFilePath.filePath.execLocation'()
//====================================================================================

def isProfilePersent

WebUI.delay(2)
try
{
	CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
	def jobsTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('NewJobPage/AppList_ShellScript'),
			20,extentTest,'App def')

	if (jobsTab) {
		WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
	}

	WebUI.delay(2)

	TestObject newAppObj = WebUI.modifyObjectProperty(findTestObject('NewJobPage/AppList_ShellScript'), 'id', 'equals', AppName, true)
	TestObject LeftNavAppIdentifier = CustomKeywords.'buildTestObj.CreateTestObjJobs.myLeftNavAppIdentifier'(proName)
	WebUI.click(newAppObj)

	def GP = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('Object Repository/NewJobPage/GenericProfile'),2,extentTest,'JSPage')

	if (GP) {
		extentTest.log(Status.PASS, 'Navigated to Job Submission For for - '+AppName)		}

	else
	{
		extentTest.log(Status.PASS, 'User not navigated to Job Submission For for - '+AppName)
	}


	extentTest.log(Status.PASS, 'Navigated to Job Submission For for - '+AppName)
	def errorPanel = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('JobSubmissionForm/JS_ErrorModalPanel'),2,extentTest,'ErrorPanel')

	if (errorPanel) {
		WebUI.click(findTestObject('Object Repository/JobSubmissionForm/button_Close'))
	}

	WebUI.delay(2)
	WebUI.click(findTestObject('Object Repository/NewJobPage/GenericProfile'))
	WebUI.delay(2)

	WebUI.scrollToElement(findTestObject('NewJobPage/List_NCPU_White_Theam'), 3)

	WebUI.setText(findTestObject('NewJobPage/List_NCPU_White_Theam'), '2')

	WebUI.setText(findTestObject('NewJobPage/List_Mem_WhiteTheam'), '120')

	extentTest.log(Status.PASS, 'Changed the value for NCPU and MEMORY fileds ')


	switch(ProfileType)
	{
		case'Local':
			WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
			def location=navLocation+'/ProfileFiles/'
			println('##################################################################')
			println (location)
			println('##################################################################')

			WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), location)
			WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
			extentTest.log(Status.PASS, 'Navigated to /stage/'+GlobalVariable.G_userName+'/ProfileFiles/ in RFB ')
			def filePath = (RunConfiguration.getProjectDir() + '/Upload/JobFiles/') + InputFile
			def newFP = CustomKeywords.'generateFilePath.filePath.getFilePath'(filePath)
			println(newFP)
			WebUI.uploadFile(findTestObject('Object Repository/JobSubmissionForm/LocalFileUploadElement'),newFP)
			extentTest.log(Status.PASS, 'Uploading File to RFB - '+InputFile)
			WebUI.delay(3)
			break;

		case 'Remote':
			WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
			def fileLocation=navLocation+'/ForProfiles/InputDeck/'
			println('##################################################################')
			println (fileLocation)
			println('##################################################################')
			WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), fileLocation)
			WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
			extentTest.log(Status.PASS, 'Navigated to /stage/InputDeck in RFB ')
			WebUI.waitForElementPresent(findTestObject('Object Repository/JobSubmissionForm/textBx_file_filter'), 5)
			WebUI.click(findTestObject('Object Repository/JobSubmissionForm/textBx_file_filter'))
			WebUI.setText(findTestObject('Object Repository/JobSubmissionForm/textBx_file_filter'), RemoteFile)
			WebUI.sendKeys(findTestObject('JobSubmissionForm/textBx_file_filter'), Keys.chord(Keys.ENTER))
			WebUI.delay(3)
			TestObject newFileObj = WebUI.modifyObjectProperty(findTestObject('JobSubmissionForm/File_InputFile'), 'text', 'equals',
					RemoteFile, true)
			WebUI.click(newFileObj)
			WebUI.rightClick(newFileObj)
			extentTest.log(Status.PASS, 'Context Menu Invoked for Input File - ' + RemoteFile)
			WebUI.delay(2)
			String idForCntxtMn = 'Add as ' + FileArg
			TestObject newRFBContextMnOption = WebUI.modifyObjectProperty(findTestObject('Object Repository/NewJobPage/ContextMenu_RFB_FilePicker'),
					'id', 'equals', idForCntxtMn, true)
			WebUI.click(newRFBContextMnOption)
			extentTest.log(Status.PASS, 'Selected Context Menu option - ' + idForCntxtMn)
			break;

		case 'Incomplete':
			WebUI.click(findTestObject('Object Repository/JobSubmissionForm/TxtBx_ForProfileReqFiled'))
			WebUI.clearText(findTestObject('Object Repository/JobSubmissionForm/TxtBx_ForProfileReqFiled'))
			break;

		case 'NoFile':
			println('No File selected')
			break;

	}

	def saveBtn = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('NewJobPage/Btn_Save As'),2,extentTest,'Save Profile Btn ')

	if(saveBtn)
	{
		WebUI.click(findTestObject('NewJobPage/Btn_Save As'))
		WebUI.delay(3)
	}

	WebUI.waitForElementPresent(findTestObject('NewJobPage/label_Save Profile'), 5)
	WebUI.delay(3)
	extentTest.log(Status.PASS, 'Clicked on Save As ')
	extentTest.log(Status.PASS, 'Entered profile name -  '+proName)

	WebUI.clearText(findTestObject('NewJobPage/TxtBx_saveProfile'))
	WebUI.delay(1)
	WebUI.setText(findTestObject('NewJobPage/TxtBx_saveProfile'), proName)
	//WebUI.sendKeys(findTestObject('NewJobPage/TxtBx_saveProfile'), proName)

	if(ProfileType.equals('Cancel'))
	{
		WebUI.click(findTestObject('Object Repository/NewJobPage/button_Cancel'))
		extentTest.log(Status.PASS, 'Clicked on Save As ')
		extentTest.log(Status.PASS, 'Entered profile name -  '+proName)
		extentTest.log(Status.PASS, 'Profile Creation Option Selected - '+ProfileType)
		def isProfilePersentProCan = WebUI.verifyElementPresent(LeftNavAppIdentifier, 3,FailureHandling.CONTINUE_ON_FAILURE)
		if(isProfilePersentProCan)
		{
			extentTest.log(Status.PASS, 'Profile not created - '+ proName)
		}
		else
		{
			extentTest.log(Status.PASS, 'Profile not created - '+ proName)
		}
	}

	else
	{
		extentTest.log(Status.PASS, 'Verified Test Case - AD-1509 Create Profile pop up validation.')
		WebUI.click(findTestObject('NewJobPage/button_Save'))
		WebUI.delay(3)
		if(ProfileType.equals('Duplicate'))
		{
			String  tooltip=WebUI.getAttribute(findTestObject('NewJobPage/TxtBx_saveProfile'), 'validationMessage')
			if (tooltip.contains('already exists'))
			{
				WebUI.click(findTestObject('Object Repository/NewJobPage/button_Cancel'))
			}
			WebUI.click(findTestObject('Object Repository/NewJobPage/GenericProfile'))

			String myXpath="//span[@title='"+proName+"']"
			List<WebElement> listElement = katalonWebDriver.findElements(By.xpath(myXpath))
			println listElement.size()
			if (listElement.size()<=1)
			{
				extentTest.log(Status.PASS, 'Verified duplicate profile not created' )
			}
			else
			{
				extentTest.log(Status.FAIL, 'Verified duplicate  profile created' )
				extentTest.log(Status.PASS, 'Profile list' )
				String myText
				for(int i =1;i<listElement.size();i++) {
					RemoteWebElement ele = listElement.get(i)
					myText=ele.getText()
					extentTest.log(Status.PASS, myText )

				}

			}

		}
		else
		{	WebUI.click(findTestObject('Object Repository/NewJobPage/GenericProfile'))
			isProfilePersent = WebUI.verifyElementPresent(LeftNavAppIdentifier, 5)

			if (isProfilePersent) {
				extentTest.log(Status.PASS, 'Profile present' )
				WebUI.waitForElementPresent(LeftNavAppIdentifier, 5)
				WebUI.click(LeftNavAppIdentifier)
				WebUI.delay(2)
				def isResetDiaPresent=WebUI.verifyElementPresent(findTestObject('Object Repository/JobSubmissionForm/Title_reset'),3,FailureHandling.CONTINUE_ON_FAILURE)
				extentTest.log(Status.PASS, 'isResetDiaPresent  -- ' +isResetDiaPresent )
				if(isResetDiaPresent)
				{
					WebUI.verifyElementPresent(findTestObject('Object Repository/JobSubmissionForm/Text_Reset'),3)
					WebUI.click(findTestObject('Object Repository/JobMonitoringPage/button_Yes'))
				}
				WebUI.click(LeftNavAppIdentifier)
				extentTest.log(Status.PASS, 'Verified newly created profile  -  '+proName)
				extentTest.log(Status.PASS, ('Verified ::  ' + TestCaseName) + ' :: Sucessfully')

			}
			else
			{

				extentTest.log(Status.FAIL, ( TestCaseName) + ' :: failed')
			}
		}
	}
println("From TC - "+GlobalVariable.G_ReportFolder )
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



