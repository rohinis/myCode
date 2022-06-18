package operations_FileModule

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.text.SimpleDateFormat

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.aventstack.extentreports.Status

import internal.GlobalVariable

public class MultifileOperations_Obsolete {


	@Keyword
	def executeFileOperations(String Operation,String TestCaseName ,extentTest) {

		def date = new Date()
		def sdf = new SimpleDateFormat("ddMMyyyy_HHmmss")
		def e1 = sdf.format(date)
		def e2 =sdf.format(date)
		boolean result=false
	//	def Status = com.relevantcodes.extentreports.Status
		println ("Control in Keyword")
		WebUI.delay(2)
		switch (Operation) {

			case 'Open':
				TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/ContextMenu_FileOperation'), 'id', 'equals', Operation, true)
				WebUI.click(newFileOp)
				extentTest.log(Status.PASS, 'Clicked on Context Menu Option for - '+Operation)
				WebUI.click(findTestObject('FilesPage/Close_Button'))
				result=true
				return result
				break

			case 'Open With':
			//TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/ContextMenu_FileOperation'), 'id', 'equals', Operation, true)
			//WebUI.click(newFileOp)
				WebUI.mouseOver(findTestObject('FilesPage/ContextMenu_Openwith'))
				WebUI.click(findTestObject('FilesPage/ContextMenu_Openwith'))
				extentTest.log(Status.PASS, 'Clicked on Context Menu Option for - '+Operation)
			//WebUI.click(findTestObject('FilesPage/Icon_Close'))
				WebUI.mouseOver(findTestObject('FilesPage/span_Text Editor'))
				WebUI.delay(1)
				WebUI.click(findTestObject('FilesPage/span_Text Editor'))
				result=true
				return result
				break

			case 'Copy':
				TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/ContextMenu_FileOperation'), 'id', 'equals', Operation, true)
				WebUI.click(newFileOp)
				extentTest.log(Status.PASS, 'Clicked on Context Menu Option for - '+Operation)
			//WebUI.click(findTestObject('FilesPage/Icon_Close'))
				def fileToCheck
				if (TestCaseName.contains('tile view')) {
					WebUI.click(findTestObject('FilesPage/btn_NewFileFolder'))
					WebUI.click(findTestObject('FilesPage/ListItem_Folder'))
					WebUI.waitForElementVisible(findTestObject('FilesPage/TextBxFolder_input'), 5)
					WebUI.setText(findTestObject('FilesPage/TextBxFolder_input'),'ToPaste')
					extentTest.log(Status.PASS, "Entered Folder Name to Create ToPaste" )
					WebUI.click(findTestObject('FilesPage/btn_Save'))
					extentTest.log(Status.PASS, "Clicked on Save Button" )

					TestObject viewIconTile = WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/Icon_ViewIcon'), 'title',
							'equals', 'Tile View', true)
					WebUI.click(viewIconTile)
					extentTest.log(Status.PASS, 'Changed View to Tile View - test case - ' +TestCaseName)

					TestObject newFolderObj
					newFolderObj=new TestObject('objectName')
					newFolderObj.addProperty('xpath', ConditionType.EQUALS, "//label[contains(text(),'ToPaste')]")
					fileToCheck='file1.txt'

					WebUI.delay(3)

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
					WebUI.delay(2)
					WebUI.click(findTestObject('Object Repository/FilesPage/ContextMenu_TileGrid_Paste'))
				}
				else {

					WebUI.click(findTestObject('FilesPage/btn_NewFileFolder'))
					WebUI.click(findTestObject('FilesPage/ListItem_Folder'))
					WebUI.waitForElementVisible(findTestObject('FilesPage/TextBxFolder_input'), 5)
					WebUI.setText(findTestObject('FilesPage/TextBxFolder_input'),'ToPaste')
					extentTest.log(Status.PASS, "Entered Folder Name to Create ToPaste" )
					WebUI.click(findTestObject('FilesPage/btn_Save'))
					extentTest.log(Status.PASS, "Clicked on Save Button" )

					estObject viewIconTile = WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/Icon_ViewIcon'), 'title',
							'equals', 'Tile View', true)
					WebUI.click(viewIconTile)
					extentTest.log(Status.PASS, 'Changed View to Tile View - test case - ' +TestCaseName)

					WebUI.click(findTestObject('Object Repository/FilesPage/Folder_ListView_ToPaste'))
					WebUI.doubleClick(findTestObject('Object Repository/FilesPage/Folder_ListView_ToPaste'))
					extentTest.log(Status.PASS, 'Navigated to ToPaste Folder')
					fileToCheck='ToCopy_LV.txt'
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


				extentTest.log(Status.PASS, 'Clicked on Paste Option')

				TestObject newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'text', 'equals',
						fileToCheck, true)

