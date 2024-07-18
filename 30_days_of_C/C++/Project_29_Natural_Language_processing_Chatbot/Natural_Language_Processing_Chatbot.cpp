#include <iostream>
#include <string>
#include <unordered_map>
#include <vector>
#include <algorithm>

// Function to convert a string to lowercase
std::string toLower(const std::string& str) {
    std::string lowerStr = str;
    std::transform(lowerStr.begin(), lowerStr.end(), lowerStr.begin(), ::tolower);
    return lowerStr;
}

// Function to get a response from the chatbot
std::string getResponse(const std::string& input) {
    static std::unordered_map<std::string, std::string> responses = {
        {"hi", "Hello! How can I help you today?"},
        {"hello", "Hi there! How can I assist you?"},
        {"how are you", "I'm just a bot, but I'm here to help you!"},
        {"bye", "Goodbye! Have a great day!"},
        {"what is your name", "I am a simple chatbot created in C++."},
        {"help", "Sure! What do you need help with?"}
    };

    std::string lowerInput = toLower(input);
    if (responses.find(lowerInput) != responses.end()) {
        return responses[lowerInput];
    } else {
        return "I'm sorry, I don't understand that.";
    }
}

int main() {
    std::cout << "Chatbot: Hello! I am a simple chatbot. Type 'bye' to exit.\n";
    std::string userInput;

    while (true) {
        std::cout << "You: ";
        std::getline(std::cin, userInput);

        std::string response = getResponse(userInput);
        std::cout << "Chatbot: " << response << "\n";

        if (toLower(userInput) == "bye") {
            break;
        }
    }

    return 0;
}