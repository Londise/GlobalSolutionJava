package br.com.fiap.main;

import br.com.fiap.bean.Autoridade;
import br.com.fiap.bean.Cidadao;
import br.com.fiap.bean.Foco;
import br.com.fiap.bean.Usuario;

import javax.swing.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Usuario usuario = null;

        // Instanciando cidadão, autoridade e foco com dados predeterminados
        Cidadao cidadao = new Cidadao("admin", "11111111111", "admin", 2500);
        Autoridade autoridade = new Autoridade("admin", "11111111111", "admin", 2700);
        Foco foco = new Foco(3000, cidadao.getIdCidadao(), LocalDate.parse("2025-05-25"), false, "GRAVE");

        int opcao;
        String nome, CPF, senha, tipoUsuario;
        boolean isLogado = false;

        while (!isLogado) {
            try {
                opcao = Integer.parseInt(JOptionPane.showInputDialog("Bem-vindo ao sistema! \n\nDigite o método de acesso \n(1) Cadastro \n(2) Login"));

                switch (opcao) {
                    case 1: // CADASTRO
                        nome = JOptionPane.showInputDialog("Digite o seu nome");
                        CPF = JOptionPane.showInputDialog("Digite os 11 digitos do CPF");
                        senha = JOptionPane.showInputDialog("Digite a senha");

                        // Inserção do tipo de usuário
                        tipoUsuario = JOptionPane.showInputDialog("Digite o tipo de usuário a ser cadastrado (CIDADÃO OU AUTORIDADE)").toUpperCase();

                        if (tipoUsuario.equalsIgnoreCase("CIDADÃO")) {
                            // Cadastro cidadão
                            int idCidadao = 2500;
                            cidadao.setIdCidadao(idCidadao);
                            cidadao.setNome(nome);
                            cidadao.setCPF(CPF);
                            cidadao.setSenha(senha);

                        } else if (tipoUsuario.equalsIgnoreCase("AUTORIDADE")) {
                            // Cadastro autoridade
                            int idAutoridade = 2700;
                            autoridade.setIdAutoridade(idAutoridade);
                            autoridade.setNome(nome);
                            autoridade.setCPF(CPF);
                            autoridade.setSenha(senha);

                        } else {
                            throw new Exception("Tipo de usuário incorreto!");
                        }
                        break;

                    case 2: // LOGIN
                        tipoUsuario = JOptionPane.showInputDialog("Digite o tipo de usuário para login (CIDADÃO ou AUTORIDADE)").toUpperCase();
                        nome = JOptionPane.showInputDialog("Digite o seu nome");
                        senha = JOptionPane.showInputDialog("Digite a senha");

                        // Login cidadão
                        if (tipoUsuario.equalsIgnoreCase("CIDADÃO")) {
                            if (nome.equals(cidadao.getNome()) && senha.equals(cidadao.getSenha())) {
                                usuario = cidadao;
                                do {
                                    // Menu Cidadão
                                    int opcaoMenu = Integer.parseInt(JOptionPane.showInputDialog(String.format("Bem-vindo %s!\n\nEscolha uma das opções abaixo \n\n(1) Registrar um foco \n(2) Exibir dados históricos da sua região \n(3) Exibir dados históricos de outra região \n(4) Exibir dados do perfil", cidadao.getNome())));

                                    switch (opcaoMenu) {
                                        case 1: // Registrar um Foco
                                            String gravidade = JOptionPane.showInputDialog("Qual a gravidade da situação? (GRAVE, MODERADO, BAIXO)");
                                            boolean statusFoco = JOptionPane.showConfirmDialog(null, "O foco está sendo atendido?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;
                                            foco = cidadao.registrarFoco(gravidade, statusFoco);

                                            if (!statusFoco) { // se o foco não é atendido chama o corpo de bombeiros
                                                autoridade.atenderFoco(foco, cidadao.getNome());
                                            }
                                            break;

                                        case 2: // Mostrar histórico de focos na região do usuário (cidadão)
                                            cidadao.exibirHistoricoDeFocos();
                                            break;

                                        case 3: // Mostrar histórico de focos da região fornecida
                                            String regiao = JOptionPane.showInputDialog("Digite a região (estado) da qual deseja ver o histórico");
                                            cidadao.exibirHistoricoDeFocos(regiao);
                                            break;

                                        case 4: // Exibir informações do usuário
                                            usuario.exibirInformacoesDoUsuario();
                                            break;

                                        default:
                                            throw new Exception("Opção incorreta!");

                                    }
                                } while (JOptionPane.showConfirmDialog(null, "Deseja continuar?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0);
                            } else {
                                throw new Exception("Senha incorreta!");
                            }

                        // Login autoridade
                        } else if (tipoUsuario.equalsIgnoreCase("AUTORIDADE")) {
                            if (nome.equals(autoridade.getNome()) && senha.equals(autoridade.getSenha())) {
                                usuario = autoridade;
                                do {
                                    // Menu Autoridade
                                    int opcaoMenu = Integer.parseInt(JOptionPane.showInputDialog("Escolha uma das opções abaixo \n(1) Atender um foco \n(2) Exibir informações do usuário \n(3) Exibir histórico de focos da região"));

                                    switch (opcaoMenu) {
                                        case 1: // Atender um foco
                                            autoridade.atenderFoco(foco);
                                            break;

                                        case 2: // Exibir informações do usuário
                                            usuario.exibirInformacoesDoUsuario();
                                            break;

                                        case 3: // Exibir histórico de focos na região do usuário (autoridade)
                                            autoridade.exibirHistoricoDeFocos();
                                            break;

                                        default:
                                            throw new Exception("Opção incorreta!");
                                    }

                                } while (JOptionPane.showConfirmDialog(null, "Deseja continuar?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0);
                            } else {
                                throw new Exception("Senha ou nome incorretos!");
                            }
                        } else {
                            throw new Exception("Tipo de usuário incorreto!");
                        }

                        isLogado = true;
                        break;

                    default:
                        throw new Exception("Escolha incorreta");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        }
        JOptionPane.showMessageDialog(null, "Volte sempre!", "Fim de programa", JOptionPane.WARNING_MESSAGE);
    }
}
