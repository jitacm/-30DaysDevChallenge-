#include <iostream>
#include <string>
#include <ctime>

void greet() {
    std::cout << "Hello! How can I assist you today?" << std::endl;
}

void tellTime() {
    time_t now = time(0);
    char* dt = ctime(&now);
    std::cout << "The current time is: " << dt << std::endl;
}

void tellDate() {
    time_t now = time(0);
    tm *ltm = localtime(&now);
    std::cout << "The current date is: " << 1900 + ltm->tm_year << "-"
              << 1 + ltm->tm_mon << "-"
              << ltm->tm_mday << std::endl;
}

void help() {
    std::cout << "I can assist with the following commands:\n"
              << "1. greet - Greets the user\n"
              << "2. time - Tells the current time\n"
              << "3. date - Tells the current date\n"
              << "4. help - Lists the available commands\n"
              << "5. exit - Exits the assistant\n";
}

int main() {
    std::string input;

    std::cout << "Welcome to your personal assistant!" << std::endl;
    help();

    while (true) {
        std::cout << "> ";
        std::getline(std::cin, input);

        if (input == "greet") {
            greet();
        } else if (input == "time") {
            tellTime();
        } else if (input == "date") {
            tellDate();
        } else if (input == "help") {
            help();
        } else if (input == "exit") {
            std::cout << "Goodbye!" << std::endl;
            break;
        } else {
            std::cout << "I'm sorry, I don't understand that command. Type 'help' to see the list of available commands." << std::endl;
        }
    }

    return 0;
}