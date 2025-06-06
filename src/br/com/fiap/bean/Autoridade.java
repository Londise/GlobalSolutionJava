// Juan Fuentes Rufino RM558095
// Pedro Henrique Silva Batista RM558137
// Fernando Carlos Colque Huaranca RM558095 



package br.com.fiap.bean;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Autoridade extends Usuario{
    private String regiaoAtendimento;
    private int idAutoridade;

    // construtores

    public Autoridade() {
    }

    public Autoridade(String nome, String CPF, String senha, String regiaoAtendimento, int idAutoridade) {
        super(nome, CPF, senha);
        this.regiaoAtendimento = regiaoAtendimento;
        this.idAutoridade = idAutoridade;
    }

    // getters/setters

    public String getRegiaoAtendimento() {
        return regiaoAtendimento;
    }

    public void setRegiaoAtendimento(String regiaoAtendimento) {
        this.regiaoAtendimento = regiaoAtendimento;
    }

    public int getIdAutoridade() {
        return idAutoridade;
    }

    public void setIdAutoridade(int idAutoridade) {
        this.idAutoridade = idAutoridade;
    }

    /**
     * Atende ao foco registrado pelo usuario e exibe uma mensagem personalizada para o usuario
     * @param foco refere-se ao foco de incendio
     * @param nome nome do usuario que registrou o foco
     */
    public void atenderFoco(Foco foco, String nome) {

        foco.setAtendido(true);
        String mensagem = String.format("Recebemos a sua solicitação, %s.\nNossos profissionais já estão cientes e em breve estarão a caminho.", nome);
        JOptionPane.showMessageDialog(null, mensagem, "Mensagem do corpo de bombeiros", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Atende a um foco registrado e exibe uma mensagem de confirmacao de atendimento
     * @param foco refere-se ao foco de incendio
     */
    public void atenderFoco(Foco foco) {
        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        foco.setAtendido(true);

        String mensagem = String.format("O foco de\nID: %s \nGRAVIDADE: %s \nRegistrado em: %s \n foi marcado como em atendimento, Equipes de resposta foram acionadas para a área.", foco.getIdFoco(), foco.getGravidade(), dtf.format(dataAtual));
        JOptionPane.showMessageDialog(null, mensagem, "Atenção", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Exibe as informacoes (nome, CPF, ID de autoridade) da autoridade
     */
    public void exibirInformacoesDoUsuario() {
        String info = String.format("Informações do usuário \nNome: %s \nCPF: %s \nID: %d \nRegião de atendimento: %s", getNome(), getCPF(), this.idAutoridade, this.regiaoAtendimento);
        JOptionPane.showMessageDialog(null, info, "Informações do usuário", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * (method overload) Exibe o historico de focos na regiao que a autoridade atua
     */
    public void exibirHistoricoDeFocos() {
        Foco focoSimulado1 = new Foco(5002, 6500, LocalDate.parse("2025-05-29"), true, "GRAVE");
        Foco focoSimulado2 = new Foco(5003, 6700, LocalDate.parse("2025-05-26"), true, "GRAVE");
        Foco focoSimulado3 = new Foco(5004, 5500, LocalDate.parse("2025-08-25"), false, "BAIXA");

        String mensagem = String.format("Data                      ID              Usuário          Gravidade            Local\n%s         %d             %d                  %s         Vila Diamantina\n%s         %d             %d                  %s         Manaus\n%s         %d             %d                  %s         Presidente Figueiredo", focoSimulado1.getDataFoco(), focoSimulado1.getIdFoco(), focoSimulado1.getIdCidadaoVinculado(), focoSimulado1.getGravidade(), focoSimulado2.getDataFoco(), focoSimulado2.getIdFoco(), focoSimulado2.getIdCidadaoVinculado(), focoSimulado2.getGravidade(),focoSimulado3.getDataFoco(), focoSimulado3.getIdFoco(), focoSimulado3.getIdCidadaoVinculado(), focoSimulado3.getGravidade());
        String titulo = String.format("Histórico de focos (DADOS DE MAIO 2025 - %s)", this.regiaoAtendimento);

        JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
}
