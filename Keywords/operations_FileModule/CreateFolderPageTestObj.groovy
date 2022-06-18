package operations_FileModule

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import org.openqa.selenium.Keys as Keys
import internal.GlobalVariable

public class CreateFolderPageTestObj {
	@Keyword
	def FolderRowIdentifireForUnCompressedFile() {


		String x1 = '//div[contains(@data-path,\'/stage/'
		String x2 =GlobalVariable.G_userName
		String x3
		String x4
		if(Operation.contains('icon')) {
			x3='/FilesModule/FolderOpsIcons'
		}
		else {
			x3='/FilesModule/FolderOpsContextMenu'
			println ("else block form VerifyCompressedFile operation - "+  Operation)
		}

		if (TestCaseName.contains('tile view')) {
			x4 = '/MyFolderCompress_TV_archive_\')]'
		}
		else {
			x4 = '/MyFolderCompress_LV_archive_\')]'
		}
		String xpath_compressedfileXpath = x1+x2+x3+x4
		println xpath_compressedfileXpath
		// Building job indentifier obj
		TestObject compressedFileObjIdentifierLatest = new TestObject('objectName')
		compressedFileObjIdentifierLatest.addProperty('xpath', ConditionType.EQUALS, xpath_compressedfileXpath)
		return compressedFileObjIdentifierLatest
	}



	@Keyword
	def VerifyCompressedFile(String TestCaseName , String Operation) {

		String x1 = '//div[contains(@data-path,\'/stage/'
		String x2 =GlobalVariable.G_userName
		String x3
		String x4
		String xpath_compressedfileXpath
		if (TestCaseName.contains('tile view')) {
			xpath_compressedfileXpath = "//label[@id='file_text'][contains(@title,'MyFolderCompress_LV_archive_')]"
		}
		else {
			xpath_compressedfileXpath = "//div[contains(text(),'MyFolderCompress_LV_archive_')]"
		}
		println xpath_compressedfileXpath
		// Building job indentifier obj
		TestObject compressedFileObjIdentifierLatest = new TestObject('objectName')
		compressedFileObjIdentifierLatest.addProperty('xpath', ConditionType.EQUALS, xpath_compressedfileXpath)
		String data=WebUI.getAttribute(compressedFileObjIdentifierLatest, "data-node")
		println"data from data node - "+ data
		String[] splitforFileSize = data.split('"filesize":"')
		String  datasetforFileSize = splitforFileSize[1]
		String[] sfs = datasetforFileSize.split('","')
		String CheckStringforFileSize=sfs[0]
		String[] splitforFileName = data.split('"name":"')
		String  datasetforFileName = splitforFileName[1]
		String[] sfn = datasetforFileName.split('","')
		String CheckStringforFileName=sfn[0]
		println ("File size from Verify Compressed file - "+CheckStringforFileSize)
		println ("File size from Verify Compressed name - "+CheckStringforFileName)
		if (CheckStringforFileSize.contains("190"))
		{
			println('true')
			return CheckStringforFileName
		}
		else
		{
			println('false')
			return false
		}




	}


	@Keyword
	def VerifyUnCompressedFile(String UnCompressedFolderName , String Operation) {
		println UnCompressedFolderName

		String x1='//div[contains(@data-path,\''
		String x2= '\')]'
		String xpath_compressedfileXpath = x1+UnCompressedFolderName+x2

		println xpath_compressedfileXpath
		// Building job indentifier obj
		TestObject compressedFileObjIdentifierLatest = new TestObject('objectName')
		compressedFileObjIdentifierLatest.addProperty('xpath', ConditionType.EQUALS, xpath_compressedfileXpath)
		String data=WebUI.getAttribute(compressedFileObjIdentifierLatest, "data-node")
		println"data from data node - "+ data

		return true
	}




}
