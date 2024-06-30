// script.js
function addItem() {
    const itemInput = document.getElementById('item-input');
    const itemText = itemInput.value.trim();

    if (itemText === '') return;

    const li = document.createElement('li');
    const textNode = document.createTextNode(itemText);
    li.appendChild(textNode);

    const actions = document.createElement('div');
    actions.classList.add('actions');

    const editButton = document.createElement('button');
    editButton.innerText = 'Edit';
    editButton.onclick = () => editItem(li);

    const deleteButton = document.createElement('button');
    deleteButton.innerText = 'Delete';
    deleteButton.onclick = () => deleteItem(li);

    actions.appendChild(editButton);
    actions.appendChild(deleteButton);
    li.appendChild(actions);

    document.getElementById('grocery-list').appendChild(li);
    itemInput.value = '';
}

function editItem(li) {
    const newItemText = prompt('Edit item:', li.firstChild.nodeValue);
    if (newItemText !== null && newItemText.trim() !== '') {
        li.firstChild.nodeValue = newItemText.trim();
    }
}

function deleteItem(li) {
    li.parentNode.removeChild(li);
}
