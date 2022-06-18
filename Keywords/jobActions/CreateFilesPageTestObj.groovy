package jobActions

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class CreateFilesPageTestObj {
	@Keyword
	def FolderRowIdentifireForUnCompressedFile() {
		String x1 = '//div[contains(@data-path,\'/stage/'
		String x2 =GlobalVariable.G_userName
		String x3
		String x4
		if(Operation.contains('icon')) {
			x3='/FilesModule/FileOpsIcons'
		}
		else {
			x3='/FilesModule/FileOps'
			println ("else block form VerifyCompressedFile operation - "+  Operation)
		}

		if (TestCaseName.contains('tile view')) {
			x4 = '/ToCompress_TV_archive_\')]'
		}
		else {
			x4 = '/ToCompress_LV_archive_\')]'
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
		if(Operation.contains('icon')) {
			x3='/FilesModule/FileOpsIcons'
		}
		else {
			x3='/FilesModule/FileOps'
			println ("else block form VerifyCompressedFile operation - "+  Operation)
		}

		if (TestCaseName.contains('tile view')) {
			x4 = '/ToCompress_TV_archive_\')]'
		}
		else {
			x4 = '/ToCompress_LV_archive_\')]'
		}


		String xpath_compressedfileXpath = x1+x2+x3+x4
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
		/*
		 if (CheckStringforFileSize.contains("184"))
		 {
		 println('true')
		 return CheckStringforFileName
		 }
		 else
		 {
		 println('false')
		 return false
		 }
		 */
		return CheckStringforFileName





	}


	@Keyword
	def VerifyUnCompressedFile(String UnCompressedFileName , String Operation) {

		String x1='//div[contains(@data-path,\''
		String x2= '\')]'
		String xpath_compressedfileXpath = x1+UnCompressedFileName+x2

		//	String xpath_compressedfileXpathTileView = "//label[contains(text(),'ToCompress.txt')]"
		println xpath_compressedfileXpath
		// Building job indentifier obj
		TestObject compressedFileObjIdentifierLatest = new TestObject('objectName')
		compressedFileObjIdentifierLatest.addProperty('xpath', ConditionType.EQUALS, xpath_compressedfileXpath)
		String data=WebUI.getAttribute(compressedFileObjIdentifierLatest, "data-node")
		println"data from data node - "+ data
		String dataAttributeString='"filesize:"'
		String[] splitAddress = data.split('"filesize":"')
		String  jobState = splitAddress[1]
		println "job state - "+ jobState
		String[] s1 = jobState.split('","')
		String CheckString=s1[0]

		println "=================================================================="
		println ("File size from Verify  UnCompressed file - "+CheckString)
		println "=================================================================="

		return true
	}
}
