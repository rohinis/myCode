import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.aventstack.extentreports.ExtentReports
import com.aventstack.extentreports.ExtentTest
import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status
import com.kms.katalon.core.exception.StepErrorException
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable


//====================================================================================
def extentTest=GlobalVariable.G_ExtentTest
//=====================================================================================
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//=====================================================================================
def isWarningPresent
def result
try
{

	WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
	WebUI.delay(2)
	WebUI.click(findTestObject('Generic_Actions/Generic_Actions'))
	extentTest.log(Status.PASS, 'Click on Generic actions')

	WebUI.click(findTestObject('Generic_Actions/Files'))
	extentTest.log(Status.PASS, 'Clicked on Files Generic Action ')

	switch(userChoice)
	{

		case'Required':

			WebUI.click(findTestObject('Object Repository/GenericObjects/btn_Yes'))
			extentTest.log(Status.PASS, 'Clicked on run')
			WebUI.delay(3)

			isWarningPresent=WebUI.verifyElementPresent(findTestObject('Generic_Actions/Confirm_button'), 5)
			if(isWarningPresent)
			{
				result=true
				extentTest.log(Status.PASS, 'Error icon dislayed for requird field')

			}
	break


		case'Enabled':

			WebUI.verifyElementPresent(findTestObject('Generic_Actions/Generic_Actions'), 5)
			extentTest.log(Status.PASS, 'Verify generic actions is enabled')

			break

		case'Run':

			genericAction=WebUI.modifyObjectProperty(findTestObject('Generic_Actions/GenericActions_Panel'), 'title', 'equals', 'Files (hpccluster)', true)
			WebUI.click(genericAction)
			extentTest.log(Status.PASS, 'Clicked on generic action - Files (hpccluster)')
			WebUI.setText(findTestObject('JobMonitoringPage/txtBx_GenericActionInput'), GlobalVariable.G_userName)
			extentTest.log(Status.PASS, 'Entered username - '+GlobalVariable.G_userName)
			WebUI.click(findTestObject('Generic_Actions/Confirm_button'))
			extentTest.log(Status.PASS, 'Clicked on Run button')
			WebUI.delay(2)
			result=WebUI.verifyElementPresent(findTestObject('Object Repository/Generic_Actions/TextArea_GAResults'), 3,FailureHandling.CONTINUE_ON_FAILURE)
			
			WebUI.click(findTestObject('Generic_Actions/Close_button'))
			extentTest.log(Status.PASS, 'Clicked on Close button')
		

			break

	}

	if (result)
		{
			extentTest.log(Status.PASS, ('Verified ::  ' + TestCaseName) + ' :: Sucessfully')
		} else {
			extentTest.log(Status.FAIL, ( TestCaseName) + ' :: failed')
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
