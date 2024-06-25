function convertLength(value, unitFrom, unitTo) {
    const units = {
        'm': 1,
        'km': 1000,
        'cm': 0.01,
        'inch': 0.0254,
        'ft': 0.3048,
        'yd': 0.9144
    };

    const valueInMeters = value * units[unitFrom];
    return valueInMeters / units[unitTo];
}

document.getElementById('convert').addEventListener('click', function() {
    const value = parseFloat(document.getElementById('value').value);
    const unitFrom = document.getElementById('unitFrom').value;
    const unitTo = document.getElementById('unitTo').value;

    if (isNaN(value)) {
        document.getElementById('result').textContent = 'Please enter a valid number.';
    } else {
        const result = convertLength(value, unitFrom, unitTo);
        document.getElementById('result').textContent = `${value} ${unitFrom} = ${result.toFixed(2)} ${unitTo}`;
    }
});
