import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.support.events.EventFiringWebDriver

import com.kms.katalon.core.exception.StepErrorException
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import com.aventstack.extentreports.ExtentReports
import com.aventstack.extentreports.ExtentTest
import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status


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

CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//=====================================================================================
def addBtn
def lblMngSrv
def colid='name'
WebUI.delay(2)

try
{
	WebUI.delay(2)
	//WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
	WebUI.delay(3)
	WebUI.mouseOver(findTestObject('Cluster_Registration/Manageservice'))
	WebUI.click(findTestObject('Cluster_Registration/Manageservice'))
	extentTest.log(Status.PASS, 'Click on Manage Service')
	WebUI.delay(2)

	switch (userChoice)
	{
		case 'valid':
			WebUI.rightClick(findTestObject('Cluster_Registration/Available'))
			WebUI.delay(3)
			WebUI.click(findTestObject('Cluster_Registration/Delete'))
			extentTest.log(Status.PASS, 'Delete Existing Cluster')

			WebUI.click(findTestObject('FilesPage/Confirm_button'))
			extentTest.log(Status.PASS, 'Click on Ok button')

			WebUI.click(findTestObject('Cluster_Registration/Configure_Services'))
			extentTest.log(Status.PASS, 'Click on Configure Service')

			WebUI.click(findTestObject('Cluster_Registration/Server_Name'))
			WebUI.setText(findTestObject('Cluster_Registration/Server_Name'),server)
			extentTest.log(Status.PASS, 'Add server name'+ server)

			WebUI.click(findTestObject('Cluster_Registration/Url'))
			WebUI.setText(findTestObject('Cluster_Registration/Url'),url)
			extentTest.log(Status.PASS, 'Add url' + url)

			WebUI.click(findTestObject('Cluster_Registration/Username'))
			WebUI.setText(findTestObject('Cluster_Registration/Username'),username)
			extentTest.log(Status.PASS, 'Add username' + username)

			WebUI.click(findTestObject('Cluster_Registration/Password'))
			WebUI.setText(findTestObject('Cluster_Registration/Password'),password)
			extentTest.log(Status.PASS, 'Add password' + password)

			WebUI.click(findTestObject('Cluster_Registration/Rootdir'))
			WebUI.setText(findTestObject('Cluster_Registration/Rootdir'),rootdir)
			extentTest.log(Status.PASS, 'Add root directory' )

			addBtn = CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('Cluster_Registration/Add_Server'),
					5 , extentTest ,'add server')

			WebUI.click(findTestObject('Cluster_Registration/Add_Server'))
			extentTest.log(Status.PASS, 'Click on add server')

			lblMngSrv=CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('Object Repository/Cluster_Registration/Label_ManageServer'),
					15 , extentTest ,'Manage Server heading')

			if (lblMngSrv) {

				result = CustomKeywords.'operations_JobsModule.GetJobRowDetails.listServer'(katalonWebDriver, colid, server,extentTest)
				extentTest.log(Status.PASS, 'Verifed the cluster')

			}

			break

		case'edit':
			WebUI.rightClick(findTestObject('Cluster_Registration/Available'))
			WebUI.delay(3)
			WebUI.click(findTestObject('Cluster_Registration/Edit'))
			extentTest.log(Status.PASS, 'Edit Existing Cluster')

			WebUI.click(findTestObject('Cluster_Registration/Username'))
			WebUI.clearText(findTestObject('Cluster_Registration/Username'))
			WebUI.setText(findTestObject('Cluster_Registration/Username'),username)

			WebUI.click(findTestObject('Cluster_Registration/Password'))
			WebUI.clearText(findTestObject('Cluster_Registration/Password'))
			WebUI.setText(findTestObject('Cluster_Registration/Password'),password)


			WebUI.click(findTestObject('Cluster_Registration/Addrootdir'))

			WebUI.click(findTestObject('Cluster_Registration/root_dir1'))
			WebUI.setText(findTestObject('Cluster_Registration/root_dir1'),rootdir1)
			extentTest.log(Status.PASS, 'Click on add root dir')

			WebUI.click(findTestObject('Cluster_Registration/Add_Server'))
			extentTest.log(Status.PASS, 'Click on add server')

			lblMngSrv=CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(findTestObject('Object Repository/Cluster_Registration/Label_ManageServer'),
					15 , extentTest ,'Manage Server heading')

			result = CustomKeywords.'operations_JobsModule.GetJobRowDetails.listServer'(katalonWebDriver, colid, server,extentTest)

			break

		case 'delete':

			WebUI.delay(2)
			WebUI.rightClick(findTestObject('Cluster_Registration/Available'))
			WebUI.delay(1)
			WebUI.click(findTestObject('Cluster_Registration/Delete'))
			extentTest.log(Status.PASS, 'Delete Existing Cluster')

			WebUI.click(findTestObject('FilesPage/Confirm_button'))
			extentTest.log(Status.PASS, 'Click on Ok')

			lblMngSrv=CustomKeywords.'customWait.WaitForElement.WaitForelementPresent'(	findTestObject('Object Repository/Cluster_Registration/text_noServer')	,
					15 , extentTest ,'Configure one or more servers - Link ')

			if (lblMngSrv) {
				result=true
				extentTest.log(Status.PASS, 'Verifed the cluster is deleted ')
			}



			break
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
 