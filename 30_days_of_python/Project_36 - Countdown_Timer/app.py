import time
import threading
import tkinter as tk
from tkinter import messagebox

class CountdownTimer:
    def __init__(self, root):
        self.root = root
        self.root.title("Countdown Timer")
        
        # Create UI elements
        self.label = tk.Label(root, text="Enter time in seconds:", font=("Helvetica", 14))
        self.label.pack(pady=10)
        
        self.entry = tk.Entry(root, font=("Helvetica", 14))
        self.entry.pack(pady=10)
        
        self.start_button = tk.Button(root, text="Start", command=self.start_countdown, font=("Helvetica", 12))
        self.start_button.pack(pady=10)
        
        self.pause_button = tk.Button(root, text="Pause", state=tk.DISABLED, command=self.pause_countdown, font=("Helvetica", 12))
        self.pause_button.pack(pady=10)
        
        self.resume_button = tk.Button(root, text="Resume", state=tk.DISABLED, command=self.resume_countdown, font=("Helvetica", 12))
        self.resume_button.pack(pady=10)
        
        self.quit_button = tk.Button(root, text="Quit", command=self.quit_countdown, font=("Helvetica", 12))
        self.quit_button.pack(pady=10)
        
        self.timer_label = tk.Label(root, text="", font=("Helvetica", 14))
        self.timer_label.pack(pady=20)
        
        self.paused = False
        self.running = False
    
    def start_countdown(self):
        try:
            t = int(self.entry.get())
            if t > 0:
                self.total_time = t
                self.remaining_time = t
                self.end_message = "Time's up!"
                
                self.start_button.config(state=tk.DISABLED)
                self.pause_button.config(state=tk.NORMAL)
                self.running = True
                
                self.update_timer()
        except ValueError:
            messagebox.showerror("Invalid input", "Please enter a positive integer.")
    
    def update_timer(self):
        if self.running and not self.paused and self.remaining_time > 0:
            mins, secs = divmod(self.remaining_time, 60)
            timer = f'{mins:02d}:{secs:02d}'
            
            progress = int((self.total_time - self.remaining_time) / self.total_time * 30)
            progress_bar = '█' * progress + '-' * (30 - progress)
            
            self.timer_label.config(text=f"{timer} [{progress_bar}] Remaining")
            self.remaining_time -= 1
            
            self.root.after(1000, self.update_timer)
        elif self.remaining_time == 0:
            self.timer_label.config(text=self.end_message)
            self.play_sound()
            self.reset_buttons()
    
    def pause_countdown(self):
        self.paused = True
        self.pause_button.config(state=tk.DISABLED)
        self.resume_button.config(state=tk.NORMAL)
    
    def resume_countdown(self):
        self.paused = False
        self.resume_button.config(state=tk.DISABLED)
        self.pause_button.config(state=tk.NORMAL)
        self.update_timer()
    
    def quit_countdown(self):
        self.running = False
        self.root.quit()
    
    def reset_buttons(self):
        self.start_button.config(state=tk.NORMAL)
        self.pause_button.config(state=tk.DISABLED)
        self.resume_button.config(state=tk.DISABLED)
    
    def play_sound(self):
        try:
            import winsound
            winsound.Beep(1000, 1000)
        except ImportError:
            messagebox.showinfo("Notification", "Time's up!")


if __name__ == "__main__":
    root = tk.Tk()
    app = CountdownTimer(root)
    root.mainloop()
