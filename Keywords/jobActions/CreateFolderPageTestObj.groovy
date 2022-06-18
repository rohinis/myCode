package jobActions

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys as Keys

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.aventstack.extentreports.Status

import internal.GlobalVariable

public class CreateFolderPageTestObj {
	@Keyword
	def FolderRowIdentifireForUnCompressedFile() {


		String x1 = '//div[contains(@data-path,\'/stage/'
		String x2 =GlobalVariable.G_userName
		String x3
		String x4
		if(Operation.contains('icon')) {
			x3='/FilesModule/FolderOpsIcons'
		}
		else {
			x3='/FilesModule/FolderOpsContextMenu'
			println ("else block form VerifyCompressedFile operation - "+  Operation)
		}

		if (TestCaseName.contains('tile view')) {
			x4 = '/MyFolderCompress_TV_archive_\')]'
		}
		else {
			x4 = '/MyFolderCompress_LV_archive_\')]'
		}
		String xpath_compressedfileXpath = x1+x2+x3+x4
		println xpath_compressedfileXpath
		// Building job indentifier obj
		TestObject compressedFileObjIdentifierLatest = new TestObject('objectName')
		compressedFileObjIdentifierLatest.addProperty('xpath', ConditionType.EQUALS, xpath_compressedfileXpath)
		return compressedFileObjIdentifierLatest
	}



	@Keyword
	def VerifyCompressedFolder(String TestCaseName , String Operation,extentTest) {
		println("Testcasename - "+TestCaseName)
		String fileNew
		if(TestCaseName.contains('tile view')) {
			fileNew='MyFolderCompress_TV_archive_'
		}
		else {
			fileNew='MyFolderCompress_LV_archive_'
		}

		WebUI.click(findTestObject('FilesPage/FilesSearch_filter'))
		WebUI.waitForElementVisible(findTestObject('FilesPage/FilesSearch_filter'), 2)
		println(fileNew)
		WebUI.setText(findTestObject('FilesPage/FilesSearch_filter'), fileNew)
		WebUI.sendKeys(findTestObject('JobDetailsPage/TextBx_DetailsFilter'), Keys.chord(Keys.ENTER))
		extentTest.log(Status.PASS, 'Looking for archived file, search text entered - '+fileNew)
		WebUI.delay(2)
		if(TestCaseName.contains('tile view')) {

			String xpath_compressedfileXpathTV = "//label[(contains(text(), 'MyFolderCompress_TV_archive_'))]"
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
			String xpath_compressedfileXpath = "//div[contains(text(),'MyFolderCompress_LV_archive_')]"
			println xpath_compressedfileXpath
			// Building job indentifier obj
			TestObject compressedFileObjIdentifierLatest = new TestObject('objectName')
			compressedFileObjIdentifierLatest.addProperty('xpath', ConditionType.EQUALS, xpath_compressedfileXpath)
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
	def VerifyUnCompressedFolder(String CompressedFileName , extentTest) {

		String CompressedFileXpath
		String FolderXpath
		WebUI.delay(2)
		String FolderName
		if(CompressedFileName.contains('TV')) {

			String f1="//label[(contains(text(), '"
			String f2 ="'))]"
			CompressedFileXpath=f1+CompressedFileName+f2
			println("==============================================================")
			println (' CompressedFileXpath  TV---- ' +CompressedFileXpath)
			println("==============================================================")

			String[] splitAddress = CompressedFileName.split('\\.')

			String x1="//label[@title='"
			String x2="']"
			FolderXpath=x1+splitAddress[0]+x2
			FolderName=splitAddress[0]
			println("==============================================================")
			println (' Folder Name TV ---- ' +FolderXpath)
			println("==============================================================")
			TestObject newFileObj=new TestObject('objectName')
			newFileObj.addProperty('xpath', ConditionType.EQUALS, CompressedFileXpath)
			WebUI.scrollToElement(newFileObj,3)
			WebUI.rightClick(findTestObject('Object Repository/FilesPage/Tick_SelectedTick_TV'))
		}
		else {

			String p1="//div[contains(text(),'"
			String p2 ="')]"
			CompressedFileXpath=p1+CompressedFileName+p2
			println("==============================================================")
			println (' CompressedFileXpath  LV---- ' +CompressedFileXpath)
			println("==============================================================")
			String x1="//div[@title='"
			String x2="']"
			String[] splitAddress = CompressedFileName.split('\\.')
			FolderXpath=x1+splitAddress[0]+x2
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

		def isSubmitPresent=(new customWait.WaitForElement()).WaitForelementPresent(findTestObject('JobSubmissionForm/button_Submit_Job'), 3,extentTest, 'Submit Button')
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
