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

public class multifolderOps {
	@Keyword
	def multiFolderOperationsIcons(String Operation,String TestCaseName ,extentTest,katalonWebDriver) {
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
			case 'copy_icon':
				op='Copy'
				TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/FileOperations_Icon'), 'id', 'equals', Operation, true)
				println(Operation)
				WebUI.click(newFileOp)
				extentTest.log(Status.PASS, 'Clicked on top menu icon -'+op)

				def fileToCheck
				if (TestCaseName.contains('tile view')) {
					TestObject newFolderObj
					newFolderObj=new TestObject('objectName')
					newFolderObj.addProperty('xpath', ConditionType.EQUALS, "//label[contains(text(),'MultipleFolderIcons')]")
					WebUI.click(findTestObject('Object Repository/FilesPage/link_BaseFolder'))
					WebUI.delay(2)
					WebUI.click(newFolderObj)
					WebUI.doubleClick(newFolderObj)
					newFolderObj.addProperty('xpath', ConditionType.EQUALS, "//label[contains(text(),'CopyPasteTV')]")
					WebUI.click(newFolderObj)
					WebUI.doubleClick(newFolderObj)
					extentTest.log(Status.PASS, 'Navigated to '+GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/MultipleFolderIcons/CopyPasteTV')
					WebUI.delay(2)
					y=5
					msg = '5 items copied successfully to'+ GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/MultipleFolderIcons/CopyPasteTV'
				}
				else {

					TestObject newFolderObj
					newFolderObj=new TestObject('objectName')
					newFolderObj.addProperty('xpath', ConditionType.EQUALS, "//div[@title = 'MultipleFolderIcons']")
					WebUI.click(findTestObject('Object Repository/FilesPage/link_BaseFolder'))
					WebUI.delay(2)
					WebUI.click(newFolderObj)
					WebUI.doubleClick(newFolderObj)
					newFolderObj.addProperty('xpath', ConditionType.EQUALS, "//div[@title = 'CopyPasteLV']")
					WebUI.click(newFolderObj)
					WebUI.doubleClick(newFolderObj)
					extentTest.log(Status.PASS, 'Navigated to '+GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/MultipleFolderIcons/CopyPasteLV')
					WebUI.delay(2)
					y=6
					msg = '5 items copied successfully to'+ GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/MultipleFolderIcons/CopyPasteLV'
				}

				WebUI.click(findTestObject('FilesPage/TopMenuIcon_ellipses'))
				extentTest.log(Status.PASS, 'Clicked on top menu icon ellipses')
				WebUI.click(findTestObject('Object Repository/FilesPage/TopMenu_Paste'))
				extentTest.log(Status.PASS, 'Clicked on top menu item for Paste')

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
					myXpath="//label[@id='tile_dir_input']" // and contains (@title,'"+searchStr+"')]"
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

				if(y==x) {
					extentTest.log(Status.PASS, 'All the files are copied')
					result = true
				}
				else {
					extentTest.log(Status.FAIL, 'All the files not are copied')
					result=false
				}
				return result
				break

			case 'cut_icon':
				op='Cut'
				TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/FileOperations_Icon'), 'id', 'equals', Operation, true)
				println(Operation)
				WebUI.click(newFileOp)
				extentTest.log(Status.PASS, 'Clicked on top menu icon -'+op)

				if (TestCaseName.contains('tile view')) {
					TestObject newFolderObj
					newFolderObj=new TestObject('objectName')
					newFolderObj.addProperty('xpath', ConditionType.EQUALS, "//label[contains(text(),'MultipleFolderIcons')]")
					WebUI.click(findTestObject('Object Repository/FilesPage/link_BaseFolder'))
					WebUI.delay(2)
					WebUI.click(newFolderObj)
					WebUI.doubleClick(newFolderObj)
					newFolderObj.addProperty('xpath', ConditionType.EQUALS, "//label[contains(text(),'CutPasteTV')]")
					WebUI.click(newFolderObj)
					WebUI.doubleClick(newFolderObj)
					extentTest.log(Status.PASS, 'Navigated to '+GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/MultipleFolderIcons/CutPasteTV')
					WebUI.delay(2)
					y=5
					msg = '5 items moved successfully to'+ GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/MultipleFolderIcons/CutPasteTV'
				}
				else {

					TestObject newFolderObj
					newFolderObj=new TestObject('objectName')
					newFolderObj.addProperty('xpath', ConditionType.EQUALS, "//div[@title = 'MultipleFolderIcons']")
					WebUI.click(findTestObject('Object Repository/FilesPage/link_BaseFolder'))
					WebUI.delay(2)
					WebUI.click(newFolderObj)
					WebUI.doubleClick(newFolderObj)
					newFolderObj.addProperty('xpath', ConditionType.EQUALS, "//div[@title = 'CutPasteLV']")
					WebUI.click(newFolderObj)
					WebUI.doubleClick(newFolderObj)
					extentTest.log(Status.PASS, 'Navigated to '+GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/MultipleFolderIcons/CutPasteLV')
					WebUI.delay(2)
					y=6
					msg = '5 items moved successfully to'+ GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/MultipleFolderIcons/CutPasteLV'
				}

				WebUI.click(findTestObject('FilesPage/TopMenuIcon_ellipses'))
				extentTest.log(Status.PASS, 'Clicked on top menu icon ellipses')
				WebUI.click(findTestObject('Object Repository/FilesPage/TopMenu_Paste'))
				extentTest.log(Status.PASS, 'Clicked on top menu item for Paste')
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
					myXpath="//label[@id='tile_dir_input']" // and contains (@title,'"+searchStr+"')]"
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

			case 'delete_icon':
				op='Delete'
				TestObject newFileObjVerify
				def oriFileName
				TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/FileOperations_Icon'), 'id', 'equals', Operation, true)
				println(Operation)
				WebUI.click(newFileOp)
				WebUI.delay(2)
				extentTest.log(Status.PASS, 'Clicked on top menu icon -'+op)
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
					extentTest.log(Status.PASS, ' Deleted folder and verified notification')
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

			case 'Compress_icon':
				op='Compress'
				WebUI.click(findTestObject('FilesPage/TopMenuIcon_ellipses'))
				WebUI.click(findTestObject('FilesPage/TopMenuIcons_Compress'))
				WebUI.delay(5)
				extentTest.log(Status.PASS, 'Clicked on top menu item for -'+op)
			//WebUI.click(findTestObject('FilesPage/Icon_Close'))
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
					WebUI.click(newCompressedFile)
				}
				else
				{

					TestObject newCompressedFile
					newCompressedFile=new TestObject('objectName')
					String xpathNew="//div[contains(text(),'"+compressedFileName+"')]"
					newCompressedFile.addProperty('xpath', ConditionType.EQUALS, xpathNew)
					WebUI.click(newCompressedFile)

				}
				WebUI.delay(3)
				WebUI.click(findTestObject('FilesPage/TopMenuIcon_ellipses'))
				WebUI.click(findTestObject('FilesPage/TopMenuIcon_Uncompress'))
				extentTest.log(Status.PASS, 'Clicked on top menu item Un-Compress on folder - '+compressedFileName)

				result=(new operations_FileModule.multiFileCompress()).VerifyUnCompressedFile(compressedFileName , TestCaseName ,extentTest)
				WebUI.delay(4)
				String myXpath
				String item=null
				if(TestCaseName.contains('tile view')) {
					myXpath="//label[@id='tile_dir_input']"
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

				if(x==y)
				{
					extentTest.log(Status.PASS, 'All the files are present after uncompress ')
					result = true
				}
				else
				{
					extentTest.log(Status.FAIL, 'All the files not are present after uncompress')
					result=false

				}

				return result
				break



			case 'Download_icon':
				println(Operation)
				WebUI.click(findTestObject('FilesPage/TopMenuIcon_ellipses'))
				WebUI.click(findTestObject('Object Repository/FilesPage/ContextMnOption_Download'))

				WebUI.delay(5)

				File downloadFolder = new File('C:\\KatalonDownloads')

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
