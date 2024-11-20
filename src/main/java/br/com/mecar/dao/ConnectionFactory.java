package br.com.mecar.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection obterConexao(){
        Connection conexao = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conexao = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl","rm556460","260999");
        }catch (SQLException erro){
            erro.printStackTrace();
        }catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }
        return conexao;
    }
}
