package com.example.projetopdm.database;

public class ScriptDLL {

    public static String getCreateTableUsuario(){

        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS Usuario (");
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
        sql.append("Valor DOUBLE NOT NULL ) ");

        return sql.toString();
    }

    public static String getCreateTableAgendamento(){

        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS Agendamento (");
        sql.append("ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sql.append("Dia VARCHAR(12) NOT NULL, ");
        sql.append("Hora VARCHAR(6) NOT NULL, ");
        sql.append("Procedimento VARCHAR(45) NOT NULL, ");
        sql.append("Valor DOUBLE NOT NULL, ");
        sql.append("Usuario_ID NOT NULL, ");
        sql.append("Procedimento_ID NOT NULL, ");
        sql.append("FOREIGN KEY (Usuario_ID) REFERENCES Usuario (ID) ");
        sql.append("ON DELETE NO ACTION ");
        sql.append("ON UPDATE NO ACTION ");
        sql.append("FOREIGN KEY (Procedimento_ID) REFERENCES Procedimento (ID) ");
        sql.append("ON DELETE NO ACTION ");
        sql.append("ON UPDATE NO ACTION )");

        return sql.toString();
    }

    public static String getInsertTableProcedimento(){

        /*
        Procedimento procedimento = new Procedimento();
        //copiar fun????o conexao pra c?? ou colar os inserts no main e depois apagar
        ProcedimentoRepo procedimentoRepo = new ProcedimentoRepo(conexao);

        procedimento.nome = "Massagem";
        procedimento.valor = 50.00;
        procedimentoRepo.inserir(procedimento);
        procedimento.nome = "Pintar o cabelo";
        procedimento.valor = 80.00;
        procedimentoRepo.inserir(procedimento);
        procedimento.nome = "Pintar as unhas";
        procedimento.valor = 40.00;
        procedimentoRepo.inserir(procedimento);
        procedimento.nome = "Esfolia????o";
        procedimento.valor = 35.00;
        procedimentoRepo.inserir(procedimento);
        procedimento.nome = "Limpeza de pele";
        procedimento.valor = 120.00;
        procedimentoRepo.inserir(procedimento);
        procedimento.nome = "Maquiagem";
        procedimento.valor = 65.00;
        procedimentoRepo.inserir(procedimento);
        procedimento.nome = "Cortar o cabelo";
        procedimento.valor = 50.00;
        procedimentoRepo.inserir(procedimento);
        procedimento.nome = "Alisamento";
        procedimento.valor = 250.00;
        procedimentoRepo.inserir(procedimento);
        procedimento.nome = "Depila????o";
        procedimento.valor = 70.00;
        procedimentoRepo.inserir(procedimento);*/

        StringBuilder sql = new StringBuilder();
        //n??o funcionou, disse que tem erro de sintexe
        /*sql.append("INSERT INTO Procedimento (ID, Nome, Valor) VALUES (null, 'Massagem', 50.00) ");
        sql.append("INSERT INTO Procedimento (ID, Nome, Valor) VALUES (null, 'Pintar o cabelo', 80.00) ");
        sql.append("INSERT INTO Procedimento (ID, Nome, Valor) VALUES (null, 'Pintar as unhas', 40.00) ");
        sql.append("INSERT INTO Procedimento (ID, Nome, Valor) VALUES (null, 'Esfolia????o', 35.00) ");
        sql.append("INSERT INTO Procedimento (ID, Nome, Valor) VALUES (null, 'Limpeza de pele', 120.00) ");
        sql.append("INSERT INTO Procedimento (ID, Nome, Valor) VALUES (null, 'Maquiagem', 65.00) ");
        sql.append("INSERT INTO Procedimento (ID, Nome, Valor) VALUES (null, 'Cortar o cabelo', 50.00) ");
        sql.append("INSERT INTO Procedimento (ID, Nome, Valor) VALUES (null, 'Alisamento', 250.00) ");
        sql.append("INSERT INTO Procedimento (ID, Nome, Valor) VALUES (null, 'Depila????o', 70.00) ");*/

        return sql.toString();
    }
}
