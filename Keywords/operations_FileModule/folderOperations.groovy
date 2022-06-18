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

public class folderOperations {


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
		WebUI.delay(2)
		switch (Operation) {

			case 'Copy':
				TestObject newFolderOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/ContextMenu_FileOperation'), 'id', 'equals', Operation, true)
				WebUI.click(newFolderOp)
				extentTest.log(Status.PASS, 'Clicked on Context Menu Option for - '+Operation)
				WebUI.click(findTestObject('FilesPage/Icon_Close'))
				def folderToCheck
				if (TestCaseName.contains('tile view')) {
					TestObject newFolderObj
					newFolderObj=new TestObject('objectName')
					newFolderObj.addProperty('xpath', ConditionType.EQUALS, "//label[contains(text(),'ToPaste')]")
					folderToCheck='MyFolderCopy_TV'
					WebUI.scrollToElement(newFolderObj, 3)
					WebUI.click(newFolderObj)
					WebUI.doubleClick(newFolderObj)
					extentTest.log(Status.PASS, 'Navigated to ToPaste Folder')
					WebUI.delay(2)
					def isElemenetPresent=WebUI.waitForElementVisible(findTestObject('FilesPage/Label_FolderEmpty'),5,FailureHandling.CONTINUE_ON_FAILURE)
					if(isElemenetPresent) {
						WebUI.rightClick(findTestObject('FilesPage/Label_FolderEmpty'))
					}
					else {
						WebUI.rightClick(findTestObject('Object Repository/FilesPage/Canvas_FilesPage_TileView'))
					}
					extentTest.log(Status.PASS, 'Invoked context menu in ToPaste Folder')

					WebUI.delay(2)
					WebUI.click(findTestObject('Object Repository/FilesPage/ContextMenu_TileGrid_Paste'))
				}
				else {
					WebUI.scrollToElement(findTestObject('Object Repository/FilesPage/Folder_ListView_ToPaste'), 3)
					WebUI.click(findTestObject('Object Repository/FilesPage/Folder_ListView_ToPaste'))
					WebUI.doubleClick(findTestObject('Object Repository/FilesPage/Folder_ListView_ToPaste'))
					extentTest.log(Status.PASS, 'Navigated to ToPaste Folder')
					folderToCheck='MyFolderCopy_LV'
					WebUI.delay(2)
					def isElemenetPresent=WebUI.waitForElementVisible(findTestObject('FilesPage/Label_FolderEmpty'),5,FailureHandling.CONTINUE_ON_FAILURE)
					if(isElemenetPresent) {
						WebUI.rightClick(findTestObject('FilesPage/Label_FolderEmpty'))
					}
					else {
						extentTest.log(Status.PASS, 'Folder not empty - Right Clicking on canvas')
						WebUI.rightClick(findTestObject('FilesPage/Canvas_FilesPage_ListView'))
					}

					WebUI.delay(2)
					WebUI.click(findTestObject('FilesPage/ContextMenu_FileGrid_Paste'))
				}

				extentTest.log(Status.PASS, 'Invoked context menu in ToPaste Folder')

				extentTest.log(Status.PASS, 'Clicked on Paste Option')

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
					extentTest.log(Status.PASS, 'Verified Pasted Folder - '+ folderToCheck)
					result=true
				}
				if(GlobalVariable.G_Browser=='chrome') {
					WebUI.refresh()

					WebUI.delay(2)
				}

				if(TestCaseName.contains('Job Submission')) {
					msg=GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/JobsModule/JobsModuleFolderOps/'+folderToCheck+' copied successfully to '+ GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/JobsModule/JobsModuleFolderOps/ToPaste.'
				}
				else {
					msg= GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/FoldersModule/FolderOpsContextMenu/'+folderToCheck+' copied successfully to '+ GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/FoldersModule/FolderOpsContextMenu/ToPaste.'
				}
				(new operations_FileModule.notifications()).getNotifications(msg,extentTest)

				return result
				break

			case 'Cut':
				TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/ContextMenu_FileOperation'), 'id', 'equals', Operation, true)
				WebUI.click(newFileOp)
				extentTest.log(Status.PASS, 'Clicked on Context Menu Option for - '+Operation)
				WebUI.click(findTestObject('FilesPage/Icon_Close'))
				def folderToCheck
				if (TestCaseName.contains('tile view')) {
					TestObject newFolderObj
					newFolderObj=new TestObject('objectName')
					newFolderObj.addProperty('xpath', ConditionType.EQUALS, "//label[contains(text(),'ToPaste')]")
					folderToCheck='MyFolderCut_TV'
					WebUI.scrollToElement(newFolderObj, 2)
					WebUI.click(newFolderObj)
					WebUI.doubleClick(newFolderObj)
					extentTest.log(Status.PASS, 'Navigated to ToPaste Folder')
					WebUI.delay(2)
					TestObject viewIconList=WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/Icon_ViewIcon'), 'title', 'equals',"List View", true)

					def viewIconListPresent=WebUI.waitForElementPresent(viewIconList, 3, FailureHandling.CONTINUE_ON_FAILURE)

					if(viewIconListPresent) {
						WebUI.click(viewIconList)
						WebUI.delay(2)
					}
				}
				else {
					folderToCheck='MyFolderCut_LV'
					WebUI.scrollToElement(findTestObject('Object Repository/FilesPage/Folder_ListView_ToPaste'),2)
					WebUI.click(findTestObject('Object Repository/FilesPage/Folder_ListView_ToPaste'))
					WebUI.doubleClick(findTestObject('Object Repository/FilesPage/Folder_ListView_ToPaste'))
					extentTest.log(Status.PASS, 'Navigated to ToPaste Folder')
					WebUI.delay(2)
				}

