package buildTestObj

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class CreateTestObj {

	@Keyword
	def myTestObjJobRow(String jobID) {

		String p1 = '//div[@id=\'list_'
		String p2 = '_row\']'
		String xpath_jobRowObj = (p1 + jobID) + p2
		println xpath_jobRowObj
		// Building job row obj
		TestObject jobRowObj = new TestObject('objectName')
		jobRowObj.addProperty('xpath', ConditionType.EQUALS, xpath_jobRowObj)
		return jobRowObj
	}


	@Keyword
	def myJobTitleIndentifier() {
		String x1 = '//span[contains(text(),\'.'
		String x2 = '\')]'

		def pbsHN=GlobalVariable.G_PBSHostName
		String xpath_jobIdentifireObj = (x1 + pbsHN) + x2

		println('********************************************')
		println xpath_jobIdentifireObj
		println('********************************************')

		// Building job indentifier obj
		TestObject jobRowObjIdentifier = new TestObject('objectName')
		jobRowObjIdentifier.addProperty('xpath', ConditionType.EQUALS, xpath_jobIdentifireObj)
		return jobRowObjIdentifier
	}


	@Keyword
	def myJobFromLastSubmitted() {
		String x1 = '//span[contains(text(),\''
		String x2 = '\')]'

		def pbsHN=GlobalVariable.G_JobID
		String xpath_jobIdentifireObj = (x1 + pbsHN) + x2

		println('********************************************')
		println xpath_jobIdentifireObj
		println('********************************************')

		// Building job indentifier obj
		TestObject jobRowObjIdentifier = new TestObject('objectName')
		jobRowObjIdentifier.addProperty('xpath', ConditionType.EQUALS, xpath_jobIdentifireObj)
		//String test=WebUI.getAttribute(jobRowObjIdentifier, 'text')
		return jobRowObjIdentifier
	}

	@Keyword
	def myTestObjJobIndentifierLatest(String jobID) {
		String x1 = '(//a[contains(text(),\''
		String x2 = '\')])'
		String xpath_jobIdentifireObjLatest = (x1 + jobID) + x2
		println xpath_jobIdentifireObjLatest
		// Building job indentifier obj
		TestObject jobRowObjIdentifierLatest = new TestObject('objectName')
		jobRowObjIdentifierLatest.addProperty('xpath', ConditionType.EQUALS, xpath_jobIdentifireObjLatest)
		return jobRowObjIdentifierLatest
	}


	@Keyword
	def myTestObjFileCreateNotification(String FileName) {
		String p1 = "//span[(text() = 'The File "
		String p2 = " has been created successfully' or . = 'The File "
		String p3 = " has been created successfully')]"
		String xpath_createFileNotification = p1+ FileName + p2 + FileName + p3
		println xpath_createFileNotification
		// Building job indentifier obj
		TestObject createFileNotificationIdentifier = new TestObject('objectName')
		createFileNotificationIdentifier.addProperty('xpath', ConditionType.EQUALS, xpath_createFileNotification)
		return createFileNotificationIdentifier
	}

	@Keyword
	def myTestObjFolderCreateNotification(String FolderName) {
		String p1 = "//span[(text() = 'The Directory "
		String p2 = " has been created successfully' or . = 'The Directory "
		String p3 = " has been created successfully')]"
		String xpath_createFolderNotification = p1+ FolderName + p2 + FolderName + p3
		println xpath_createFolderNotification
		// Building job indentifier obj
		TestObject createFolderNotificationIdentifier = new TestObject('objectName')
		createFolderNotificationIdentifier.addProperty('xpath', ConditionType.EQUALS, xpath_createFolderNotification)
		return createFolderNotificationIdentifier
	}



	@Keyword
	def myTestObjFileRowIdentifier(String FileName) {
		String p1 = "//div[(contains(text(), '"
		String p2 = "') or contains(., '"
		String p3 = "'))]"
		String xpath_FileRowIdentifier = p1+ FileName + p2 + FileName + p3
		println xpath_FileRowIdentifier
		// Building job indentifier obj
		TestObject FileRowIdentifier = new TestObject('objectName')
		FileRowIdentifier.addProperty('xpath', ConditionType.EQUALS, xpath_FileRowIdentifier)
		return FileRowIdentifier
	}



	@Keyword
	def myTestObjUploadElement(String xpath) {
		println(xpath)
		TestObject UploadFileElement = new TestObject('objectName')
		UploadFileElement.addProperty('xapth', ConditionType.EQUALS, xpath)
		return UploadFileElement
	}




	@Keyword
	def myTestObjTerminateJobNotification() {
		String xpath_terminateJob = "//span[contains(text(),'The job has been terminated successfully!')]"
		println xpath_terminateJob
		// Building job indentifier obj
		TestObject createFileNotificationIdentifier = new TestObject('objectName')
		createFileNotificationIdentifier.addProperty('xpath', ConditionType.EQUALS, xpath_terminateJob)
		return createFileNotificationIdentifier
	}


	@Keyword
	def myTestObjFilterCategoryIdentifierDown(String FilterCategory) {
		String p1 = "//div[@id='"
		String p2 = "']//span[contains(@class,'unity_down_chevron fi-size-medium')]"
		String xpath_FilterCategoryDown = p1+ FilterCategory + p2
		println xpath_FilterCategoryDown
		// Building job indentifier obj
		TestObject FilterCategoryIdentifierDown = new TestObject('objectName')
		FilterCategoryIdentifierDown.addProperty('xpath', ConditionType.EQUALS, xpath_FilterCategoryDown)
		return FilterCategoryIdentifierDown
	}

	@Keyword
	def myTestObjFilterCategoryIdentifierRight(String FilterCategory) {
		String p1 = "//div[@id='"
		String p2 = "']//span[contains(@class,'unity_right_chevron fi-size-medium')]"
		String xpath_FilterCategoryRight = p1+ FilterCategory + p2
		println xpath_FilterCategoryRight
		// Building job indentifier obj
		TestObject FilterCategoryIdentifierRight = new TestObject('objectName')
		FilterCategoryIdentifierRight.addProperty('xpath', ConditionType.EQUALS, xpath_FilterCategoryRight)
		return FilterCategoryIdentifierRight
	}
}
