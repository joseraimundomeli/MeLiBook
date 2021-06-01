package br.com.socialmeli;


import br.com.socialmeli.models.User;

import java.util.ArrayList;
import java.util.List;

public class TempTest {

    public static ArrayList<User> users = new ArrayList<User> ();



    public static void start(){
        users.add(
                new User(1, "José Raimundo", new ArrayList<Integer>(List.of(2, 3)), new ArrayList<Integer>(), false )
        );
        users.add(
                new User(2, "Pedro Luiz", new ArrayList<Integer>(), new ArrayList<Integer>(List.of(1, 3)), true)
        );
        users.add(
                new User(3, "Andre Vitor", new ArrayList<Integer>(),new ArrayList<Integer>( List.of(2)), true)
        );

        users.add(
                new User(4, "Gabriel Pedrosa", new ArrayList<Integer>(List.of(2)), new ArrayList<Integer>(), false )
        );
    }


//    public static void writeJsonSimpleDemo(String filename) throws Exception {
//        JSONObject sampleObject = new JSONObject();
//        sampleObject.put("name", "Stackabuser");
//        sampleObject.put("age", 35);
//
//        JSONArray messages = new JSONArray();
//        messages.add("Hey!");
//        messages.add("What's up?!");
//
//        sampleObject.put("messages", messages);
//        Files.write(Paths.get(filename), sampleObject.toJSONString().getBytes());
//    }
//
//    public static Object readJsonSimpleDemo(String filename) throws Exception {
//        FileReader reader = new FileReader(filename);
//        JSONParser jsonParser = new JSONParser();
//        return jsonParser.parse(reader);
//    }
//
//    public static void main(String[] args) {
//        String filename = "src/data/database.json";
//        try {
//            writeJsonSimpleDemo(filename);
//            System.out.println(readJsonSimpleDemo(filename).toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
