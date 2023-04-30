package org.balsani;

import org.balsani.entities.Usuario;
import org.balsani.exceptions.BusinessException;
import org.balsani.services.UsuarioServices;



import java.util.Scanner;

public class Main {
    private static UsuarioServices service = new UsuarioServices();
    private static Scanner sc = new Scanner(System.in).useDelimiter("\n");

    public static void main(String[] args) {
        var opcao = menu();
        while (opcao != 6) {
            try {
                switch (opcao) {
                    case 1:
                        listarUsuarios();
                        break;
                    case 2:
                        cadastrar();
                        break;
                    case 3:
                        buscarUsuario();
                        break;
                    case 4:
                        deletarUsuario();
                }
            } catch (BusinessException e) {
                System.out.println("Erro: " + e.getMessage());
                System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu");
                sc.next();

            }
            opcao = menu();

        }


    }

    private static int menu() {
        System.out.println("""
                CADASTRO DE USUÁRIOS - ESCOLHA A OPÇÃO:
                1 - Listar usuários
                2 - Cadastro de usuário
                3 - Buscar Usuário
                4 - Atualizar Usuário
                5 - Deletar Usuário
                6 - Sair
                """);
        return sc.nextInt();
    }

    private static void cadastrar() {
        System.out.println("Digite o nome do usuário: ");
        var nome = sc.next();
        System.out.println("Digite o email do usuário: ");
        var email = sc.next();
        service.cadastrarUsuario(new Usuario(nome, email));

    }

    private static void listarUsuarios() {
        System.out.println("Usuários cadastrados: ");
        var usuarios = service.listarUsuarios();
        usuarios.stream().forEach(System.out::println);

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        sc.next();

    }

    private static void buscarUsuario() {
        System.out.println("Digite o id do usuário: ");
        var id = sc.nextLong();
        Usuario usuario = service.buscarUsuario(id);
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("Email: " + usuario.getEmail());
        sc.next();
    }



    public static void deletarUsuario() {
        System.out.println("Digite o id do usuário: ");
        var id = sc.nextLong();
        service.buscarUsuario(id);
        service.apagarUsuario(id);
        System.out.println("Usuário deletado");
    }
}