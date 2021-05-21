package CommonClasses;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {
    private Properties prop = new Properties();
    public static String env = null;
    public static String projLocation = null;
    public static String testDataRelativePath  = null;
    public static String testResultsRelativePath  = null;
    public String employeeTestDataFileName = null;
    public String dbUrl = null;
    public String dbUserName = null;
    public String dbPassword = null;

    public void executionProperties() throws IOException{
        projLocation = System.getProperty("user.dir");
        String propertiesFileLocn = projLocation+"\\src\\test\\resources\\properties\\execution.properties";
        FileInputStream fis = new FileInputStream(propertiesFileLocn);
        prop.load(fis);

        env = prop.getProperty("TestEnvironment");

        if(env.equalsIgnoreCase("")){
            env = "QA";
        }
        if(env.equalsIgnoreCase("QA")){
            testDataRelativePath  = projLocation+"\\src\\test\\resources\\testData\\QA\\";
            testResultsRelativePath = projLocation+"\\src\\test\\resources\\testResults\\QA\\";
            dbUrl = prop.getProperty("qa_activeDbURL");
            dbUserName = prop.getProperty("qa_activeDBUsername");
            dbPassword = prop.getProperty("qa_activeDBPassword");
        }

        if(env.equalsIgnoreCase("DEV")){
            testDataRelativePath  = projLocation+"\\src\\test\\resources\\testData\\DEV\\";
            testResultsRelativePath = projLocation+"\\src\\test\\resources\\testResults\\DEV\\";
            dbUrl = prop.getProperty("dev_activeDbURL");
            dbUserName = prop.getProperty("dev_activeDBUsername");
            dbPassword = prop.getProperty("dev_activeDBPassword");
        }

        employeeTestDataFileName = prop.getProperty("employeeTestDataFileName");
    }
}
