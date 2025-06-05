package br.com.fiap.bean;

import javax.swing.*;

public class Usuario {
    private String nome;
    private String CPF;
    private String senha;

    // construtores

    public Usuario() {
    }

    public Usuario(String nome, String CPF, String senha) {
        setNome(nome);
        setCPF(CPF);
        setSenha(senha);
    }

    // getters/setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        try {
            if (CPF.length() == 11)
                this.CPF = CPF;
            else {
                throw new Exception("O CPF precisa ter 11 digitos");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"ERRO",JOptionPane.ERROR_MESSAGE);
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void exibirInformacoesDoUsuario() {
        String info = String.format("Informações do usuário \nNome: %s \nCPF: %s", this.nome, this.CPF);
        JOptionPane.showMessageDialog(null, info, "Informações do usuário", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Exibe o historico de focos da regiao do usuario mostrando informacoes gerais sobre cada foco
     */
    public void exibirHistoricoDeFocos() {
    }

    /**
     * Exibe o historico de focos da regiao fornecida mostrando informacoes gerais sobre cada foco
     * @param regiao - regiao da qual sera exibido o historico de focos
     */
    public void exibirHistoricoDeFocos(String regiao) {
    }

}
