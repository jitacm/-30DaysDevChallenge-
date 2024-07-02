import random
import string

url_mapping = {}

def shorten_url(url):
    short_url = ''.join(random.choices(string.ascii_letters + string.digits, k=6))
    url_mapping[short_url] = url
    return short_url

def get_original_url(short_url):
    return url_mapping.get(short_url)

url = input("Enter the URL to shorten: ")
short_url = shorten_url(url)
print(f"Short URL: {short_url}")

short_url_input = input("Enter the short URL to get the original URL: ")
original_url = get_original_url(short_url_input)
print(f"Original URL: {original_url}")
