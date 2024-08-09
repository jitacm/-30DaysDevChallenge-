import tkinter as tk
from tkinter import messagebox

class StackGUI:
    def __init__(self, root):
        self.root = root
        self.root.title("Stack Demonstration")
        
        self.left_frame = tk.Frame(self.root)
        self.canvas = tk.Canvas(root, width=200, height=300, bg='white')
        self.left_frame.pack(side='left', padx=20, pady=20)
        self.canvas.pack()
        
        self.stack = []
        self.stack_height = 0
        self.stack_top = 300

        self.lable=tk.Label(self.left_frame,text="Enter your input here")
        self.lable.pack(pady=3)
        self.entry1 = tk.Entry(self.left_frame)
        self.entry1.pack()
        
        # Buttons for operations
        self.push_button = tk.Button(self.left_frame, text="Push", command=self.push)
        self.push_button.pack(side='left', padx=10, pady=10)
        
        self.pop_button = tk.Button(self.left_frame, text="Pop", command=self.pop)
        self.pop_button.pack(side='right', padx=10, pady=10)

    def push(self):
        value = self.entry1.get()
        if value!='':
            # print(f".{value}.")
            if self.stack_height < 280:
                self.box = self.canvas.create_rectangle(60, 0, 140, 20, fill="blue")
                self.text = self.canvas.create_text(100, 10, text=f"{value}", fill="white")
                self.push_animate()
                self.stack.append((self.box, self.text))
                self.stack_height += 20
                self.stack_top -= 20
                self.disable_push_button()
            else:
                messagebox.showinfo("Notification", "stack is full!")
        else:
            messagebox.showinfo("Notification", "add your entry")

    def push_animate(self):
        x1, y1, x2, y2 = self.canvas.coords(self.box)

        if y2 < self.stack_top + 20:
            self.canvas.move(self.box, 0, 5)
            self.canvas.move(self.text, 0, 5)
            self.root.after(50, self.push_animate)

    def disable_push_button(self):
        self.push_button.config(state=tk.DISABLED)
        self.root.after(3500-(len(self.stack)*100), self.enable_push_button)

    def enable_push_button(self):
        self.push_button.config(state=tk.NORMAL)

    def pop(self):
        if self.stack:
            box, text = self.stack.pop()
            self.canvas.delete(box)
            self.canvas.delete(text)
            self.stack_height -= 20
            self.stack_top += 20
        else:
            messagebox.showinfo("Notification", "stack is empty")

root = tk.Tk()
app = StackGUI(root)
root.mainloop()
