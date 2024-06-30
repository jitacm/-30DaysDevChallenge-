contacts = {}

def add_contact(name, phone):
    contacts[name] = phone
    print(f"Contact {name} added.")

def view_contacts():
    for name, phone in contacts.items():
        print(f"{name}: {phone}")

while True:
    print("\nContact Book")
    print("1. Add Contact")
    print("2. View Contacts")
    print("3. Exit")
    choice = input("Enter choice: ")

    if choice == '1':
        name = input("Enter name: ")
        phone = input("Enter phone number: ")
        add_contact(name, phone)
    elif choice == '2':
        view_contacts()
    elif choice == '3':
        break
    else:
        print("Invalid choice")
