package operations_JobsModule

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.aventstack.extentreports.Status
import internal.GlobalVariable

public class executeJobAction_JobFiles_Operations {


	@Keyword
	def perfromJobAction(String Action , String TestCaseName , extentTest,userChoice) {
		def isNotoficationPresent
		def fileName
		def fileName1
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
				return result
				break



			case 'View Subjobs':

				println ("Form job actions - "+Action)
				TestObject newJobAction = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/ContextMenu_JobAction'),
						'id', 'equals', Action, true)
				WebUI.delay(2)
				WebUI.click(newJobAction)
				return result
				break

			case 'Delete':
				fileName='ToDelete.txt'
				TestObject newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'title', 'equals',
						fileName, true)
				WebUI.scrollToElement(newFileObj, 3)
				WebUI.rightClick(newFileObj)
				WebUI.delay(2)
				extentTest.log(Status.PASS, 'Clicked on Context Menu Option for - Delete')
				println ("Form job actions - "+Action)
				TestObject newJobAction = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/ContextMenu_JobAction'),
						'id', 'equals', Action, true)
				WebUI.delay(2)
				WebUI.click(newJobAction)
				WebUI.delay(3)
				if (TestCaseName.contains("No")) {
					WebUI.click(findTestObject('GenericObjects/btn_No'))
					result=true
					extentTest.log(Status.PASS, 'Not deleting file as per test case ')
				}
				else {
					WebUI.click(findTestObject('GenericObjects/btn_Yes'))
					WebUI.delay(2)
					extentTest.log(Status.PASS, 'Deleting file   ')
					WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
					WebUI.delay(3)
					isNotoficationPresent=WebUI.waitForElementPresent(findTestObject('Object Repository/Notificactions/Notification_DeleteFile'), 5)
					println("notification status - "+isNotoficationPresent)
					extentTest.log(Status.PASS, 'Verified notification')
					result=isNotoficationPresent
				}
				return result
				break

			case 'Rename':
				fileName='ToRename.txt'
				TestObject newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'title', 'equals',
						fileName, true)
				WebUI.scrollToElement(newFileObj, 3)
				WebUI.rightClick(newFileObj)
				WebUI.delay(2)

				println ("Form job actions - "+Action)
				TestObject newJobAction = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/ContextMenu_JobAction'),
						'id', 'equals', Action, true)
				WebUI.delay(2)
				WebUI.click(newJobAction)

				def Renameto='Renamefile.fem'
				TestObject renameTextBxObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/NewFile_input'), 'value', 'equals', fileName, true)
				WebUI.setText(renameTextBxObj, Renameto)
				extentTest.log(Status.PASS, 'Renamed file to  '+Renameto)

				WebUI.click(findTestObject('FilesPage/btn_Save'))
				WebUI.delay(3)

				extentTest.log(Status.PASS, 'Clicked on Save Button')
				TestObject newFileObj1 = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'title', 'equals',
						Renameto, true)
				def isElemenetPresent=WebUI.waitForElementVisible(newFileObj1, 10,FailureHandling.CONTINUE_ON_FAILURE)
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
				fileName='Running.sh'
				TestObject newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'title', 'equals',
						fileName, true)
				WebUI.scrollToElement(newFileObj, 3)
				WebUI.rightClick(newFileObj)
				WebUI.delay(2)

				println ("Form job actions refresh - "+Action)
				TestObject newJobAction = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/ContextMenu_JobAction'),
						'id', 'equals', Action, true)
				WebUI.delay(2)
				WebUI.click(newJobAction)
				return true
				break


			case 'New File':
				if(userChoice=='Running') {
					fileName='Running.sh'
				}
				else  {
					fileName ='RunJob.sh'
				}
				TestObject newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'title', 'equals',fileName, true)
				WebUI.scrollToElement(newFileObj, 3)
				WebUI.rightClick(newFileObj)
				WebUI.delay(2)

				println ("Form job actions - "+Action)
				TestObject newJobAction = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/ContextMenu_JobAction'),
						'id', 'equals', Action, true)
				WebUI.delay(2)
				WebUI.click(newJobAction)

				if(TestCaseName.contains('duplicate')) {
					def newfile=fileName
					TestObject renameTextBxObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/NewFile_input'), 'value', 'equals', 'New Text Document.txt', true)
					WebUI.setText(renameTextBxObj,newfile)
					extentTest.log(Status.PASS, 'NewFile name -   '+newfile)


					WebUI.click(findTestObject('FilesPage/btn_Save'))
					WebUI.delay(3)
					extentTest.log(Status.PASS, 'Clicked on Save Button')

					WebUI.click(findTestObject('Object Repository/JobMonitoringPage/button_Cancel'))
					extentTest.log(Status.PASS, "Click on Cancel button to return to page " )

					WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
					result=WebUI.verifyElementPresent(findTestObject('Object Repository/Notificactions/Notification_Duplicate'), 5)
					def text4 = WebUI.getText(findTestObject('Object Repository/Notificactions/Notification_Duplicate'))

					extentTest.log(Status.PASS, 'Notification Generated ' + text4)
					extentTest.log(Status.PASS, "Opened Notification Panel and verify the notification" )
				}/*
			 else if(TestCaseName.contains('duplicate Output')) {
			 def newfile1=fileName
			 TestObject renameTextBxObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/NewFile_input'), 'value', 'equals', 'New Text Document.txt', true)
			 WebUI.setText(renameTextBxObj,newfile1)
			 extentTest.log(Status.PASS, 'NewFile name -   '+newfile1)
			 WebUI.click(findTestObject('Object Repository/JobMonitoringPage/button_Cancel'))
			 extentTest.log(Status.PASS, "Click on Cancel button" )
			 WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
			 result=WebUI.verifyElementPresent(findTestObject('Object Repository/Notificactions/Notification_Duplicate'), 5)
			 def text4 = WebUI.getText(findTestObject('Object Repository/Notificactions/Notification_Duplicate'))
			 extentTest.log(Status.PASS, 'Notification Generated ' + text4)
			 extentTest.log(Status.PASS, "Opened Notification Panel and verify the notification" )
			 }
			 */
				else{
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
				}
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
				return result
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
				extentTest.log(Status.PASS, 'resubmitted job  ')
				isNotoficationPresent=WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobSubmission'), 5)
				def jobText = WebUI.getText(findTestObject('Notificactions/Notification_JobSubmission'))
				String[] splitAddress = jobText.split('\\(')
				def JobID = splitAddress[1]
				String[] jobIdArr = JobID.split('\\)')
				def toget = (jobIdArr[0])
				println ("job id from keyword - "+ toget)
				extentTest.log(Status.PASS, 'Verified notification - new job id '+ toget)
				result=isNotoficationPresent
				return result
				break

			case 'Open':

				if(TestCaseName.contains('Running')) {

					fileName='Running.sh'
					TestObject newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'title', 'equals',
							fileName, true)
					WebUI.rightClick(newFileObj)
					WebUI.delay(2)
				}
				else{


					fileName1= 'RunJob.sh'
					TestObject newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'title', 'equals',
							fileName1, true)
					WebUI.rightClick(newFileObj)
					WebUI.delay(2)
				}
				println ("Form job actions - "+Action)
				TestObject newJobAction = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/ContextMenu_JobAction'),
						'id', 'equals', Action, true)
				WebUI.delay(2)
				WebUI.click(newJobAction)
				extentTest.log(Status.PASS, 'Clicked on Context Menu Option for - '+Action)
				WebUI.click(findTestObject('Object Repository/FileEditor/Close_Button_fileEditor'))
				extentTest.log(Status.PASS, 'Clicked on Close Button ')
				result=true
				TestObject TestObjFolder=(new buildTestObj.CreateJobSubmissionObjs()).myJobDetailsFolder(userChoice)
				def isFolderPresent=WebUI.verifyElementPresent(TestObjFolder,3)
				println isFolderPresent
				return result
				break


			case 'Open With':
				if(TestCaseName.contains('Running')) {

					WebUI.rightClick(findTestObject('Object Repository/JobMonitoringPage/Running.e'))
					extentTest.log(Status.PASS, 'Click on Running.e file')
				}
				else if(TestCaseName.contains('Input')) {
					WebUI.rightClick(findTestObject('Object Repository/JobMonitoringPage/RunJob'))
					extentTest.log(Status.PASS, 'Click on RunJob file')
				}
				else {
					WebUI.rightClick(findTestObject('Object Repository/JobMonitoringPage/RunJob.e'))
					extentTest.log(Status.PASS, 'Click on RunJob.e file')
				}
				WebUI.delay(2)
				println ("Form job actions - "+Action)
				WebUI.mouseOver(findTestObject('Object Repository/FileEditor/ContextMenu_OpenWith'))
				WebUI.click(findTestObject('Object Repository/FileEditor/ContextMenu_OpenWith'))
				WebUI.delay(2)
				extentTest.log(Status.PASS, 'Clicked on Context Menu Option for - '+Action)

				WebUI.delay(4)
			//WebUI.click(findTestObject('Object Repository/FileEditor/Close_Button_fileEditor'))
				extentTest.log(Status.PASS, 'Clicked on Close Button ')

				WebUI.mouseOver(findTestObject('Object Repository/JobMonitoringPage/OtherApps'))
				WebUI.click(findTestObject('Object Repository/JobMonitoringPage/OtherApps'))
				extentTest.log(Status.PASS, 'Clicked on Other apps ')
				WebUI.verifyElementPresent(findTestObject('Object Repository/JobMonitoringPage/All'), 3)
				extentTest.log(Status.PASS, 'Verify All radio button is present ')

				WebUI.verifyElementPresent(findTestObject('Object Repository/JobMonitoringPage/Native'), 3)
				extentTest.log(Status.PASS, 'Verify Native radio button is present ')

				WebUI.verifyElementPresent(findTestObject('Object Repository/JobMonitoringPage/Always'), 3)
				extentTest.log(Status.PASS, 'Verify Always use this checkbox is present ')

				WebUI.verifyElementPresent(findTestObject('Object Repository/JobMonitoringPage/Customize'), 3)
				extentTest.log(Status.PASS, 'Verify Customize Options checkbox is present ')

				WebUI.verifyElementPresent(findTestObject('Object Repository/JobMonitoringPage/Search_input'), 3)
				extentTest.log(Status.PASS, 'Verify Search button is present ')

				WebUI.click(findTestObject('Object Repository/JobMonitoringPage/TextEditor'))
				extentTest.log(Status.PASS, 'Click on TextEditor ')

				WebUI.click(findTestObject('Object Repository/JobMonitoringPage/Ok_btn'))
				extentTest.log(Status.PASS, 'Click on Ok button ')

				WebUI.click(findTestObject('Object Repository/FileEditor/Close_Button_fileEditor'))
				extentTest.log(Status.PASS, 'Clicked on Close Button ')

			//String fileLocation = WebUI.getAttribute(findTestObject,  "title")



				result=true
				return result
				break

			case 'Download':
				fileName='Running.sh'
				TestObject newFileObj = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'title', 'equals',
						fileName, true)
				WebUI.rightClick(newFileObj)
				WebUI.delay(2)
				println ("Form job actions - "+Action)
				TestObject newJobAction = WebUI.modifyObjectProperty(findTestObject('JobMonitoringPage/ContextMenu_JobAction'),
						'id', 'equals', Action, true)
				WebUI.delay(2)
				WebUI.click(newJobAction)
				extentTest.log(Status.PASS, 'Downloading job')


				def downloadLoc=GlobalVariable.G_DownloadFolder
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
				}

				return true
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
				return result
				break


			default:
				break
		}
		return result
	}
}
