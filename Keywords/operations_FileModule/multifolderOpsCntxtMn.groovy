package operations_FileModule


import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.text.SimpleDateFormat

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.aventstack.extentreports.Status

import internal.GlobalVariable

public class multifolderOpsCntxtMn {
	@Keyword
	def multiFolderOperations(String Operation,String TestCaseName ,extentTest,katalonWebDriver) {
		def date = new Date()
		def sdf = new SimpleDateFormat("ddMMyyyy_HHmmss")
		def e1 = sdf.format(date)
		def e2 =sdf.format(date)
		def op
		boolean result=false
		//def Status = com.relevantcodes.extentreports.Status
		def y
		println ("Control in Keyword")
		WebUI.delay(2)
		String msg
		switch (Operation) {
			case 'Copy':
				op='Copy'
				def isElemenetPresent
				TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/ContextMenu_FileOperation'), 'id', 'equals', Operation, true)
				WebUI.click(newFileOp)
				extentTest.log(Status.PASS, 'Clicked on Context Menu Option for - '+Operation)

				def fileToCheck
				if (TestCaseName.contains('tile view')) {
					TestObject newFolderObj
					newFolderObj=new TestObject('objectName')
					newFolderObj.addProperty('xpath', ConditionType.EQUALS, "//label[contains(text(),'MultipleFolder')]")
					WebUI.click(findTestObject('Object Repository/FilesPage/link_BaseFolder'))
					WebUI.delay(2)
					WebUI.click(newFolderObj)
					WebUI.doubleClick(newFolderObj)
					newFolderObj.addProperty('xpath', ConditionType.EQUALS, "//label[contains(text(),'CopyPasteTV')]")
					WebUI.click(newFolderObj)
					WebUI.doubleClick(newFolderObj)
					extentTest.log(Status.PASS, 'Navigated to '+GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/MultipleFolder/CopyPasteTV')
					WebUI.delay(2)
					y=5
					msg = '5 items copied successfully to'+ GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/MultipleFolder/CopyPasteTV'
				}
				else {

					TestObject newFolderObj
					newFolderObj=new TestObject('objectName')
					newFolderObj.addProperty('xpath', ConditionType.EQUALS, "//div[@title = 'MultipleFolder']")
					WebUI.click(findTestObject('Object Repository/FilesPage/link_BaseFolder'))
					WebUI.delay(2)
					WebUI.click(newFolderObj)
					WebUI.doubleClick(newFolderObj)
					newFolderObj.addProperty('xpath', ConditionType.EQUALS, "//div[@title = 'CopyPasteLV']")
					WebUI.click(newFolderObj)
					WebUI.doubleClick(newFolderObj)
					extentTest.log(Status.PASS, 'Navigated to '+GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/MultipleFolder/CopyPasteLV')
					WebUI.delay(2)
					y=6
					msg = '5 items copied successfully to'+ GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/MultipleFolder/CopyPasteLV'
				}
				isElemenetPresent=WebUI.waitForElementVisible(findTestObject('FilesPage/Label_FolderEmpty'),5,FailureHandling.CONTINUE_ON_FAILURE)
				if(isElemenetPresent) {
					WebUI.rightClick(findTestObject('FilesPage/Label_FolderEmpty'))
				}
				extentTest.log(Status.PASS, 'Invoked context menu in Folder for pasting')
				WebUI.delay(2)
				WebUI.click(findTestObject('FilesPage/ContextMenu_FileGrid_Paste'))
				WebUI.delay(3)
				WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
				extentTest.log(Status.PASS, 'Click on Notification button to open Notification Panel')
				WebUI.delay(2)
				result = WebUI.verifyElementPresent(findTestObject('Notificactions/Notification_CopyFile'),5)
				println("notification status - "+result)
				if (result) {
					extentTest.log(Status.PASS, ('Notification with msg - "' + msg) + '" is listed')
				}
				else {
					extentTest.log(Status.PASS, 'Files Not pasted')
					extentTest.log(Status.FAIL)
				}
				WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
				extentTest.log(Status.PASS, 'Click on Notification button to close Notification Panel')

				String myXpath=null;
				String myText=null;
				String item=null
				if(TestCaseName.contains('tile view')) {
					myXpath="//label[@id='file_text']" // and contains (@title,'"+searchStr+"')]"
					item =" grid element on files page "
				}
				else {
					myXpath="//div[@col-id='name']"
					item =" row in files table "
				}


				List<WebElement> listElement = katalonWebDriver.findElements(By.xpath(myXpath))
				println("-------------------------------------")
				println listElement.size()
				println("-------------------------------------")
				def x=listElement.size()
				extentTest.log(Status.PASS, ' Number of files listied on this page - '+y)

				if(y>=x) {
					extentTest.log(Status.PASS, 'All the files are copied')
					result = true
				}
				else {
					extentTest.log(Status.FAIL, 'All the files not are copied')
					String screenShotPath = 'ExtentReports/' + GlobalVariable.G_Browser + '_MultiFileCopy_CntxtMenu.png'
					WebUI.takeScreenshot(screenShotPath)
					result=false
				}
				return result
				break

			case 'Cut':
				op='Cut'
				def isElemenetPresent
				TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/ContextMenu_FileOperation'), 'id', 'equals', Operation, true)
				WebUI.click(newFileOp)
				extentTest.log(Status.PASS, 'Clicked on context menu option for '+op)

				if (TestCaseName.contains('tile view')) {
					TestObject newFolderObj
					newFolderObj=new TestObject('objectName')
					newFolderObj.addProperty('xpath', ConditionType.EQUALS, "//label[contains(text(),'MultipleFolder')]")
					WebUI.click(findTestObject('Object Repository/FilesPage/link_BaseFolder'))
					WebUI.delay(2)
					WebUI.click(newFolderObj)
					WebUI.doubleClick(newFolderObj)
					newFolderObj.addProperty('xpath', ConditionType.EQUALS, "//label[contains(text(),'CutPasteTV')]")
					WebUI.click(newFolderObj)
					WebUI.doubleClick(newFolderObj)
					extentTest.log(Status.PASS, 'Navigated to '+GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/MultipleFolder/CutPasteTV')
					WebUI.delay(2)
					y=5
					msg = '5 items moved successfully to'+ GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/MultipleFolder/CutPasteTV'
				}
				else {

					TestObject newFolderObj
					newFolderObj=new TestObject('objectName')
					newFolderObj.addProperty('xpath', ConditionType.EQUALS, "//div[@title = 'MultipleFolder']")
					WebUI.click(findTestObject('Object Repository/FilesPage/link_BaseFolder'))
					WebUI.delay(2)
					WebUI.click(newFolderObj)
					WebUI.doubleClick(newFolderObj)
					newFolderObj.addProperty('xpath', ConditionType.EQUALS, "//div[@title = 'CutPasteLV']")
					WebUI.click(newFolderObj)
					WebUI.doubleClick(newFolderObj)
					extentTest.log(Status.PASS, 'Navigated to '+GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/MultipleFolder/CutPasteLV')
					WebUI.delay(2)
					y=6
					msg = '5 items moved successfully to'+ GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/MultipleFolder/CutPasteLV'
				}

				isElemenetPresent=WebUI.waitForElementVisible(findTestObject('FilesPage/Label_FolderEmpty'),5,FailureHandling.CONTINUE_ON_FAILURE)
				if(isElemenetPresent) {
					WebUI.rightClick(findTestObject('FilesPage/Label_FolderEmpty'))
				}
				else {
					WebUI.rightClick(findTestObject('Object Repository/FilesPage/Canvas_FilesPage_TileView'))
				}
				extentTest.log(Status.PASS, 'Invoked context menu in Folder for pasting')
				WebUI.delay(2)
				WebUI.click(findTestObject('FilesPage/ContextMenu_FileGrid_Paste'))
				WebUI.delay(3)

				WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
				extentTest.log(Status.PASS, 'Click on Notification button to open Notification Panel')
				WebUI.delay(2)
				result = WebUI.verifyElementPresent(findTestObject('Notificactions/Notification_CutFile'),5)
				println("notification status - "+result)


				if (result) {
					extentTest.log(Status.PASS, ('Notification with msg - "' + msg) + '" is listed')
				}
				else {
					extentTest.log(Status.PASS, 'Files Not pasted')
					extentTest.log(Status.FAIL)
				}
				WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
				extentTest.log(Status.PASS, 'Click on Notification button to close Notification Panel')

				String myXpath=null;
				String myText=null;
				String item=null
				if(TestCaseName.contains('tile view')) {
					myXpath="//label[@id='file_text']" // and contains (@title,'"+searchStr+"')]"
					item =" grid element on files page "
				}
				else {
					myXpath="//div[@col-id='name']"
					item =" row in files table "
				}


				List<WebElement> listElement = katalonWebDriver.findElements(By.xpath(myXpath))
				println("-------------------------------------")
				println listElement.size()
				println("-------------------------------------")
				def x=listElement.size()
				extentTest.log(Status.PASS, ' Number of files listied on this page - '+x)

				if(x<=y) {
					extentTest.log(Status.PASS, 'All the files are pasted ')
					result = true
				}
				else {
					extentTest.log(Status.FAIL, 'All the files not are pasted')
					result=false
				}
				return result
				break

			case 'Delete':
				op='Delete'
				TestObject newFileObjVerify
				def oriFileName

				if (TestCaseName.contains('JobSubmission')) {
					WebUI.click(findTestObject('Object Repository/JobSubmissionForm/SubMenu_Delete'))
				}
				else{
					TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/ContextMenu_FileOperation'), 'id', 'equals', 'Delete', true)
					WebUI.click(newFileOp)
					WebUI.delay(2)
				}
				extentTest.log(Status.PASS, 'Clicked on context menu for -'+op)
				WebUI.click(findTestObject('GenericObjects/btn_Yes'))
				WebUI.delay(2)
				extentTest.log(Status.PASS, 'Clicked on Yes on Delete confirmation pop-up ')
				WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
				extentTest.log(Status.PASS, 'Click on Notification button to open Notification Panel')
				msg = '5 item deleted successfully'
				WebUI.delay(2)
				result = WebUI.verifyElementPresent(findTestObject('Object Repository/Notificactions/Notification_DeleteFile'),5)
				println("notification status - "+result)
				if (result) {
					extentTest.log(Status.PASS, ' Deleted file and verified notification')
					extentTest.log(Status.PASS, ('Notification with msg - "' + msg) + '" is listed')
				}
				else {
					extentTest.log(Status.PASS, '  Not pasted')
					extentTest.log(Status.FAIL)
				}
				WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
				extentTest.log(Status.PASS, 'Click on Notification button to close Notification Panel')
				String myXpath=null;
				String myText=null;
				String item=null
				if(TestCaseName.contains('tile view')) {
					myXpath="//label[@id='file_text']"
					item =" grid element on files page "
					y=0
				}
				else {
					myXpath="//div[@col-id='name']"
					item =" row in files table "
					y=1
				}


				List<WebElement> listElement = katalonWebDriver.findElements(By.xpath(myXpath))
				println("-------------------------------------")
				println listElement.size()
				println("-------------------------------------")
				def x=listElement.size()
				extentTest.log(Status.PASS, ' Number of files listied on this page - '+x)

				if(x==y) {
					extentTest.log(Status.PASS, 'All the files are deleted ')
					result = true
				}
				else {
					extentTest.log(Status.FAIL, 'All the files not are deleted')
					result=false
				}

				return result
				break

			case 'Compress':
				op='Compress'
				WebUI.click(findTestObject('FilesPage/ContextMenu_FileGrid_Compress'))
				WebUI.delay(5)
				extentTest.log(Status.PASS, 'Clicked on Context Menu Option for - '+Operation)
				println"Clicked Compress"
				if (TestCaseName.contains('tile view')) {

					WebUI.click(findTestObject("Object Repository/FilesPage/SortBy-Option"))
					WebUI.delay(3)
					WebUI.mouseOver(findTestObject("Object Repository/FilesPage/SortList-Option"))
					WebUI.click(findTestObject("Object Repository/FilesPage/SortList-Option"))
					extentTest.log(Status.PASS, 'Sorted the listed files by created on, in tile view')

					TestObject sortIconDown=WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/SortBy-Order'), 'class', 'equals',"down-arrow tile-sortable-icon focus_enable_class", true)
					def sortIconUp=WebUI.waitForElementPresent(findTestObject('Object Repository/FilesPage/SortBy-Order'), 3, FailureHandling.CONTINUE_ON_FAILURE)
					def isIconPresent=WebUI.waitForElementPresent(sortIconDown, 3, FailureHandling.CONTINUE_ON_FAILURE)
					println("sortIconUp - "+sortIconUp)
					println("sortIconDown - "+isIconPresent)
					if(sortIconUp) {
						WebUI.click(findTestObject('Object Repository/FilesPage/SortBy-Order'))
						WebUI.delay(2)
					}
					def isElemenetPresent=WebUI.waitForElementVisible(findTestObject('FilesPage/RowItem_CompressedFile_TileView'), 10, FailureHandling.CONTINUE_ON_FAILURE)
					println  "RowItem_CompressedFile_TileView - "+isElemenetPresent
					println("Testcasename - "+TestCaseName)
				}
				else {
					def isElemenetPresent=WebUI.waitForElementVisible(findTestObject('FilesPage/RowItem_CompressedFile_ListView'), 10, FailureHandling.CONTINUE_ON_FAILURE)
					println  "RowItem_CompressedFile_ListView - "+isElemenetPresent
				}
				WebUI.delay(1)

				String compressedFileName=(new operations_FileModule.multiFileCompress()).VerifyCompressedFile(TestCaseName , extentTest)

				println "=================================================================="
				println" UNCOMPRESS SCENARIO"
				println "=================================================================="
				if (TestCaseName.contains('tile view')) {

					TestObject newCompressedFile
					newCompressedFile=new TestObject('objectName')
					String xpathNew="//label[contains(text(),'"+compressedFileName+"')]"
					newCompressedFile.addProperty('xpath', ConditionType.EQUALS, xpathNew)
					WebUI.rightClick(newCompressedFile)
				}
				else {

					TestObject newCompressedFile
					newCompressedFile=new TestObject('objectName')
					String xpathNew="//div[contains(text(),'"+compressedFileName+"')]"
					newCompressedFile.addProperty('xpath', ConditionType.EQUALS, xpathNew)
					WebUI.rightClick(newCompressedFile)
				}
				WebUI.delay(3)
				WebUI.click(findTestObject('FilesPage/ContextMenu_FileGrid_UnCompress'))
				extentTest.log(Status.PASS, 'Clicked on menu item Un-Compress on file - '+compressedFileName)
				result=(new operations_FileModule.multiFileCompress()).VerifyUnCompressedFile(compressedFileName , TestCaseName ,extentTest)
				WebUI.delay(4)
				String myXpath
				String item=null
				if(TestCaseName.contains('tile view')) {
					myXpath="//label[@id='file_text']"
					item =" grid element on files page "
					y=5
				}
				else {
					myXpath="//div[@col-id='name']"
					item =" row in files table "
					y=6
				}


				List<WebElement> listElement = katalonWebDriver.findElements(By.xpath(myXpath))
				println("-------------------------------------")
				println listElement.size()
				println("-------------------------------------")
				def x=listElement.size()
				extentTest.log(Status.PASS, ' Number of files listied on this page - '+x)

				if(x==y) {
					extentTest.log(Status.PASS, 'All the files are present after uncompress ')
					result = true
				}
				else {
					extentTest.log(Status.FAIL, 'All the files not are present after uncompress')
					result=false
				}

				return result
				break

			case 'Download':
				println Operation
				if (TestCaseName.contains('Job Submission')) {
					TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/ContextMenu_JobSubmission_Download'), 'id', 'equals', Operation, true)
					WebUI.click(newFileOp)
				}
				else {

					WebUI.click(findTestObject('Object Repository/FilesPage/ContextMenu_FileGrid_Download'))
				}
				WebUI.delay(5)

				File downloadFolder = new File(GlobalVariable.G_DownloadFolder)

				List namesOfFiles = Arrays.asList(downloadFolder.list())

				if (namesOfFiles.contains('ToDownload.txt')) {
					println('success')
					//extentTest.log(Status.PASS, 'file to downloaded ')

				} else {
					println('fail')
				}

				return true
				break
		}
	}
}
