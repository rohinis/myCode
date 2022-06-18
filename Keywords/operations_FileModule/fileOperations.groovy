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

public class fileOperations {


	@Keyword
	def executeFileOperations(String TestOperation,String TestCaseName ,extentTest) {

		def date = new Date()
		def sdf = new SimpleDateFormat("ddMMyyyy_HHmmss")
		def e1 = sdf.format(date)
		def e2 =sdf.format(date)
		boolean result=false
		//def Status = com.relevantcodes.extentreports.Status
		String msg
		println ("Control in Keyword")
		WebUI.delay(2)
		switch (TestOperation) {

			case 'Open':
				TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/ContextMenu_FileOperation'), 'id', 'equals', TestOperation, true)
				WebUI.click(newFileOp)
				extentTest.log(Status.PASS, 'Clicked on Context Menu Option for - '+TestOperation)

				TestObject newFileObjVerify
				TestObject breadCrumbLabel
				def fileName
				String oriFileName
				if (TestCaseName.contains('unsupported')) {
					if(TestCaseName.contains('tile view')) {
						oriFileName='ToOpen_TV.log'
						newFileObjVerify = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_TileView'), 'title', 'equals',oriFileName, true)
						breadCrumbLabel=WebUI.modifyObjectProperty(findTestObject('Object Repository/FileEditor/breadCrumbLabel'), 'title', 'equals',oriFileName, true)
					}
					else {
						oriFileName='ToOpen_LV.log'
						newFileObjVerify = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'title', 'equals',oriFileName, true)
						breadCrumbLabel=WebUI.modifyObjectProperty(findTestObject('Object Repository/FileEditor/breadCrumbLabel'), 'title', 'equals',oriFileName, true)
					}
				}
				else {
					if(TestCaseName.contains('tile view')) {
						extentTest.log(Status.PASS, 'in tile view regular')
						oriFileName='ToOpen_TV.txt'
						newFileObjVerify = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_TileView'), 'title', 'equals',oriFileName, true)
						breadCrumbLabel=WebUI.modifyObjectProperty(findTestObject('Object Repository/FileEditor/breadCrumbLabel'), 'title', 'equals',oriFileName, true)
					}
					else {
						oriFileName='ToOpen_LV.txt'
						newFileObjVerify = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'title', 'equals',oriFileName, true)
						breadCrumbLabel=WebUI.modifyObjectProperty(findTestObject('Object Repository/FileEditor/breadCrumbLabel'), 'title', 'equals',oriFileName, true)
					}
				}
				def closeButtonPresent=WebUI.verifyElementPresent(findTestObject('Object Repository/FilesPage/Close_Button'), 3, FailureHandling.CONTINUE_ON_FAILURE)
				def resultsLabelPresent= WebUI.verifyElementPresent(findTestObject('Object Repository/FileEditor/Label_Result'), 3, FailureHandling.CONTINUE_ON_FAILURE)
				def breadCrumbLabelPresent=WebUI.verifyElementPresent(breadCrumbLabel, 3, FailureHandling.CONTINUE_ON_FAILURE)

				if(closeButtonPresent&&resultsLabelPresent&&breadCrumbLabelPresent) {
					extentTest.log(Status.PASS, 'Verified the bread crumb for file name - '+oriFileName)
					extentTest.log(Status.PASS, 'Verified the Results Lable')
					extentTest.log(Status.PASS, 'Verified the Close button')
					WebUI.click(findTestObject('FilesPage/Close_Button'))
					extentTest.log(Status.PASS, 'Clicked on Close button to navigate back to files page')
					result=true
				}
				else {
					extentTest.log(Status.PASS, 'Results label and close button not present , verification failed')
					result=false
				}

				def fileItem =(new customWait.WaitForElement()).WaitForelementPresent(newFileObjVerify,	5,extentTest,oriFileName)
				println(fileItem)
				if (fileItem) {
					extentTest.log(Status.PASS, 'File listed on files page')
				}

				return result
				break

