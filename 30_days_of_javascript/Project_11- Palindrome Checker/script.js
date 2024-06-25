document.getElementById('checkPalindrome').addEventListener('click', function() {
    const inputString = document.getElementById('inputString').value;
    const result = document.getElementById('result');

    if (isPalindrome(inputString)) {
        result.textContent = `"${inputString}" is a palindrome!`;
    } else {
        result.textContent = `"${inputString}" is not a palindrome.`;
    }
});

function isPalindrome(str) {
    const cleanedStr = str.replace(/[^a-zA-Z0-9]/g, '').toLowerCase();
    const reversedStr = cleanedStr.split('').reverse().join('');
    return cleanedStr === reversedStr;
}
