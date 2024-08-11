function update() {
    const typeFrom = document.getElementById("typeFrom").value;
    const typeTo = document.getElementById("typeTo").value;
    const inputValue = document.getElementById("input").value;
    
    let result = parseInt(inputValue, typeFrom);
    
    if (isNaN(result)) {
        document.getElementById("res").value = "Invalid Input";
        return;
    }

    document.getElementById("res").value = result.toString(typeTo).toUpperCase();
    
    // Update labels to show the current selected systems
    const typeFromLabel = document.getElementById("typeFrom").selectedOptions[0].textContent;
    const typeToLabel = document.getElementById("typeTo").selectedOptions[0].textContent;
    document.getElementById("inputType").textContent = `Enter {${typeFromLabel}}:`;
    document.getElementById("resultType").textContent = `Result {${typeToLabel}}:`;
}

function reverse() {
    const typeFrom = document.getElementById("typeTo").value;
    const typeTo = document.getElementById("typeFrom").value;
    const resultValue = document.getElementById("res").value;

    let result = parseInt(resultValue, typeFrom);

    if (isNaN(result)) {
        document.getElementById("input").value = "Invalid Input";
        return;
    }

    document.getElementById("input").value = result.toString(typeTo).toUpperCase();
    
    // Update labels to show the current selected systems
    const typeFromLabel = document.getElementById("typeTo").selectedOptions[0].textContent;
    const typeToLabel = document.getElementById("typeFrom").selectedOptions[0].textContent;
    document.getElementById("resultType").textContent = `Enter {${typeFromLabel}}:`;
    document.getElementById("inputType").textContent = `Result {${typeToLabel}}:`;
}
