package buildTestObj


import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject


public class CreateJobSubmissionObjs {


	@Keyword
	def myLeftNavAppIdentifier(String AppName) {
		String p1 = "//span[contains(@data-node,'\"id\":\""
		String p2 = "\"')]"
		String xpath_LeftNavAppIdentifier = p1+ AppName + p2
		println xpath_LeftNavAppIdentifier
		// Building job indentifier obj
		TestObject LeftNavAppIdentifier = new TestObject('objectName')
		LeftNavAppIdentifier.addProperty('xpath', ConditionType.EQUALS, xpath_LeftNavAppIdentifier)
		return LeftNavAppIdentifier
	}

	@Keyword
	def myJobLinkRow(String JobID) {

		String p1 = "//a[contains(text(),'"
		String p2 = "')]"
		String xpath_JobIDLink = p1+ JobID + p2
		println xpath_JobIDLink
		// Building job indentifier obj
		TestObject JobIDLinkIdentifier = new TestObject('objectName')
		JobIDLinkIdentifier.addProperty('xpath', ConditionType.EQUALS, xpath_JobIDLink)
		return JobIDLinkIdentifier

	}
	@Keyword
	def myJobDetailsFolder(String Folder) {
		//a[@class='active  focus_enable_class'][contains(text(),'Output')]
		String p1 = "//a[@class='active  focus_enable_class'][contains(text(),'"
		String p2 = "')]"
		String xpath_JobDetailsFolder = p1+ Folder + p2
		println xpath_JobDetailsFolder
		// Building job indentifier obj
		TestObject JobDetailsFolderIdentifier = new TestObject('objectName')
		JobDetailsFolderIdentifier.addProperty('xpath', ConditionType.EQUALS, xpath_JobDetailsFolder)
		return JobDetailsFolderIdentifier

	}


	@Keyword
	def myJobFile(String FileName) {

		String p1 = "//span[contains(@title, 'ForProfiles/InputDeck/"
		String p2 = "')]"
		String xpath_JobFile = p1+ FileName + p2
		println xpath_JobFile
		// Building job indentifier obj
		TestObject JobFileIdentifier = new TestObject('objectName')
		JobFileIdentifier.addProperty('xpath', ConditionType.EQUALS, xpath_JobFile)
		return JobFileIdentifier

	}
}


