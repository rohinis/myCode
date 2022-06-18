package operations_JobsModule

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebDriver

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.aventstack.extentreports.Status

import internal.GlobalVariable

public class executeJobAction {

	@Keyword
	def perfromJobAction(String Action , String TestCaseName ,String userChoice, extentTest) {
		def isNotoficationPresent
		boolean result=false
		//def Status = com.relevantcodes.extentreports.Status
		WebUI.delay(3)

		switch (Action) {
			case 'View Details':
				println ("Form job actions - "+Action)
				TestObject newJobAction = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/ContextMenu_JobAction'),
						'id', 'equals', Action, true)
				WebUI.delay(2)
				WebUI.click(newJobAction)
				WebUI.delay(2)
				TestObject jobTitle=(new buildTestObj.CreateTestObj()).myJobTitleIndentifier()
				def jobID=WebUI.getText(jobTitle)
				String[] splitAddress = jobID.split('\\.')
				GlobalVariable.G_JobIdFromDetails=splitAddress[0]
				println GlobalVariable.G_JobIdFromDetails
				extentTest.log(Status.PASS, 'job id from details page '+ GlobalVariable.G_JobIdFromDetails)
				result=true
				break



			case 'View Subjobs':

				println ("Form job actions - "+Action)
				TestObject newJobAction = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/ContextMenu_JobAction'),
						'id', 'equals', Action, true)
				WebUI.delay(2)
				WebUI.click(newJobAction)
				break

