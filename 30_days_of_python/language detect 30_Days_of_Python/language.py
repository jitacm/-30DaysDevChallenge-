from langdetect import detect
import tkinter as tk


def detect_lang():
   window = tk.Tk()
   window.geometry('600x400')
   head = tk.Label(window, text='Language Detector', font=('Calibri 15'))
   head.pack(pady=20)

   def check_language():
       new_text = text.get()
       lang = detect(str(new_text))
       tk.Label(window, text=lang, font=('Calibri 15')).place(x=260, y=200)

   text = tk.StringVar()
   tk.Entry(window, textvariable=text).place(
       x=200, y=80, height=30, width=280)
   tk.Button(window, text='Check Language',
             command=check_language).place(x=285, y=150)
   window.mainloop()


if __name__ == '__main__':
   detect_lang()