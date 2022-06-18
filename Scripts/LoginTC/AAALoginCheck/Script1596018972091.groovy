import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status
import com.kms.katalon.core.exception.StepErrorException
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable





//====================================================================================
//====================================================================================
//ReportFile = (GlobalVariable.G_ReportName + '.html')
//ExtentReports extent = CustomKeywords.'generateReports.GenerateReport.createSpark'(ReportFile, GlobalVariable.G_Browser, GlobalVariable.G_BrowserVersion)
//def   extentTest = extent.createTest(TestCaseName)
//=====================================================================================
def extentTest=GlobalVariable.G_ExtentTest
//=====================================================================================
//CustomKeywords.'toLogin.ForLogin.Login'(extentTest)
//=====================================================================================
extentTest.log(Status.PASS, 'step 1 ')
extentTest.log(Status.PASS, 'step 2')
//WebUI.click(findTestObject('GenericObjects/TitleLink_Jobs'))
//WebUI.delay(2)

//extent.flush()

try {
	CustomKeywords.'toLogin.ForLogin.Login'(extentTest)

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