				def isElemenetPresent=WebUI.waitForElementVisible(findTestObject('FilesPage/Label_FolderEmpty'), 10,FailureHandling.CONTINUE_ON_FAILURE)

				if(isElemenetPresent) {
					extentTest.log(Status.PASS, 'ToPaste Folder is Currently Empty')

					WebUI.rightClick(findTestObject('FilesPage/Label_FolderEmpty'))
					String SC='RC'+e1
					String screenShotPath = (('ExtentReports/' + SC) + GlobalVariable.G_Browser) + '.png'
					WebUI.takeScreenshot(screenShotPath)
				}

				else {
					extentTest.log(Status.PASS, 'ToPaste Folder is Currently Not Empty')
					WebUI.rightClick(findTestObject('FilesPage/Canvas_FilesPage_ListView'))
					String SC='RC-not'+e2
					String screenShotPath = (('ExtentReports/' + SC) + GlobalVariable.G_Browser) + '.png'
					WebUI.takeScreenshot(screenShotPath)
				}
				WebUI.delay(2)
				WebUI.click(findTestObject('FilesPage/ContextMenu_FileGrid_Paste'))
				extentTest.log(Status.PASS, 'Invoked context menu in ToPaste Folder')
				extentTest.log(Status.PASS, 'Clicked on Paste Option')

				TestObject newFolderObj

				if (TestCaseName.contains('tile view')) {
					WebUI.delay(2)

					TestObject viewIconTile = WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/Icon_ViewIcon'), 'title',
							'equals', 'Tile View', true)
					def viewIconTilePresent=WebUI.waitForElementPresent(viewIconTile, 3, FailureHandling.CONTINUE_ON_FAILURE)

