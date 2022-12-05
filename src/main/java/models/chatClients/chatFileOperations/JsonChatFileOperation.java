package models.chatClients.chatFileOperations;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import models.Message;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JsonChatFileOperation implements ChatFileOperations {

    private final Gson gson;
    private static final String MESSAGES_FILE = "./messages.json";

    public JsonChatFileOperation() {
        GsonBuilder gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting();

        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());

        this.gson = gsonBuilder.create();
    }

    @Override
    public List<Message> loadMessages() {
        try {
            FileReader reader = new FileReader(MESSAGES_FILE);
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder jsonText = new StringBuilder();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonText.append(line);
            }
            reader.close();

            Type targetType = new TypeToken<ArrayList<Message>>() {
            }.getType();

            return gson.fromJson(jsonText.toString(), targetType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void writeMessage(List<Message> messages) {
        String jsonText = gson.toJson(messages);
        try {
            FileWriter writer = new FileWriter(MESSAGES_FILE);
            writer.write(jsonText);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<String> loadLoggedUsers() {
        return null;
    }

    @Override
    public void writeLoggedUsers(List<String> users) {

    }
}

