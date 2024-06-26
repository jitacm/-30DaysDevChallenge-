library = []

def add_book(title, author):
    library.append({'title': title, 'author': author})
    print(f"Book '{title}' by {author} added.")

def view_books():
    if not library:
        print("No books in the library.")
    else:
        for idx, book in enumerate(library, 1):
            print(f"{idx}. {book['title']} by {book['author']}")

def remove_book(index):
    if 0 < index <= len(library):
        removed_book = library.pop(index - 1)
        print(f"Removed book '{removed_book['title']}' by {removed_book['author']}")
    else:
        print("Invalid index")

while True:
    print("\nLibrary Management System")
    print("1. Add Book")
    print("2. View Books")
    print("3. Remove Book")
    print("4. Exit")
    choice = input("Enter choice: ")

    if choice == '1':
        title = input("Enter book title: ")
        author = input("Enter book author: ")
        add_book(title, author)
    elif choice == '2':
        view_books()
    elif choice == '3':
        index = int(input("Enter book number to remove: "))
        remove_book(index)
    elif choice == '4':
        break
    else:
        print("Invalid choice")
