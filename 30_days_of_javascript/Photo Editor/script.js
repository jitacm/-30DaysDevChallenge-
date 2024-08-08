window.onload = function () {
    const editor = document.getElementById("editor");
    const context = editor.getContext("2d");
    const toolbar = document.getElementById("toolbar");
    const resizeSidebar = document.getElementById("resizeSidebar");
    const widthInput = document.getElementById("widthInput");
    const heightInput = document.getElementById("heightInput");
    const applyResize = document.getElementById("applyResize");

    let history = [];
    let historyStep = -1;

    const tools = {
        "upload": function () {
            const upload = document.createElement('input');
            upload.type = "file";
            upload.click();
            upload.onchange = function () {
                const img = new Image();
                img.onload = () => {
                    editor.width = img.width;
                    editor.height = img.height;
                    context.drawImage(img, 0, 0);
                    saveHistory();
                };
                img.onerror = () => {
                    console.error("The provided file couldn't be loaded as an Image media");
                };
                img.src = URL.createObjectURL(this.files[0]);
            };
        },
        "save": function () {
            const image = editor.toDataURL();
            const link = document.createElement('a');
            link.download = 'image.png';
            link.href = image;
            link.click();
        },
        "flipHor": function () {
            flipImage(true);
        },
        "flipVert": function () {
            flipImage(false);
        },
        "rotateL": function () {
            rotateImage(false);
        },
        "rotateR": function () {
            rotateImage(true);
        },
        "resize": function () {
            console.log('Resize button clicked');
            widthInput.value = editor.width;
            heightInput.value = editor.height;
            resizeSidebar.style.display = 'block';
        },
        "greyscale": function () {
            applyFilter((pixel) => {
                let shade = Math.floor(0.3 * pixel[0] + 0.59 * pixel[1] + 0.11 * pixel[2]);
                return [shade, shade, shade, pixel[3]];
            });
        },
        "adjustBrightness": function () {
            let brightness = parseFloat(prompt("Enter brightness level (0.0 - 2.0)", "1"));
            if (isNaN(brightness) || brightness < 0 || brightness > 2) {
                alert("Invalid brightness level.");
                return;
            }

            applyFilter((pixel) => {
                return pixel.map((value, index) => index < 3 ? Math.min(255, value * brightness) : value);
            });
        },
        "crop": function () {
            let startX = parseInt(prompt("Enter starting X coordinate", "0"));
            let startY = parseInt(prompt("Enter starting Y coordinate", "0"));
            let width = parseInt(prompt("Enter width of the crop area", "100"));
            let height = parseInt(prompt("Enter height of the crop area", "100"));

            let imageData = context.getImageData(startX, startY, width, height);
            editor.width = width;
            editor.height = height;
            context.putImageData(imageData, 0, 0);
            saveHistory();
        },
        "addText": function () {
            let text = prompt("Enter text to add", "Sample Text");
            if (!text) return;
            let x = parseInt(prompt("Enter X coordinate", "10"));
            let y = parseInt(prompt("Enter Y coordinate", "50"));
            let fontSize = parseInt(prompt("Enter font size", "30"));
            let color = prompt("Enter text color", "#000000");

            context.font = `${fontSize}px Arial`;
            context.fillStyle = color;
            context.fillText(text, x, y);
            saveHistory();
        },
        "undo": function () {
            if (historyStep > 0) {
                historyStep--;
                context.putImageData(history[historyStep], 0, 0);
            }
        },
        "redo": function () {
            if (historyStep < history.length - 1) {
                historyStep++;
                context.putImageData(history[historyStep], 0, 0);
            }
        },
        "importURL": function () {
            let url = prompt("Enter image URL");
            if (url) {
                const img = new Image();
                img.crossOrigin = "Anonymous";
                img.onload = () => {
                    editor.width = img.width;
                    editor.height = img.height;
                    context.drawImage(img, 0, 0);
                    saveHistory();
                };
                img.src = url;
            }
        },
        "addWatermark": function () {
            const watermarkText = "Sample Watermark";
            context.font = "30px Arial";
            context.fillStyle = "rgba(255, 255, 255, 0.5)";
            context.fillText(watermarkText, editor.width - 200, editor.height - 30);
            saveHistory();
        }
    };

    toolbar.addEventListener("click", (event) => {
        const id = event.target.id;
        if (id in tools) {
            console.log(`${id} tool selected`);
            tools[id]();
        }
    });

    applyResize.addEventListener("click", () => {
        const newWidth = parseInt(widthInput.value);
        const newHeight = parseInt(heightInput.value);
        if (newWidth && newHeight) {
            const imageData = context.getImageData(0, 0, editor.width, editor.height);
            const offscreenCanvas = document.createElement("canvas");
            offscreenCanvas.width = newWidth;
            offscreenCanvas.height = newHeight;
            const offscreenContext = offscreenCanvas.getContext("2d");
            offscreenContext.putImageData(imageData, 0, 0);
            editor.width = newWidth;
            editor.height = newHeight;
            context.drawImage(offscreenCanvas, 0, 0, newWidth, newHeight);
            saveHistory();
            resizeSidebar.style.display = 'none';
        } else {
            alert("Invalid dimensions");
        }
    });

    function saveHistory() {
        history = history.slice(0, historyStep + 1);
        history.push(context.getImageData(0, 0, editor.width, editor.height));
        historyStep++;
    }

    function flipImage(horizontal = true) {
        const imageData = context.getImageData(0, 0, editor.width, editor.height);
        const newData = context.createImageData(imageData);

        for (let y = 0; y < imageData.height; y++) {
            for (let x = 0; x < imageData.width; x++) {
                let newX = horizontal ? imageData.width - x - 1 : x;
                let newY = horizontal ? y : imageData.height - y - 1;

                let index = (x + y * imageData.width) * 4;
                let newIndex = (newX + newY * imageData.width) * 4;

                newData.data[newIndex] = imageData.data[index];
                newData.data[newIndex + 1] = imageData.data[index + 1];
                newData.data[newIndex + 2] = imageData.data[index + 2];
                newData.data[newIndex + 3] = imageData.data[index + 3];
            }
        }

        context.putImageData(newData, 0, 0);
        saveHistory();
    }

    function rotateImage(clockwise = true) {
        const imageData = context.getImageData(0, 0, editor.width, editor.height);
        const offscreenCanvas = document.createElement("canvas");
        offscreenCanvas.width = editor.height;
        offscreenCanvas.height = editor.width;
        const offscreenContext = offscreenCanvas.getContext("2d");

        offscreenContext.translate(offscreenCanvas.width / 2, offscreenCanvas.height / 2);
        offscreenContext.rotate((clockwise ? 1 : -1) * Math.PI / 2);
        offscreenContext.drawImage(editor, -editor.width / 2, -editor.height / 2);

        editor.width = offscreenCanvas.width;
        editor.height = offscreenCanvas.height;
        context.drawImage(offscreenCanvas, 0, 0);
        saveHistory();
    }

    function applyFilter(filterFunction) {
        const imageData = context.getImageData(0, 0, editor.width, editor.height);
        const data = imageData.data;

        for (let i = 0; i < data.length; i += 4) {
            const pixel = filterFunction([data[i], data[i + 1], data[i + 2], data[i + 3]]);
            data[i] = pixel[0];
            data[i + 1] = pixel[1];
            data[i + 2] = pixel[2];
            data[i + 3] = pixel[3];
        }

        context.putImageData(imageData, 0, 0);
        saveHistory();
    }
};
