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

def search_books():
    query = input("Enter title or author to search: ").lower()
    found = False
    for book in library:
        if query in book['title'].lower() or query in book['author'].lower():
            print(f"Found: {book['title']} by {book['author']}")
            found = True
    if not found:
        print("No books found.")

def sort_books():
    sort_by = input("Sort by title or author? ").lower()
    if sort_by in ['title', 'author']:
        library.sort(key=lambda book: book[sort_by].lower())
        print(f"Books sorted by {sort_by}.")
        view_books()
    else:
        print("Invalid sort option")

while True:
    print("\nLibrary Management System")
    print("1. Add Book")
    print("2. View Books")
    print("3. Remove Book")
    print("4. Search Books")
    print("5. Sort Books")
    print("6. Exit")
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
        search_books()
    elif choice == '5':
        sort_books()
    elif choice == '6':
        break
    else:
        print("Invalid choice")
