**Data Structure:**

- `contacts`: This variable is a dictionary that serves as the storage for contact information. Dictionaries in Python use key-value pairs, where the `key` (in this case, the name) uniquely identifies the corresponding value (the phone number).

**Functions:**

- `add_contact(name, phone)`: This function takes two arguments, `name` and `phone`. It adds a new contact to the `contacts` dictionary using the provided `name` as the key and the `phone` number as the value. The `print` statement confirms the successful addition.

- `view_contacts()`: This function iterates through all the key-value pairs in the `contacts` dictionary using a `for` loop. Inside the loop, it unpacks each pair into `name` and `phone` variables and prints them in a formatted way, displaying the contact's name and phone number.

**Main Loop:**

- The `while True` loop ensures the program keeps running until the user explicitly exits.

  - **Menu:**
    - Inside the loop, the code displays a menu with three options:
      1. Add Contact
      2. View Contacts
      3. Exit
    - The user is prompted to enter their choice.

  - **Choice Handling:**
    - An `if...elif...else` statement handles the user's choice:
      - If the choice is '1' (Add Contact), the `add_contact` function is called to add a new contact.
      - If the choice is '2' (View Contacts), the `view_contacts` function is called to display all existing contacts.
      - If the choice is '3' (Exit), the loop breaks, terminating the program.
      - Any other choice is considered invalid, and an error message is printed.

**Overall Functionality:**

This code provides a user-friendly way to manage a simple contact list. Users can add new contacts, view all existing contacts, and exit the program when finished.

**Potential Enhancements:**

- **Error Handling:** Implement more robust error handling to gracefully handle situations like invalid user input or duplicate contact names.
- **Search Functionality:** Allow users to search for specific contacts by name or phone number.
- **Delete Functionality:** Enable users to delete existing contacts from the list.
- **Persistence:** Consider saving the contact information to a file (e.g., JSON, CSV) to persist data even after program termination.