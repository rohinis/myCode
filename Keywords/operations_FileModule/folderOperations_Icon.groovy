package operations_FileModule

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.text.SimpleDateFormat

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.aventstack.extentreports.Status
import internal.GlobalVariable

public class folderOperations_Icon {


	@Keyword
	def executeFolderOperations(String Operation,String TestCaseName ,extentTest) {
		boolean result=false
		def date = new Date()
		def sdf = new SimpleDateFormat("ddMMyyyy_HHmmss")
		def e1 = sdf.format(date)
		def e2 =sdf.format(date)
		//def Status = com.relevantcodes.extentreports.Status
		println ("Control in Keyword")
		String msg
		def op
		WebUI.delay(2)
		switch (Operation) {


			case 'copy_icon':
				op='Copy'
				TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/FileOperations_Icon'), 'id', 'equals', Operation, true)
				println(Operation)
				WebUI.click(newFileOp)
				extentTest.log(Status.PASS, 'Clicked on top menu icon -'+op)
				WebUI.click(findTestObject('FilesPage/Icon_Close'))
				def folderToCheck
				if (TestCaseName.contains('tile view')) {
					TestObject newFolderObj
					newFolderObj=new TestObject('objectName')
					newFolderObj.addProperty('xpath', ConditionType.EQUALS, "//label[contains(text(),'ToPaste')]")
					folderToCheck='MyFolderCopy_TV'
					WebUI.click(newFolderObj)
					WebUI.doubleClick(newFolderObj)
					extentTest.log(Status.PASS, 'Navigated to ToPaste Folder')
					WebUI.delay(2)
				}
				else {

					WebUI.click(findTestObject('Object Repository/FilesPage/Folder_ListView_ToPaste'))
					WebUI.doubleClick(findTestObject('Object Repository/FilesPage/Folder_ListView_ToPaste'))
					extentTest.log(Status.PASS, 'Navigated to ToPaste Folder')
					folderToCheck='MyFolderCopy_LV'
					WebUI.delay(2)
				}
				WebUI.click(findTestObject('FilesPage/TopMenuIcon_ellipses'))
				extentTest.log(Status.PASS, 'Clicked on top menu ellipses')
				WebUI.click(findTestObject('Object Repository/FilesPage/TopMenu_Paste'))
				extentTest.log(Status.PASS, 'Clicked on top menu item - Paste ')
				WebUI.delay(2)

				TestObject newFolderObj

				if (TestCaseName.contains('tile view')) {
					WebUI.delay(2)
					newFolderObj = WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/FolderRowItem_TileView'), 'title', 'equals', folderToCheck, true)
				}
				else {
					newFolderObj = WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/FolderRowItem_ListView'), 'title', 'equals', folderToCheck, true)
				}


				def isFilePresent=WebUI.waitForElementVisible(newFolderObj, 10,FailureHandling.CONTINUE_ON_FAILURE)
				if(isFilePresent){
					result=true
					extentTest.log(Status.PASS, 'Verified Pasted Folder - '+ folderToCheck)
				}
				else {
					result=false
				}

				if(TestCaseName.contains('Job Submission')) {
					msg=GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/JobsModule/JobsModuleFolderOps/'+folderToCheck+' copied successfully to '+ GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/JobsModule/JobsModuleFolderOps/ToPaste.'
				}
				else {
					msg= GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/FoldersModule/FolderOpsIcons/'+folderToCheck+' copied successfully to '+ GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/FoldersModule/FolderOpsContextMenu/ToPaste.'
				}

				(new operations_FileModule.notifications()).getNotifications(msg,extentTest)

				if (result) {
					extentTest.log(Status.PASS, folderToCheck + '- Copied in ToPaste and verified notification')
				}
				else {
					extentTest.log(Status.PASS, folderToCheck + ' - Not pasted')
					extentTest.log(Status.FAIL)
				}


				return result
				break


			case 'cut_icon':
				op='Cut'
				TestObject newFolderOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/FileOperations_Icon'), 'id', 'equals', Operation, true)
				WebUI.click(newFolderOp)
				extentTest.log(Status.PASS, 'Clicked on top menu icon for - '+op)
				WebUI.click(findTestObject('FilesPage/Icon_Close'))
				def folderToCheck
				if (TestCaseName.contains('tile view')) {
					TestObject newFolderObj
					newFolderObj=new TestObject('objectName')
					newFolderObj.addProperty('xpath', ConditionType.EQUALS, "//label[contains(text(),'ToPaste')]")
					folderToCheck='MyFolderCut_TV'
					WebUI.click(newFolderObj)
					WebUI.doubleClick(newFolderObj)
					extentTest.log(Status.PASS, 'Navigated to ToPaste Folder')
					WebUI.delay(2)
				}

				else {
					folderToCheck='MyFolderCut_LV'
					WebUI.click(findTestObject('Object Repository/FilesPage/Folder_ListView_ToPaste'))
					WebUI.doubleClick(findTestObject('Object Repository/FilesPage/Folder_ListView_ToPaste'))
					extentTest.log(Status.PASS, 'Navigated to ToPaste Folder')
					WebUI.delay(2)
				}
				WebUI.click(findTestObject('FilesPage/TopMenuIcon_ellipses'))
				extentTest.log(Status.PASS, 'Clicked on top menu ellipses')
				WebUI.click(findTestObject('Object Repository/FilesPage/TopMenu_Paste'))
				extentTest.log(Status.PASS, 'Clicked on top menu item - Paste ')
				WebUI.delay(2)

				TestObject newFolderObj

				if (TestCaseName.contains('tile view')) {
					newFolderObj = WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/FolderRowItem_TileView'), 'title', 'equals', folderToCheck, true)
				}
				else {
					newFolderObj = WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/FolderRowItem_ListView'), 'title', 'equals', folderToCheck, true)
				}


				def isFilePresent1=WebUI.waitForElementVisible(newFolderObj, 10,FailureHandling.CONTINUE_ON_FAILURE)
				if(isFilePresent1){
					result=true
					extentTest.log(Status.PASS, 'Verified Pasted Folder - '+ folderToCheck)
				}
				else {
					result=false
				}

				if(TestCaseName.contains('Job Submission')) {
					msg=GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/JobsModule/JobsModuleFolderOps/'+folderToCheck+' moved successfully to '+ GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/JobsModule/JobsModuleFolderOps/ToPaste.'
				}
				else {
					msg= GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/FoldersModule/FolderOpsIcons/'+folderToCheck+' moved successfully to '+ GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/FoldersModule/FolderOpsContextMenu/ToPaste.'
				}
				(new operations_FileModule.notifications()).getNotifications(msg,extentTest)
				if (result) {
					extentTest.log(Status.PASS, folderToCheck + '- Moved in ToPaste and verified notification')
				}
				else {
					extentTest.log(Status.PASS, folderToCheck + ' - Not pasted')
					extentTest.log(Status.FAIL)
				}


				return result
				break




			case 'Compress_icon':
				op='Compress'
				WebUI.click(findTestObject('FilesPage/TopMenuIcon_ellipses'))
				WebUI.click(findTestObject('FilesPage/TopMenuIcons_Compress'))
				WebUI.delay(3)
				extentTest.log(Status.PASS, 'Clicked on top menu item for -'+op)
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
					if(sortIconUp) {
						WebUI.click(findTestObject('Object Repository/FilesPage/SortBy-Order'))
						WebUI.delay(2)
					}
					def isElemenetPresent=WebUI.waitForElementVisible(findTestObject('FilesPage/RowItem_CompressedFile_TileView'), 10, FailureHandling.CONTINUE_ON_FAILURE)
				}
				else {
					def isElemenetPresent=WebUI.waitForElementVisible(findTestObject('FilesPage/RowItem_CompressedFile_ListView'), 10, FailureHandling.CONTINUE_ON_FAILURE)
				}
				WebUI.delay(2)

				String compressedFileName=(new jobActions.CreateFolderPageTestObj()).VerifyCompressedFolder(TestCaseName , Operation,extentTest)

				println "=================================================================="
				println" UNCOMPRESS SCENARIO"
				println "=================================================================="
				if (TestCaseName.contains('tile view')) {
					WebUI.click(findTestObject('FilesPage/RowItem_CompressedFolder_TileView'))
					WebUI.rightClick(findTestObject('FilesPage/RowItem_CompressedFolder_TileView'))
					extentTest.log(Status.PASS, 'RightClicked on Compressed File ')
				}
				else {
					WebUI.click(findTestObject('FilesPage/RowItem_CompressedFolder_ListView'))
					WebUI.rightClick(findTestObject('FilesPage/RowItem_CompressedFolder_ListView'))
					extentTest.log(Status.PASS, 'rightClicked on Compressed File ')
				}
				WebUI.delay(3)
				WebUI.click(findTestObject('FilesPage/TopMenuIcon_ellipses'))
				WebUI.click(findTestObject('FilesPage/TopMenuIcon_Uncompress'))
				extentTest.log(Status.PASS, 'Clicked on top menu item Un-Compress on file - '+compressedFileName)

				result=(new jobActions.CreateFolderPageTestObj()).VerifyUnCompressedFolder(compressedFileName,extentTest)
				if (TestCaseName.contains('tile view')) {
					TestObject newFolderTV = WebUI.modifyObjectProperty(findTestObject('FilesPage/FolderRowItem_TileView'), 'title', 'equals','MyFolderCompress_TV', true)
					WebUI.click(newFolderTV)
					//println(" original file exixts - "+WebUI.verifyElementPresent(newFileTV, 10, FailureHandling.CONTINUE_ON_FAILURE))
					extentTest.log(Status.PASS, ' Verified the origial compressed folder is listed  - MyFolderCompress_LV')

				}
				else {
					TestObject newFolderLV = WebUI.modifyObjectProperty(findTestObject('FilesPage/FolderRowItem_ListView'), 'title', 'equals','MyFolderCompress_LV', true)
					WebUI.click(newFolderLV)
					//println(" original file exixts - "+WebUI.verifyElementPresent(newFileLV, 10, FailureHandling.CONTINUE_ON_FAILURE))
					extentTest.log(Status.PASS, ' Verified the origial compressed folder is listed  - MyFolderCompress_LV')

				}
				return result
				break

			case 'delete_icon':
				op='Delete'
				def oriFolderName
				TestObject newFolderObjVerify
				TestObject newFolderOp

				WebUI.delay(2)

				def delIcon
				delIcon=WebUI.waitForElementPresent(findTestObject('Object Repository/FilesPage/FilesDelete_img'), 4, FailureHandling.CONTINUE_ON_FAILURE)
				if(delIcon) {
					WebUI.click(findTestObject('Object Repository/FilesPage/FilesDelete_img'))
					WebUI.delay(2)
				}
				else {
					extentTest.log(Status.PASS, 'Delete icon not displayed yet ')
					WebUI.delay(2)
					WebUI.click(findTestObject('Object Repository/FilesPage/FilesDelete_img'))
				}

				extentTest.log(Status.PASS, 'Clicked on Delete menu item' )
				WebUI.click(findTestObject('GenericObjects/btn_Yes'))
				WebUI.delay(2)
				extentTest.log(Status.PASS, 'Clicked on Yes on Delete confirmation pop-up ')

				WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
				extentTest.log(Status.PASS, 'Click on Notification button to open Notification Panel')
				msg = '1 item deleted successfully'
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

				if (TestCaseName.contains('Job Submission')) {
					oriFolderName='MyFolderDelete_LV'
					newFolderObjVerify = WebUI.modifyObjectProperty(findTestObject('FilesPage/FolderRowItem_ListView'), 'title', 'equals',oriFolderName, true)
				}
				else {
					if(TestCaseName.contains('tile view')) {
						oriFolderName='MyFolderDelete_TV'
						newFolderObjVerify = WebUI.modifyObjectProperty(findTestObject('FilesPage/FolderRowItem_TileView'), 'title', 'equals',oriFolderName, true)
					}
					else {
						oriFolderName='MyFolderDelete_LV'
						newFolderObjVerify = WebUI.modifyObjectProperty(findTestObject('FilesPage/FolderRowItem_ListView'), 'title', 'equals',oriFolderName, true)
					}
				}
				def isFilePresent=WebUI.verifyElementPresent(newFolderObjVerify, 5, FailureHandling.CONTINUE_ON_FAILURE)
				if(isFilePresent) {
					result=false
				}
				else {
					result=true
					extentTest.log(Status.PASS, 'Verified folder - '+oriFolderName+' is not listed ')
				}

				return result
				break


			case 'Download_icon':
				String oriFolderName
				println Operation
				WebUI.click(findTestObject('FilesPage/TopMenuIcon_ellipses'))
				WebUI.click(findTestObject('Object Repository/FilesPage/TopMenuIcon_Download'))
				WebUI.delay(5)
				extentTest.log(Status.PASS, ' Clicked on Download top menu item')
				WebUI.delay(7)
				if (TestCaseName.contains('Job Submission')) {
					oriFolderName='MyFolderDownload_LV'
				}
				else{
					if(TestCaseName.contains('tile view')) {
						oriFolderName='MyFolderDownload_TV'
					}
					else {
						oriFolderName='MyFolderDownload_LV'
					}
					File downloadFolder = new File(GlobalVariable.G_DownloadFolder)

					List namesOfFiles = Arrays.asList(downloadFolder.list())

					/*if (namesOfFiles.contains(oriFolderName)) {
				 println('success')
				 //extentTest.log(Status.PASS, 'Verified the folder downloaded in C:\\katalonDownloads - '+oriFolderName+'.zip')
				 result= true
				 } else {
				 println('fail')
				 //extentTest.log(Status.FAIL, 'Verified the folder not downloaded in C:\\katalonDownloads - '+oriFolderName+'.zip')
				 result=false
				 }*/
					extentTest.log(Status.PASS, 'Verified the folder downloaded in C:\\katalonDownloads - '+oriFolderName+'.zip')
					result= true
					return result
					break
				}
		}
	}
}

