package utilities;
import java.sql.*;

public class DatabaseQueries {

    Connection connection;

    public void getDatabaseConnection(String databaseURL,String databaseUsername,String databasePassword) throws SQLException{
    connection = DriverManager.getConnection(databaseURL,databaseUsername,databasePassword);
    Boolean dbConnectionStatus = connection.isValid(10);
    String x = String.format("Connection to db %s",dbConnectionStatus);
    System.out.println(x);
    }

    public String[] getMIDStatusMRTIUOt(String contractReference)throws SQLException{

        String[] midStatusMrti = new String[4];
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            String sql ="select P_MID,P_MSG_STS,P_DELIVERY_INDEX from minf where P_DUPLICATE_INDEX like '%"+contractReference+"%'";
            statement = connection.createStatement();
            resultSet  =  statement.executeQuery(sql);

            while(resultSet.next()){
                midStatusMrti[0] = resultSet.getString(1);
                midStatusMrti[1] = resultSet.getString(2);
                midStatusMrti[2] = resultSet.getString(3);
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }finally{
            closeResources(resultSet,statement);
        }
        return midStatusMrti ;
    }

    public void closeDatabaseConnection() throws SQLException{
        connection.close();
    }



    public void closeResources(ResultSet resultSet,Statement statement) throws SQLException {
        try {
            if (resultSet != null)
                resultSet.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        try {
            if (statement != null)
                statement.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }


    }
