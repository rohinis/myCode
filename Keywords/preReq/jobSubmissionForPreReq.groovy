package preReq

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI



public class jobSubmissionForPreReq {

	@Keyword
	def JSPreReq(String newFP , String AppName,extentTest) {

		String App=AppName.toLowerCase()

		String x1="//span[@id='node-name-"
		String x2="']"
		String xpathAppIdentifier=x1+App+x2
		TestObject AppObj = new TestObject('objectName')
		AppObj.addProperty('xpath', ConditionType.EQUALS, xpathAppIdentifier)
		WebUI.click(AppObj)

		WebUI.delay(2)

		def errorPanel = (new customWait.WaitForElement()).WaitForelementPresent(findTestObject('JobSubmissionForm/JS_ErrorModalPanel'), 2,extentTest,'ErrorPanel')
		if (errorPanel) {
			WebUI.click(findTestObject('Object Repository/JobSubmissionForm/button_Close'))
		}


		WebUI.click(findTestObject('Object Repository/NewJobPage/GenericProfile'))
		WebUI.delay(2)
		WebUI.click(findTestObject('JobSubmissionForm/List_NCPUS'))
		WebUI.setText(findTestObject('JobSubmissionForm/List_NCPUS'),'2')


		if(App.equals('radioss-smp')) {
			WebUI.setText(findTestObject('JobSubmissionForm/List_NCPUS'),'99')
			//def f1 = RunConfiguration.getProjectDir() + '/Upload/LAME_EQUERRE_0000.rad'
			def f2 = RunConfiguration.getProjectDir() + '/Upload/JobFiles/CUBE_0001.rad'
			def p2 = (new generateFilePath.filePath()).getFilePath(f2)

			WebUI.uploadFile(findTestObject('Object Repository/JobSubmissionForm/LocalFileUploadElement'), p2)
			WebUI.click(findTestObject('JobSubmissionForm/RadioBtn_All Fields'))
			WebUI.scrollToElement(findTestObject('JobSubmissionForm/label_Queue'), 0)
			WebUI.click(findTestObject('WIP/div_workq'))
			TestObject newQueueObj = WebUI.modifyObjectProperty(findTestObject('JobSubmissionForm/dropDown_version'), 'text', 'equals',
					'compute', true)

			WebUI.mouseOver(newQueueObj)
			WebUI.click(newQueueObj)

		}
		else {

			WebUI.setText(findTestObject('JobSubmissionForm/List_NCPUS'),'2')
		}
		WebUI.scrollToElement(findTestObject('JobSubmissionForm/Link_Server'), 3)
		WebUI.delay(3)
		WebUI.uploadFile(findTestObject('Object Repository/JobSubmissionForm/LocalFileUploadElement'),newFP )
		String xpathJobFileIdentifier="//i[contains(@class,'remove-icon unity_close')]"
		TestObject jobFileObj = new TestObject('objectName')
		jobFileObj.addProperty('xpath', ConditionType.EQUALS, xpathJobFileIdentifier)

		def jobFileObjPresent =	(new customWait.WaitForElement()).WaitForelementPresent(jobFileObj, 10,extentTest,'UploadCloseIcon')

		for(int i =0;i<4;i++) {
			def submitBtn=(new customWait.WaitForElement()).WaitForelementPresent (findTestObject('JobSubmissionForm/button_Submit_Job'), 20,extentTest,'SubmitButton')
			if(submitBtn) {
				WebUI.click(findTestObject('JobSubmissionForm/button_Submit_Job'))
			}
		}
		def isNotification=WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobSubmission'), 5)
		if(isNotification) {
			def jobText = WebUI.getText(findTestObject('Notificactions/Notification_JobSubmission'))
			def JobID=(new operations_JobsModule.GetJobRowDetails()).getJobID(jobText)
			return JobID
		}
		else {
			println('Fail')
		}
	}


