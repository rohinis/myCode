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

public class MultifolderOperations_JobSub {


	@Keyword
	def executeFileOperations(String Operation,String TestCaseName ,extentTest) {

		def date = new Date()
		def sdf = new SimpleDateFormat("ddMMyyyy_HHmmss")
		def e1 = sdf.format(date)
		def e2 =sdf.format(date)
		boolean result=false
		//def Status = com.relevantcodes.extentreports.Status
		println ("Control in Keyword")
		WebUI.delay(2)
		switch (Operation) {




			case 'Delete':

				WebUI.doubleClick(findTestObject('JobMonitoringPage/ListView'))
				extentTest.log(Status.PASS, 'Double click on List view folder')
				WebUI.delay(3)
				WebUI.click(findTestObject('Object Repository/FilesPage/CheckBox_SelectAll-JS-RFB'))
				extentTest.log(Status.PASS, ' Click on select all ')
				WebUI.rightClick(findTestObject('Object Repository/JobMonitoringPage/four'))
				extentTest.log(Status.PASS, 'Right click on folder to perform Delete operation')

				WebUI.click(findTestObject('JobMonitoringPage/Delete'))

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
				WebUI.doubleClick(findTestObject('JobMonitoringPage/ListView'))
				extentTest.log(Status.PASS, 'Double click on List view folder')
				WebUI.delay(3)
				WebUI.click(findTestObject('Object Repository/FilesPage/CheckBox_SelectAll-JS-RFB'))
				extentTest.log(Status.PASS, ' Click on select all ')
				WebUI.rightClick(findTestObject('Object Repository/JobMonitoringPage/four'))
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

				WebUI.doubleClick(findTestObject('JobMonitoringPage/ListView'))
				extentTest.log(Status.PASS, 'Double click on List view folder')
				WebUI.delay(3)
				WebUI.click(findTestObject('Object Repository/FilesPage/CheckBox_SelectAll-JS-RFB'))
				extentTest.log(Status.PASS, ' Click on select all ')
				WebUI.rightClick(findTestObject('Object Repository/JobMonitoringPage/four'))
				extentTest.log(Status.PASS, 'Right click on folder to perform New File operation')

				WebUI.click(findTestObject('JobMonitoringPage/New File'))
				extentTest.log(Status.PASS, 'Click on New File')
				def Renameto='NewFile.txt'
				TestObject renameTextBxObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/NewFile_input'), 'value', 'equals', 'New Text Document.txt', true)
				WebUI.setText(renameTextBxObj, Renameto)
				extentTest.log(Status.PASS, 'Renamed file to  '+Renameto)

				WebUI.click(findTestObject('FilesPage/btn_Save'))
				WebUI.delay(3)
				extentTest.log(Status.PASS, 'Clicked on Save Button')
				WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
				result=WebUI.verifyElementPresent(findTestObject('Object Repository/Notificactions/Notification_FileCreation'), 5)
				extentTest.log(Status.PASS, "Opened Notification Panel" )


				break



		}
	}
}