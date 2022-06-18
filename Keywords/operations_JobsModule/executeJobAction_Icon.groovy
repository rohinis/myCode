package operations_JobsModule

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.aventstack.extentreports.Status

import internal.GlobalVariable

public class executeJobAction_Icon {

	@Keyword
	def perfromJobAction(String Action , String TestCaseName , extentTest) {
		def isNotoficationPresent
		boolean result=false
		//def Status = com.relevantcodes.extentreports.Status
		WebUI.delay(3)

		switch (Action) {

			case 'delete_icon':
				TestObject newFileOp=WebUI.modifyObjectProperty(findTestObject('Object Repository/JobMonitoringPage/Icon_JObAction'), 'id', 'equals', Action, true)
				WebUI.click(newFileOp)
				WebUI.delay(3)
				WebUI.click(findTestObject('GenericObjects/btn_Yes'))
			//WebUI.click(findTestObject('FilesPage/Icon_Close'))
				WebUI.delay(2)
				WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
				WebUI.delay(2)
			//Verify notification
				result = WebUI.verifyElementPresent(findTestObject('Object Repository/Notificactions/Notification_JobDelete'),5)
				println("notification status - "+result)
				return result
				break


			case 'terminate_icon':
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

			case 'move_to_queue':
				WebUI.click(findTestObject('Object Repository/JobMonitoringPage/Icon_ellipses'))
				WebUI.delay(3)
				WebUI.mouseOver(findTestObject('Object Repository/JobMonitoringPage/TopIcon_MoveToQueue'))
				WebUI.click(findTestObject('Object Repository/JobMonitoringPage/TopIcon_MoveToQueue'))
				WebUI.delay(3)
				WebUI.click(findTestObject('Object Repository/JobMonitoringPage/QueueSubMenu_AccessQueue'))

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
			 WebUI.click(findTestObject('GenericObjects/btn_Yes'))
			 //WebUI.click(findTestObject('FilesPage/Icon_Close'))
			 WebUI.delay(2)
			 WebUI.click(findTestObject('Landing_Page/Btn_Notifiction'))
			 WebUI.delay(2)
			 //Verify notification
			 result = WebUI.verifyElementPresent(findTestObject('Object Repository/Notificactions/Notification_JobDelete'),5)
			 println("notification status - "+result)*/
				return result
				break

		}

	}
}
