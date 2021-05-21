package TestCases;

import CommonClasses.BaseClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilities.*;

public class DatabaseTest extends BaseClass{

    @BeforeTest
    public static void configread(){
        try{
            BaseClass bc = new BaseClass();
            bc.executionProperties();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void ddbTest() throws Exception {
        DatabaseQueries dbQuery = new DatabaseQueries();
        dbQuery.getDatabaseConnection(dbUrl,dbUserName,dbPassword);

    }
}
