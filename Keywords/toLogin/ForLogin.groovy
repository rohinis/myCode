package toLogin

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.assertthat.selenium_shutterbug.utils.web.Browser
import com.aventstack.extentreports.Status
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class ForLogin {

	@Keyword
	def Login(extentTest) {

		def Browser = GlobalVariable.G_Browser
	

		WebUI.setText(findTestObject('LoginPage/username_txtbx'), GlobalVariable.G_userName)
		WebUI.setText(findTestObject('LoginPage/password_txtbx'), GlobalVariable.G_Password)
		WebUI.click(findTestObject('LoginPage/login_btn'))
		extentTest.log(Status.PASS, 'Entered Creds - username - '+GlobalVariable.G_userName +' password - '+GlobalVariable.G_Password)

		extentTest.log(Status.PASS, 'Clicked on Login Button ')


		def jobsTab = (new customWait.WaitForElement()).WaitForelementPresent(findTestObject('NewJobPage/AppList_ShellScript'),	1,extentTest, 'Application - ShellScript loaded ')

		if (jobsTab) {
			WebUI.click(findTestObject('Object Repository/Landing_Page/LandigPage_AltairAccess_Link'))
			extentTest.log(Status.PASS, 'Verified AltairAccess Logo post login ')
		}

	
	}
}
