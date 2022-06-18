package operations_FileModule

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.text.SimpleDateFormat

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.aventstack.extentreports.Status

import internal.GlobalVariable

public class one {



	@Keyword
	def executeFileOperations(String Operation,String TestCaseName ,extentTest) {

		def date = new Date()
		def sdf = new SimpleDateFormat("ddMMyyyy_HHmmss")
		def e1 = sdf.format(date)
		def e2 =sdf.format(date)
		boolean result=false
		String msg
		//def Status = com.relevantcodes.extentreports.Status
		println ("Control in Keyword  ---- contextMenu ---- e2 -- "+e2)
		WebUI.delay(2)


		TestObject newFolderObjLevel2 = WebUI.modifyObjectProperty(findTestObject('FilesPage/FolderRowItem_ListView'), 'title','equals', 'ListView', true)

		TestObject newFolderObjToDelete = WebUI.modifyObjectProperty(findTestObject('FilesPage/FolderRowItem_ListView'), 'title','equals', 'ListViewCut', true)


		switch (Operation) {




			case 'Delete':

				WebUI.doubleClick(newFolderObjToDelete)
				extentTest.log(Status.PASS, 'Naigated to ListViewCut folder')
				WebUI.delay(3)
				WebUI.click(findTestObject('Object Repository/FilesPage/CheckBox_SelectAll-JS-RFB'))
				extentTest.log(Status.PASS, ' Click on select all ')
				WebUI.rightClick(findTestObject('JobMonitoringPage/RowItem_JobdDeails'))
				extentTest.log(Status.PASS, 'Right click on folder to perform Delete operation')

				WebUI.click(findTestObject('JobMonitoringPage/Delete'))

				WebUI.click(findTestObject('GenericObjects/btn_Yes'))
				WebUI.delay(2)
				WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
				WebUI.delay(2)
				extentTest.log(Status.PASS, 'Click on Notification button to open Notification Panel')
			//Verify notification

				result = WebUI.verifyElementPresent(findTestObject('Object Repository/Notificactions/Notification_DeleteFile'),5)
				println("notification status - "+result)
				if (result) {
					extentTest.log(Status.PASS, ' Deleted folders and verified notification')
					extentTest.log(Status.PASS, ('Notification is listed'))
				}
				else {
					extentTest.log(Status.PASS, '  Not pasted')
					extentTest.log(Status.FAIL)
				}
				WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
				extentTest.log(Status.PASS, 'Click on Notification button to close Notification Panel')
				return result
				break

			case 'Download':
				WebUI.doubleClick(newFolderObjLevel2)
				WebUI.delay(1)
				WebUI.click(findTestObject('Object Repository/FilesPage/CheckBox_SelectAll-JS-RFB'))
				extentTest.log(Status.PASS, ' Click on select all ')
				WebUI.rightClick(findTestObject('JobMonitoringPage/RowItem_JobdDeails'))
				extentTest.log(Status.PASS, 'Right click on folder to perform Download operation')

				TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('FilesPage/ContextMenu_JobSubmission_Download'), 'id', 'equals', Operation, true)
				WebUI.click(newFileOp)




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

			case 'New File':

				WebUI.doubleClick(newFolderObjLevel2)
				WebUI.click(findTestObject('Object Repository/FilesPage/CheckBox_SelectAll-JS-RFB'))
				extentTest.log(Status.PASS, ' Click on select all ')
				WebUI.rightClick(findTestObject('JobMonitoringPage/RowItem_JobdDeails'))
				extentTest.log(Status.PASS, 'Right click on folder to perform New File operation')

				WebUI.click(findTestObject('JobMonitoringPage/New File'))
				extentTest.log(Status.PASS, 'Click on New File')
				def Renameto=e2+'-NewFile.txt'
				TestObject renameTextBxObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/NewFile_input'), 'value', 'equals', 'New Text Document.txt', true)
				WebUI.setText(renameTextBxObj, Renameto)
				extentTest.log(Status.PASS, 'enterted file name  '+Renameto)

				WebUI.click(findTestObject('FilesPage/btn_Save'))
				WebUI.delay(3)
				extentTest.log(Status.PASS, 'Clicked on Save Button')

				WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
				extentTest.log(Status.PASS, 'Click on Notification button to open Notification Panel')
				WebUI.delay(2)
				TestObject createFileNotification = (new buildTestObj.CreateTestObjFiles()).myTestObjFileCreateNotification(Renameto)
				result = WebUI.verifyElementPresent(createFileNotification, 5)

				msg = ('The File ' + Renameto) + ' has been created successfully'
				println(result)
				if (result)
				{
					extentTest.log(Status.PASS, Renameto + '- Created File and verified notification')
					extentTest.log(Status.PASS, ('Notification with msg - "' + msg) + '" is listed')
				}
				else
				{
					extentTest.log(Status.PASS, Renameto + ' - Not Created')
					extentTest.log(Status.FAIL)
				}
				WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
				extentTest.log(Status.PASS, 'Click on Notification button to close Notification Panel')

				TestObject newFileObj


				newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'title', 'equals', Renameto, true)
				def fileItem = (new customWait.WaitForElement()).WaitForelementPresent(newFileObj, 5,extentTest, 'New file')
				println(Renameto)
				if (Renameto)
				{
					WebUI.waitForElementPresent(newFileObj, 3)
					extentTest.log(Status.PASS, ('Verified File - ' + Renameto) + ' is listed')
				}

				return result

				break


		}
	}

}
