import os
import time
import psutil
from prettytable import PrettyTable
from prettytable import DOUBLE_BORDER


size = ['bytes', 'KB', 'MB', 'GB', 'TB']


def getSize(bytes):
    for unit in size:
        if bytes < 1024:
            return f"{bytes:.1f}{unit}"
        bytes /= 1024


def printData():
    
    card = PrettyTable()
    card.set_style(DOUBLE_BORDER)
    
    card.field_names = ["Received", "Receiving", "Sent", 'Sending']
    
    card.add_row([f"{getSize(netStats2.bytes_recv)}", 
    f"{getSize(downloadStat)}/s", f"{getSize(netStats2.bytes_sent)}", 
    f"{getSize(uploadStat)}/s"])
    print(card)


netStats1 = psutil.net_io_counters()


dataSent = netStats1.bytes_sent
dataRecv = netStats1.bytes_recv


while True:
   
    time.sleep(1)

    
    os.system('clear')

   
    netStats2 = psutil.net_io_counters()

  
    uploadStat = netStats2.bytes_sent - dataSent

    downloadStat = netStats2.bytes_recv - dataRecv

    # Print the Data
    printData()

    
    dataSent = netStats2.bytes_sent
    dataRecv = netStats2.bytes_recv