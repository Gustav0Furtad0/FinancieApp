package App;

import java.time.LocalDate;
import java.util.*;

public class User {
    private double money;
    private String username;
    private List<acao> actions  = new ArrayList<acao>();
    private String moeda;

    public User (String username) {
        this.username = username;
        this.moeda = "BRL";
    }

    public void compraAcao(String acao, double d, LocalDate dataCompra) {
        this.actions.add(new acao(acao, d, dataCompra));
    }

    public void acoesConta(){
        System.out.println(this.actions);
    }
}
