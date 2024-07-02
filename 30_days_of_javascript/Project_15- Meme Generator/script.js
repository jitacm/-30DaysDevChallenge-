document.getElementById('generateMeme').addEventListener('click', function() {
    const text = document.getElementById('textInput').value;
    const memeContainer = document.getElementById('memeContainer');
    
    const meme = document.createElement('img');
    meme.classList.add('meme');
    meme.src = `https://api.memegen.link/images/custom/${encodeURIComponent(text)}.png`;
    
    memeContainer.innerHTML = '';
    memeContainer.appendChild(meme);
});
