import time
import pygame
import tkinter as tk
from tkinter import ttk

class TimeInputWindow:
    def __init__(self):
        self.root = tk.Tk()
        self.root.title("SET Alram")

        # Get current time
        self.current_time = time.localtime()

        # Configure grid
        self.root.columnconfigure(0, weight=1)
        
        # Create frames for each time component
        hours_frame = ttk.LabelFrame(self.root, text="Hours")
        minutes_frame = ttk.LabelFrame(self.root, text="Minutes")
        seconds_frame = ttk.LabelFrame(self.root, text="Seconds")
        period_frame = ttk.LabelFrame(self.root, text="AM/PM")

        hours_frame.grid(row=0, column=0, padx=10, pady=5, sticky="ew")
        minutes_frame.grid(row=1, column=0, padx=10, pady=5, sticky="ew")
        seconds_frame.grid(row=2, column=0, padx=10, pady=5, sticky="ew")
        period_frame.grid(row=3, column=0, padx=10, pady=5, sticky="ew")

        # Hours
        self.hours = tk.IntVar(value=time.strftime("%I", self.current_time))
        hours_spin = ttk.Spinbox(hours_frame, from_=1, to=12, wrap=True, textvariable=self.hours)
        hours_spin.pack()

        # Minutes
        self.minutes = tk.IntVar(value=time.strftime("%M", self.current_time))
        minutes_spin = ttk.Spinbox(minutes_frame, from_=0, to=59, wrap=True, textvariable=self.minutes)
        minutes_spin.pack()

        # Seconds
        self.seconds = tk.IntVar(value=0)
        seconds_spin = ttk.Spinbox(seconds_frame, from_=0, to=59, wrap=True, textvariable=self.seconds)
        seconds_spin.pack()

        # AM/PM
        self.period = tk.StringVar(value=time.strftime("%p", self.current_time))
        period_spin = ttk.Spinbox(period_frame, values=("AM", "PM"), wrap=True, textvariable=self.period)
        period_spin.pack()

        # Submit button
        submit_button = ttk.Button(self.root, text="Submit", command=self.submit_time)
        submit_button.grid(row=4, column=0, pady=10)
        self.root.mainloop()
        

    

    def submit_time(self):
        # Get the time input
        time_str = f"{self.hours.get():02}:{self.minutes.get():02}:{self.seconds.get():02} {self.period.get()}"
        print("Time entered:", time_str)
        self.root.destroy()
        self.set_alarm(time_str)

    def set_alarm(self,alarm_time):
        while True:
            
            # Get current time
            self.current_time = time.localtime()
            # Format time to AM/PM
            formatted_time = time.strftime("%I:%M:%S %p", self.current_time)
            
            # print(formatted_time,alarm_time)

            if formatted_time == alarm_time:
                print("Time to wake up!",formatted_time)
                self.alarm()
                break
            time.sleep(1)
            
    def alarm(self):
        self.root = tk.Tk()
        self.root.title("Wake Up App")

        pygame.mixer.init()
        self.sound = pygame.mixer.Sound(r"Project_20_Alarm_clock\alarm.mp3")
         

        # Configure the grid
        self.root.columnconfigure(0, weight=1)
        self.root.rowconfigure(0, weight=1)
        self.root.rowconfigure(1, weight=1)

        # Create a frame for the content
        frame = ttk.Frame(self.root, padding="10")
        frame.grid(sticky=(tk.W, tk.E, tk.N, tk.S))

        # Text in the middle
        label = ttk.Label(frame, text="Wake Up!", anchor="center", font=("Arial", 24))
        label.grid(row=0, column=0, pady=20)

        # Stop button below
        stop_button = ttk.Button(frame, text="Stop", command=self.stop)
        stop_button.grid(row=1, column=0, pady=20)

        def play_sound():
            self.sound.play(-1)
        self.root.after(100, play_sound)
        
        self.root.mainloop()
        

    def stop(self):
        self.sound.stop()
        self.root.destroy()

app = TimeInputWindow()
