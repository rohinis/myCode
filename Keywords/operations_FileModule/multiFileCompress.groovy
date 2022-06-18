package operations_FileModule

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.aventstack.extentreports.Status

public class multiFileCompress {
	@Keyword
	def VerifyCompressedFile(String TestCaseName , extentTest) {
		println("Testcasename - "+TestCaseName)
		String fileNew='five_archive_'

		WebUI.click(findTestObject('FilesPage/FilesSearch_filter'))
		WebUI.waitForElementVisible(findTestObject('FilesPage/FilesSearch_filter'), 2)
		println(fileNew)
		WebUI.setText(findTestObject('FilesPage/FilesSearch_filter'), fileNew)
		WebUI.sendKeys(findTestObject('JobDetailsPage/TextBx_DetailsFilter'), Keys.chord(Keys.ENTER))
		extentTest.log(Status.PASS, 'Looking for archived file, search text entered - '+fileNew)

		if(TestCaseName.contains('tile view')) {

			String xpath_compressedfileXpathTV = "//label[@id='file_text'][contains(text(),'five_archive_')]"
			println xpath_compressedfileXpathTV
			// Building job indentifier obj
			TestObject compressedFileObjIdentifierTV = new TestObject('objectName')
			compressedFileObjIdentifierTV.addProperty('xpath', ConditionType.EQUALS, xpath_compressedfileXpathTV)
			String CompressTitle=WebUI.getAttribute(compressedFileObjIdentifierTV,'title')
			def newEmelemt=(new customWait.WaitForElement()).WaitForelementPresent(compressedFileObjIdentifierTV, 5,extentTest, 'Compressed File')
			if(newEmelemt)
			{
				extentTest.log(Status.PASS, ' Verified the compresed file - '+CompressTitle)
			}
			println("==========================================================")
			println(CompressTitle)
			println("==========================================================")
			return CompressTitle

		}
		else {
			String xpath_compressedfileXpath = "//div[@class='show-text-ellipsis retain-whitespace'][contains(text(),'five_archive_')]"
			println xpath_compressedfileXpath
			// Building job indentifier obj
			TestObject compressedFileObjIdentifierLatest = new TestObject('objectName')
			compressedFileObjIdentifierLatest.addProperty('xpath', ConditionType.EQUALS, xpath_compressedfileXpath)
			WebUI.delay(2)
			String CompressTitle=WebUI.getAttribute(compressedFileObjIdentifierLatest,'title')
			def newEmelemt=(new customWait.WaitForElement()).WaitForelementPresent(compressedFileObjIdentifierLatest, 5,extentTest, 'Compressed File')
			if(newEmelemt)
			{
				extentTest.log(Status.PASS, ' Verified the compresed file - '+CompressTitle)
			}
			println("==========================================================")
			println(CompressTitle)
			println("==========================================================")
			return CompressTitle
		}
	}


	@Keyword
	def VerifyUnCompressedFile(String CompressedFileName , String TestCaseName,extentTest) {

		String CompressedFileXpath
		String FolderXpath
		WebUI.delay(2)
		String FolderName
		if(TestCaseName.contains('tile view')) {

			String f1="//label[@id='file_text'][@title='"
			String f2 ="']"
			CompressedFileXpath=f1+CompressedFileName+f2
			println("==============================================================")
			println (' CompressedFileXpath  TV---- ' +CompressedFileXpath)
			println("==============================================================")

			String[] splitAddress = CompressedFileName.split('\\.')
			String x1="//label[@id='tile_dir_input'][@title='"
			FolderXpath=x1+splitAddress[0]+f2
			FolderName=splitAddress[0]
			println("==============================================================")
			println (' Folder Name TV ---- ' +FolderXpath)
			println("==============================================================")

			WebUI.rightClick(findTestObject('Object Repository/FilesPage/Tick_SelectedTick_TV'))
		}
		else {
			String p1="//div[@title = '"
			String p2 ="']"
			CompressedFileXpath=p1+CompressedFileName+p2
			println("==============================================================")
			println (' CompressedFileXpath  LV---- ' +CompressedFileXpath)
			println("==============================================================")

			String[] splitAddress = CompressedFileName.split('\\.')
			FolderXpath=p1+splitAddress[0]+p2
			FolderName=splitAddress[0]
			println("==============================================================")
			println (' Folder Name LV ---- ' +FolderXpath)
			println("==============================================================")
			TestObject newFileObj=new TestObject('objectName')
			newFileObj.addProperty('xpath', ConditionType.EQUALS, CompressedFileXpath)
			WebUI.click(newFileObj)
			WebUI.rightClick(newFileObj)
		}

		extentTest.log(Status.PASS, 'Right clicked on compressed file - '+CompressedFileName+' to invoke contextmenu' )

		def isSubmitPresent=WebUI.verifyElementPresent(findTestObject('Object Repository/JobSubmissionForm/button_Submit_Job'), 3, FailureHandling.CONTINUE_ON_FAILURE)
		if(isSubmitPresent){
			WebUI.click(findTestObject('Object Repository/JobSubmissionForm/SubMenu_Delete'))
		}
		else{
			TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/ContextMenu_FileOperation'), 'id', 'equals', 'Delete', true)
			WebUI.click(newFileOp)
		}
		WebUI.delay(2)
		extentTest.log(Status.PASS, 'Clicked on Delete menu item on compressed file - '+CompressedFileName)

		WebUI.click(findTestObject('GenericObjects/btn_Yes'))
		WebUI.delay(2)

		extentTest.log(Status.PASS, 'Clicked on Yes on Delete confirmation pop-up ')

		TestObject newFolderObj=new TestObject('objectName')
		newFolderObj.addProperty('xpath', ConditionType.EQUALS, FolderXpath)
		def isElemenet=WebUI.verifyElementPresent(newFolderObj, 5, FailureHandling.CONTINUE_ON_FAILURE)
		extentTest.log(Status.PASS, ' Navigated into uncompressed folder - '+FolderName)
		WebUI.click(newFolderObj)
		WebUI.doubleClick(newFolderObj)

		return true
	}


}
