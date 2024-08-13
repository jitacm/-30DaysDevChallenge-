import urllib.request
import tkinter as tk
from tkinter import messagebox
from urllib.error import HTTPError, URLError

def test_connectivity():
    # Initialize the main window
    window = tk.Tk()
    window.geometry('800x600')
    window.title('Website Connectivity Checker')

    # Header
    head = tk.Label(window, text='Website Connectivity Checker', font=('Calibri', 18, 'bold'))
    head.pack(pady=20)

    # Instruction Label
    instructions = tk.Label(window, text='Enter the website URL below and click "Check" to verify connectivity.',
                             font=('Calibri', 12), fg='gray')
    instructions.pack(pady=10)

    # URL Entry Field
    url_label = tk.Label(window, text='Website URL:', font=('Calibri', 12))
    url_label.pack(pady=5)
    url = tk.StringVar()
    url_entry = tk.Entry(window, textvariable=url, font=('Calibri', 12), width=50)
    url_entry.pack(pady=5)

    # Status Message Mapping
    status_messages = {
        200: 'Website Available (200)',
        301: 'Website Moved Permanently (301)',
        302: 'Website Found (Redirect) (302)',
        404: 'Website Not Found (404)',
        500: 'Server Error (500)',
        403: 'Forbidden (403)',
        401: 'Unauthorized (401)',
        503: 'Service Unavailable (503)',
        408: 'Request Timeout (408)',
        502: 'Bad Gateway (502)'
    }

    # Result Label
    result_label = tk.Label(window, text='', font=('Calibri', 15, 'bold'), fg='blue')
    result_label.pack(pady=20)

    # History of status checks
    history_frame = tk.Frame(window)
    history_frame.pack(pady=10)

    history_label = tk.Label(history_frame, text='Status History:', font=('Calibri', 12, 'underline'))
    history_label.grid(row=0, column=0, sticky='w')

    history_text = tk.Text(history_frame, font=('Calibri', 12), height=8, width=70, state='disabled')
    history_text.grid(row=1, column=0, pady=10)

    # Function to check the URL
    def check_url():
        web = url.get()
        if not web.startswith(('http://', 'https://')):
            messagebox.showwarning('Invalid URL', 'Please enter a valid URL starting with http:// or https://')
            return

        try:
            response = urllib.request.urlopen(web)
            status_code = response.getcode()
            status_message = status_messages.get(status_code, f'Website returned status code: {status_code}')
        except HTTPError as e:
            status_message = f'HTTP Error: {e.code}'
        except URLError as e:
            status_message = f'URL Error: {e.reason}'
        except Exception as e:
            status_message = f'Error: {str(e)}'

        # Display the result
        result_label.config(text=status_message)
        update_history(web, status_message)

    # Function to update the history of status checks
    def update_history(web, status_message):
        history_text.config(state='normal')
        history_text.insert(tk.END, f'URL: {web}\nStatus: {status_message}\n\n')
        history_text.config(state='disabled')
        history_text.see(tk.END)

    # Reset function
    def reset_fields():
        url.set('')
        result_label.config(text='')
        history_text.config(state='normal')
        history_text.delete(1.0, tk.END)
        history_text.config(state='disabled')

    # Add Check Button
    check_button = tk.Button(window, text='Check', font=('Calibri', 12), command=check_url, bg='#4CAF50', fg='white', padx=10, pady=5)
    check_button.pack(pady=10)

    # Add Reset Button
    reset_button = tk.Button(window, text='Reset', font=('Calibri', 12), command=reset_fields, bg='#f44336', fg='white', padx=10, pady=5)
    reset_button.pack(pady=5)

    # Enhanced UI: Add History Export Button
    def export_history():
        with open('history.txt', 'w') as file:
            history_text.config(state='normal')
            file.write(history_text.get(1.0, tk.END))
            history_text.config(state='disabled')
        messagebox.showinfo('Export Successful', 'History has been exported to history.txt')

    export_button = tk.Button(window, text='Export History', font=('Calibri', 12), command=export_history, bg='#2196F3', fg='white', padx=10, pady=5)
    export_button.pack(pady=5)

    # Enhanced UI: Add Detailed Status Description
    def show_status_info():
        info = (
            "Status Codes Explained:\n"
            "200 - OK: The request was successful.\n"
            "301 - Moved Permanently: The resource has been moved to a different URL.\n"
            "302 - Found: The resource has been temporarily moved to a different URL.\n"
            "403 - Forbidden: The server understands the request, but refuses to authorize it.\n"
            "404 - Not Found: The server can't find the requested resource.\n"
            "500 - Internal Server Error: The server encountered an unexpected condition.\n"
            "503 - Service Unavailable: The server is not ready to handle the request.\n"
        )
        messagebox.showinfo('Status Information', info)

    status_info_button = tk.Button(window, text='Status Info', font=('Calibri', 12), command=show_status_info, bg='#FFC107', fg='black', padx=10, pady=5)
    status_info_button.pack(pady=5)

    # Footer
    footer = tk.Label(window, text='Developed by Your Name', font=('Calibri', 10), fg='gray')
    footer.pack(side=tk.BOTTOM, pady=20)

    # Run the main loop
    window.mainloop()

if __name__ == '__main__':
    test_connectivity()