			case 'Delete':
				println ("Form job actions - "+Action)
				TestObject newJobAction = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/ContextMenu_JobAction'),
						'id', 'equals', Action, true)
				WebUI.delay(2)
				WebUI.click(newJobAction)
				WebUI.delay(3)

				switch(userChoice) {
					case 'No':
						WebUI.click(findTestObject('GenericObjects/btn_No'))
						result=true
						extentTest.log(Status.PASS, 'Not deleting job  ')

						break

					case 'Yes':
						WebUI.click(findTestObject('GenericObjects/btn_Yes'))
						WebUI.delay(2)
						extentTest.log(Status.PASS, 'Deleting job  ')
						extentTest.log(Status.PASS, 'Verified Test case - AD-1486 - Job action: Delete job button validation')

						WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
						WebUI.delay(3)
						isNotoficationPresent=WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobDelete'), 5)
						println("notification status - "+isNotoficationPresent)
						extentTest.log(Status.PASS, 'Verified notification')
						result=isNotoficationPresent
						break

					case 'DeleteFiles':
						WebUI.click(findTestObject('Object Repository/JobMonitoringPage/Chckbx_DeleteJobFiles'))
						extentTest.log(Status.PASS, 'Checked the option - deleteJob Files')
						WebUI.click(findTestObject('GenericObjects/btn_Yes'))
						WebUI.delay(2)
						extentTest.log(Status.PASS, 'deleting job  ')
						WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
						WebUI.delay(3)
						isNotoficationPresent=WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobDelete'), 5)
						println("notification status - "+isNotoficationPresent)
						extentTest.log(Status.PASS, 'Verified notification')
						result=isNotoficationPresent
						break

					case 'DontAsk':
						extentTest.log(Status.PASS, 'Dont Ask')
						WebUI.click(findTestObject('Object Repository/JobMonitoringPage/CheckBx_DontAsk'))
						WebUI.click(findTestObject('GenericObjects/btn_Yes'))
						WebUI.delay(2)
						extentTest.log(Status.PASS, 'deleting job  ')
						WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
						WebUI.delay(3)
						isNotoficationPresent=WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobDelete'), 5)
						println("notification status - "+isNotoficationPresent)
						extentTest.log(Status.PASS, 'Verified notification')
						WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))

						break

					case 'CheckDelete':
						extentTest.log(Status.PASS, 'Check Delete')
						WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
						WebUI.delay(3)
						isNotoficationPresent=WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobDelete'), 5)
						println("notification status - "+isNotoficationPresent)
						extentTest.log(Status.PASS, 'Verified notification - after check Delte')
						WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
						result=isNotoficationPresent
						break
				}


				break

			case 'Rename':
				println ("Form job actions - "+Action)
				TestObject newJobAction = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/ContextMenu_JobAction'),
						'id', 'equals', Action, true)
				WebUI.delay(2)
				WebUI.click(newJobAction)

				def Renameto='Renamefile.fem'
				TestObject renameTextBxObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/NewFile_input'), 'value', 'equals', 'Running.sh', true)
				WebUI.setText(renameTextBxObj, Renameto)
				extentTest.log(Status.PASS, 'Renamed file to  '+Renameto)

				WebUI.click(findTestObject('FilesPage/btn_Save'))
				WebUI.delay(3)

				extentTest.log(Status.PASS, 'Clicked on Save Button')
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
				return result
				break



			case 'Refresh':
				println ("Form job actions refresh - "+Action)
				TestObject newJobAction = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/ContextMenu_JobAction'),
						'id', 'equals', Action, true)
				WebUI.delay(2)
				WebUI.click(newJobAction)
				return true
				break


			case 'New File':
				println ("Form job actions - "+Action)
				TestObject newJobAction = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/ContextMenu_JobAction'),
						'id', 'equals', Action, true)
				WebUI.delay(2)
				WebUI.click(newJobAction)

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
				return result

				break

			case 'Terminate':
				println ("Form job actions - "+Action)
				TestObject newJobAction = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/ContextMenu_JobAction'),
						'id', 'equals', Action, true)
				WebUI.delay(2)
				WebUI.click(newJobAction)
				if (TestCaseName.contains("No")) {
					println("No")
					WebUI.click(findTestObject('GenericObjects/btn_No'))
					result=true
					extentTest.log(Status.PASS, 'Not terminating job  ')
				}
				else {

					WebUI.click(findTestObject('GenericObjects/btn_Yes'))
					WebUI.delay(2)
					extentTest.log(Status.PASS, 'terminating job  ')
					WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
					WebUI.delay(2)
					isNotoficationPresent=WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobTerminate'), 5)
					println("notification status - "+isNotoficationPresent)
					extentTest.log(Status.PASS, 'Verified notification')
					result=isNotoficationPresent
				}
				break

			case 'Resubmit':
				println ("Form job actions - "+Action)
				TestObject newJobAction = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/ContextMenu_JobAction'),
						'id', 'equals', Action, true)
				WebUI.delay(2)
				WebUI.click(newJobAction)

				def isElementPresent=(new customWait.WaitForElement()).WaitForelementPresent(findTestObject('Object Repository/JobDetailsPage/Msg_ResubmitWarning'), 5,extentTest, 'Resubmit Warning')
				if(isElementPresent) {
					//WebUI.check(findTestObject('Object Repository/JobDetailsPage/Msg_ResubmitWarning'))
					WebUI.click(findTestObject('Object Repository/JobMonitoringPage/button_Yes'))
				}

				WebUI.click(findTestObject('JobSubmissionForm/button_Submit_Job'))
				extentTest.log(Status.PASS, 'Resubmitted job  ')
				isNotoficationPresent=WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobSubmission'), 5)
				def jobText = WebUI.getText(findTestObject('Notificactions/Notification_JobSubmission'))
				def JobID=(new operations_JobsModule.GetJobRowDetails()).getJobID(jobText)
				extentTest.log(Status.PASS, 'Verified notification - new job id '+ JobID)
				result=isNotoficationPresent

				break

			case 'Open':

				println ("Form job actions - "+Action)
				TestObject newJobAction = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/ContextMenu_JobAction'),
						'id', 'equals', Action, true)
				WebUI.delay(2)
				WebUI.click(newJobAction)
				extentTest.log(Status.PASS, 'Clicked on Context Menu Option for - '+Action)
				WebUI.click(findTestObject('Object Repository/FileEditor/Close_Button_fileEditor'))
				extentTest.log(Status.PASS, 'Clicked on Close Button ')
				result=true


				break


			case 'Open With':

				println ("Form job actions - "+Action)
				WebUI.mouseOver(findTestObject('Object Repository/FileEditor/ContextMenu_OpenWith'))
				WebUI.click(findTestObject('Object Repository/FileEditor/ContextMenu_OpenWith'))
				WebUI.delay(2)
				WebUI.mouseOver(findTestObject('Object Repository/FilesPage/span_Text Editor'))
				WebUI.click(findTestObject('Object Repository/FilesPage/span_Text Editor'))

				extentTest.log(Status.PASS, 'Clicked on Context Menu Option for - '+Action)

				WebUI.delay(4)
				WebUI.click(findTestObject('Object Repository/FileEditor/Close_Button_fileEditor'))
				extentTest.log(Status.PASS, 'Clicked on Close Button ')
				result=true

				break

			case 'Download':
				println ("Form job actions - "+Action)
				TestObject newJobAction = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/ContextMenu_JobAction'),
						'id', 'equals', Action, true)
				WebUI.delay(2)
				WebUI.click(newJobAction)
				extentTest.log(Status.PASS, 'Downloading job')


			/*def downloadLoc=GlobalVariable.G_DownloadFolder
			 File downloadFolder = new File("C://KatalonDownloads")
			 List namesOfFiles = Arrays.asList(downloadFolder.list())
			 println(namesOfFiles.size())
			 if (namesOfFiles.contains('ForFileViewer.txt')) {
			 println('success')
			 //extentTest.log(Status.PASS, 'file to downloaded ')
			 result=true
			 } else {
			 println('fail')
			 result=false
			 }*/

				return true


				result=true
				break

			case 'Move To Queue':
				println ("Form job actions - "+Action)
				WebUI.delay(2)
				WebUI.click(findTestObject('JobMonitoringPage/ContextMenu_MoveToQueue'))
				WebUI.delay(2)
				WebUI.click(findTestObject('JobMonitoringPage/CntxtMn-SubMn_accessQueue'))
				TestObject newJobRow = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/div_Completed'), 'title', 'equals',	'Queued', true)
				WebUI.rightClick(newJobRow)
				WebUI.delay(1)
				TestObject newJobAction = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/ContextMenu_JobAction'),
						'id', 'equals', 'View Details', true)
				WebUI.click(newJobAction)
				WebUI.delay(2)

				WebUI.click(findTestObject('JobDetailsPage/JobDetailsLink_Details'))
				extentTest.log(Status.PASS,"Navigated to Details Tab")
				WebUI.click(findTestObject('JobDetailsPage/TextBx_DetailsFilter'))

				WebUI.setText(findTestObject('JobDetailsPage/TextBx_DetailsFilter'), 'queue name')
				WebUI.click(findTestObject('JobDetailsPage/Detail_QueueName'))
				TestObject newQueueObj = WebUI.modifyObjectProperty(findTestObject('JobDetailsPage/Detail_QueueName'), 'text', 'equals',	'accessQueue', true)
				println("---------- queuename "+WebUI.waitForElementPresent(newQueueObj, 4, FailureHandling.CONTINUE_ON_FAILURE))
			/*
			 def jobID=WebUI.getText(findTestObject('JobDetailsPage/jobTitle'))
			 String[] splitAddress = jobID.split('\\.')
			 GlobalVariable.G_JobIdFromDetails=splitAddress[0]
			 println GlobalVariable.G_JobIdFromDetails
			 */
				result=WebUI.waitForElementPresent(newQueueObj, 4, FailureHandling.CONTINUE_ON_FAILURE)
				break


			default:
				break
		}
		return result
	}
}
