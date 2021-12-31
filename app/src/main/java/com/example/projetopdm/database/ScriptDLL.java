package com.example.projetopdm.database;

public class ScriptDLL {

    public static String getCreateTableUsuarios(){

        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS Usuarios (");
        sql.append("ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sql.append("Email VARCHAR(100) NOT NULL,");
        sql.append("Senha VARCHAR(45) NOT NULL, ");
        sql.append("Nome VARCHAR(45) NOT NULL, ");
        sql.append("Sobrenome VARCHAR(45) NOT NULL, ");
        sql.append("CPF VARCHAR(12) NOT NULL, ");
        sql.append("RG VARCHAR(12) NOT NULL, ");
        sql.append("Cidade VARCHAR(45) NOT NULL, ");
        sql.append("Telefone VARCHAR(20) NOT NULL, ");
        sql.append("Nascimento VARCHAR(12) NOT NULL )");

        return sql.toString();
    }

    public static String getCreateTableProcedimento(){

        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS Procedimento (");
        sql.append("ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sql.append("Nome VARCHAR(45) NOT NULL, ");
        sql.append("Valor DOUBLE NOT NULL )");

        return sql.toString();
    }

    public static String getCreateTableAgendamento(){

        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS Agendamento (");
        sql.append("ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sql.append("Dia VARCHAR(12) NOT NULL, ");
        sql.append("Hora VARCHAR(6) NOT NULL, ");
        sql.append("Usuarios_ID NOT NULL, ");
        sql.append("Procedimento_ID NOT NULL, ");
        sql.append("FOREIGN KEY (Usuarios_ID) REFERENCES Usuarios (ID) ");
        sql.append("ON DELETE NO ACTION ");
        sql.append("ON UPDATE NO ACTION ");
        sql.append("FOREIGN KEY (Procedimento_ID) REFERENCES Procedimento (ID) ");
        sql.append("ON DELETE NO ACTION ");
        sql.append("ON UPDATE NO ACTION )");

        return sql.toString();
    }
}
