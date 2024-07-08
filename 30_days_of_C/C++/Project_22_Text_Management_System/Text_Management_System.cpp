#include <iostream>
#include <vector>
#include <string>

void displayMenu() {
    std::cout << "Text Management System" << std::endl;
    std::cout << "1. Add text" << std::endl;
    std::cout << "2. View texts" << std::endl;
    std::cout << "3. Delete text" << std::endl;
    std::cout << "4. Exit" << std::endl;
    std::cout << "Choose an option: ";
}

void addText(std::vector<std::string>& texts) {
    std::cin.ignore(); 
    std::string text;
    std::cout << "Enter text: ";
    std::getline(std::cin, text);
    texts.push_back(text);
}

void viewTexts(const std::vector<std::string>& texts) {
    std::cout << "Texts:" << std::endl;
    for (size_t i = 0; i < texts.size(); ++i) {
        std::cout << i + 1 << ". " << texts[i] << std::endl;
    }
}

void deleteText(std::vector<std::string>& texts) {
    int index;
    std::cout << "Enter the index of the text to delete: ";
    std::cin >> index;
    if (index > 0 && index <= texts.size()) {
        texts.erase(texts.begin() + index - 1);
        std::cout << "Text deleted." << std::endl;
    } else {
        std::cout << "Invalid index." << std::endl;
    }
}

int main() {
    std::vector<std::string> texts;
    int choice;

    while (true) {
        displayMenu();
        std::cin >> choice;

        switch (choice) {
            case 1:
                addText(texts);
                break;
            case 2:
                viewTexts(texts);
                break;
            case 3:
                deleteText(texts);
                break;
            case 4:
                std::cout << "Exiting..." << std::endl;
                return 0;
            default:
                std::cout << "Invalid option. Please try again." << std::endl;
        }

        std::cout << std::endl;
    }
}