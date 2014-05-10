package connection;

import java.sql.Connection;
import java.sql.SQLException;

import org.h2.jdbcx.JdbcConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Cette classe permet de récupérer une connexion à la base de données embarquée.
 * @author dyonysos
 *
 */
public class ConnectionProvider {
    
    private static final Logger LOG = LoggerFactory.getLogger(ConnectionProvider.class);
    private static final String ressourcePath = ConnectionProvider.class.getClassLoader().getResource("../../ressources/db.h2.db").getPath().replace("db.h2.db","");
    private static final String userDB = "sa";
    private static final String passDB = "sa";
    private static  JdbcConnectionPool jcp = null;
    
    /**
     * Permet de récupérer une connexion dans le pool.
     * @return une Connection
     */
    public static Connection getConnection(){
	if(jcp == null){
	    initPool();
	}
	try {
	    return jcp.getConnection();
	} catch (SQLException e) {
	    LOG.error("Impossible d'obtenir une connexion depuis le pool",e);
	    return null;
	}
    }
    private static void initPool(){
	ConnectionProvider.jcp= JdbcConnectionPool.create("jdbc:h2:"+ressourcePath+"db;AUTO_SERVER=TRUE;INIT=runscript from 'classpath:../../ressources/initSQL.sql'\\;",userDB,passDB);
	LOG.warn("jdbc:h2:"+ressourcePath+"db");
    }
}
