package util;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String JNDI_NAME = "java:comp/env/jdbc/MyFinanceApiDB";
    
    public static Connection getConnection() throws SQLException {
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(JNDI_NAME);
            
            return ds.getConnection();
        } catch (NamingException e) {
            throw new SQLException("Erro ao obter conexão JDBC via JNDI", e);
        }
    }
    
}