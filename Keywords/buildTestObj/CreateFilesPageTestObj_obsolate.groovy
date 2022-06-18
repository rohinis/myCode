package buildTestObj

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject

import internal.GlobalVariable

public class CreateFilesPageTestObj_obsolate {

	@Keyword
	def FileRowIdentifireForCompressedFile() {
		String x1 = '//div[contains(@data-path,\'/stage/'
		String x2 =GlobalVariable.G_userName
		String x3 = '/ToCompress_archive_\')]'
		String xpath_compressedfileXpath = x1+x2+x3
		println xpath_compressedfileXpath
		// Building job indentifier obj
		TestObject compressedFileObjIdentifierLatest = new TestObject('objectName')
		compressedFileObjIdentifierLatest.addProperty('xpath', ConditionType.EQUALS, xpath_compressedfileXpath)
		return compressedFileObjIdentifierLatest
	}
}
