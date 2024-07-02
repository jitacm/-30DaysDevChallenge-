document.getElementById('calculateBMI').addEventListener('click', function() {
    const height = parseFloat(document.getElementById('height').value);
    const weight = parseFloat(document.getElementById('weight').value);
    const result = document.getElementById('result');

    if (isNaN(height) || isNaN(weight) || height <= 0 || weight <= 0) {
        result.textContent = 'Please enter valid height and weight values.';
    } else {
        const bmi = weight / (height * height);
        result.textContent = `Your BMI is ${bmi.toFixed(2)}.`;
    }
});
