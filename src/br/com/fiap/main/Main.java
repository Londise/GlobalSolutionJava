// Juan Fuentes Rufino RM557673
// Pedro Henrique Silva Batista RM558137
// Fernando Carlos Colque Huaranca RM558095 

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
        Cidadao cidadao = new Cidadao("admin", "11111111111", "admin", 2500, 0);
        Autoridade autoridade = new Autoridade("admin", "11111111111", "admin", "AMAZONAS", 2700);
        Foco foco = new Foco(3000, cidadao.getIdCidadao(), LocalDate.parse("2025-05-25"), false, "GRAVE");

        int opcao;
        String nome, CPF, senha, tipoUsuario;
        boolean continuar = true;

        JOptionPane.showMessageDialog(null, "Bem-vindo ao Sistema de Monitoramento de Queimadas!\n\nEste programa faz uma simples simulação da nossa solução criada para ajudar cidadãos e autoridades a colaborarem no combate às queimadas no Brasil.\nAqui, você pode registrar novos focos de incêndio, acompanhar o histórico de ocorrências de focos\ne ajudar a proteger o meio ambiente.\n\nSe você é um cidadão, poderá informar novos focos que visualizar, e ver o histórico de focos tanto da sua região\ncomo de outras regiões, ajudando a tornar os dados mais atualizados.\nSe você for uma autoridade, você atua em uma determinada região, pode visualizar os registros e atuar diretamente no atendimento.\n\nEcognition - todos os direitos reservados", "", JOptionPane.INFORMATION_MESSAGE);

        while (continuar) {
            boolean isLogado = false;

            while (!isLogado && continuar) {
                try {
                    opcao = Integer.parseInt(JOptionPane.showInputDialog("Bem-vindo ao sistema! \n\nDigite o método de acesso \n(1) Cadastro \n(2) Login \n(3) Sair do programa"));

                    switch (opcao) {
                        case 1: // CADASTRO
                            nome = JOptionPane.showInputDialog("Digite o seu nome");
                            CPF = JOptionPane.showInputDialog("Digite os 11 digitos do CPF");
                            senha = JOptionPane.showInputDialog("Digite a senha");

                            tipoUsuario = JOptionPane.showInputDialog("Digite o tipo de usuário a ser cadastrado (CIDADÃO OU AUTORIDADE)").toUpperCase();

                            if (tipoUsuario.equalsIgnoreCase("CIDADÃO")) {
                                int idCidadao = 2500;
                                cidadao.setIdCidadao(idCidadao);
                                cidadao.setNome(nome);
                                cidadao.setCPF(CPF);
                                cidadao.setSenha(senha);
                                cidadao.setNumeroDeRegistros(0);

                            } else if (tipoUsuario.equalsIgnoreCase("AUTORIDADE")) {
                                String regiaoAtendimento = JOptionPane.showInputDialog("Digite a região de atendimento").toUpperCase();
                                int idAutoridade = 2700;
                                autoridade.setIdAutoridade(idAutoridade);
                                autoridade.setNome(nome);
                                autoridade.setCPF(CPF);
                                autoridade.setSenha(senha);
                                autoridade.setRegiaoAtendimento(regiaoAtendimento);

                            } else {
                                throw new Exception("Tipo de usuário incorreto!");
                            }
                            break;

                        case 2: // LOGIN
                            tipoUsuario = JOptionPane.showInputDialog("Digite o tipo de usuário para login (CIDADÃO ou AUTORIDADE)").toUpperCase();
                            nome = JOptionPane.showInputDialog("Digite o seu nome");
                            senha = JOptionPane.showInputDialog("Digite a senha");

                            if (tipoUsuario.equalsIgnoreCase("CIDADÃO")) {
                                if (nome.equals(cidadao.getNome()) && senha.equals(cidadao.getSenha())) {
                                    usuario = cidadao;
                                    isLogado = true;

                                    do {
                                        int opcaoMenu = Integer.parseInt(JOptionPane.showInputDialog(String.format("Bem-vindo %s!\n\nEscolha uma das opções abaixo \n\n(1) Registrar um foco \n(2) Exibir dados históricos da sua região \n(3) Exibir dados históricos de outra região \n(4) Exibir dados do perfil \n(5) Deslogar \n(6) Sair do programa", cidadao.getNome())));

                                        switch (opcaoMenu) {
                                            case 1:
                                                String gravidade = JOptionPane.showInputDialog("Qual a gravidade da situação? (GRAVE, MODERADO, BAIXO)");
                                                boolean statusFoco = JOptionPane.showConfirmDialog(null, "O foco está sendo atendido?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;
                                                foco = cidadao.registrarFoco(gravidade, statusFoco);

                                                if (!statusFoco) {
                                                    autoridade.atenderFoco(foco, cidadao.getNome());
                                                }
                                                break;

                                            case 2:
                                                cidadao.exibirHistoricoDeFocos();
                                                break;

                                            case 3:
                                                String regiao = JOptionPane.showInputDialog("Digite a região (estado) da qual deseja ver o histórico");
                                                cidadao.exibirHistoricoDeFocos(regiao);
                                                break;

                                            case 4:
                                                usuario.exibirInformacoesDoUsuario();
                                                break;

                                            case 5:
                                                isLogado = false;
                                                break;

                                            case 6:
                                                isLogado = false;
                                                continuar = false;
                                                break;

                                            default:
                                                throw new Exception("Opção incorreta!");
                                        }
                                    } while (isLogado);

                                } else {
                                    throw new Exception("Senha incorreta!");
                                }

                            } else if (tipoUsuario.equalsIgnoreCase("AUTORIDADE")) {
                                if (nome.equals(autoridade.getNome()) && senha.equals(autoridade.getSenha())) {
                                    usuario = autoridade;
                                    isLogado = true;

                                    do {
                                        int opcaoMenu = Integer.parseInt(JOptionPane.showInputDialog(String.format("Bem-vindo %s!\n\nEscolha uma das opções abaixo\n(1) Atender um foco \n(2) Exibir informações do usuário \n(3) Exibir histórico de focos da região \n(4) Deslogar \n(5) Sair do programa", autoridade.getNome())));

                                        switch (opcaoMenu) {
                                            case 1:
                                                autoridade.atenderFoco(foco);
                                                break;

                                            case 2:
                                                usuario.exibirInformacoesDoUsuario();
                                                break;

                                            case 3:
                                                autoridade.exibirHistoricoDeFocos();
                                                break;

                                            case 4:
                                                isLogado = false;
                                                break;

                                            case 5:
                                                isLogado = false;
                                                continuar = false;
                                                break;

                                            default:
                                                throw new Exception("Opção incorreta!");
                                        }

                                    } while (isLogado);

                                } else {
                                    throw new Exception("Senha ou nome incorretos!");
                                }
                            } else {
                                throw new Exception("Tipo de usuário incorreto!");
                            }
                            break;

                        case 3: // SAIR DO PROGRAMA
                            continuar = false;
                            break;

                        default:
                            throw new Exception("Escolha incorreta");
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        JOptionPane.showMessageDialog(null, "Volte sempre!", "Fim de programa", JOptionPane.WARNING_MESSAGE);
    }
}
