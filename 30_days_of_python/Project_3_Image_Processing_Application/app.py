import cv2
import numpy as np
from tkinter import *
from tkinter import filedialog
from PIL import Image, ImageTk

# Function to apply filters
def apply_filter(img, filter_type):
    if filter_type == 'Gray':
        return cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    elif filter_type == 'Blur':
        return cv2.GaussianBlur(img, (15, 15), 0)
    elif filter_type == 'Edge Detection':
        return cv2.Canny(img, 100, 200)
    elif filter_type == 'Sepia':
        kernel = np.array([[0.272, 0.534, 0.131],
                           [0.349, 0.686, 0.168],
                           [0.393, 0.769, 0.189]])
        return cv2.transform(img, kernel)
    elif filter_type == 'Pencil Sketch':
        gray, sketch = cv2.pencilSketch(img, sigma_s=60, sigma_r=0.07, shade_factor=0.05)
        return sketch
    else:
        return img

# Function to open an image file
def open_image():
    global img, img_display
    filepath = filedialog.askopenfilename()
    if filepath:
        img = cv2.imread(filepath)
        img_rgb = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
        img_pil = Image.fromarray(img_rgb)
        img_tk = ImageTk.PhotoImage(img_pil)
        img_label.config(image=img_tk)
        img_label.image = img_tk

# Function to save the processed image
def save_image():
    if img is not None:
        savepath = filedialog.asksaveasfilename(defaultextension=".png")
        if savepath:
            cv2.imwrite(savepath, img)

# Function to apply the selected filter
def apply_selected_filter():
    global img, img_display
    if img is not None:
        filter_type = filter_var.get()
        processed_img = apply_filter(img, filter_type)
        img_rgb = cv2.cvtColor(processed_img, cv2.COLOR_BGR2RGB) if len(processed_img.shape) == 3 else processed_img
        img_pil = Image.fromarray(img_rgb)
        img_tk = ImageTk.PhotoImage(img_pil)
        img_label.config(image=img_tk)
        img_label.image = img_tk

# Initialize Tkinter window
root = Tk()
root.title("Smart Image Filter")

# Initialize global variables
img = None
img_display = None

# UI Elements
btn_open = Button(root, text="Open Image", command=open_image)
btn_open.pack()

filter_var = StringVar(value="None")
filter_options = ["None", "Gray", "Blur", "Edge Detection", "Sepia", "Pencil Sketch"]
filter_menu = OptionMenu(root, filter_var, *filter_options)
filter_menu.pack()

btn_apply = Button(root, text="Apply Filter", command=apply_selected_filter)
btn_apply.pack()

btn_save = Button(root, text="Save Image", command=save_image)
btn_save.pack()

img_label = Label(root)
img_label.pack()

# Run the Tkinter event loop
root.mainloop()