					if(viewIconTilePresent) {
						WebUI.click(viewIconTile)
						WebUI.delay(2)
					}
					newFolderObj = WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/FolderRowItem_TileView'), 'title', 'equals', folderToCheck, true)
				}
				else {
					newFolderObj = WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/FolderRowItem_ListView'), 'title', 'equals', folderToCheck, true)
				}


				def isFilePresent=WebUI.waitForElementVisible(newFolderObj, 10,FailureHandling.CONTINUE_ON_FAILURE)
				if(isFilePresent){
					extentTest.log(Status.PASS, 'Verified Pasted File - '+ folderToCheck)
					result=true
				}
				if(GlobalVariable.G_Browser=='chrome') {
					WebUI.refresh()

					WebUI.delay(2)
				}

				if(TestCaseName.contains('Job Submission')) {
					msg=GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/JobsModule/JobsModuleFolderOps/'+folderToCheck+' moved successfully to '+ GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/JobsModule/JobsModuleFolderOps/ToPaste.'
				}
				else {
					msg= GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/FoldersModule/FolderOpsContextMenu/'+folderToCheck+' moved successfully to '+ GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/FoldersModule/FolderOpsContextMenu/ToPaste.'
				}

				WebUI.delay(2)

				(new operations_FileModule.notifications()).getNotifications(msg,extentTest)


				return result
				break



			case 'Rename':
				TestObject newFolderObjVerify
				TestObject renameTextBxObj
				def Renameto
				def oriFolderName
				msg
				TestObject newFolderObj=WebUI.modifyObjectProperty(findTestObject('FilesPage/ContextMenu_FileOperation'), 'id', 'equals', Operation, true)
				WebUI.click(newFolderObj)
				extentTest.log(Status.PASS, 'Clicked on Context Menu Option for - '+Operation)
				WebUI.delay(2)
				if (TestCaseName.contains('Job Submission')) {
					oriFolderName='MyFolderRename'
					Renameto='RenamedFolder'
					renameTextBxObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/NewFile_input'), 'value', 'equals', oriFolderName, true)
					newFolderObjVerify = WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/FolderRowItem_ListView'), 'title', 'equals',Renameto, true)
					extentTest.log(Status.PASS,'oriFolderName - '+oriFolderName)
					msg = 'File/Folder renamed successfully from ' +GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/JobsModule/JobsModuleFileOps/'+oriFolderName+' to '+ GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/JobsModule/JobsModuleFileOps/'+Renameto
				}
				else{
					if(TestCaseName.contains('tile view')) {
						oriFolderName='MyFolderRename_TV'
						Renameto='RenamedFolder_TV'
						renameTextBxObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/NewFile_input'), 'value', 'equals', oriFolderName, true)
						newFolderObjVerify =WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/FolderRowItem_TileView'), 'title', 'equals', Renameto, true)
					}
					else {
						oriFolderName='MyFolderRename_LV'
						Renameto='RenamedFolder_LV'
						renameTextBxObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/NewFile_input'), 'value', 'equals', oriFolderName, true)
						newFolderObjVerify = WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/FolderRowItem_ListView'), 'title', 'equals',Renameto, true)
					}
					msg = 'File/Folder renamed successfully from ' +GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/FoldersModule/FolderOpsContextMenu/'+oriFolderName+' to '+ GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/FoldersModule/FolderOpsContextMenu/'+Renameto
				}
				WebUI.waitForElementVisible(renameTextBxObj, 3)
				WebUI.setText(renameTextBxObj, Renameto)
				extentTest.log(Status.PASS, 'Entered the new name - '+ Renameto)
				WebUI.click(findTestObject('FilesPage/btn_Save'))
				extentTest.log(Status.PASS, 'Clicked on Save Button')
				WebUI.delay(3)
				WebUI.click(findTestObject('FilesPage/Icon_Close'))
				extentTest.log(Status.PASS, 'Clicked on Close icon on search text box ')
				WebUI.delay(2)
				def isElemenetPresent=WebUI.waitForElementVisible(newFolderObjVerify, 3,FailureHandling.CONTINUE_ON_FAILURE)
				if(isElemenetPresent){
					extentTest.log(Status.PASS, "Renamed file - "+Renameto + " is listed ")
					result=true
				}
				else {
					extentTest.log(Status.PASS, "Renamed file - "+Renameto + " is listed ")
					result=false
				}
				result=WebUI.waitForElementPresent(newFolderObjVerify, 10, FailureHandling.CONTINUE_ON_FAILURE)
				if(GlobalVariable.G_Browser=='chrome') {
					WebUI.refresh()
					WebUI.delay(2)
				}
				(new operations_FileModule.notifications()).getNotifications(msg,extentTest)

				return result
				break



			case 'Compress':
				WebUI.click(findTestObject('FilesPage/ContextMenu_FileGrid_Compress'))
				WebUI.delay(5)
				extentTest.log(Status.PASS, 'Clicked on Context Menu Option for - '+Operation)
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
				WebUI.click(findTestObject('FilesPage/ContextMenu_FileGrid_UnCompress'))
				extentTest.log(Status.PASS, 'Clicked on menu item Un-Compress on file - '+compressedFileName)

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


			case 'Delete':
				def oriFolderName
				TestObject newFolderObjVerify
				TestObject newFolderOp

				if (TestCaseName.contains('JobSubmission')) {
					WebUI.click(findTestObject('Object Repository/JobSubmissionForm/SubMenu_Delete'))
				}
				else{
					newFolderOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/ContextMenu_FileOperation'), 'id', 'equals', 'Delete', true)
					WebUI.click(newFolderOp)
					WebUI.delay(2)
				}
				extentTest.log(Status.PASS, 'Clicked on Delete menu item' )
				WebUI.click(findTestObject('GenericObjects/btn_Yes'))
				WebUI.delay(2)
				extentTest.log(Status.PASS, 'Clicked on Yes on Delete confirmation pop-up ')
				if(GlobalVariable.G_Browser=='chrome') {
					WebUI.refresh()

					WebUI.delay(2)
				}

				msg = '1 item deleted successfully'



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


			case 'Download':
				println Operation
				String oriFolderName

				extentTest.log(Status.PASS, 'Clicked on Download mentu item ')

				if (TestCaseName.contains('Job Submission')) {
					TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/ContextMenu_JobSubmission_Download'), 'id', 'equals', Operation, true)
					WebUI.click(newFileOp)
				}
				else {

					WebUI.click(findTestObject('Object Repository/FilesPage/ContextMenu_FileGrid_Download'))
				}
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
					/*File downloadFolder = new File(GlobalVariable.G_DownloadFolder)
				 List namesOfFiles = Arrays.asList(downloadFolder.list())
				 if (namesOfFiles.contains(oriFolderName)) {
				 println('success')
				 extentTest.log(Status.PASS, 'Verified the folder downloaded in C:\\katalonDownloads - '+oriFolderName+'.zip')
				 result= true
				 } else {
				 println('fail')
				 extentTest.log(Status.FAIL, 'Verified the folder not downloaded in C:\\katalonDownloads - '+oriFolderName+'.zip')
				 result=false
				 }*/

					return true
					break
				}
		}
	}
}