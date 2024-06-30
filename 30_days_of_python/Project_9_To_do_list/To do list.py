todo_list = []

def show_tasks():
    if len(todo_list) == 0:
        print("No tasks in the list.")
    else:
        for idx, task in enumerate(todo_list, 1):
            print(f"{idx}. {task}")

def add_task(task):
    todo_list.append(task)
    print(f"Task '{task}' added.")

def remove_task(task_number):
    if 0 < task_number <= len(todo_list):
        removed_task = todo_list.pop(task_number - 1)
        print(f"Task '{removed_task}' removed.")
    else:
        print("Invalid task number.")

while True:
    print("\nTo-Do List:")
    show_tasks()
    print("\nOptions: 1. Add Task  2. Remove Task  3. Exit")
    choice = input("Enter choice: ")

    if choice == '1':
        task = input("Enter the task: ")
        add_task(task)
    elif choice == '2':
        task_number = int(input("Enter task number to remove: "))
        remove_task(task_number)
    elif choice == '3':
        break
    else:
        print("Invalid choice")