				def isFilePresent=WebUI.waitForElementVisible(newFileObj, 10,FailureHandling.CONTINUE_ON_FAILURE)
				if(isFilePresent){
					extentTest.log(Status.PASS, 'Verified Pasted File - '+ fileToCheck)
				}
				WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
				extentTest.log(Status.PASS, "Opened Notification Panel" )
				WebUI.delay(2)
				result = WebUI.verifyElementPresent(findTestObject('Notificactions/Notification_CopyFile'),5)
				println("notification status - "+result)
				extentTest.log(Status.PASS, "Verified notification for operation - "+Operation)
				return result
				break

			case 'Cut':
				TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/ContextMenu_FileOperation'), 'id', 'equals', Operation, true)
				WebUI.click(newFileOp)
				extentTest.log(Status.PASS, 'Clicked on Context Menu Option for - '+Operation)
			//WebUI.click(findTestObject('FilesPage/Icon_Close'))
				if (TestCaseName.contains('tile view')) {

					WebUI.click(findTestObject('FilesPage/btn_NewFileFolder'))
					WebUI.click(findTestObject('FilesPage/ListItem_Folder'))
					WebUI.waitForElementVisible(findTestObject('FilesPage/TextBxFolder_input'), 5)
					WebUI.setText(findTestObject('FilesPage/TextBxFolder_input'),'ToPaste')
					extentTest.log(Status.PASS, "Entered Folder Name to Create ToPaste" )
					WebUI.click(findTestObject('FilesPage/btn_Save'))
					extentTest.log(Status.PASS, "Clicked on Save Button" )

					println("in tile wala if")

					TestObject viewIconTile = WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/Icon_ViewIcon'), 'title',
							'equals', 'Tile View', true)
					WebUI.click(viewIconTile)
					extentTest.log(Status.PASS, 'Changed View to Tile View - test case - ' +TestCaseName)

					WebUI.delay(2)

					WebUI.delay(3)
					TestObject newFolderObj
					newFolderObj=new TestObject('objectName')
					newFolderObj.addProperty('xpath', ConditionType.EQUALS, "//label[contains(text(),'ToPaste')]")

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

					WebUI.click(findTestObject('FilesPage/btn_NewFileFolder'))
					WebUI.click(findTestObject('FilesPage/ListItem_Folder'))
					WebUI.waitForElementVisible(findTestObject('FilesPage/TextBxFolder_input'), 5)
					WebUI.setText(findTestObject('FilesPage/TextBxFolder_input'),'ToPaste')
					extentTest.log(Status.PASS, "Entered Folder Name to Create ToPaste" )
					WebUI.click(findTestObject('FilesPage/btn_Save'))
					extentTest.log(Status.PASS, "Clicked on Save Button" )
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
				extentTest.log(Status.PASS, 'Clicked on Paste Option')
				WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
				extentTest.log(Status.PASS, "Opened Notification Panel" )

				WebUI.delay(2)
			//Verify notification
				result = WebUI.verifyElementPresent(findTestObject('Notificactions/Notification_CutFile'),5)
				extentTest.log(Status.PASS, "verified notification for operation - "+Operation)
				println("notification status - "+result)
				return result
				break
			case 'Rename':

				TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/ContextMenu_FileOperation'), 'id', 'equals', Operation, true)
				WebUI.click(newFileOp)
				extentTest.log(Status.PASS, 'Clicked on Context Menu Option for - '+Operation)