			case 'Open With':
				WebUI.mouseOver(findTestObject('FilesPage/ContextMenu_Openwith'))
				WebUI.click(findTestObject('FilesPage/ContextMenu_Openwith'))
				WebUI.mouseOver(findTestObject('FilesPage/span_Text Editor'))
				WebUI.delay(1)
				WebUI.click(findTestObject('FilesPage/span_Text Editor'))
				extentTest.log(Status.PASS, 'Clicked on Context Menu Option for - '+TestOperation+' Text Editor')

				TestObject newFileObjVerify
				def fileName
				String oriFileName
				TestObject breadCrumbLabel

				if (TestCaseName.contains('unsupported')) {
					if(TestCaseName.contains('tile view')) {
						println("in tile view unsupported ")
						oriFileName='ToOpenWith_TV.log'
						newFileObjVerify = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_TileView'), 'title', 'equals',oriFileName, true)
						breadCrumbLabel=WebUI.modifyObjectProperty(findTestObject('Object Repository/FileEditor/breadCrumbLabel'), 'title', 'equals',oriFileName, true)
					}
					else {
						oriFileName='ToOpenWith_LV.log'
						newFileObjVerify = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'title', 'equals',oriFileName, true)
						breadCrumbLabel=WebUI.modifyObjectProperty(findTestObject('Object Repository/FileEditor/breadCrumbLabel'), 'title', 'equals',oriFileName, true)
					}
				}else{
					if(TestCaseName.contains('tile view')) {
						oriFileName='ToOpenWith_TV.txt'
						newFileObjVerify = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_TileView'), 'title', 'equals',oriFileName, true)
						breadCrumbLabel=WebUI.modifyObjectProperty(findTestObject('Object Repository/FileEditor/breadCrumbLabel'), 'title', 'equals',oriFileName, true)
					}
					else {
						oriFileName='ToOpenWith_LV.txt'
						newFileObjVerify = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'title', 'equals',oriFileName, true)
						breadCrumbLabel=WebUI.modifyObjectProperty(findTestObject('Object Repository/FileEditor/breadCrumbLabel'), 'title', 'equals',oriFileName, true)
					}
				}

				def closeButtonPresent=WebUI.verifyElementPresent(findTestObject('Object Repository/FilesPage/Close_Button'), 3, FailureHandling.CONTINUE_ON_FAILURE)
				def resultsLabelPresent= WebUI.verifyElementPresent(findTestObject('Object Repository/FileEditor/Label_Result'), 3, FailureHandling.CONTINUE_ON_FAILURE)
				def breadCrumbLabelPresent=WebUI.verifyElementPresent(breadCrumbLabel, 3, FailureHandling.CONTINUE_ON_FAILURE)

				if(closeButtonPresent&&resultsLabelPresent&&breadCrumbLabelPresent) {
					extentTest.log(Status.PASS, 'Verified the bread crumb for file name - '+oriFileName)
					extentTest.log(Status.PASS, 'Verified the Results Lable')
					extentTest.log(Status.PASS, 'Verified the Close button')
					WebUI.click(findTestObject('FilesPage/Close_Button'))
					extentTest.log(Status.PASS, 'Clicked on Close button to navigate back to files page')
					result=true
				}
				else {
					extentTest.log(Status.FAIL, 'Results label and close button not present , verification failed')
					result=false
				}

				def fileItem =(new customWait.WaitForElement()).WaitForelementPresent(newFileObjVerify,	20,extentTest,oriFileName)
				println(fileItem)
				if (fileItem) {
					extentTest.log(Status.PASS, 'File listed on files page')
				}

				return result
				break

			case 'Copy':
				TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/ContextMenu_FileOperation'), 'id', 'equals', TestOperation, true)
				WebUI.click(newFileOp)
				extentTest.log(Status.PASS, 'Clicked on Context Menu Option for - '+TestOperation)
				WebUI.click(findTestObject('FilesPage/Icon_Close'))
				def fileToCheck
				if (TestCaseName.contains('tile view')) {
					TestObject newFolderObj
					newFolderObj=new TestObject('objectName')
					newFolderObj.addProperty('xpath', ConditionType.EQUALS, "//label[contains(text(),'ToPaste')]")
					fileToCheck='ToCopy_TV.txt'
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

				extentTest.log(Status.PASS, 'Invoked context menu in ToPaste Folder')

				extentTest.log(Status.PASS, 'Clicked on Paste Option')

				TestObject newFileObj

				if (TestCaseName.contains('tile view')) {
					WebUI.delay(2)
					newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_TileView'), 'title', 'equals', fileToCheck, true)
				}
				else {
					newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'title', 'equals', fileToCheck, true)
				}
				def isFilePresent=WebUI.waitForElementVisible(newFileObj, 10,FailureHandling.CONTINUE_ON_FAILURE)
				if(isFilePresent){
					result=isFilePresent
					extentTest.log(Status.PASS, 'Verified Pasted File - '+ fileToCheck)
				}

				if(TestCaseName.contains('Job Submission')) {
					msg=GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/JobsModule/JobsModuleFileOps/'+fileToCheck+' copied successfully to '+ GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/JobsModule/JobsModuleFileOps/ToPaste.'
				}
				else {
					msg= GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/FilesModule/FileOps/'+fileToCheck+' copied successfully to '+ GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/FilesModule/FileOps/ToPaste.'
				}

				(new operations_FileModule.notifications()).getNotifications(msg,extentTest)
				extentTest.log(Status.PASS, 'out of notification')


				return result
				break

			case 'Cut':
				TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/ContextMenu_FileOperation'), 'id', 'equals', TestOperation, true)
				WebUI.click(newFileOp)
				extentTest.log(Status.PASS, 'Clicked on Context Menu Option for - '+TestOperation)
				WebUI.click(findTestObject('FilesPage/Icon_Close'))
				def fileToCheck
				if (TestCaseName.contains('tile view')) {
					TestObject newFolderObj
					newFolderObj=new TestObject('objectName')
					newFolderObj.addProperty('xpath', ConditionType.EQUALS, "//label[contains(text(),'ToPaste')]")
					fileToCheck='ToCut_TV.txt'
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
					fileToCheck='ToCut_LV.txt'
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

				TestObject newFileObj

				if (TestCaseName.contains('tile view')) {
					WebUI.delay(2)

					TestObject viewIconTile = WebUI.modifyObjectProperty(findTestObject('Object Repository/FilesPage/Icon_ViewIcon'), 'title',
							'equals', 'Tile View', true)
					def viewIconTilePresent=WebUI.waitForElementPresent(viewIconTile, 3, FailureHandling.CONTINUE_ON_FAILURE)

					if(viewIconTilePresent) {
						WebUI.click(viewIconTile)
						WebUI.delay(2)
					}


					newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_TileView'), 'title', 'equals', fileToCheck, true)
				}
				else {
					newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'title', 'equals', fileToCheck, true)
				}
				def isFilePresent=WebUI.waitForElementVisible(newFileObj, 10,FailureHandling.CONTINUE_ON_FAILURE)
				if(isFilePresent){
					result=isFilePresent
					extentTest.log(Status.PASS, 'Verified Pasted File - '+ fileToCheck)
				}


				if(TestCaseName.contains('Job Submission')) {
					msg=GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/JobsModule/JobsModuleFileOps/'+fileToCheck+' moved successfully to '+ GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/JobsModule/JobsModuleFileOps/ToPaste.'
				}
				else {
					msg= GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/FilesModule/FileOps/'+fileToCheck+' moved successfully to '+ GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/FilesModule/FileOps/ToPaste.'
				}
				WebUI.delay(2)



				(new operations_FileModule.notifications()).getNotifications(msg,extentTest)
				extentTest.log(Status.PASS, 'out of notification')

				return result
				break


			case 'Rename':
				TestObject newFileObjVerify
				TestObject renameTextBxObj
				def Renameto
				def oriFileName
				msg
				TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/ContextMenu_FileOperation'), 'id', 'equals', TestOperation, true)
				WebUI.click(newFileOp)
				extentTest.log(Status.PASS, 'Clicked on Context Menu Option for - '+TestOperation)
				WebUI.delay(2)
				if (TestCaseName.contains('Job Submission')) {
					msg = 'File/Folder renamed successfully from ' +GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/JobsModule/JobsModuleFileOps/'+oriFileName+' to '+ GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/JobsModule/JobsModuleFileOps/'+Renameto
				}
				else{
					if(TestCaseName.contains('tile view')) {
						oriFileName='ToRename_TV.txt'
						Renameto='Renamefile_TV.txt'
						renameTextBxObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/NewFile_input'), 'value', 'equals', 'ToRename_TV.txt', true)
						newFileObjVerify = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_TileView'), 'title', 'equals',Renameto, true)
					}
					else {
						oriFileName='ToRename_LV.txt'
						Renameto='Renamefile_LV.txt'
						renameTextBxObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/NewFile_input'), 'value', 'equals', 'ToRename_LV.txt', true)
						newFileObjVerify = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'title', 'equals',Renameto, true)
					}
					msg = 'File/Folder renamed successfully from ' +GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/FilesModule/FileOps/'+oriFileName+' to '+ GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/FilesModule/FileOps/'+Renameto
				}
				WebUI.waitForElementVisible(renameTextBxObj, 3)
				WebUI.setText(renameTextBxObj, Renameto)
				extentTest.log(Status.PASS, 'Entered the new name - '+ Renameto)
				WebUI.click(findTestObject('FilesPage/btn_Save'))
				extentTest.log(Status.PASS, 'Clicked on Save Button')
				(new operations_FileModule.notifications()).getNotifications(msg,extentTest)
				WebUI.delay(3)
				WebUI.click(findTestObject('FilesPage/Icon_Close'))

				extentTest.log(Status.PASS, 'Clicked on Close icon on search text box ')
				WebUI.delay(2)
				def isElemenetPresent=WebUI.waitForElementVisible(newFileObjVerify, 3,FailureHandling.CONTINUE_ON_FAILURE)
				if(isElemenetPresent){
					extentTest.log(Status.PASS, "Renamed file - "+Renameto + " is listed ")
					result=true
				}
				else {
					extentTest.log(Status.PASS, "Renamed file - "+Renameto + " is listed ")
					result=false
				}

				return result
				break

			case 'Compress':
				WebUI.click(findTestObject('FilesPage/ContextMenu_FileGrid_Compress'))
				WebUI.delay(5)
				extentTest.log(Status.PASS, 'Clicked on Context Menu Option for - '+TestOperation)
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
				WebUI.delay(1)

				String compressedFileName=(new operations_FileModule.CreateFilesPageTestObj()).VerifyCompressedFile(TestCaseName , extentTest)

				println "=================================================================="
				println" UNCOMPRESS SCENARIO"
				println "=================================================================="
				if (TestCaseName.contains('tile view')) {
					WebUI.click(findTestObject('FilesPage/RowItem_CompressedFile_TileView'))
					WebUI.rightClick(findTestObject('FilesPage/RowItem_CompressedFile_TileView'))
					extentTest.log(Status.PASS, 'RightClicked on Compressed File ')
				}
				else {
					WebUI.click(findTestObject('FilesPage/RowItem_CompressedFile_ListView'))
					WebUI.rightClick(findTestObject('FilesPage/RowItem_CompressedFile_ListView'))
					extentTest.log(Status.PASS, 'rightClicked on Compressed File ')
				}
				WebUI.delay(3)
				WebUI.click(findTestObject('FilesPage/ContextMenu_FileGrid_UnCompress'))
				extentTest.log(Status.PASS, 'Clicked on menu item Un-Compress on file - '+compressedFileName)

				result=(new operations_FileModule.CreateFilesPageTestObj()).VerifyUnCompressedFile(compressedFileName , extentTest)
				if (TestCaseName.contains('tile view')) {
					TestObject newFileTV = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_TileView'), 'title', 'equals','ToCompress_TV.txt', true)
					WebUI.click(newFileTV)
					println(" original file exixts - "+WebUI.verifyElementPresent(newFileTV, 10, FailureHandling.CONTINUE_ON_FAILURE))
					extentTest.log(Status.PASS, ' Verified the origial compressed file is listed  - ToCompress_TV.txt')
				}
				else {
					TestObject newFileLV = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'title', 'equals','ToCompress_LV.txt', true)
					WebUI.click(newFileLV)
					println(" original file exixts - "+WebUI.verifyElementPresent(newFileLV, 10, FailureHandling.CONTINUE_ON_FAILURE))
					extentTest.log(Status.PASS, ' Verified the origial compressed file is listed  - ToCompress_LV.txt')
				}
				return result
				break

			case 'Download':
				println TestOperation
				if (TestCaseName.contains('Job Submission')) {
					TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/ContextMenu_JobSubmission_Download'), 'id', 'equals', TestOperation, true)
					WebUI.click(newFileOp)
				}
				else {

					WebUI.click(findTestObject('Object Repository/FilesPage/ContextMenu_FileGrid_Download'))
				}
				WebUI.delay(5)
				extentTest.log(Status.PASS, ' Clicked on Download Menu Item')
				extentTest.log(Status.PASS, 'File downloaded ')

			/*File downloadFolder = new File(GlobalVariable.G_DownloadFolder)
			 List namesOfFiles = Arrays.asList(downloadFolder.list())
			 if (namesOfFiles.contains('ToDownload_LV.txt')) {
			 println('success')
			 //extentTest.log(Status.PASS, 'file to downloaded ')
			 } else {
			 println('fail')
			 }
			 extentTest.log(Status.PASS, 'Verified file existes on host machine at path - '+GlobalVariable.G_DownloadFolder)
			 */
				return true
				break

			case 'Upload':
				def filePath = (RunConfiguration.getProjectDir() + '/Upload/ToUpload/ToUpload.txt')
				def newFP=(new generateFilePath.filePath()).getFilePath(filePath)
				println(newFP)
				WebUI.uploadFile(findTestObject('FilesPage/UploadFileBtn'), newFP )
				return true
				break

			case 'Delete':
				def oriFileName
				TestObject newFileObjVerify
				TestObject newFileOp

				if (TestCaseName.contains('JobSubmission')) {
					WebUI.click(findTestObject('Object Repository/JobSubmissionForm/SubMenu_Delete'))
				}
				else{
					newFileOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/ContextMenu_FileOperation'), 'id', 'equals', 'Delete', true)
					WebUI.click(newFileOp)
					WebUI.delay(2)
				}
				extentTest.log(Status.PASS, 'Clicked on Delete menu item' )
				WebUI.click(findTestObject('GenericObjects/btn_Yes'))
				WebUI.delay(2)
				extentTest.log(Status.PASS, 'Clicked on Yes on Delete confirmation pop-up ')
				WebUI.delay(3)
				WebUI.click(findTestObject('FilesPage/Icon_Close'))
				TestObject newFileObj

				if(TestCaseName.contains('tile view')) {
					oriFileName='ToDelete_TV.txt'
					newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_TileView'), 'title', 'equals',oriFileName, true)
				}
				else {
					oriFileName='ToDelete_LV.txt'
					newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'title', 'equals',oriFileName, true)
				}
				def isDelFilePresent=WebUI.waitForElementVisible(newFileObj, 3,FailureHandling.CONTINUE_ON_FAILURE)

				if(isDelFilePresent==false){
					result=true
					extentTest.log(Status.PASS, 'Verified deleted file - '+ oriFileName +' not listed')
				}

				if (result) {
					extentTest.log(Status.PASS, ' Deleted file and verified notification')
					//	extentTest.log(Status.PASS, ('Notification with msg - "' + msg) + '" is listed')
				}
				else {
					extentTest.log(Status.PASS, '  Not deleted')
					extentTest.log(Status.FAIL)
				}
				return result
				break
		}
	}
}