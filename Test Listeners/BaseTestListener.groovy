import java.text.SimpleDateFormat

import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.support.events.EventFiringWebDriver

import com.assertthat.selenium_shutterbug.utils.web.Browser
import com.aventstack.extentreports.ExtentReports
import com.aventstack.extentreports.MediaEntityBuilder
import com.aventstack.extentreports.Status
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

class BaseTestListener {
	/**
	 * Executes before every test suite starts.
	 * @param testSuiteContext: related information of the executed test suite.
	 */
	ExtentReports extent
	//= CustomKeywords.'generateReports.GenerateReport.createSpark'(ReportFile, GlobalVariable.G_Browser, GlobalVariable.G_BrowserVersion)
	def reportFolder=(RunConfiguration.getReportFolder()+'/')
	def ReportFile
	def filePath
	def reportFliePath
	@BeforeTestSuite
	def BeforeTestSuite(TestSuiteContext testSuiteContext)
	{
		String execID = RunConfiguration.getExecutionSourceName()
		def Browser=GlobalVariable.G_Browser

		filePath = (RunConfiguration.getProjectDir() + '/reports/'+execID+Browser+'.txt')
		//reportFliePath=RunConfiguration.getReportFolder()
		reportFliePath=(RunConfiguration.getProjectDir() + '/ExtentReports/')
		GlobalVariable.G_TestSuite=execID
		//GlobalVariable.G_ReportFolder=reportFliePath
		GlobalVariable.G_ReportFolder=RunConfiguration.getReportFolder()
		GlobalVariable.G_ConfigFile=filePath
		def date = new Date()
		def sdf = new SimpleDateFormat("ddMMyyyy_HHmmss")
		def execTime = sdf.format(date)
		String execTag=Browser+'_'+execTime
		//GlobalVariable.G_ReportName=execID+'_'+execTag
		//ReportFile = (GlobalVariable.G_ReportName + '.html')
		ReportFile =execID+'.html'

		extent = CustomKeywords.'generateReports.GenerateReport.createSpark'(ReportFile, GlobalVariable.G_Browser, GlobalVariable.G_BrowserVersion)
		println ("From Brefore Suite")
		println(RunConfiguration.getReportFolder())
	}
	@BeforeTestCase
	def BeforeTestCase(TestCaseContext testCaseContext) {

		//String tcName="{variable_1=v3, passwd=rohini, variable_2=v4, variable_0=v2, variable=v1, usename=rohini, TestCaseName=Login,variable_0=v2,}"
		String variableList=testCaseContext.getTestCaseVariables()
		println("context - "+variableList)
		println(testCaseContext.getTestCaseId())
		def tcID=testCaseContext.getTestCaseId()

		String [] tcScript=tcID.split('\\/')
		def len=tcScript.size()
		def tcScriptName=tcScript[len-1]
		println("==========================")
		println(tcScriptName)
		println("==========================")

		String testcaseName=CustomKeywords.'generateReports.testCaseNameChange.toSplit'(variableList,'TestCaseName')
		String UpdatedtestcaseName=CustomKeywords.'generateReports.testCaseNameChange.updateTCName'(testcaseName,variableList,tcScriptName)
		println ("new tn  --- "+UpdatedtestcaseName)
		println("-------------------------")
		if(tcScriptName.equals('JobSubmission-FileOperations')&&UpdatedtestcaseName.contains('tile view'))
		{
			println('Skipping ')
			testCaseContext.skipThisTestCase()

		}
		else
		{

			GlobalVariable.G_ExtentTest = extent.createTest(UpdatedtestcaseName)
			def extentTest=GlobalVariable.G_ExtentTest
			try {
				WebUI.openBrowser('')
				GlobalVariable.G_ExtentTest.log(Status.PASS, 'Navigated to Acces Instance - '+GlobalVariable.G_BaseUrl)
				WebUI.deleteAllCookies()
				WebUI.navigateToUrl(GlobalVariable.G_BaseUrl)
				WebUI.maximizeWindow()
				WebDriver driver = DriverFactory.getWebDriver()
				Capabilities caps =((RemoteWebDriver) (((EventFiringWebDriver) driver).getWrappedDriver())).getCapabilities()
				def bn= caps.getBrowserName()
				def bv = caps.getVersion()
			}
			catch (Exception ex)
			{
				String screenShotPath = ((reportFolder + tn) + GlobalVariable.G_Browser) + '.png'
				WebUI.takeScreenshot(screenShotPath)
				String p = (tn + GlobalVariable.G_Browser) + '.png'
				extentTest.log(Status.FAIL, ex)
				extentTest.fail(MediaEntityBuilder.createScreenCaptureFromPath(p).build())
			}

		}

	}

	@AfterTestSuite
	def AfterTestSuite()
	{

		extent.flush()
		println ("*****************************************************************")
		println("After Suite ")
		println ("*****************************************************************")
		println ("Report location - "+reportFliePath)

	}

}