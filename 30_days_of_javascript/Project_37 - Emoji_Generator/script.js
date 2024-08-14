function emojiCallFunction(callback) {
    fetch("emojis.json")
        .then((response) => {
            if (!response.ok) {
                throw new Error("Emoji Fetch Failed");
            }
            return response.json();
        })
        .then((data) => {
            callback(data);
        })
        .catch((error) => {
            console.error("Error Occurred:", error);
            callback({});
        });
}

function randomEmoji(emojiData) {
    const randomButton = document.getElementById("generate-random-btn");
    randomButton.addEventListener("click", () => {
        emojiShow(emojiData);
    });
}

function emojiShow(emojiData) {
    const feelings = Object.keys(emojiData);
    const randomFeeling = feelings[Math.floor(Math.random() * feelings.length)];
    const emojis = emojiData[randomFeeling];
    if (emojis && emojis.length > 0) {
        const randomEmoji = emojis[Math.floor(Math.random() * emojis.length)];
        previewEmoji(randomFeeling, randomEmoji.emoji);
    }
}

function previewEmoji(feeling, emoji) {
    document.getElementById("emoji-name").textContent = feeling;
    document.getElementById("emoji").textContent = emoji;
}

function defaultEmoji() {
    emojiCallFunction(function (emojiData) {
        document.querySelectorAll(".feeling-button").forEach((button) => {
            button.addEventListener("click", () => {
                const feeling = button.dataset.feeling;
                const emojis = emojiData[feeling];
                if (emojis && emojis.length > 0) {
                    const randomEmoji = emojis[Math.floor(Math.random() * emojis.length)];
                    previewEmoji(feeling, randomEmoji.emoji);
                }
            });
        });
        randomEmoji(emojiData);
        emojiShow(emojiData);
    });
}

defaultEmoji();
