import tkinter as tk
from tkinter import filedialog
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

class DataVisualizationApp:
    def __init__(self, root):
        self.root = root
        self.root.title("Data Visualization Software")

        self.filepath = None
        self.data = None

        self.create_widgets()

    def create_widgets(self):
        self.load_button = tk.Button(self.root, text="Load Data", command=self.load_data)
        self.load_button.pack()

        self.plot_type_var = tk.StringVar(self.root)
        self.plot_type_var.set("Line Plot")
        self.plot_type_menu = tk.OptionMenu(self.root, self.plot_type_var, "Line Plot", "Bar Chart", "Scatter Plot", "Histogram", "Heatmap", "Pie Chart")
        self.plot_type_menu.pack()

        self.plot_button = tk.Button(self.root, text="Generate Plot", command=self.generate_plot)
        self.plot_button.pack()

    def load_data(self):
        self.filepath = filedialog.askopenfilename(filetypes=[("CSV files", "*.csv"), ("Excel files", "*.xlsx"), ("JSON files", "*.json")])
        if self.filepath:
            if self.filepath.endswith('.csv'):
                self.data = pd.read_csv(self.filepath)
            elif self.filepath.endswith('.xlsx'):
                self.data = pd.read_excel(self.filepath)
            elif self.filepath.endswith('.json'):
                self.data = pd.read_json(self.filepath)
            print(f"Data loaded from {self.filepath}")

    def generate_plot(self):
        if self.data is None:
            print("No data loaded.")
            return

        plot_type = self.plot_type_var.get()

        if plot_type == "Line Plot":
            self.line_plot()
        elif plot_type == "Bar Chart":
            self.bar_chart()
        elif plot_type == "Scatter Plot":
            self.scatter_plot()
        elif plot_type == "Histogram":
            self.histogram()
        elif plot_type == "Heatmap":
            self.heatmap()
        elif plot_type == "Pie Chart":
            self.pie_chart()

    def line_plot(self):
        plt.figure(figsize=(10, 6))
        for column in self.data.select_dtypes(include='number').columns:
            sns.lineplot(data=self.data, x=self.data.index, y=column, label=column)
        plt.title("Line Plot")
        plt.show()

    def bar_chart(self):
        plt.figure(figsize=(10, 6))
        for column in self.data.select_dtypes(include='number').columns:
            sns.barplot(data=self.data, x=self.data.index, y=column)
        plt.title("Bar Chart")
        plt.show()

    def scatter_plot(self):
        plt.figure(figsize=(10, 6))
        numeric_columns = self.data.select_dtypes(include='number').columns
        if len(numeric_columns) >= 2:
            sns.scatterplot(data=self.data, x=numeric_columns[0], y=numeric_columns[1])
            plt.title("Scatter Plot")
            plt.show()
        else:
            print("Not enough numeric columns for scatter plot.")

    def histogram(self):
        plt.figure(figsize=(10, 6))
        for column in self.data.select_dtypes(include='number').columns:
            sns.histplot(self.data[column], kde=True)
        plt.title("Histogram")
        plt.show()

    def heatmap(self):
        plt.figure(figsize=(10, 6))
        sns.heatmap(self.data.corr(), annot=True, cmap='coolwarm')
        plt.title("Heatmap")
        plt.show()

    def pie_chart(self):
        plt.figure(figsize=(10, 6))
        column = self.data.select_dtypes(include='number').columns[0]
        self.data[column].value_counts().plot.pie(autopct='%1.1f%%')
        plt.title("Pie Chart")
        plt.show()

if __name__ == "__main__":
    root = tk.Tk()
    app = DataVisualizationApp(root)
    root.mainloop()