				WebUI.delay(2)
				WebUI.waitForElementVisible(findTestObject('FilesPage/NewFile_input'), 3)
				if (TestCaseName.contains('Job Submission')) {
					def Renameto='RenamedFile.txt'
					TestObject renameTextBxObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/NewFile_input'), 'value', 'equals', 'ToRename.txt', true)
					WebUI.setText(renameTextBxObj, Renameto)
					extentTest.log(Status.PASS, 'Renamed file to  '+Renameto)

					WebUI.click(findTestObject('FilesPage/btn_Save'))
					WebUI.delay(3)
					extentTest.log(Status.PASS, 'Clicked on Save Button')
					WebUI.click(findTestObject('FilesPage/Icon_Close'))
					TestObject newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'title', 'equals',
							Renameto, true)
					def isElemenetPresent=WebUI.waitForElementVisible(newFileObj, 10,FailureHandling.CONTINUE_ON_FAILURE)
					if(isElemenetPresent){
						println ("Filed renamed to "+Renameto + " isElemenetPresent " + isElemenetPresent)
						result=true
					}
					else {
						println ("Filed not renamed to "+Renameto + " isElemenetPresent " + isElemenetPresent)
						result=false
					}

				}
				else{

					if(TestCaseName.contains('tile view')) {
						def Renameto='Renamefile_TV.txt'
						TestObject renameTextBxObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/NewFile_input'), 'value', 'equals', 'ToRename_TV.txt', true)
						WebUI.setText(renameTextBxObj, Renameto)
						extentTest.log(Status.PASS, 'Renamed file to  '+Renameto)

						WebUI.click(findTestObject('FilesPage/btn_Save'))
						WebUI.delay(3)
						extentTest.log(Status.PASS, 'Clicked on Save Button')
						WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
						extentTest.log(Status.PASS, "Opened Notification Panel" )


						WebUI.delay(2)
						WebUI.click(findTestObject('FilesPage/Icon_Close'))
						TestObject  newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_TileView'), 'title', 'equals',
								Renameto, true)
						def isElemenetPresent=WebUI.waitForElementVisible(findTestObject('Object Repository/Notificactions/Notification_CopyFile'), 10,FailureHandling.CONTINUE_ON_FAILURE)
						if(isElemenetPresent){
							println ("Filed renamed to "+Renameto + " isElemenetPresent " + isElemenetPresent)
						}
						else {
							println ("Filed not renamed to "+Renameto + " isElemenetPresent " + isElemenetPresent)
						}
						WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
						result=WebUI.waitForElementPresent(newFileObj, 10, FailureHandling.CONTINUE_ON_FAILURE)
						extentTest.log(Status.PASS, "verified notification for operation - "+Operation)
					}
					else {
						def Renameto='Renamefile_LV.txt'
						TestObject renameTextBxObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/NewFile_input'), 'value', 'equals', 'ToRename_LV.txt', true)
						WebUI.setText(renameTextBxObj, Renameto)
						extentTest.log(Status.PASS, 'Renamed file to  '+Renameto)
						WebUI.click(findTestObject('FilesPage/btn_Save'))
						WebUI.delay(2)
						extentTest.log(Status.PASS, 'Clicked on Save Button')
						WebUI.click(findTestObject('FilesPage/Icon_Close'))
						TestObject newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'title', 'equals',
								Renameto, true)
						def isElemenetPresent=WebUI.waitForElementVisible(newFileObj, 10,FailureHandling.CONTINUE_ON_FAILURE)
						if(isElemenetPresent){
							println ("Filed renamed to "+Renameto + " isElemenetPresent " + isElemenetPresent)
							result=true
						}
						else {
							println ("Filed not renamed to "+Renameto + " isElemenetPresent " + isElemenetPresent)
							result=false
						}

						WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
						extentTest.log(Status.PASS, "Opened Notification Panel" )


						result=WebUI.waitForElementPresent(newFileObj, 10, FailureHandling.CONTINUE_ON_FAILURE)
						extentTest.log(Status.PASS, "verified notification for operation - "+Operation)
					}
				}
				return result
				break


			case 'Compress':

				WebUI.click(findTestObject('FilesPage/ContextMenu_FileGrid_Compress'))
				WebUI.delay(5)

				extentTest.log(Status.PASS, 'Clicked on Context Menu Option for - '+Operation)

				println"Clicked Compress"
				WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
				extentTest.log(Status.PASS, "Opened Notification Panel" )

				WebUI.delay(2)
			//Verify notification
				result = WebUI.verifyElementPresent(findTestObject('Notificactions/Notification_CompressFile'),5)
				extentTest.log(Status.PASS, "verified notification for operation - "+Operation)
				println("notification status - "+result)
				WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))

				if (TestCaseName.contains('tile view')) {
					WebUI.delay(2)

					WebUI.click(findTestObject("Object Repository/FilesPage/SortBy-Option"))
					WebUI.delay(3)
					WebUI.mouseOver(findTestObject("Object Repository/FilesPage/SortList-Option"))
					WebUI.click(findTestObject("Object Repository/FilesPage/SortList-Option"))
					TestObject sortIconDown=WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/SortBy-Order'), 'class', 'equals',"down-arrow tile-sortable-icon focus_enable_class", true)
					def sortIconUp=WebUI.waitForElementPresent(findTestObject('Object Repository/FilesPage/SortBy-Order'), 3, FailureHandling.CONTINUE_ON_FAILURE)
					def isIconPresent=WebUI.waitForElementPresent(sortIconDown, 3, FailureHandling.CONTINUE_ON_FAILURE)
					println("sortIconUp - "+sortIconUp)
					println("sortIconDown - "+isIconPresent)
					if(sortIconUp)
					{
						WebUI.click(findTestObject('Object Repository/FilesPage/SortBy-Order'))
						WebUI.delay(2)
					}
					def isElemenetPresent=WebUI.waitForElementVisible(findTestObject('FilesPage/RowItem_CompressedFile_TileView'), 10, FailureHandling.CONTINUE_ON_FAILURE)
					println  "RowItem_CompressedFile_TileView - "+isElemenetPresent
					result=isElemenetPresent
				}
				else {
					def isElemenetPresent=WebUI.waitForElementVisible(findTestObject('FilesPage/RowItem_CompressedFile_ListView'), 10, FailureHandling.CONTINUE_ON_FAILURE)
					println  "RowItem_CompressedFile_ListView - "+isElemenetPresent
					result=isElemenetPresent
				}
				WebUI.delay(1)
				String compressedFileName=(new operations_FileModule.CreateFilesPageTestObj()).VerifyCompressedFile(TestCaseName , Operation)
				println "=================================================================="
				println" UNCOMPRESS SCENARIO"
				println "=================================================================="

				if (TestCaseName.contains('tile view')) {
					WebUI.click(findTestObject('FilesPage/RowItem_CompressedFile_TileView'))
					WebUI.rightClick(findTestObject('FilesPage/RowItem_CompressedFile_TileView'))
					extentTest.log(Status.PASS, 'rightClicked on Compressed File ')
					WebUI.delay(3)
					WebUI.click(findTestObject('FilesPage/ContextMenu_FileGrid_UnCompress'))
				}
				else
				{
					WebUI.click(findTestObject('FilesPage/RowItem_CompressedFile_ListView'))
					WebUI.rightClick(findTestObject('FilesPage/RowItem_CompressedFile_ListView'))
					extentTest.log(Status.PASS, 'rightClicked on Compressed File ')
					WebUI.delay(3)
					WebUI.click(findTestObject('FilesPage/ContextMenu_FileGrid_UnCompress'))
					extentTest.log(Status.PASS, 'performed Un-Compress Operation on'+compressedFileName)
				}

				result=(new operations_FileModule.CreateFilesPageTestObj()).VerifyUnCompressedFile(compressedFileName , Operation)
				if (TestCaseName.contains('tile view'))
				{

					TestObject newFileTV = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_TileView'), 'title', 'equals','ToCompress_TV.txt', true)
					WebUI.click(newFileTV)

					println(" original file exixts - "+WebUI.verifyElementPresent(newFileTV, 10, FailureHandling.CONTINUE_ON_FAILURE))
				}
				else
				{
					TestObject newFileLV = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'title', 'equals','ToCompress_LV.txt', true)
					WebUI.click(newFileLV)
					println(" original file exixts - "+WebUI.verifyElementPresent(newFileLV, 10, FailureHandling.CONTINUE_ON_FAILURE))
				}

				return result
				break


			case 'Delete':
				if (TestCaseName.contains('Job Submission')) {
					TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/ContextMenu_JobSubmission_FileOperations'), 'id', 'equals', 'Delete', true)
					WebUI.click(newFileOp)

				}
				else {
					TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/ContextMenu_FileOperation'), 'id', 'equals', 'Delete', true)
					WebUI.click(newFileOp)
				}
				WebUI.delay(3)
				WebUI.click(findTestObject('GenericObjects/btn_Yes'))
			//WebUI.click(findTestObject('FilesPage/Icon_Close'))
				WebUI.delay(2)
				WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
				WebUI.delay(2)
			//Verify notification
				result = WebUI.verifyElementPresent(findTestObject('Notificactions/Notification_DeleteFile'),5)
				println("notification status - "+result)
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

			case 'Upload':
				def filePath = (RunConfiguration.getProjectDir() + '/Upload/ToUpload/ToUpload.txt')
				def newFP=(new generateFilePath.filePath()).getFilePath(filePath)
				println(newFP)
				WebUI.uploadFile(findTestObject('FilesPage/UploadFileBtn'), newFP )
				return true
				break



		}
	}
}