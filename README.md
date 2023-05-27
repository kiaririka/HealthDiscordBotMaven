# HealthDiscordBotMaven


# Documentation

## Introduction

The Health Discord bot is designed to provide various functionalities and features related to health using the Discord Java API. It interacts with the Discord API to respond to commands and provide health-related information and services, it can your personal friend to vent to or your intellligent doctor friend.

## Getting Started

### Prerequisites

To run the Health Discord bot, you'll need the following:

-   Discord account
-   Discord bot token
-   Java development environment
-   Dependencies: JSON, Discord Java API

### Installation

1.  Clone the bot repository from GitHub.
2.  Set up a new Discord bot and obtain the bot token.
3.  Install Java on your system.
4.  Add the JSON, Discord Java API dependencies to your Java project.
5.  Build the project using your preferred build tool (e.g., Maven or Gradle).

### Configuration

1.  Create a configuration file (e.g., `config.properties`) to store sensitive information.
2.  Add the Discord bot token to the configuration file.
3.  Provide any other necessary configuration parameters (e.g., channel IDs, API keys).

### Running the Bot

1.  Execute the compiled Java application or run the bot using your build tool.
2.  The bot will connect to Discord using the provided token and appear online.
3.  Invite the bot to your Discord server using the OAuth2 URL generated for your bot.

## Features

### Command List

1.  **/help** - Display a list of available bot commands and their descriptions.
2.  **/about** - Send a greeting message to the user.
3.  **/talk** - the chatbot will talk to you in your private channel and adress your issues.

### Command Usage

-   Use the prefix "/" followed by the command name to execute a command.

## Discord Java API Integration

The Health Discord bot integrates with the Discord Java API library, which provides the following functionality:

-   Bot authentication and connection to Discord.
-   Event handling for message and user-related events.
-   Interaction with the Discord API to send messages and receive user input.


## Troubleshooting

If you encounter any issues while running the bot or using the Discord API, consider the following:

-   Ensure the bot token is correct and properly configured.
-   Check for any error messages or exceptions in the console output.
-   Verify the bot has the necessary permissions in the Discord server.

## Resources

-   [Discord API Documentation](https://discord.com/developers/docs/intro)
-   [Discord Java API Documentation](https://github.com/DV8FromTheWorld/JDA/wiki)
