**Network Monitoring Tool**

This Python script is a simple yet effective network monitoring tool that displays real-time data usage. It shows the amount of data received and sent over the network, along with the data transfer rates (receiving and sending speeds). The script leverages the psutil library to retrieve network statistics and PrettyTable to format the output into a well-organized table.

**Features:**

Real-Time Monitoring: Updates every second to provide real-time statistics.
Data Usage: Displays total bytes received and sent.
Transfer Rates: Shows data transfer rates for both receiving and sending.
Requirements:
•psutil (Install via pip install psutil)
•PrettyTable (Install via pip install prettytable)

**How to Run:**
•Ensure all dependencies are installed.
•Run the script using python network_monitor.py.
•The script will output a table in the terminal, showing the network statistics in real-time.