package com.estetify.backend.models.users;

import java.util.Date;
import java.util.Scanner;

public class TestUserCompany {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserCompany empresa = new UserCompany();

        // ID
        while (true) {
            try {
                System.out.print("Digite o ID (número maior que zero): ");
                empresa.setId(Long.parseLong(scanner.nextLine()));
                break;
            } catch (Exception e) {
                System.out.println("ID inválido. Tente novamente.");
            }
        }

        // Nome
        while (true) {
            try {
                System.out.print("Digite o nome da empresa: ");
                empresa.setName(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Nome inválido. Tente novamente.");
            }
        }

        // E-mail
        while (true) {
            try {
                System.out.print("Digite o e-mail: ");
                empresa.setEmail(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("E-mail inválido. Tente novamente.");
            }
        }

        // CNPJ
        while (true) {
            try {
                System.out.print("Digite o CNPJ (com ou sem pontuação): ");
                empresa.setCnpj(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("CNPJ inválido. Tente novamente.");
            }
        }

        // Endereço
        System.out.print("Digite o endereço: ");
        empresa.setEndereco(scanner.nextLine());

        // Senha
        while (true) {
            try {
                System.out.print("Digite a senha (mínimo 8 caracteres): ");
                String senha = scanner.nextLine();
                empresa.setPassword(senha.toCharArray());
                break;
            } catch (Exception e) {
                System.out.println("Senha inválida. Tente novamente.");
            }
        }

        // Datas e status
        empresa.setDateRegister(new Date());
        empresa.setLastAccess(new Date());
        empresa.setActive(true);

        // Exibir resultado
        System.out.println("\n--- DADOS CADASTRADOS ---");
        System.out.println(empresa);

        scanner.close();
    }
}