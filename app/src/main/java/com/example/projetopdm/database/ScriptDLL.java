package com.example.projetopdm.database;

public class ScriptDLL {

    public static String getCreateTableUsuario(){

        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS Usuario (");
        sql.append("Email VARCHAR(100) PRIMARY KEY NOT NULL,");
        sql.append("Senha VARCHAR(45) NOT NULL )");

        return sql.toString();
    }

    public static String getCreateTableCliente(){

        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS Cliente (");
        sql.append("ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sql.append("Nome VARCHAR(45) NOT NULL, ");
        sql.append("Sobrenome VARCHAR(45) NOT NULL, ");
        sql.append("CPF VARCHAR(12) NOT NULL, ");
        sql.append("RG VARCHAR(12) NOT NULL, ");
        sql.append("Cidade VARCHAR(45) NOT NULL, ");
        sql.append("Telefone VARCHAR(20) NOT NULL, ");
        sql.append("Nascimento VARCHAR(12) NOT NULL, ");
        sql.append("Usuario_Email VARCHAR(100) NOT NULL, ");
        sql.append("FOREIGN KEY (Usuario_Email) REFERENCES Usuario (Email) ");
        sql.append("ON DELETE NO ACTION ");
        sql.append("ON UPDATE NO ACTION )");


        return sql.toString();
    }

    public static String getCreateTableADM(){

        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS ADM (");
        sql.append("ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sql.append("Nome VARCHAR(45) NOT NULL, ");
        sql.append("Sobrenome VARCHAR(45) NOT NULL, ");
        sql.append("Cidade VARCHAR(45) NOT NULL, ");
        sql.append("Telefone VARCHAR(20) NOT NULL, ");
        sql.append("Usuario_Email VARCHAR(100) NOT NULL, ");
        sql.append("FOREIGN KEY (Usuario_Email) REFERENCES Usuario (Email) ");
        sql.append("ON DELETE NO ACTION ");
        sql.append("ON UPDATE NO ACTION )");
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
        sql.append("Cliente_ID NOT NULL, ");
        sql.append("Procedimento_ID NOT NULL, ");
        sql.append("FOREIGN KEY (Cliente_ID) REFERENCES Cliente (ID) ");
        sql.append("ON DELETE NO ACTION ");
        sql.append("ON UPDATE NO ACTION ");
        sql.append("FOREIGN KEY (Procedimento_ID) REFERENCES Procedimento (ID) ");
        sql.append("ON DELETE NO ACTION ");
        sql.append("ON UPDATE NO ACTION )");

        return sql.toString();
    }

    public static String getCreateTableNotificacao(){

        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS Notificacao (");
        sql.append("ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sql.append("Dia_old VARCHAR(12) NOT NULL, ");
        sql.append("Hora_old VARCHAR(6) NOT NULL, ");
        sql.append("Dia_new VARCHAR(12) NOT NULL, ");
        sql.append("Hora_new VARCHAR(6) NOT NULL, ");
        sql.append("Cliente_ID NOT NULL, ");
        sql.append("FOREIGN KEY (Cliente_ID) REFERENCES Cliente (ID) ");
        sql.append("ON DELETE NO ACTION ");
        sql.append("ON UPDATE NO ACTION )");

        return sql.toString();
    }

}
