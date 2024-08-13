import tkinter as tk
from tkinter.filedialog import askopenfilename, asksaveasfilename

def text_editor():
    def open_file():
        filepath = askopenfilename(
            filetypes=[('Text Files', '*.txt'), ('All Files', '*.*')]
        )

        if not filepath:
            return

        txt_edit.delete(1.0, tk.END)
        with open(filepath, 'r') as input_file:
            text = input_file.read()
            txt_edit.insert(tk.END, text)
        window.title(f'TextEditor - {filepath}')
        status_var.set(f"Opened: {filepath}")

    def save_file():
        filepath = asksaveasfilename(
            defaultextension='txt',
            filetypes=[('Text Files', '*.txt'), ('All Files', '*.*')],
        )

        if not filepath:
            return

        with open(filepath, 'w') as output_file:
            text = txt_edit.get(1.0, tk.END)
            output_file.write(text)
        window.title(f'Text Editor - {filepath}')
        status_var.set(f"Saved: {filepath}")

    def on_enter(event):
        event.widget.config(bg='#0056b3')  # Darker blue on hover

    def on_leave(event):
        event.widget.config(bg='#007bff')  # Original blue color

    window = tk.Tk()
    window.title('Text Editor')
    window.geometry('800x600')
    window.configure(bg='#f0f0f0')  # Light gray background

    # Create a modern font
    font = ('Helvetica', 12)

    # Create and style Text widget with scrollbars
    txt_edit_frame = tk.Frame(window)
    txt_edit_frame.pack(fill=tk.BOTH, expand=True)

    txt_edit = tk.Text(txt_edit_frame, wrap=tk.WORD, font=font, bg='#ffffff', fg='#000000', bd=0, padx=10, pady=10)
    txt_scroll_y = tk.Scrollbar(txt_edit_frame, orient=tk.VERTICAL, command=txt_edit.yview)
    txt_scroll_x = tk.Scrollbar(txt_edit_frame, orient=tk.HORIZONTAL, command=txt_edit.xview)
    
    txt_edit.config(yscrollcommand=txt_scroll_y.set, xscrollcommand=txt_scroll_x.set)

    txt_edit.pack(side=tk.LEFT, fill=tk.BOTH, expand=True)
    txt_scroll_y.pack(side=tk.RIGHT, fill=tk.Y)
    txt_scroll_x.pack(side=tk.BOTTOM, fill=tk.X)

    # Create buttons with improved styling
    fr_buttons = tk.Frame(window, bg='#e0e0e0', pady=10)
    fr_buttons.pack(side=tk.LEFT, fill=tk.Y)

    btn_open = tk.Button(fr_buttons, text='Open', command=open_file, font=font, bg='#007bff', fg='#ffffff', relief=tk.FLAT, padx=10, pady=5)
    btn_save = tk.Button(fr_buttons, text='Save As...', command=save_file, font=font, bg='#28a745', fg='#ffffff', relief=tk.FLAT, padx=10, pady=5)

    btn_open.bind("<Enter>", on_enter)
    btn_open.bind("<Leave>", on_leave)
    btn_save.bind("<Enter>", on_enter)
    btn_save.bind("<Leave>", on_leave)

    btn_open.pack(fill=tk.X, padx=10, pady=5)
    btn_save.pack(fill=tk.X, padx=10, pady=5)

    # Create status bar
    status_var = tk.StringVar()
    status_var.set("Welcome to the Text Editor")
    status_bar = tk.Label(window, textvariable=status_var, bg='#dcdcdc', anchor=tk.W, padx=10, pady=5)
    status_bar.pack(side=tk.BOTTOM, fill=tk.X)

    window.mainloop()

if __name__ == '__main__':
    text_editor()
