import matplotlib.pyplot as plt
import geopandas as gpd
import folium
from shapely.geometry import Point, Polygon
from mpl_toolkits.mplot3d import Axes3D
import numpy as np

class GeologicalMapGenerator:
    def __init__(self):
        self.layers = []

    def import_geological_data(self, filepath):
        data = gpd.read_file(filepath)
        self.layers.append(data)
        return data

    def create_2d_map(self):
        fig, ax = plt.subplots()
        for layer in self.layers:
            layer.plot(ax=ax)
        plt.show()

    def create_3d_map(self):
        fig = plt.figure()
        ax = fig.add_subplot(111, projection='3d')
        for layer in self.layers:
            if 'elevation' in layer.columns:
                x, y = layer.geometry.x, layer.geometry.y
                z = layer['elevation']
                ax.scatter(x, y, z)
            else:
                print("Layer does not contain elevation data for 3D visualization.")
        plt.show()

    def create_interactive_map(self, filepath='map.html'):
        m = folium.Map(location=[0, 0], zoom_start=2)
        for layer in self.layers:
            folium.GeoJson(layer).add_to(m)
        m.save(filepath)

    def add_custom_layer(self, geometry, name):
        custom_layer = gpd.GeoDataFrame({'geometry': [geometry], 'name': [name]})
        self.layers.append(custom_layer)

    def measure_distance(self, point1, point2):
        return point1.distance(point2)

    def measure_area(self, polygon):
        return polygon.area

    def measure_angle(self, point1, point2, point3):
        line1 = np.array([point2.x - point1.x, point2.y - point1.y])
        line2 = np.array([point3.x - point2.x, point3.y - point2.y])
        angle = np.arccos(np.dot(line1, line2) / (np.linalg.norm(line1) * np.linalg.norm(line2)))
        return np.degrees(angle)

    def export_map(self, filepath):
        fig, ax = plt.subplots()
        for layer in self.layers:
            layer.plot(ax=ax)
        plt.savefig(filepath)

if __name__ == '__main__':
    gmg = GeologicalMapGenerator()
    # Example usage
    try:
        gmg.import_geological_data('30_days_of_python\Project_29_Geological_Map_Gennerator\data') 
    except Exception as e:
        print(f"Error importing data: {e}")
    
    # Create 2D Map
    gmg.create_2d_map()
    
    # Create 3D Map
    gmg.create_3d_map()
    
    # Create Interactive Map
    gmg.create_interactive_map('geological_map.html')
    
    # Adding custom layer
    point = Point(1, 1)
    gmg.add_custom_layer(point, 'Custom Point')
    
    # Measure distance
    point1 = Point(0, 0)
    point2 = Point(3, 4)
    print("Distance:", gmg.measure_distance(point1, point2))
    
    # Measure area
    polygon = Polygon([(0, 0), (1, 1), (1, 0)])
    print("Area:", gmg.measure_area(polygon))
    
    # Measure angle
    point3 = Point(1, 1)
    print("Angle:", gmg.measure_angle(point1, point2, point3))
    
    # Export map
    gmg.export_map('geological_map.png')
