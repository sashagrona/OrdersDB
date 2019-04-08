package net.bigmir;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CommonDBOperation {
    public void init(String table, String query, Connection conn){
        try {
            Statement st = conn.createStatement();
            st.execute("DROP TABLE IF EXISTS " + table);
            st.execute(query);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
