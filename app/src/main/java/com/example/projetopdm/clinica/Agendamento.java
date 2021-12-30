package com.example.projetopdm.clinica;


public class Agendamento {
    public String horario;
    public String data;
//    public Cliente cliente;
    public Procedimento procedimento;

//    public static ArrayList<Agendamento> agendamentos = new ArrayList<>();

//    public static void listar() {
//            for (int i = 0; i < agendamentos.size(); i++) { //agenda do cliente (usa pra editar)
//                if (i == 0) {
//                    System.out.println("\n------- LISTA DE AGENDAMENTOS -------\n");
//                }
//                if(agendamentos.get(i).getCliente().getNomeUsuario().equals(Agenda.sessao.getNomeUsuario())){
//                    System.out.println("ID: " + (i+1));
//                    System.out.println("Nome do cliente: " + cliente.getHorario().get(i).getCliente().getNome());
//                    System.out.println("Procedimento: " + cliente.getHorario().get(i).getProcedimento());
//                    System.out.println("Data: " + cliente.getHorario().get(i).getData());
//                    System.out.println("Horario: " + cliente.getHorario().get(i).getHorario());
//                    System.out.println("\n              ..........\n");
//                }
//                if (i == agendamentos.size() - 1) {
//                    System.out.println("\n****************************************\n");
//                }
//            }
//        }


//    public static void criar() {
//        Agendamento agendamento = new Agendamento();
//
//        System.out.println("\nNome do cliente: " + Agenda.sessao.getNome());
//        agendamento.setCliente( (Cliente) Agenda.sessao);
//
//        System.out.println("Digite o numero do procedimento que deseja selecionar:");
//        Procedimento.listar();
//        System.out.println("Procedimento:");
//        Integer nProced = Integer.parseInt(leitor.nextLine());
//        agendamento.setProcedimento(Agenda.listaProcedimentos.get(nProced - 1));
//
//        System.out.println("\nDigite o dia que deseja no formato dd/mm:");
//        String escolha = leitor.nextLine();
//        agendamento.setData(escolha);
//
//        System.out.println("\nHorarios Disponiveis:");
//        Horario.printHorarios();
//
//        System.out.println("\nDigite o horario que deseja no formato hh:mm:");
//        escolha = leitor.nextLine();
//        agendamento.setHorario(escolha);
//
//        agendamentos.add(agendamento);
//
//        Cliente cliente = (Cliente) Agenda.sessao;
//        cliente.setHorario(agendamento);

        //colocar isso em AgendarHorarioFragment
//    }

//    public static void editar() {
//        listar();
//        System.out.println("\n Digite o ID do agendamento que deseja editar:");
//        Integer id = Integer.parseInt(leitor.nextLine());
//        System.out.println("\nData atual: " + agendamentos.get(id - 1).getData());
//        System.out.println("Nova data (no formato dd/mm):");
//        String escolha = leitor.nextLine();
//        agendamentos.get(id - 1).setData(escolha);
//        System.out.println("\nHorario atual: " + agendamentos.get(id - 1).getHorario());
//        System.out.println("Novo horario:");
//        escolha = leitor.nextLine();
//        agendamentos.get(id - 1).setHorario(escolha);

        //No fragment AgendamentosFragment tem o botaozinho de editar, tem que dar um update no banco
//    }

//    public static void deletar() {
//        Cliente.verHorario();
//        Cliente cliente = (Cliente) Agenda.sessao;
//
//        System.out.println("Selecione o ID do agendamento que voce quer deletar: ");
//        int id = Integer.parseInt(leitor.nextLine());
//
//        cliente.getHorario().remove(id-1);
//
//        for (int i = 0; i < agendamentos.size(); i++){
//            if(cliente.getHorario().get(id-1).getHorario().equals(agendamentos.get(i).getHorario()) &&
//                    cliente.getHorario().get(id-1).getData().equals(agendamentos.get(i).getData())){
//                agendamentos.remove(i);
//            }
//        }
//        System.out.println("\nAgendamento deletado!\n");

        //No fragment AgendamentosFragment tem o botaozinho de deletar, tem que dar delete no banco
//    }

    /*public String getHorario() {
        return horario;
    }

    public String getData() {
        return data;
    }

//    public Cliente getCliente() {
//        return cliente;
//    }

    public String getProcedimento() {
        return procedimento.nome;
    }

//    public void setCliente(Cliente cliente) {
//        this.cliente = cliente;
//    }

    public void setProcedimento(Procedimento procedimento) {
        this.procedimento = procedimento;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }*/

}
