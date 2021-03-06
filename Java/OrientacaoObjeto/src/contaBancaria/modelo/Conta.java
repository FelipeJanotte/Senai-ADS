package contaBancaria.modelo;

import contaBancaria.visualizacao.EntradaSaida;

import java.util.ArrayList;

public class Conta {
    private String titularDaConta;
    private String tipo;
    private double saldo;
    private ArrayList<Movimentacao> listaDeMovimentacao;

    public void setTitularDaConta(String titularDaConta){
        this.titularDaConta = titularDaConta;
    }

    public String getTitularDaConta(){
        return titularDaConta;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public String getTipo(){
        return tipo;
    }

    private void setSaldo(double saldo){
        this.saldo = saldo;
    }

    public double getSaldo(){
        return saldo;
    }

    private void depositar(double deposito){
        this.saldo = this.saldo + deposito;
    }

    private void sacar(double saque){
        this.saldo = this.saldo - saque;
    }

    public void gerarSaldo(String metodo, double valor){
        switch (metodo){
            case "Deposito": {
                depositar(valor);
                break;
            }
            case "Saque": {
                sacar(valor);
                break;
            }
            default:{
                EntradaSaida.exibirMetodoNaoEncontrado(metodo);
                break;
            }
        }

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setData();
        movimentacao.setTipo(metodo);
        movimentacao.setValor(valor);

        if(listaDeMovimentacao == null){
            listaDeMovimentacao = new ArrayList<>();
        }
        listaDeMovimentacao.add(movimentacao);
    }

    public String[] gerarDadosDaConta() {
        String[] dados = new String[]{titularDaConta, tipo, String.format("%.2f",saldo)};
        return dados;
    }

    public ArrayList<Movimentacao> gerarExtrato(){
        if(listaDeMovimentacao == null){
            ArrayList<Movimentacao> listaVazia = new ArrayList<>();
            return listaVazia;
        }
        return listaDeMovimentacao;
    }

    public ArrayList<Movimentacao> gerarExtratoDepositos(){
        if(listaDeMovimentacao == null){
            ArrayList<Movimentacao> listaVazia = new ArrayList<>();
            return listaVazia;
        }
        ArrayList<Movimentacao> listaDeDepositos = new ArrayList<Movimentacao>();
        for(Movimentacao movimentacao: listaDeMovimentacao){
            if(movimentacao.getTipo().equals("Deposito")){
                listaDeDepositos.add(movimentacao);
            }
        }
        return listaDeDepositos;
    }

    public ArrayList<Movimentacao> gerarExtratoSaques(){
        if(listaDeMovimentacao == null){
            ArrayList<Movimentacao> listaVazia = new ArrayList<>();
            return listaVazia;
        }
        ArrayList<Movimentacao> listaDeSaques = new ArrayList<Movimentacao>();
        for(Movimentacao movimentacao: listaDeMovimentacao){
            if(movimentacao.getTipo().equals("Saque")){
                listaDeSaques.add(movimentacao);
            }
        }
        return listaDeSaques;
    }

}

