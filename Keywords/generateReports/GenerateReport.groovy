package generateReports
import java.text.SimpleDateFormat

import com.aventstack.extentreports.ExtentReports
import com.aventstack.extentreports.reporter.ExtentSparkReporter
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration

import internal.GlobalVariable as GlobalVariable



public class GenerateReport {


	@Keyword
	def createSpark(String ReportName , String BrowserName , String BrowserVersion) {
		def date = new Date()
		def filePath = (RunConfiguration.getProjectDir() + '/ExtentReports/')
		def path = filePath+ReportName
	//	def path = GlobalVariable.G_ReportFolder+'/'+ReportName
		println ("********************* - "+path+" - *********************")
		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter(path);
		extent.setSystemInfo("browserName",BrowserName)
		extent.attachReporter(spark);
		return extent
	}


	@Keyword
	def getDate() {
		def date = new Date()
		def sdf = new SimpleDateFormat("dd_MM_yyyy_HHmmss")
		def start_time = sdf.format(date)
		println start_time
	}
}
