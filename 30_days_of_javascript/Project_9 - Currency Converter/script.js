const apiKey = 'YOUR_API_KEY'; // Replace with your Exchange Rate API key
const apiUrl = `https://v6.exchangerate-api.com/v6/${apiKey}/latest/USD`;

const fromCurrency = document.getElementById('fromCurrency');
const toCurrency = document.getElementById('toCurrency');
const amount = document.getElementById('amount');
const convertButton = document.getElementById('convert');
const result = document.getElementById('result');

fetch(apiUrl)
    .then(response => response.json())
    .then(data => {
        const currencies = Object.keys(data.conversion_rates);
        currencies.forEach(currency => {
            const option1 = document.createElement('option');
            const option2 = document.createElement('option');
            option1.value = option2.value = currency;
            option1.text = option2.text = currency;
            fromCurrency.add(option1);
            toCurrency.add(option2);
        });
    })
    .catch(error => console.error('Error fetching currency data:', error));
convertButton.addEventListener('click', () => {
    const from = fromCurrency.value;
    const to = toCurrency.value;
    const amountValue = amount.value;

    fetch(`https://v6.exchangerate-api.com/v6/${apiKey}/pair/${from}/${to}`)
        .then(response => response.json())
        .then(data => {
            const rate = data.conversion_rate;
            const convertedAmount = (amountValue * rate).toFixed(2);
            result.textContent = `${amountValue} ${from} = ${convertedAmount} ${to}`;
        })
        .catch(error => console.
