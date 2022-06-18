package generateReports

import com.kms.katalon.core.annotation.Keyword













public class testCaseNameChange {

	@Keyword
	def updateTCName(String testcaseName,String variableList,String tcScriptName) {

		//Test Cases/LoginTC/AAALoginCheck
		switch(tcScriptName)
		{
			case 'CreateProfile':
				String toAppend=(new generateReports.testCaseNameChange()).toSplit(variableList,'proName')
				testcaseName= testcaseName + ' - '+toAppend
				break

			case 'DeleteProfile':
				String toAppend=(new generateReports.testCaseNameChange()).toSplit(variableList,'proName')
				testcaseName= 'Test Case to delete profile - '+toAppend
				break

			case 'JobSubmissionThroughProfile':
				String toAppend=(new generateReports.testCaseNameChange()).toSplit(variableList,'proName')
				testcaseName=testcaseName +' based profile - '+toAppend
				break

			case 'JobSubmissionThroughProfileContextMenuAllProfiles':
				String submissionType=(new generateReports.testCaseNameChange()).toSplit(variableList,'submissionType')
				String proName=(new generateReports.testCaseNameChange()).toSplit(variableList,'proName')
				testcaseName=testcaseName+' based profile - '+ proName+' - from option - '+submissionType
				break


			case 'FileOperations':
				String TestOperation=(new generateReports.testCaseNameChange()).toSplit(variableList,'TestOperation')
				if (TestOperation.contains('icon')) {
					testcaseName = (testcaseName + ' thorugh top menu icons')
				}
				break

			case 'FolderOperations':
				String TestOperation=(new generateReports.testCaseNameChange()).toSplit(variableList,'TestOperation')
				if (TestOperation.contains('icon')) {
					testcaseName = (testcaseName + ' thorugh top menu icons')
				}
				break

			case 'FileViewerOperations':
				String userChoice=(new generateReports.testCaseNameChange()).toSplit(variableList,'userChoice')
				if(userChoice.equals('DoubleClick')){
					testcaseName=testcaseName+' - open file by Double Clicking on it'
				}
				else{
					testcaseName=testcaseName+' - open file through context menu open option'
				}
				break

			case 'MultiFileOperations':
				String Operation=(new generateReports.testCaseNameChange()).toSplit(variableList,'Operation')
				testcaseName='MultiFileOperation -'+testcaseName
				if (Operation.contains('icon')) {
					testcaseName = (testcaseName + ' thorugh top menu icons')
				}
				break

			case 'MultiFolderOperations':
				String Operation=(new generateReports.testCaseNameChange()).toSplit(variableList,'Operation')
				testcaseName='MultiFolderOperation -'+testcaseName
				if (Operation.contains('icon')) {
					testcaseName = (testcaseName + ' thorugh top menu icons')
				}
				break

			case 'JobSubmission-FileOperations':
				testcaseName='JobSubmission RFB - '+testcaseName
				break


		}

		return testcaseName
	}



	@Keyword
	def toSplit(String variableList,String param)
	{
		String[] splitAddress = variableList.split(param)
		String part2 = splitAddress[1]
		String[] n=part2.split('=')
		String part3=n[1]
		n=part3.split(',|}|]')
		String toAppend=n[0]
		println("-------------------------")
		println ("value to append to TC name    --- "+toAppend)
		println("-------------------------")

		return toAppend
	}
}



