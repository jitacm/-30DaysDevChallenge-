import qrcode
from PIL import Image

def generate_qr_code(data, fill_color='black', back_color='white'):
    qr = qrcode.QRCode(
        version=1,
        error_correction=qrcode.constants.ERROR_CORRECT_L,
        box_size=10,
        border=4,
    )
    qr.add_data(data)
    qr.make(fit=True)

    # Create an RGB image for the QR code
    img = qr.make_image(fill_color=fill_color, back_color=back_color).convert('RGB')
    img.save("qrcode.png")

data = input("Enter the data for the QR code: ")
fill_color = input("Enter the QR code fill color (e.g., 'black'): ")
back_color = input("Enter the QR code background color (e.g., 'white'): ")

generate_qr_code(data, fill_color, back_color)
print("QR code generated and saved as qrcode.png")