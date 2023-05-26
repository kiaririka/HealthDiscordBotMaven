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

        if (messageContent.equalsIgnoreCase("/about")) {
            String reply = "I am a chatbot made with the intent of helping anyone and everyone looking for a friend to talk to.\n" +
                    "Feel free to ask me anything or simply chat with me.";
            event.getChannel().sendMessage(reply).queue();
        } else if (messageContent.equalsIgnoreCase("/help")) {
            String reply = "Here are some options you can ask me:\n" +
                    "1. /about - To know more about me\n" +
                    "2. /help - To know more options to ask me\n" +
                    "3. /talk - To have a one-on-one talk session";
            event.getChannel().sendMessage(reply).queue();
        } else if (messageContent.equalsIgnoreCase("/talk")) {
            String reply = "Sure! Let's have a one-on-one talk. I will shift to your private chat to respect our privacy.";
            event.getChannel().sendMessage(reply).queue();
            event.getMember().getUser().openPrivateChannel().queue(channel -> {
                // Perform actions in the private channel
                channel.sendMessage("Hello! How can I assist you?").queue();
            });

        } else {
            // If no matching command is found, pass the message to the PostRequest class for processing
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
}
