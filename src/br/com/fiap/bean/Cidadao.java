// Juan Fuentes Rufino RM558095
// Pedro Henrique Silva Batista RM558137
// Fernando Carlos Colque Huaranca RM558095

package br.com.fiap.bean;
/**
 * Classe que representa um cidadao que pode registrar focos de incendio e consultar historicos.
 * Estende a classe Usuario, adicionando funcionalidades específicas ao cidadão.
 */

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Cidadao extends Usuario{
    private int idCidadao;
    private int numeroDeRegistros;

    // construtores

    public Cidadao() {
    }

    public Cidadao(String nome, String CPF, String senha, int idCidadao, int numeroDeRegistros) {
        super(nome, CPF, senha);
        this.idCidadao = idCidadao;
        this.numeroDeRegistros = numeroDeRegistros;
    }

    // getters/setters

    public int getIdCidadao() {
        return idCidadao;
    }

    public void setIdCidadao(int idCidadao) {
        this.idCidadao = idCidadao;
    }

    public int getNumeroDeRegistros() {
        return numeroDeRegistros;
    }

    public void setNumeroDeRegistros(int numeroDeRegistros) {
        this.numeroDeRegistros = numeroDeRegistros;
    }

    /**
     * Possibilita ao cidadao registrar um foco informando a gravidade e a presenca ou nao de autoridades na area
     * @param gravidade indica a gravidade do foco registrado
     * @param isAtendido indica se ha autoridades ou nao atendendo o foco registrado
     * @return retorna o foco registrado pelo usuario
     */
    public Foco registrarFoco(String gravidade, boolean isAtendido) {
        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int idFoco = 5000;

        Foco foco = new Foco(idFoco, this.idCidadao, dataAtual, isAtendido, gravidade);
        numeroDeRegistros++;
        JOptionPane.showMessageDialog(null, String.format("Foco registrado com sucesso! \nID: %d \nGRAVIDADE: %s \nDATA: %s \nRegistrado por (%s)", idFoco, gravidade, dtf.format(dataAtual), this.getNome()), "Registro de foco", JOptionPane.WARNING_MESSAGE);

        return foco;
    }

    /**
     * Exibe as informacoes (Nome, CPF, ID de cidadao) do cidadao
     */
    public void exibirInformacoesDoUsuario() {
        String info = String.format("Informações do usuário \nNome: %s \nCPF: %s \nID: %d \nNúmero de focos registrados: %d", getNome(), getCPF(), this.idCidadao, this.numeroDeRegistros);
        JOptionPane.showMessageDialog(null, info, "Informações do usuário", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Exibe um historico simulado de focos de incendio da regiao do proprio cidadao com dados ficticios.
     * As informacoes exibidas incluem a data, a gravidade e a localidade do foco.
     */

    public void exibirHistoricoDeFocos() {
        Foco focoSimulado1 = new Foco(5002, 6500, LocalDate.parse("2025-05-29"), true, "GRAVE");
        Foco focoSimulado2 = new Foco(5003, 6700, LocalDate.parse("2025-05-26"), true, "GRAVE");
        Foco focoSimulado3 = new Foco(5004, 5500, LocalDate.parse("2025-08-25"), false, "BAIXA");

        String mensagem = String.format("Data                      Gravidade                Local\n%s              %s         Vila Gustavo\n%s              %s         Cachoeirinha\n%s              %s          Coari", focoSimulado1.getDataFoco(), focoSimulado1.getGravidade(), focoSimulado2.getDataFoco(), focoSimulado2.getGravidade(),focoSimulado3.getDataFoco(), focoSimulado3.getGravidade());
        JOptionPane.showMessageDialog(null, mensagem, "Histórico de focos (DADOS DE MAIO 2025 - AMAZONAS)", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Exibe um historico simulado de focos de incendio de uma regiao especifica informada pelo usuario com dados ficticios
     * As informacoes exibidas incluem a data, a gravidade e a localidade dos focos.
     * @param regiao Nome da regiao (estado ou localidade) da qual se deseja visualizar o historico de focos.
     */

    public void exibirHistoricoDeFocos(String regiao) {
        Foco focoSimulado1 = new Foco(5002, 6600, LocalDate.parse("2025-05-29"), true, "GRAVE");
        Foco focoSimulado2 = new Foco(5003, 6200, LocalDate.parse("2025-05-26"), false, "MODERADA");
        Foco focoSimulado3 = new Foco(5004, 5100, LocalDate.parse("2025-08-25"), true, "BAIXA");

        String mensagem = String.format("Data                      Gravidade                      Local\n%s            %s                     Vila Diamantina\n%s             %s            Vila Matilde\n%s             %s                     Presidente Figueiredo", focoSimulado1.getDataFoco(), focoSimulado1.getGravidade(), focoSimulado2.getDataFoco(), focoSimulado2.getGravidade(),focoSimulado3.getDataFoco(), focoSimulado3.getGravidade());
        String titulo = String.format("Histórico de focos (DADOS DE MAIO 2025 - %s)", regiao);

        JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

}
