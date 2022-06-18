import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status
import com.kms.katalon.core.exception.StepErrorException
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import com.kms.katalon.core.configuration.RunConfiguration

//====================================================================================
def extentTest=GlobalVariable.G_ExtentTest
//=====================================================================================
CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//=====================================================================================

def result
def isAppPresent
TestObject newApp
WebUI.delay(2)

try {

	def jobsTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/JobsTab_disabled'),
			20, extentTest, 'Jobs Tab')

	if (jobsTab) {
		WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
	}
	extentTest.log(Status.PASS, 'Navigated Jobs Tab')



	WebUI.click(findTestObject('Preferences/Profiletab'))

	extentTest.log(Status.PASS, 'Click on profile tab')

	WebUI.delay(2)

	WebUI.click(findTestObject('AppComposer/Appcomposer'))

	extentTest.log(Status.PASS, 'Click on App composer')



	// ============================================
	// creating apps for publish , unbublish , rename , delete ,edit
	// ============================================


	switch (userChoice) {
		case 'new':

			WebUI.click(findTestObject('AppComposer/AppName'))
			extentTest.log(Status.PASS, 'Click to Entered App name')

			WebUI.doubleClick(findTestObject('AppComposer/AppName'))
			WebUI.setText(findTestObject('AppComposer/AppName'), '')
			WebUI.setText(findTestObject('AppComposer/AppName'), app)
			extentTest.log(Status.PASS, 'Entered App name - ' + app)

			WebUI.click(findTestObject('AppComposer/Executableinput'))
			extentTest.log(Status.PASS, 'Click to add exec commands')

			WebUI.setText(findTestObject('AppComposer/Executableinput'), '')
			WebUI.setText(findTestObject('AppComposer/Executableinput'), exec)
			extentTest.log(Status.PASS, 'Add Exec commands - ' + exec)

			WebUI.click(findTestObject('AppComposer/Save'))
			extentTest.log(Status.PASS, 'Click on Save button')

			newApp=WebUI.modifyObjectProperty(findTestObject('AppComposer/TestApp'), 'title', 'equals', app, true)

			isAppPresent=CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(newApp,10, extentTest, 'app listed')

			WebUI.delay(3)
			WebUI.click(findTestObject('AppComposer/JobsTab'))
			extentTest.log(Status.PASS, 'Navigated to Jobs Tab')

			TestObject newAppObj = WebUI.modifyObjectProperty(findTestObject('NewJobPage/AppList_ShellScript'), 'id', 'contains',app, true)
			def jobsPageApp=CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(newAppObj,10, extentTest, 'app listed on jobs page')

			if (jobsPageApp)
			{
				result=true
				extentTest.log(Status.PASS, 'Application - ' + app + ' listed on Jobs page')
			}

			else {
				result=false
			}

			break


		case 'PopUp':


			newApp=WebUI.modifyObjectProperty(findTestObject('AppComposer/TestApp'), 'title', 'equals', app, true)

			isAppPresent=CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(newApp,10, extentTest, 'app listed')
			WebUI.click(newApp)
			WebUI.delay(2)
			if (isAppPresent)
			{
				WebUI.scrollToElement(findTestObject('AppComposer/Save'), 3)
				WebUI.click(findTestObject('AppComposer/Save'))
			}

			WebUI.verifyElementPresent(findTestObject('AppComposer/InputF'), 3)

			def filePath = RunConfiguration.getProjectDir() + '/Upload/JobFiles/Running.sh'
			def newFP = new generateFilePath.filePath().getFilePath(filePath)
			println(newFP)
			WebUI.uploadFile(findTestObject('AppComposer/InputF'), newFP)
			extentTest.log(Status.PASS, 'Upload input file')

			WebUI.delay(2)

			WebUI.click(findTestObject('AppComposer/Submit_btn'))
			extentTest.log(Status.PASS, 'Click on submit and test  button')

			def isPopUpPresent=isAppPresent=CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('AppComposer/JMPanel-AppComp'),15, extentTest, 'app listed')
			if(isPopUpPresent)
			{
				extentTest.log(Status.PASS, 'Test Job Monitoring panel displayed')
				return true
			}
			else
			{
				return false
			}
			break

		case'PublishAll':


			newApp=WebUI.modifyObjectProperty(findTestObject('AppComposer/TestApp'), 'title', 'equals', app, true)
			isAppPresent=CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(newApp,10, extentTest, 'app listed')
			WebUI.click(newApp)
			WebUI.delay(2)

			WebUI.click(findTestObject('AppComposer/Publishall'))
			extentTest.log(Status.PASS, 'Click on Publish to all button')
			WebUI.delay(5)
			WebUI.click(findTestObject('AppComposer/Ok_btn'))
			extentTest.log(Status.PASS, 'Click on Ok button , PAS configuration change pop up')

			WebUI.click(findTestObject('Preferences/Profiletab'))
			extentTest.log(Status.PASS, 'Click on profile tab')
			WebUI.delay(2)
			WebUI.click(findTestObject('Landing_Page/ListItem_Logout'))
			extentTest.log(Status.PASS, 'Click on logout')
			WebUI.verifyElementVisible(findTestObject('LogOut-PopUp/Title_Logout'))
			WebUI.delay(2)
			WebUI.click(findTestObject('GenericObjects/btn_Yes'))
			extentTest.log(Status.PASS, 'Click on yes button')
			WebUI.delay(3)
			WebUI.click(findTestObject('AppComposer/Login'))
			extentTest.log(Status.PASS, 'Click on Login again')
			WebUI.setText(findTestObject('LoginPage/username_txtbx'),'rohini')
			extentTest.log(Status.PASS, 'Enter username rohini ')
			WebUI.setText(findTestObject('LoginPage/password_txtbx'), 'rohini')
			extentTest.log(Status.PASS, 'Enter  password  rohini')
			WebUI.click(findTestObject('LoginPage/login_btn'))
			extentTest.log(Status.PASS, 'Click on Login')


			jobsTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/JobsTab_disabled'),
					20, extentTest, 'Jobs Tab')

			if (jobsTab) {
				WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
			}


			TestObject newAppObj = WebUI.modifyObjectProperty(findTestObject('NewJobPage/AppList_ShellScript'), 'id', 'contains',app, true)
			def jobsPageApp=CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(newAppObj,10, extentTest, 'app listed on jobs page')

			if (jobsPageApp)
			{
				result=true
				extentTest.log(Status.PASS, 'Application - ' + app + ' listed on Jobs page for other user ')
			}

			else {
				result=false
			}

			break

		case'Unpublish':

			newApp=WebUI.modifyObjectProperty(findTestObject('AppComposer/TestApp'), 'title', 'equals', app, true)
			isAppPresent=CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(newApp,10, extentTest, 'app listed')
			WebUI.click(newApp)
			WebUI.delay(2)
			WebUI.click(findTestObject('AppComposer/Ellipses'))
			WebUI.click(findTestObject('AppComposer/Unpublish'))
			extentTest.log(Status.PASS, 'Clicked on Unpublish option')

			result=WebUI.verifyElementPresent(findTestObject('AppComposer/Unpubpop'), 10)
			println("result +++++"+result)

			WebUI.delay(5)
			WebUI.click(findTestObject('AppComposer/Ok_btn'))
			extentTest.log(Status.PASS, 'Click on Ok button , PAS configuration change pop up')
			WebUI.click(findTestObject('Preferences/Profiletab'))
			extentTest.log(Status.PASS, 'Click on profile tab')
			WebUI.delay(2)
			WebUI.click(findTestObject('Landing_Page/ListItem_Logout'))
			extentTest.log(Status.PASS, 'Click on logout')
			WebUI.verifyElementVisible(findTestObject('LogOut-PopUp/Title_Logout'))
			WebUI.delay(2)
			WebUI.click(findTestObject('GenericObjects/btn_Yes'))
			extentTest.log(Status.PASS, 'Click on yes button')
			WebUI.delay(3)
			WebUI.click(findTestObject('AppComposer/Login'))
			extentTest.log(Status.PASS, 'Click on Login again')
			WebUI.setText(findTestObject('LoginPage/username_txtbx'),'rohini')
			extentTest.log(Status.PASS, 'Enter username rohini ')
			WebUI.setText(findTestObject('LoginPage/password_txtbx'), 'rohini')
			extentTest.log(Status.PASS, 'Enter  password  rohini')
			WebUI.click(findTestObject('LoginPage/login_btn'))
			extentTest.log(Status.PASS, 'Click on Login')

			jobsTab = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('GenericObjects/JobsTab_disabled'),
					20, extentTest, 'Jobs Tab')

			if (jobsTab) {
				WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
			}

			TestObject newAppObj = WebUI.modifyObjectProperty(findTestObject('NewJobPage/AppList_ShellScript'), 'id', 'contains',app, true)
			def jobsPageApp=WebUI.verifyElementPresent(newAppObj, 3,FailureHandling.CONTINUE_ON_FAILURE)
			if (jobsPageApp)
			{
				result=false

			}
			else {
				result=true
				extentTest.log(Status.PASS, 'Application - ' + app + ' no more listed on Jobs page for other user ')
			}
			break

		case 'Delete':


			newApp=WebUI.modifyObjectProperty(findTestObject('AppComposer/TestApp'), 'title', 'equals', app, true)
			isAppPresent=CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(newApp,10, extentTest, 'app listed')
			WebUI.click(newApp)
			WebUI.delay(2)
			WebUI.click(findTestObject('AppComposer/Ellipses'))
			extentTest.log(Status.PASS, 'Select the app '+ app +' created and click on ellipses')
			TestObject newOption=WebUI.modifyObjectProperty(findTestObject('AppComposer/Unpublish'), 'text', 'equals', 'Delete', true)
			WebUI.click(newOption)

			
			extentTest.log(Status.PASS, 'Clicked on Delete option')
			def msg='Deleted '+app+' Deleteapp application successfully'
			TestObject newnotification = WebUI.modifyObjectProperty(findTestObject('AppComposer/DelPop'), 'text', 'equals', msg, true)
			WebUI.waitForElementPresent(newnotification, 5,FailureHandling.CONTINUE_ON_FAILURE)

			
			TestObject newAppObj = WebUI.modifyObjectProperty(findTestObject('NewJobPage/AppList_ShellScript'), 'id', 'contains',app, true)
			def jobsPageApp=WebUI.verifyElementPresent(newAppObj, 3,FailureHandling.CONTINUE_ON_FAILURE)
			if (jobsPageApp)
			{
				result=false

			}
			else {
				result=true
				extentTest.log(Status.PASS, 'Application - ' + app + ' no more listed on Jobs page  ')
			}
		

			break


		case'Edit':

			newApp=WebUI.modifyObjectProperty(findTestObject('AppComposer/TestApp'), 'title', 'equals', app, true)
			isAppPresent=CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(newApp,10, extentTest, 'app listed')
			WebUI.click(newApp)
			WebUI.delay(2)
			WebUI.click(findTestObject('AppComposer/Ellipses'))
			extentTest.log(Status.PASS, 'Select the app '+ app +' created and click on ellipses')
			TestObject newOption=WebUI.modifyObjectProperty(findTestObject('AppComposer/Unpublish'), 'text', 'equals', 'Edit', true)
			WebUI.click(newOption)
			extentTest.log(Status.PASS, 'Click on Edit option')


			WebUI.clearText(findTestObject('AppComposer/AppName'))
			WebUI.setText(findTestObject('AppComposer/AppName'),newAppName)
			extentTest.log(Status.PASS, 'edited app name - EditedApp' )

			WebUI.click(findTestObject('AppComposer/Save'))
			extentTest.log(Status.PASS, 'Click on Save button')
			WebUI.waitForElementPresent(findTestObject('Object Repository/AppComposer/AppSaved'), 5)

			def editnotification = WebUI.getText(findTestObject('Object Repository/AppComposer/AppSaved'))

			extentTest.log(Status.PASS, 'Notification Generated ' + editnotification)

			WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
			extentTest.log(Status.PASS, 'Click on Jobs Tab')


			TestObject newAppObj = WebUI.modifyObjectProperty(findTestObject('NewJobPage/AppList_ShellScript'), 'id', 'contains',newAppName, true)
			def jobsPageApp=WebUI.verifyElementPresent(newAppObj, 10,FailureHandling.CONTINUE_ON_FAILURE)
			if (jobsPageApp)
			{
				result=true
				extentTest.log(Status.PASS, 'Edited Application - '+ newAppName+' listed on Jobs page for other user ')
			}
			else {
				result=false

			}

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