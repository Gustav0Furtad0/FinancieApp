package App;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import ReadJson.GetJson;

public class acao {
    protected final String nome;
    protected final String empresa;
    protected final double valor;
    protected final Object volume;

    public acao (String nome, double valor, String empresa, Object volume) {
        this.nome = nome;
        this.valor = valor;
        this.empresa = empresa;
        this.volume = volume;
    }

    public String toString() {
        String str = "Stock: "+this.nome+", Price R$"+valor+" .";
        return str;
    }

    public String getNome() {
        return this.nome;
    }

    public double getValor() {
        return this.valor;
    }

    public Object getVolume() {
        return volume;
    }
    
    public String getEmpresa() {
        return empresa;
    }

    public static List<acao> puxaValores() throws IOException {
        
        List<acao> result = new ArrayList<acao>();

        String response = GetJson.Get("https://brapi.dev/api/quote/list?sortBy=name&sortOrder=asc&limit=2000");
        JSONObject stocks = new JSONObject(response);
        JSONArray stocksArray = new JSONArray(stocks.getJSONArray("stocks"));

        for (int i = 0 ; i < stocksArray.length(); i++) {
            JSONObject album = stocksArray.getJSONObject(i);			
            String stock = album.getString("stock");
            String name = album.getString("name");
            double valor = album.getDouble("close");
            Object volume = album.get("volume");
            result.add(new acao(stock, valor, name, volume));
        }

        return result;
    }

    
}
