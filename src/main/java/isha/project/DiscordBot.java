package isha.project;


import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class DiscordBot extends ListenerAdapter {
//    private JsonArray messages;

    public static void main(String[] args) {
        Properties properties = new Properties();

        String apiKey = null;
        try (InputStream input = DiscordBot.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
            apiKey = properties.getProperty("api.key");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String token = apiKey;

        DiscordBot main = new DiscordBot();

        JDA jda = JDABuilder.createDefault(token)
                .addEventListeners(main)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .build();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return; // Ignore messages from other bots
        }

        String messageContent = event.getMessage().getContentRaw();

//        // Iterate over the messages array and find a matching message
//        for (int i = 0; i < messages.size(); i++) {
//            JsonObject messageObject = messages.get(i).getAsJsonObject();
//            String message = messageObject.get("message").getAsString();
//            String reply = messageObject.get("reply").getAsString();
//
//            if (messageContent.equalsIgnoreCase(message)) {
//                event.getChannel().sendMessage(reply).queue();
//                return;
//            }
//        }

        // If no matching message is found, pass the message to PostRequest class for processing
        PostRequest request = new PostRequest();
        try {
            String reply = request.post(messageContent);
            event.getChannel().sendMessage(reply).queue();
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
}
