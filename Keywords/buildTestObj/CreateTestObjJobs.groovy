package buildTestObj


import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject


public class CreateTestObjJobs {

	@Keyword
	def leftNavAppIdentifier(String AppName) {

		//String App=AppName.toLowerCase()
		String p1="//span[@id='node-name-"+ AppName +"']"
		//String p2="']"
		String xpath_LeftNavAppIdentifier = p1
		println xpath_LeftNavAppIdentifier
		// Building job indentifier obj
		TestObject LeftNavAppIdentifier = new TestObject('objectName')
		LeftNavAppIdentifier.addProperty('xpath', ConditionType.EQUALS, xpath_LeftNavAppIdentifier)
		return LeftNavAppIdentifier

	}

	@Keyword
	def myLeftNavAppIdentifier(String AppName) {
		//String App=AppName.toLowerCase()
		String p1="//span[@title='"
		String p2="']"
		String xpath_LeftNavAppIdentifier = p1+ AppName + p2
		println xpath_LeftNavAppIdentifier
		// Building job indentifier obj
		TestObject LeftNavAppIdentifier = new TestObject('objectName')
		LeftNavAppIdentifier.addProperty('xpath', ConditionType.EQUALS, xpath_LeftNavAppIdentifier)
		return LeftNavAppIdentifier
	}

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
	def myTestObjJobIndentifier(String jobID) {
		String x1 = '(//a[contains(text(),\''
		String x2 = '\')])'
		String xpath_jobIdentifireObj = (x1 + jobID) + x2
		println xpath_jobIdentifireObj
		// Building job indentifier obj
		TestObject jobRowObjIdentifier = new TestObject('objectName')
		jobRowObjIdentifier.addProperty('xpath', ConditionType.EQUALS, xpath_jobIdentifireObj)
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
