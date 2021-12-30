package com.example.projetopdm.usuarios;

import com.example.projetopdm.clinica.Agenda;
import com.example.projetopdm.clinica.Agendamento;

import java.util.ArrayList;

public class Usuario {
    private String senha;
    public String nome;
    public String sobrenome;
    public String email;
    public String telefone;
    String cidade;
    String dataNascimento;
    String rg;
    String cpf;
    public ArrayList <Agendamento> agendamentos = new ArrayList<>();

    public static void cadastrar(Usuario usu) {
        Usuario usuario = new Usuario();
        usuario.setNome(usu.nome);
        usuario.setSobrenome(usu.sobrenome);
        usuario.setdataNascimento(usu.dataNascimento);
        usuario.setRg(usu.rg);
        usuario.setCpf(usu.cpf);
        usuario.setTelefone(usu.telefone);
        usuario.setCidade(usu.cidade);
        usuario.setEmail(usu.email);
        usuario.setSenha(usu.getSenha());

        Agenda.usuarios.add(usuario);
    }

    ////SETTER
//
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setdataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setHorario(Agendamento horario) {
        this.agendamentos.add(horario);
    }

    //GETTER

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCidade() {
        return cidade;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getRg() {
        return rg;
    }

    public String getCpf() {
        return cpf;
    }

    public ArrayList<Agendamento> getHorario() {
        return agendamentos;
    }


    //METODOS CLIENTE CASO PRECISE

    //    public static void listar() {
//        for (int i = 0; i < Agenda.listaClientes.size(); i++) {
//            if (i == 0) {
//                System.out.println("\n----------- LISTA de CLIENTES -----------\n");
//            }
//            System.out.println("Cliente nº: " + (i + 1));
//            System.out.println("Nome completo: " + Agenda.listaClientes.get(i).getSobrenome());
//            System.out.println("Email: " + Agenda.listaClientes.get(i).getEmail());
//            System.out.println("Nome de Usuário: " + Agenda.listaClientes.get(i).getNomeUsuario());
//            System.out.println("Senha: " + Agenda.listaClientes.get(i).getSenha());
//            System.out.println("Data de Nascimento: " + Agenda.listaClientes.get(i).getDataNascimento());
//            System.out.println("Cidade: " + Agenda.listaClientes.get(i).getCidade());
//            System.out.println("Telefone: " + Agenda.listaClientes.get(i).getTelefone());
//            System.out.println("CPF: " + Agenda.listaClientes.get(i).getCpf());
//            System.out.println("RG: " + Agenda.listaClientes.get(i).getRg());
//            System.out.println("Sexo Biologico: " + Agenda.listaClientes.get(i).getSexo());
//            System.out.println("Remédios que usa com frequência: " + Agenda.listaClientes.get(i).getRemedios());
//
//            if (i != Agenda.listaClientes.size() - 1) {
//                System.out.println("\n             ..........\n");
//            }
//            if (i == Agenda.listaClientes.size() - 1) {
//                System.out.println("\n****************************************\n");
//            }
//        }
//    }
//
//    public static void deletar() {
//
//        if (Agenda.sessao instanceof Gerente) {
//            listar();
//            System.out.println("\n Digite o ID do cliente que deseja deletar:");
//            Integer id = Integer.parseInt(leitor.nextLine());
//            for (int i = 0; i < Agenda.usuarios.size(); i++){ //remove da lista de usuarios
//                if(Agenda.usuarios.get(i).getNomeUsuario().equals(Agenda.listaClientes.get(id-1).getNomeUsuario())){
//                    Agenda.usuarios.remove(i);
//                }
//            }
//            System.out.println("O cliente " + Agenda.listaClientes.get(id-1).getNomeUsuario() + " foi deletado com sucesso");
//            Agenda.listaClientes.remove(id - 1);
//
//        } else if (Agenda.sessao instanceof Cliente) {
//            for (int j = 0; j < Agenda.usuarios.size(); j++){ //remove da lista de usuarios
//                if(Agenda.usuarios.get(j).getNomeUsuario().equals(Agenda.sessao.getNomeUsuario())){
//                    Agenda.usuarios.remove(j);
//                }
//            }
//            for (int i = 0; i < Agenda.listaClientes.size(); i++) { //remove da lista de clientes
//                if (Agenda.sessao.getNomeUsuario().equals(Agenda.listaClientes.get(i).getNomeUsuario())) {
//                    System.out.println("O cliente " + Agenda.listaClientes.get(i).getNomeUsuario() + " foi deletado com sucesso");
//                    Agenda.listaClientes.remove(i);
//                    Agenda.sessao = null;
//                    Menu.inicial();
//                }
//            }
//        } else {
//            System.out.println("Você não tem permissão para deletar esse usuário");
//        }
//
//    }
//
//    public static void editar() {
//        Cliente cliente = (Cliente) Agenda.sessao;
//
//        System.out.println("\nNome de usuario atual: " + cliente.getNomeUsuario());
//        boolean valido = false;
//        while(!valido){
//            int cont = 0;
//            System.out.println("Novo nome de usuario:");
//            String addnome = leitor.nextLine();
//            for (Usuario usuarioProcurado : Agenda.usuarios) {
//                if (usuarioProcurado.getNomeUsuario().equals(addnome)) {
//                    System.out.println("Esse usuário já existe, crie outro nome\n");
//                } else{
//                    cont++;
//                }
//            }
//            if(cont == Agenda.usuarios.size()){
//                cliente.setNomeUsuario(addnome);
//                valido = true;
//            }
//        }
//
//        System.out.println("\nEmail atual: " + cliente.getEmail());
//        System.out.println("Novo email:");
//        cliente.setEmail(leitor.nextLine());
//
//        System.out.println("\nTelefone atual: " + cliente.getTelefone());
//        System.out.println("Novo telefone:");
//        cliente.setTelefone(leitor.nextLine());
//
//        System.out.println("\nCidade atual: " + cliente.getCidade());
//        System.out.println("Nova cidade:");
//        cliente.setCidade(leitor.nextLine());
//    }
//
//    public static void verHorario() {
//        Usuario user = Agenda.sessao;
//        if (user instanceof Cliente) {
//            Cliente cliente = (Cliente) Agenda.sessao;
//            for (int i = 0; i < cliente.horarios.size(); i++) {
//                if (i == 0) {
//                    System.out.println("\n------- LISTA DE AGENDAMENTOS -------\n"); //usa pra listar e deletar
//                }
//                System.out.println("ID: " + (i + 1));
//                System.out.println("Procedimento: " + cliente.horarios.get(i).procedimento.nome);
//                System.out.println("Data: " + cliente.horarios.get(i).data);
//                System.out.println("Horario: " + cliente.horarios.get(i).horario);
//
//                if (i != cliente.horarios.size() - 1) {
//                    System.out.println("\n             ..........\n");
//                }
//                if (i == cliente.horarios.size() - 1) {
//                    System.out.println("\n****************************************\n");
//                }
//            }
//        }
//    }
//
}