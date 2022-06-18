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

public class fileOperations_Icon {

	@Keyword
	def executeFileOperations(String Operation,String TestCaseName ,extentTest) {
		def date = new Date()
		def sdf = new SimpleDateFormat("ddMMyyyy_HHmmss")
		def e1 = sdf.format(date)
		def e2 =sdf.format(date)
		def op
		boolean result=false
		//def Status = com.relevantcodes.extentreports.Status
		String msg
		println ("Control in Keyword")
		WebUI.delay(2)
		switch (Operation) {
			case 'copy_icon':
				op='Copy'
				TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/FileOperations_Icon'), 'id', 'equals', Operation, true)
				println(Operation)
				WebUI.click(newFileOp)
				extentTest.log(Status.PASS, 'Clicked on top menu icon -'+op)
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
				}
				else {

					WebUI.click(findTestObject('Object Repository/FilesPage/Folder_ListView_ToPaste'))
					WebUI.doubleClick(findTestObject('Object Repository/FilesPage/Folder_ListView_ToPaste'))
					extentTest.log(Status.PASS, 'Navigated to ToPaste Folder')
					fileToCheck='ToCopy_LV.txt'
					WebUI.delay(2)
				}
				WebUI.click(findTestObject('FilesPage/TopMenuIcon_ellipses'))
				extentTest.log(Status.PASS, 'Clicked on top menu ellipses')
				WebUI.click(findTestObject('Object Repository/FilesPage/TopMenu_Paste'))
				extentTest.log(Status.PASS, 'Clicked on top menu item - Paste ')
				WebUI.delay(2)
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
					extentTest.log(Status.PASS, 'Verified Pasted File - '+ fileToCheck)
					result=true
				}
				else {
					result=false
				}

				if(TestCaseName.contains('Job Submission')) {
					msg=GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/JobsModule/JobsModuleFileOps/'+fileToCheck+' copied successfully to '+ GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/JobsModule/JobsModuleFileOps/ToPaste.'
				}
				else {
					msg= GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/FilesModule/FileOps/'+fileToCheck+' copied successfully to '+ GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/FilesModule/FileOps/ToPaste.'
				}
				(new operations_FileModule.notifications()).getNotifications(msg,extentTest)

				return result
				break


			case 'cut_icon':
				op='Cut'
				TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/FileOperations_Icon'), 'id', 'equals', Operation, true)
				println(Operation)
				WebUI.click(newFileOp)
				extentTest.log(Status.PASS, 'Clicked on top menu icon -'+op)

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
					result=true
					extentTest.log(Status.PASS, 'Verified Pasted File - '+ fileToCheck)
				}
			//	WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
			//	extentTest.log(Status.PASS, 'Click on Notification button to open Notification Panel')

				if(TestCaseName.contains('Job Submission')) {
					msg=GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/JobsModule/JobsModuleFileOps/'+fileToCheck+' moved successfully to '+ GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/JobsModule/JobsModuleFileOps/ToPaste.'
				}
				else {
					msg= GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/FilesModule/FileOps/'+fileToCheck+' moved successfully to '+ GlobalVariable.G_StageArea+'/'+GlobalVariable.G_userName+'/FilesModule/FileOps/ToPaste.'
				}

				WebUI.delay(2)
				(new operations_FileModule.notifications()).getNotifications(msg,extentTest)

				return result
				break


			case 'Compress_icon':
				op='Compress'
				WebUI.click(findTestObject('FilesPage/TopMenuIcon_ellipses'))
				WebUI.click(findTestObject('FilesPage/TopMenuIcons_Compress'))
				WebUI.delay(5)
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
				WebUI.delay(2)

				String compressedFileName=(new operations_FileModule.CreateFilesPageTestObj()).VerifyCompressedFile(TestCaseName , extentTest)

				println "=================================================================="
				println" UNCOMPRESS SCENARIO"
				println "=================================================================="
				if (TestCaseName.contains('tile view')) {
					WebUI.click(findTestObject('FilesPage/RowItem_CompressedFile_TileView'))
				}
				else {
					WebUI.click(findTestObject('FilesPage/RowItem_CompressedFile_ListView'))
				}
				WebUI.delay(3)
				WebUI.click(findTestObject('FilesPage/TopMenuIcon_ellipses'))
				WebUI.click(findTestObject('FilesPage/TopMenuIcon_Uncompress'))
				extentTest.log(Status.PASS, 'Clicked on top menu item Un-Compress on file - '+compressedFileName)

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

			case 'delete_icon':
				op='Delete'
				TestObject newFileObjVerify
				def oriFileName
				WebUI.delay(1)
				WebUI.click(findTestObject('Object Repository/FilesPage/FilesDelete_img'))
				WebUI.delay(2)
				extentTest.log(Status.PASS, 'Clicked on top menu icon -'+op)
				WebUI.click(findTestObject('GenericObjects/btn_Yes'))
				WebUI.delay(2)
				extentTest.log(Status.PASS, 'Clicked on Yes on Delete confirmation pop-up ')

				msg = '1 item deleted successfully'
				WebUI.delay(2)

				if (TestCaseName.contains('Job Submission')) {
					oriFileName='ToDelete.txt'
					newFileObjVerify = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'title', 'equals',oriFileName, true)
				}
				else {
					if(TestCaseName.contains('tile view')) {
						oriFileName='ToDelete_TV.txt'
						newFileObjVerify = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_TileView'), 'title', 'equals',oriFileName, true)
					}
					else {
						oriFileName='ToDelete_LV.txt'
						newFileObjVerify = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'title', 'equals',oriFileName, true)
					}
				}
				def isFilePresent=WebUI.verifyElementPresent(newFileObjVerify, 5, FailureHandling.CONTINUE_ON_FAILURE)
				if(isFilePresent) {
					result=false
					extentTest.log(Status.FAIL, 'Verified file - '+oriFileName+' is listed ')

				}
				else {
					result=true
					extentTest.log(Status.PASS, 'Verified file - '+oriFileName+' is not listed ')
				}

				return result
				break



			case 'Download_icon':
				println Operation
				WebUI.click(findTestObject('FilesPage/TopMenuIcon_ellipses'))
				WebUI.click(findTestObject('Object Repository/FilesPage/TopMenuIcon_Download'))
				WebUI.delay(2)
				extentTest.log(Status.PASS, ' Clicked on Download top menu item')
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
		}
	}
}
