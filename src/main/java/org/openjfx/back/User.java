package org.openjfx.back;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*Alunos:
 * Gustavo de Jesus Furtado ,   202176024;
 * Pedro Andrade Pereira Leão , 202035008.
 */

public class User implements Vendas {
    private double money;
    private String username;
    private List<AcaoComprada> actions;

    public User (String username, double money) {
        this.username = username;
        this.money = money;
        this.actions = new ArrayList<AcaoComprada>();
    }

    public void setActions(List<AcaoComprada> actions) {
        this.actions = actions;
    }

    public void compraAcao(AcaoListada acaoCompra, LocalDate dataCompra, int quantidade) {
        if ((this.money - acaoCompra.getValor()) < 0) {
            System.out.println("Error: Não tem dinheiro para comprar a acao.");
        } else {

            if(this.actions.size() == 0) {
                this.actions.add(new AcaoComprada(acaoCompra.getNome(), acaoCompra.getValor(), acaoCompra.getEmpresa(), dataCompra, quantidade));
            } else {
                int verificaSeAcao = verificaAcao(acaoCompra.getNome());

                if (verificaSeAcao == -1) {
                    this.actions.add(new AcaoComprada(acaoCompra.getNome(), acaoCompra.getValor(), acaoCompra.getEmpresa(), dataCompra, quantidade));
                    System.out.println("Acao adicionada a carteira!");
                } else {
                    this.actions.get(verificaSeAcao).aumentaQtd(quantidade);
                    System.out.println("Acao incrementada a carteira!");
                }
            }
           
            this.money -= acaoCompra.getValor() * quantidade;
            this.actions = AcaoListada.ordenaAcao(actions);
            System.out.println("Saldo resultante em conta: R$" + this.money + "\n\n");
        }
    }

    public void vendeAcao(AcaoComprada acaoVende, int qtd) {
        try {
            AcaoComprada acao = actions.get(actions.indexOf(acaoVende));
            acao.diminuiQtd(qtd);
            this.money += acao.getValor() * qtd;
            if (acao.getQuantidade() == 0) {
                this.actions.remove(acao);
            }
        } catch (Exception e) { 
            System.out.println("Acao não encontrada!");
            return;
        }
    }

    public void updateCarteira(List<AcaoListada> acoesList) {
        for (AcaoComprada acaoC : actions) {
            for (AcaoListada acaoL : acoesList) {
                if (acaoL.getNome().equals(acaoC.getNome())) {
                    acaoC.setValor(acaoL.getValor());
                }
            }
        }
    }

    public List<AcaoComprada> acoesConta(){
        double valorAcoes = 0;
        for(int i = 0; i < this.actions.size(); i++) {
            System.out.println(this.actions.get(i));
            valorAcoes += this.actions.get(i).getValor();
        }
        System.out.println("O valor de acoes que detém é R$" + valorAcoes + "." );
        return this.actions;
    }

    public void exibirCarteira(){
        System.out.println("Carteira de " + this.getUsername() + "\n");
        this.acoesConta();
        System.out.println("O saldo em dinheiro atual é : " + this.getMoney() + "\n\n");
    }

    private int verificaAcao(String nome) {
        for(int i = 0; i < this.actions.size(); i++) {
            if(this.actions.get(i).getNome().equals(nome)) return i;
        }
        return -1;
    }

    public Map<String, String> userToMap() {
        Map<String, String> mapUser = new HashMap<>();
        mapUser.put("username", this.username);
        mapUser.put("money", Double.toString(this.money));
        return mapUser;
    }

    public String getUsername() {
        return this.username;
    }

    public Double getMoney() {
        return this.money;
    }

    public List<AcaoComprada> getActions() {
        return actions;
    }

    public Double ativosCount() {
        Double count = 0.0;
        for (AcaoComprada a : actions) {
            count += a.getValor() * a.getQuantidade();
        }
        return count;
    }
}
