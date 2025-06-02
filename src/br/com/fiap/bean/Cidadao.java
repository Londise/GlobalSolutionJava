package br.com.fiap.bean;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Cidadao extends Usuario{
    private int idCidadao;

    public Cidadao() {
    }

    public Cidadao(String nome, String CPF, String senha, int idCidadao) {
        super(nome, CPF, senha);
        this.idCidadao = idCidadao;
    }

    public int getIdCidadao() {
        return idCidadao;
    }

    public void setIdCidadao(int idCidadao) {
        this.idCidadao = idCidadao;
    }

    /**
     * Possibilita ao cidadao registrar um foco informando a gravidade e a presenca ou nao de autoridades na area
     *
     * @param gravidade - indica a gravidade do foco registrado
     * @param isAtendido - indica se ha autoridades ou nao atendendo o foco registrado
     */
    public Foco registrarFoco(String gravidade, boolean isAtendido) {
        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int idFoco = 5000;

        Foco foco = new Foco(idFoco, this.idCidadao, dataAtual, isAtendido, gravidade);
        JOptionPane.showMessageDialog(null, String.format("O foco de\nID: %s \nGRAVIDADE: %s \nFoi registrado com sucesso em %s", idFoco, gravidade, dtf.format(dataAtual)));

        return foco;
    }

    /**
     * Exibe as informacoes (Nome, CPF, ID de cidadao) do cidadao
     */
    public void exibirInformacoesDoUsuario() {
        String info = String.format("Informações do usuário \nNome: %s \nCPF: %s \nID: %d", getNome(), getCPF(), this.idCidadao);
        JOptionPane.showMessageDialog(null, info, "Informações do usuário", JOptionPane.INFORMATION_MESSAGE);
    }

    public void exibirHistoricoDeFocos() {
        Foco focoSimulado1 = new Foco(5002, 6500, LocalDate.parse("2025-05-29"), true, "GRAVE");
        Foco focoSimulado2 = new Foco(5003, 6700, LocalDate.parse("2025-05-26"), true, "GRAVE");
        Foco focoSimulado3 = new Foco(5004, 5500, LocalDate.parse("2025-08-25"), false, "BAIXA");

        String mensagem = String.format("Data                      Gravidade                Local\n%s              %s         Vila Gustavo\n%s              %s         Cachoeirinha\n%s              %s          Coari", focoSimulado1.getDataFoco(), focoSimulado1.getGravidade(), focoSimulado2.getDataFoco(), focoSimulado2.getGravidade(),focoSimulado3.getDataFoco(), focoSimulado3.getGravidade());
        JOptionPane.showMessageDialog(null, mensagem, "Histórico de focos (DADOS DE MAIO 2025 - AMAZONAS)", JOptionPane.INFORMATION_MESSAGE);
    }

    public void exibirHistoricoDeFocos(String regiao) {
        Foco focoSimulado1 = new Foco(5002, 6600, LocalDate.parse("2025-05-29"), true, "GRAVE");
        Foco focoSimulado2 = new Foco(5003, 6200, LocalDate.parse("2025-05-26"), false, "MODERADA");
        Foco focoSimulado3 = new Foco(5004, 5100, LocalDate.parse("2025-08-25"), true, "BAIXA");

        String mensagem = String.format("Data                      Gravidade                      Local\n%s            %s                     Vila Diamantina\n%s             %s            Vila Matilde\n%s             %s                     Presidente Figueiredo", focoSimulado1.getDataFoco(), focoSimulado1.getGravidade(), focoSimulado2.getDataFoco(), focoSimulado2.getGravidade(),focoSimulado3.getDataFoco(), focoSimulado3.getGravidade());
        String titulo = String.format("Histórico de focos (DADOS DE MAIO 2025 - %s)", regiao);

        JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

}
