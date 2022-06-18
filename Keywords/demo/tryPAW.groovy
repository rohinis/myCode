package demo

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebElement

import com.kms.katalon.core.annotation.Keyword
//import com.relevantcodes.extentreports.Status


public class tryPAW {

	@Keyword
	def downloadTest() {

		File downloadFolder = new File("C:\\KatalonDownloads")
		List namesOfFiles = Arrays.asList(downloadFolder.list())
		if (namesOfFiles.contains('ToDownload_LV.txt')) {
			println('success')
			//extentTest.log(Status.PASS, 'file to downloaded ')

		} else {
			println('fail')
		}
	}
}