	@Keyword
	def OpsJobs(String newFP,extentTest) {
		def toget
		def navLocation =(new generateFilePath.filePath()).execLocation()
		def JobID
		def location=navLocation
		println('##################################################################')
		println (location)
		println('##################################################################')


		def OpsCom=RunConfiguration.getProjectDir() + '/Upload/OpsCompleted.zip'
		def Ops=RunConfiguration.getProjectDir() + '/Upload/Ops.zip'

		def filePathOpsCom = (new generateFilePath.filePath()).getFilePath(OpsCom)
		def filePathOps = (new generateFilePath.filePath()).getFilePath(Ops)


		String [] DirName = ['C1', 'R1', 'F1']
		String [] ZipName = [filePathOpsCom, filePathOps, filePathOps]
		String [] NavFolder=['OpsCompleted', 'Ops', 'Ops']
		int i =0

		TestObject newFileObj = WebUI.modifyObjectProperty(findTestObject('JobSubmissionForm/File_InputFile'), 'text', 'equals',
				'Running.sh', true)


		for (String name:DirName) {

			String Dir =DirName[i]
			WebUI.waitForElementVisible(findTestObject('FilesPage/btn_NewFileFolder'), 10)
			WebUI.click(findTestObject('FilesPage/btn_NewFileFolder'))
			WebUI.click(findTestObject('FilesPage/ListItem_Folder'))
			WebUI.waitForElementVisible(findTestObject('FilesPage/TextBxFolder_input'), 5)
			WebUI.setText(findTestObject('FilesPage/TextBxFolder_input'), Dir)
			WebUI.click(findTestObject('FilesPage/btn_Save'))
			WebUI.click(findTestObject('Object Repository/FilesPage/Icon_Refresh'))
			i=i+1
		}
		int j=0
		for(String name2:DirName) {

			String Dir1 =DirName[j]
			String Zip = ZipName[j]
			String Nav=NavFolder[j]
			println('Dir for 2 - '+ Dir1);
			location=location+'/'+Dir1

			WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
			WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), location)
			WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
			println(Zip)
			WebUI.uploadFile(findTestObject('FilesPage/UploadFileBtn'), Zip)
			WebUI.delay(3)
			def zipToFind=Nav+'.zip'

			TestObject OpsFolder = WebUI.modifyObjectProperty(findTestObject('FilesPage/RowItem_File_ListView'), 'title', 'equals',
					zipToFind, true)

			def UnzipBox =(new customWait.WaitForElement()).WaitForelementPresent(findTestObject('Object Repository/FilesPage/button_Yes'), 2,extentTest,'YesButton')

			if (UnzipBox) {
				WebUI.click(findTestObject('Object Repository/FilesPage/button_Yes'))
				def FileUploadClose = (new customWait.WaitForElement()).WaitForelementPresent(findTestObject('Object Repository/JobSubmissionForm/Icon_Close_UploadNotification'), 10,extentTest,'UploadCloseIcon')
				println("upload Notfication - "+FileUploadClose)
				def UploadedFile = (new customWait.WaitForElement()).WaitForelementPresent(OpsFolder, 10,extentTest,'InputFolder')
				println("uploaded file - "+UploadedFile)
				if (UploadedFile) {
					WebUI.click(findTestObject('Object Repository/JobSubmissionForm/Icon_Close_UploadNotification'))
				}
			}
			else {
				def FileUploadClose = (new customWait.WaitForElement()).WaitForelementPresent(findTestObject('Object Repository/JobSubmissionForm/Icon_Close_UploadNotification'), 10,extentTest,'UploadCloseIcon')
				println("upload Notfication - "+FileUploadClose)
				def UploadedFile = (new customWait.WaitForElement()).WaitForelementPresent(OpsFolder, 10,extentTest,'UploadCloseIcon')
				println("uploaded file - "+UploadedFile)
				if (UploadedFile) {
					WebUI.click(findTestObject('Object Repository/JobSubmissionForm/Icon_Close_UploadNotification'))
				}

				WebUI.rightClick(OpsFolder)
				WebUI.delay(3)
				WebUI.click(findTestObject('FilesPage/ContextMenu_FileGrid_UnCompress'))
			}
			WebUI.delay(2)
			location=location+'/'+Nav
			WebUI.click(findTestObject('Object Repository/FilesPage/Icon_EditFilePath'))
			WebUI.setText(findTestObject('Object Repository/FilesPage/textBx_FilePath'), location)
			WebUI.sendKeys(findTestObject('Object Repository/FilesPage/textBx_FilePath'), Keys.chord(Keys.ENTER))
			WebUI.click(findTestObject('Object Repository/FilesPage/Icon_Refresh'))


			TestObject LeftNavAppIdentifier = (new buildTestObj.CreateTestObjJobs()).myLeftNavAppIdentifier('ShellScript')
			WebUI.click(LeftNavAppIdentifier)
			WebUI.click(findTestObject('Object Repository/NewJobPage/GenericProfile'))
			WebUI.click(findTestObject('Object Repository/JobSubmissionForm/Link_ResetLink'))
			WebUI.click(findTestObject('Object Repository/JobMonitoringPage/button_Yes'))

