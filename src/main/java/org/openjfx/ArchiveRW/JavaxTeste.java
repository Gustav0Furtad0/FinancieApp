package org.openjfx.ArchiveRW;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;

import org.openjfx.back.AcaoComprada;
import org.openjfx.back.User;
import org.openjfx.back.Acao;



public class JavaxTeste {

    public static Boolean existe() {
        File file = new File("user.json");
        if (file.exists()) return true;
        return false;
    }

    public static User readFileUser() throws Exception {
        InputStream fis = new FileInputStream("user.json");
        JsonReader reader = Json.createReader(fis);
        JsonObject personObject = reader.readObject();
        reader.close();

        User user = new User(personObject.getString("username"), personObject.getJsonNumber("money").doubleValue() );

        JsonArray actions = personObject.getJsonArray("actions");
        List<AcaoComprada> actionsUser = new ArrayList<AcaoComprada>();

        if (actions != null) {
            for (JsonValue actionObj : actions) {
                JsonReader jsonReader = Json.createReader(new StringReader(actionObj.toString()));
                JsonObject action = jsonReader.readObject();

                //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date =  LocalDate.parse(action.getString      ("dataCompra"));
                actionsUser.add(
                    new AcaoComprada(
                        new Acao(
                            action.getString("nome"),  
                            action.getJsonNumber("valor").doubleValue(),  
                            action.getString("empresa"), 
                            action.getJsonNumber("volume").doubleValue(),
                            action.getJsonNumber("variacao").doubleValue()
                        ),
                        date, 
                        action.getInt("quantidade")
                    )
                );
            }
        }

        user.setActions(actionsUser);

        return user;
    }

    public static void createFileUser(Map<String, String> map) throws IOException {
        FileWriter jsonFile = new FileWriter("user.json");

        JsonArrayBuilder actions = Json.createArrayBuilder();
        JsonArray actionsBuild = actions.build();

        JsonObject jsonObj = Json.createObjectBuilder()
            .add("username", map.get("username"))

            .add("money", Double.parseDouble(map.get("money")) )

            .add("actions", actionsBuild)

            .build();
        
        StringWriter stringWriter = new StringWriter();
        JsonWriter writer = Json.createWriter(stringWriter);
        writer.writeObject(jsonObj);
        writer.close();


        jsonFile.append(stringWriter.getBuffer().toString());
        jsonFile.close();
    }

    public static void updateFileUser(User user) throws IOException {
        JsonArrayBuilder actions = Json.createArrayBuilder();
        List<AcaoComprada> actionsUser = user.getActions();

        if (actions != null) {
            for (AcaoComprada action : actionsUser) {
                actions.add(Json.createObjectBuilder()
                    .add("nome", action.getNome())
                    .add("empresa", action.getEmpresa())
                    .add("valor", action.getValor())
                    .add("quantidade", action.getQuantidade())
                    .add("valorCompra", action.getValorCompra())
                    .add("dataCompra", action.getDataCompra().toString())
                    .add("volume", Double.parseDouble(action.getVolume().toString()))
                    .add("variacao", Double.parseDouble(action.getVariacao().toString()))
                    .build()
                );
            }
        }

        JsonArray actionsBuild = actions.build();

        JsonObject jsonObj = Json.createObjectBuilder()
            .add("username", user.getUsername())
            .add("money", user.getMoney())
            .add("actions", actionsBuild)
            .build();

        JsonWriter writer = Json.createWriter(new FileWriter("user.json"));
        writer.writeObject(jsonObj);
        writer.close();
    }
}