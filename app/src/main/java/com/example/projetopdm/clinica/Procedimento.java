package com.example.projetopdm.clinica;

public class Procedimento {
    public String nome;
    public Integer duracao;
    public Double valor;
//    public static Scanner leitor = new Scanner(System.in);

//    public static void criar() {
//        System.out.println("Informacoes sobre o procedimento:");
//
//        Procedimento procedimento = new Procedimento();

//        System.out.println("Nome:");
//        procedimento.setNome(leitor.nextLine());
//
//        System.out.println("Duracao (em minutos):");
//        procedimento.setDuracao(Integer.parseInt(leitor.nextLine()));
//
//        System.out.println("Valor: -no formato XX.xx-");
//        procedimento.setValor(Double.parseDouble(leitor.nextLine()));
//
//        Agenda.listaProcedimentos.add(procedimento);
//    }

//    public static void editar() {
//        listar();
//
//        System.out.println("\n\n Digite o ID do procedimento que deseja editar:");

        //Tornar os edttext de ListaProcedimentos_Fragment editaveis

//        Integer id = Integer.parseInt(leitor.nextLine());
//
//        Procedimento procedimento = Agenda.listaProcedimentos.get(id-1);
//
//        System.out.println("Novo nome:");
//        procedimento.setNome(leitor.nextLine());
//
//        System.out.println("Nova descricao:");
//        procedimento.setDescricao(leitor.nextLine());
//
//        System.out.println("Nova duracao (em minutos):");
//        procedimento.setDuracao(Integer.parseInt(leitor.nextLine()));
//
//        System.out.println("Novo valor: -no formato XX.xx-");
//        procedimento.setValor(Double.parseDouble(leitor.nextLine()));
//    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public Double getValor() {
        return valor;
    }
}
