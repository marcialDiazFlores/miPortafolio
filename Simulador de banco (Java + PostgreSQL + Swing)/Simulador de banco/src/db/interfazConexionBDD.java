package db;

import java.sql.Connection;

public interface interfazConexionBDD {
    Connection conectar();
    void desconectar();
}