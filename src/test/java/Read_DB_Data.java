

import org.testng.annotations.Test;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class Read_DB_Data {
    ArrayList<ArrayList<String>> DBdata = new ArrayList<>();


    public ArrayList FetchDBData() throws Exception {
        String connectionString = null;
        String DBDriver =null;
        String sqlQuery =null;
        String userName = null;
        String passWord = null;
        String[][] DB_data=null;
        int numOfRows;
        int numOfColumns;


        connectionString = "jdbc:mysql://sql5.freesqldatabase.com:3306/sql5512536";
        userName = "sql5512536";
        passWord = "iJ76IuMtm7";
        DBDriver = "com.mysql.cj.jdbc.Driver";

        sqlQuery = "select * from AddressData";

        try{
            Class.forName(DBDriver);
            Connection oConn = DriverManager.getConnection(connectionString,userName,passWord);
            Statement oStmt = oConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = oStmt.executeQuery(sqlQuery);

            rs.last();
            numOfRows = rs.getRow();
            System.out.println(numOfRows);
            rs.beforeFirst();

            int i = 0;
            while(rs.next()){
                DBdata.add(new ArrayList<String>());
                DBdata.get(i).add(rs.getString(1));
                DBdata.get(i).add(rs.getString(2));
                DBdata.get(i).add(rs.getString(3));
                DBdata.get(i).add(rs.getString(4));
                DBdata.get(i).add(rs.getString(5));
                DBdata.get(i).add(rs.getString(6));
                //System.out.println("firstName: "+ DBdata.get(i).get(0));
                i=i+1;

            }
            //System.out.println("firstName: "+ DBdata.get(0).size());

            /*DB_data = new String[numOfRows][7];
            int i=0;
            while(rs.next()){
                //DB_data[i][0] = rs.getString(0);
                DB_data[i][1] = rs.getString(1);
                DB_data[i][2] = rs.getString(2);
                DB_data[i][3] = rs.getString(3);
                DB_data[i][4] = rs.getString(4);
                DB_data[i][5] = rs.getString(5);
                DB_data[i][6] = rs.getString(6);


                //System.out.println("firstName: "+ rs.getString(0));
                System.out.println("firstName: "+ rs.getString(1));
                System.out.println("firstName: "+ rs.getString(2));
                System.out.println("firstName: "+ rs.getString(3));
                System.out.println("firstName: "+ rs.getString(4));
                System.out.println("firstName: "+ rs.getString(5));
                System.out.println("firstName: "+ rs.getString(6));


                i=i+1;
            }
*/
//            System.out.println("Total record rows are: ");


        }catch (Exception e){
            System.out.println("error occurred while connecting to Db: "+ e.getMessage());
        }

        return DBdata;
    }

    @Test
    public void passData() throws Exception {
        ArrayList<ArrayList<String>> DBdata = FetchDBData();

        System.out.println(DBdata.get(0).size());

        for(int i=0; i<DBdata.size();i++){
            for(int j=0; j<DBdata.get(0).size();j++){
                System.out.println("firstName: "+DBdata.get(i).get(j));
            }

        }
    }


}


