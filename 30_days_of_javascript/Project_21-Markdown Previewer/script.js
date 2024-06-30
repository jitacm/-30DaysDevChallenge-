// script.js
document.getElementById('markdown-input').addEventListener('input', function() {
    const markdownText = this.value;
    const htmlContent = marked(markdownText);
    document.getElementById('html-preview').innerHTML = htmlContent;
});
