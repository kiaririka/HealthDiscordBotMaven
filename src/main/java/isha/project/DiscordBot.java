package isha.project;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Properties;

public class DiscordBot extends ListenerAdapter {
    public static void main(String[] args) {
        Properties properties = new Properties();

//        String apiKey = null;
//        try (InputStream input = DiscordBot.class.getClassLoader().getResourceAsStream("config.properties")) {
//            properties.load(input);
//            apiKey = properties.getProperty("api.key");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        String apiKey = System.getenv("DISCORD_API");

        // Check if the API key is null or empty
        if (apiKey == null || apiKey.isEmpty()) {
            System.out.println("API key is not set. Please make sure to set the API_KEY environment variable.");
            return;
        }
        String token = apiKey;

        DiscordBot main = new DiscordBot();

        JDABuilder.createDefault(token)
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

        } else if (messageContent.equalsIgnoreCase("Hey Maven")) {
            String reply = "Hi I'm Maven. I'm your AI health care friend. I'm here to help you live a healthier life. I can answer your questions about your health, provide you with resources and information, and even help you track your progress. I'm always learning new things, so please don't hesitate to ask me anything. \n Here are some options you can ask me: \n /about - To know more about me \n /help - To know more options to ask me\n /talk - To have a one-on-one talk session";
            event.getChannel().sendMessage(reply).queue();
        }else {
            // pass the message to the PostRequest class for processing if no match found
            PostRequest request = new PostRequest();
            try {
                String reply = request.post(messageContent);
                event.getChannel().sendMessage(reply).queue();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }



}
