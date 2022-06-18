package buildTestObj

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject

public class CreateTestObjFiles {


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
	def myTestObjFolderCreateErrorNotification(String FolderName) {

		String xpath_createFolderErrorNotification = "//span[contains(text(),'Please provide a valid name')]"
		println xpath_createFolderErrorNotification
		// Building job indentifier obj
		TestObject createFolderErrorNotificationIdentifier = new TestObject('objectName')
		createFolderErrorNotificationIdentifier.addProperty('xpath', ConditionType.EQUALS, xpath_createFolderErrorNotification)
		return createFolderErrorNotificationIdentifier
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
	def ProLabelIdentifier(String proName,String AppName) {
		String p1 = "//label[@id='profile_radio_"
		String p2 = proName+AppName
		String p3 = "']//span[@class='dotmark']"
		String xpath_ProLabelIdentifier = p1+ p2+p3
		println xpath_ProLabelIdentifier
		// Building job indentifier obj
		TestObject ProLabelIdentifier = new TestObject('objectName')
		ProLabelIdentifier.addProperty('xpath', ConditionType.EQUALS, xpath_ProLabelIdentifier)
		return ProLabelIdentifier
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
}