			def errorPanel =(new customWait.WaitForElement()).WaitForelementPresent(findTestObject('JobSubmissionForm/JS_ErrorModalPanel'), 2,extentTest,'ErrorPanel')
			if (errorPanel) {
				WebUI.click(findTestObject('Object Repository/JobSubmissionForm/button_Close'))
			}
			WebUI.click(findTestObject('WIP/RadioBtn_All Fields'))
			WebUI.click(findTestObject('Object Repository/JobSubmissionForm/TxtBx_JobName'))
			WebUI.setText(findTestObject('Object Repository/JobSubmissionForm/TxtBx_JobName'),'Ops')
			WebUI.click(newFileObj)
			WebUI.rightClick(newFileObj)
			String idForCntxtMn = 'Add as Job Script'
			TestObject newRFBContextMnOption = WebUI.modifyObjectProperty(findTestObject('Object Repository/NewJobPage/ContextMenu_RFB_FilePicker'),
					'id', 'equals', idForCntxtMn, true)
			WebUI.click(newRFBContextMnOption)
			println('context menu ')
			String [] JobFiles = ['ToDelete.txt', 'ToOpen.txt', 'ToOpenWith.txt', 'ToRename.txt', 'ToEdit.txt']
			int x=0
			for (String name1:JobFiles) {
				String JF =JobFiles[x]
				WebUI.click(findTestObject('Object Repository/FilesPage/Icon_Refresh'))

				println(JF)
				TestObject newJobFile = WebUI.modifyObjectProperty(findTestObject('JobSubmissionForm/File_InputFile'), 'text', 'equals',JF, true)
				WebUI.click(newJobFile)
				WebUI.rightClick(newJobFile)
				String idForCntxtMnJF = 'Add in Job Files'
				TestObject newRFBContextMnOption1 = WebUI.modifyObjectProperty(findTestObject('Object Repository/NewJobPage/ContextMenu_RFB_FilePicker'),
						'id', 'equals', idForCntxtMnJF, true)
				WebUI.click(newRFBContextMnOption1)
				x=x+1
			}

			def submitBtn=(new customWait.WaitForElement()).WaitForelementPresent (findTestObject('JobSubmissionForm/button_Submit_Job'), 10,extentTest,'SubmitButton')
			if(submitBtn) {
				WebUI.click(findTestObject('JobSubmissionForm/button_Submit_Job'))
			}

			j=j+1
			location=navLocation
			WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobSubmission'), 5)
			def jobText = WebUI.getText(findTestObject('Notificactions/Notification_JobSubmission'))
			JobID=(new operations_JobsModule.GetJobRowDetails()).getJobID(jobText)
		}
		return JobID
	}


	@Keyword
	def JSMulti(String newFP , String AppName,extentTest) {

		println("my keyword")
		String App=AppName.toLowerCase()

		String x1="//span[@id='node-name-"
		String x2="']"
		String xpathAppIdentifier=x1+App+x2
		TestObject AppObj = new TestObject('objectName')
		AppObj.addProperty('xpath', ConditionType.EQUALS, xpathAppIdentifier)
		WebUI.click(AppObj)

		WebUI.delay(2)

		def errorPanel = (new customWait.WaitForElement()).WaitForelementPresent(findTestObject('JobSubmissionForm/JS_ErrorModalPanel'), 2,extentTest,'ErrorPanel')
		if (errorPanel) {
			WebUI.click(findTestObject('Object Repository/JobSubmissionForm/button_Close'))
		}

		WebUI.click(findTestObject('Object Repository/NewJobPage/GenericProfile'))
		WebUI.delay(2)
		WebUI.click(findTestObject('JobSubmissionForm/List_NCPUS'))

		WebUI.scrollToElement(findTestObject('JobSubmissionForm/Link_Server'), 3)
		WebUI.delay(3)
		WebUI.uploadFile(findTestObject('Object Repository/JobSubmissionForm/LocalFileUploadElement'),newFP )
		String xpathJobFileIdentifier="//i[contains(@class,'remove-icon unity_close')]"
		TestObject jobFileObj = new TestObject('objectName')
		jobFileObj.addProperty('xpath', ConditionType.EQUALS, xpathJobFileIdentifier)

		def jobFileObjPresent =	(new customWait.WaitForElement()).WaitForelementPresent(jobFileObj, 10,extentTest,'InputFile')

		def submitBtn=(new customWait.WaitForElement()).WaitForelementPresent (findTestObject('JobSubmissionForm/button_Submit_Job'), 10,extentTest,'SubmitButton')
		if(submitBtn) {
			WebUI.click(findTestObject('JobSubmissionForm/button_Submit_Job'))
		}
		WebUI.waitForElementPresent(findTestObject('Notificactions/Notification_JobSubmission'), 5)
		def jobText = WebUI.getText(findTestObject('Notificactions/Notification_JobSubmission'))

		def JobID=(new operations_JobsModule.GetJobRowDetails()).getJobID(jobText)
		return JobID
	}
}
