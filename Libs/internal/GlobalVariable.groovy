package internal

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.main.TestCaseMain


/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */
public class GlobalVariable {
     
    /**
     * <p></p>
     */
    public static Object G_userName
     
    /**
     * <p></p>
     */
    public static Object G_Password
     
    /**
     * <p></p>
     */
    public static Object G_AdminUser
     
    /**
     * <p></p>
     */
    public static Object G_AdminPasswd
     
    /**
     * <p></p>
     */
    public static Object G_JobID
     
    /**
     * <p></p>
     */
    public static Object G_Browser
     
    /**
     * <p></p>
     */
    public static Object G_BaseUrl
     
    /**
     * <p></p>
     */
    public static Object G_ReportName
     
    /**
     * <p></p>
     */
    public static Object G_JobIdFromDetails
     
    /**
     * <p></p>
     */
    public static Object G_DownloadFolder
     
    /**
     * <p></p>
     */
    public static Object G_ReportFolder
     
    /**
     * <p></p>
     */
    public static Object G_ConfigFile
     
    /**
     * <p></p>
     */
    public static Object G_TestSuite
     
    /**
     * <p></p>
     */
    public static Object G_HostMachine
     
    /**
     * <p></p>
     */
    public static Object G_PBSHostName
     
    /**
     * <p></p>
     */
    public static Object G_bookmark
     
    /**
     * <p></p>
     */
    public static Object G_Platform
     
    /**
     * <p></p>
     */
    public static Object G_StageArea
     
    /**
     * <p></p>
     */
    public static Object G_ExtentTest
     
    /**
     * <p></p>
     */
    public static Object G_BrowserVersion
     

    static {
        try {
            def selectedVariables = TestCaseMain.getGlobalVariables("default")
			selectedVariables += TestCaseMain.getGlobalVariables(RunConfiguration.getExecutionProfile())
            selectedVariables += TestCaseMain.getParsedValues(RunConfiguration.getOverridingParameters())
    
            G_userName = selectedVariables['G_userName']
            G_Password = selectedVariables['G_Password']
            G_AdminUser = selectedVariables['G_AdminUser']
            G_AdminPasswd = selectedVariables['G_AdminPasswd']
            G_JobID = selectedVariables['G_JobID']
            G_Browser = selectedVariables['G_Browser']
            G_BaseUrl = selectedVariables['G_BaseUrl']
            G_ReportName = selectedVariables['G_ReportName']
            G_JobIdFromDetails = selectedVariables['G_JobIdFromDetails']
            G_DownloadFolder = selectedVariables['G_DownloadFolder']
            G_ReportFolder = selectedVariables['G_ReportFolder']
            G_ConfigFile = selectedVariables['G_ConfigFile']
            G_TestSuite = selectedVariables['G_TestSuite']
            G_HostMachine = selectedVariables['G_HostMachine']
            G_PBSHostName = selectedVariables['G_PBSHostName']
            G_bookmark = selectedVariables['G_bookmark']
            G_Platform = selectedVariables['G_Platform']
            G_StageArea = selectedVariables['G_StageArea']
            G_ExtentTest = selectedVariables['G_ExtentTest']
            G_BrowserVersion = selectedVariables['G_BrowserVersion']
            
        } catch (Exception e) {
            TestCaseMain.logGlobalVariableError(e)
        }
    }
}
