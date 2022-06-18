package operations_FileModule

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.exception.StepErrorException
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.aventstack.extentreports.Status

import internal.GlobalVariable as GlobalVariable



public class ChangeView {
	@Keyword
	def changePageView(String TestCaseName,extentTest) {

		try {
			def tileView
			def listView
			def isElementPresent
			def editIcon
			TestObject viewIconTile = WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/Icon_ViewIcon'), 'title','equals', 'Tile View', true)
			TestObject viewIconList = WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/Icon_ViewIcon'), 'title','equals', 'List View', true)

			tileView = WebUI.verifyElementPresent(findTestObject('Object Repository/FilesPage/SortLableTileView'), 3, FailureHandling.CONTINUE_ON_FAILURE)

			listView = WebUI.verifyElementPresent(findTestObject('Object Repository/JobMonitoringPage/setting_icon'), 3, FailureHandling.CONTINUE_ON_FAILURE)

			println('current view - tile view  - ' + tileView)
			println('current view - list view - ' + listView)

			if (tileView) {
				println (" need to change to list view ")
				isElementPresent = WebUI.verifyElementPresent(viewIconList, 3, FailureHandling.CONTINUE_ON_FAILURE)
				if(isElementPresent) {
					println("listview Icon present ")
				}
				else {
					WebUI.refresh()
				}
				WebUI.click(viewIconList)
				extentTest.log(Status.PASS, 'Changed View to ListView')
				WebUI.delay(2)
			}

			if (TestCaseName.contains('tile view')) {
				println("in tile wala if")
				isElementPresent = WebUI.verifyElementPresent(viewIconTile, 3, FailureHandling.CONTINUE_ON_FAILURE)

				if(isElementPresent) {
					println("tile Icon present ")
				}
				else {
					WebUI.refresh()
				}
				WebUI.click(viewIconTile)
				extentTest.log(Status.PASS, 'Changed View to Tile View as per the test case - ' +TestCaseName)
			}

			editIcon=(new customWait.WaitForElement()).WaitForelementPresent(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'), 5,extentTest, 'Page Loaded')
			if(editIcon) {
				extentTest.log(Status.PASS, 'Page loaded after changing the view')
			}
			else {
				extentTest.log(Status.PASS, 'Page not loaded after changing the view')
				extentTest.log(Status.PASS, 'Refreshing the browser ')
				WebUI.refresh()
				WebUI.delay(3)
				int i
				while (editIcon == false && i<10) {
					WebUI.delay(1)
					try {
						WebUI.verifyElementPresent(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'), 1)
						editIcon =WebUI.verifyElementClickable(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
						extentTest.log(Status.PASS, 'Page is loaded now - took '+i+' secs')
					}
					catch (Exception  ex) {
						println("Exception")
					}
					catch (StepFailedException e) {
						println("StepFailedException")
					}
					println(editIcon)
					i++
				}
			}
		}
		catch (Exception  ex) {

			String screenShotPath='ExtentReports/'+TestCaseName+GlobalVariable.G_Browser+'.png'
			WebUI.takeScreenshot(screenShotPath)
			extentTest.log(Status.FAIL,ex)
		}
		catch (StepErrorException  e) {

			String screenShotPath='ExtentReports/'+TestCaseName+GlobalVariable.G_Browser+'.png'
			WebUI.takeScreenshot(screenShotPath)
			extentTest.log(Status.FAIL,e)
		}
	}
}
