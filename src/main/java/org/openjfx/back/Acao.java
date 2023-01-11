package org.openjfx.back;

import java.util.ArrayList;
import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openjfx.ReadJson.GetJson;

/*Alunos:
 * Gustavo de Jesus Furtado ,   202176024;
 * Pedro Andrade Pereira Leão , 202035008.
 */

public abstract class Acao {
    protected final String nome;
    protected final String empresa;
    protected double valor;

    public Acao (String nome, double valor, String empresa) {
        this.nome = nome;
        this.valor = valor;
        this.empresa = empresa;
    }

    public String getNome() {
        return this.nome;
    }

    public double getValor() {
        return this.valor;
    }

    public String getEmpresa() {
        return this.empresa;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public static String FormatD (double n) {
        String dn = String.format("%.2f", n);
        return dn;
    }

    public static List<AcaoListada> puxaValores() throws IOException {
        
        List<AcaoListada> result = new ArrayList<AcaoListada>();

        String response = GetJson.Get("https://brapi.dev/api/quote/list?sortBy=name&sortOrder=asc&limit=2000");
        JSONObject stocks = new JSONObject(response);
        JSONArray stocksArray = new JSONArray(stocks.getJSONArray("stocks"));

        for (int i = 0 ; i < stocksArray.length(); i++) {
            JSONObject album = stocksArray.getJSONObject(i);			
            String stock = album.getString("stock");
            String name = album.getString("name");
            double valor = album.getDouble("close");
            Object volume = album.get("volume");
            Object variacao = album.get("change");
            result.add(new AcaoListada(stock, valor, name, volume, variacao));
        }

        return result;
    }

    public static List<AcaoComprada> ordenaAcao(List<AcaoComprada> actions) {
        if(actions.size() == 1) return actions;
        String esse, frente;
        Boolean ordem = false;
        while (ordem == false) {
            for(int i = 0; i < (actions.size()-1); i++) {
                esse = actions.get(i).getNome();
                frente = actions.get(i+1).getNome();
                System.out.println("Comparando " + esse + " " + frente);
                System.out.println("É oq? : " + esse + " " + (charMaior(esse, frente)));
                if(charMaior(esse, frente) == 0) {
                    AcaoComprada auxiliar = actions.get(i);
                    actions.set(i, actions.get(i+1));
                    actions.set(i+1, auxiliar);
                    System.out.println("");
                    break;
                }
                ordem = true;
            }
        }
        return actions;
    }

    private static int charMaior(String a, String b) {
        if (a.length() == b.length()){

            for(int i = 0; i < a.length(); i++) {
                if(a.charAt(i) > b.charAt(i)) return 0;
                else if(a.charAt(i) < b.charAt(i)) return 1;
            }
            return -1;

        } else if (a.length() < b.length()){

            for(int i = 0; i < a.length(); i++) {   
                if(a.charAt(i) > b.charAt(i)) return 0;
                else if(a.charAt(i) < b.charAt(i)) return 1;
            }
            return -1;

        } else {

            for(int i = 0; i < b.length(); i++) {   
                if(a.charAt(i) > b.charAt(i)) return 0;
                else if(a.charAt(i) < b.charAt(i)) return 1;
            }
            return 1;
        }
    }
}
