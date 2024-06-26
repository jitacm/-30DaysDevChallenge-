import time
import datetime
import winsound

def set_alarm(alarm_time):
    while True:
        current_time = datetime.datetime.now().strftime("%H:%M:%S")
        if current_time == alarm_time:
            print("Time to wake up!")
            winsound.PlaySound("sound.wav", winsound.SND_FILENAME)
            break
        time.sleep(1)

alarm_time = input("Enter the alarm time (HH:MM:SS): ")
set_alarm(alarm_time)