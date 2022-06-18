package operations_FileModule

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.text.SimpleDateFormat

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.aventstack.extentreports.Status


import internal.GlobalVariable

public class MultifolderOperations_JobSub_Icon {


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

				TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('Object Repository/JobMonitoringPage/Resubmit_Icon'), 'text', 'equals', Operation, true)
				WebUI.click(newFileOp)

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
				extentTest.log(Status.PASS, 'Right click on folder to perform Download operation using icon')

				WebUI.click(findTestObject('Object Repository/JobMonitoringPage/Download_Icon'))

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

			case 'Resubmit':

				WebUI.doubleClick(findTestObject('JobMonitoringPage/ListView'))
				extentTest.log(Status.PASS, 'Double click on List view folder')
				WebUI.delay(3)
				WebUI.click(findTestObject('Object Repository/FilesPage/CheckBox_SelectAll-JS-RFB'))
				extentTest.log(Status.PASS, ' Click on select all ')
				WebUI.rightClick(findTestObject('Object Repository/JobMonitoringPage/four'))
				extentTest.log(Status.PASS, 'Right click on folder to perform Resubmit  operation')

				TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('Object Repository/JobMonitoringPage/Resubmit_Icon'), 'text', 'equals', Operation, true)
				WebUI.click(newFileOp)

				WebUI.click(findTestObject('Object Repository/JobMonitoringPage/button_Yes'))

				WebUI.click(findTestObject('JobSubmissionForm/button_Submit_Job'))

				extentTest.log(Status.PASS, 'Clicked on Submit Button ')


				WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobSubmission'), 5)

				def jobText = WebUI.getText(findTestObject('Notificactions/Notification_JobSubmission'))

				extentTest.log(Status.PASS, 'Notification Generated')

				GlobalVariable.G_JobID=(new operations_JobsModule.GetJobRowDetails()).getJobID(jobText)

				extentTest.log(Status.PASS, 'Job ID - ' + GlobalVariable.G_JobID)

				extentTest.log(Status.PASS, 'Job Submission Done for - ' + TestCaseName)


				break



		}
	}
}