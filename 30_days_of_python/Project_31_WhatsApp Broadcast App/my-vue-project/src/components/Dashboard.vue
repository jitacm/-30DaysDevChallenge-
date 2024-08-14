<template>
    <div>
     
         <div class="navbar">
            <div class="logo"><i class="fa-regular fa-message"></i> WatNot</div>
            <div class="nav-item" @click="showSidebar('broadcast')"><i class="fa-solid fa-tower-broadcast"></i>Broadcast</div>
            <div class="nav-item" @click="showSidebar('Contacts')"><i class="fa-regular fa-address-book"></i>Contacts</div>
            <div class="nav-item" @click="redirectToAnalysisPage"><i class="fa-solid fa-chart-line"></i>Analysis</div>
            <div class="nav-item" @click="showSidebar('More')"><i class="fa-solid fa-ellipsis"></i>More</div>
        </div>

        <div class="content">
            <!-- Broadcast sidebar options -->
            <div class="sidebar" id="broadcast">
                <a href="#" @click="showContent('Broadcast2')"><i class="fa-solid fa-tower-broadcast"></i>Broadcast Messages</a>
                <a href="#" @click="showContent('Broadcast1')"><i class="fa-regular fa-message"></i>Manage Templates</a>
                <a href="#" @click="showContent('Broadcast3')"><i class="fa-regular fa-clock"></i>Scheduled Broadcasts</a>
            </div>

            <!-- Contacts sidebar options -->
            <div class="sidebar" id="Contacts">
                <a href="#" @click="showContent('Contacts1')"><i class="fa-regular fa-address-book"></i>Manage Contacts</a>
                <a href="#" @click="showContent('Contacts2')"><i class="bi bi-tags"></i>Manage Tags</a>
            </div>

            <!-- More sidebar options -->
            <div class="sidebar" id="More">
                <a href="#" @click="showContent('More1')">More 1</a>
            </div>

            <div class="main-content">
                <!-- Broadcast Content -->
                <div id="Broadcast1" class="content-section">
                    <h2>Manage Templates</h2>
                    <p>Your content for template messages goes here.</p>
                </div>
                <div id="Broadcast2" class="content-section">
                    <!-- Broadcast Message form -->
                    <div class="container">
                        <form id="messageForm">
                            <h3>New Broadcast</h3>
                            <label>Broadcast Name</label>
                            <input type="text" id="broadcast-name" placeholder="Broadcast Name">
                            <label>Recipients</label>
                            <input type="text" id="phoneNumbers" placeholder="Enter phone numbers, comma-separated" required>
                            <label for="templates">Choose a template</label>
                            <select id="templates" required>
                                <option value="" disabled selected>Select your option</option>
                                <!-- Options will be populated here by JavaScript -->
                            </select>
                            <h4>Contacts</h4>
                            <table class="contact-table" id="contactTable">
                                <thead>
                                    <tr>
                                        <th>Select</th>
                                        <th>Name</th>
                                        <th>Phone Number</th>
                                    </tr>
                                </thead>
                                <tbody id="contactTableBody">
                                    <!-- Contacts will be populated here -->
                                </tbody>
                            </table>
                            <button type="submit" @click="sendMessage">Send Message</button>
                        </form>
                        <div id="response"></div>
                    </div>
                </div>
                <div id="Broadcast3" class="content-section">
                    <h2>Scheduled Broadcasts</h2>
                    <p>Your content for scheduled broadcasts goes here.</p>
                </div>
                <!-- Contacts Content -->
                <div id="Contacts1" class="content-section">
                    <div class="container-contacts">
                        <h3>Contact Directory</h3>
                        <form id="contactForm">
                            <input type="hidden" id="contactId">
                            <input type="text" id="name" placeholder="Name" required>
                            <input type="email" id="email" placeholder="Email" required>
                            <input type="text" id="phone" placeholder="Phone" required>
                            <input type="text" id="tags" placeholder="Tags (comma separated)">
                            <button type="submit">Add Contact</button>
                        </form>
                        <div class="search">
                            <input type="text" id="searchPhone" placeholder="Search by Phone">
                            <button id="searchButton">Search</button>
                        </div>
                        <div id="contactsList"></div>
                    </div>
                </div>
                <div id="Contacts2" class="content-section">
                    <h2>Manage Tags</h2>
                    <p>Your content for scheduled broadcasts goes here.</p>
                </div>
                <!-- More Content -->
                <div id="More1" class="content-section">
                    <h2>Coming Soon...</h2>
                    <p></p>
                </div>
            </div>
        </div>
    </div>
</template>


<script>
export default {
  name: 'DashboardComponents',

  methods: {
    setDefaultActiveSection() {
      this.showSidebar('broadcast');
      this.showContent('Broadcast2');
    },

    showSidebar(id) {
      const sidebars = document.querySelectorAll('.sidebar');
      sidebars.forEach(sidebar => {
        sidebar.style.display = 'none';
      });

      const selectedSidebar = document.getElementById(id);
      if (selectedSidebar) {
        selectedSidebar.style.display = 'flex';
      }
    },

    showContent(id) {
      const sections = document.querySelectorAll('.content-section');
      sections.forEach(section => {
        section.classList.remove('active');
      });

      const activeSection = document.getElementById(id);
      if (activeSection) {
        activeSection.classList.add('active');
      }
    },

    async fetchTemplates() {
      try {
        const response = await fetch('http://localhost:8000/templates/', {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
          },
        });

        const templateNames = await response.json();
        const dropdown = document.getElementById('templates');
        templateNames.forEach(name => {
          const option = document.createElement('option');
          option.value = name;
          option.textContent = name;
          dropdown.appendChild(option);
        });
      } catch (error) {
        console.error('There has been a problem with your fetch operation:', error);
      }
    },

    async sendMessage(event) {
      event.preventDefault();

      const phoneNumbers = document.getElementById('phoneNumbers').value.split(',').map(num => num.trim());
      const selectedTemplate = document.getElementById('templates').value;

      const responseDiv = document.getElementById('response');
      responseDiv.textContent = 'Sending...';

      try {
        const response = await fetch('http://localhost:8000/send-template-message/', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            "recipients": phoneNumbers,
            "template": selectedTemplate
          }),
        });

        const result = await response.json();
        responseDiv.textContent = `Success: ${result.successful_messages}, Errors: ${result.errors.length}`;
      } catch (error) {
        responseDiv.textContent = 'Error sending messages.';
      }
    },

    async saveContact(event) {
      event.preventDefault();

      const id = document.getElementById('contactId').value;
      const name = document.getElementById('name').value;
      const email = document.getElementById('email').value;
      const phone = document.getElementById('phone').value;
      const tags = document.getElementById('tags').value.split(',').map(tag => tag.trim());

      const url = id ? `http://localhost:8000/contacts/phone/${phone}` : 'http://localhost:8000/contacts/';
      const method = id ? 'PUT' : 'POST';

      try {
        const response = await fetch(url, {
          method: method,
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({ name, email, phone, tags }),
        });

        if (response.ok) {
          alert('Contact saved successfully');
          this.clearForm();
          this.loadContacts();
        } else {
          const errorData = await response.json();
          alert(`Error: ${errorData.detail}`);
        }
      } catch (error) {
        console.error('Error saving contact:', error);
        alert('Error saving contact');
      }
    },

    async searchContact() {
      const phone = document.getElementById('searchPhone').value.trim();
      if (phone) {
        try {
          const response = await fetch(`http://localhost:8000/contacts/phone/${phone}`);
          if (response.ok) {
            const contact = await response.json();
            this.displayContacts([contact]);
          } else {
            alert('Contact not found');
            this.loadContacts(); // Refresh the list if not found
          }
        } catch (error) {
          console.error('Error searching contact:', error);
          alert('Error searching contact');
        }
      }
    },

    async loadContacts() {
      try {
        const response = await fetch('http://localhost:8000/contacts/');
        if (response.ok) {
          const contacts = await response.json();
          this.displayContacts(contacts);
        } else {
          console.error('Failed to load contacts');
        }
      } catch (error) {
        console.error('Error loading contacts:', error);
      }
    },

    displayContacts(contacts) {
      const contactsList = document.getElementById('contactsList');
      contactsList.innerHTML = '';

      contacts.forEach(contact => {
        const contactDiv = document.createElement('div');
        contactDiv.className = 'contact';
        contactDiv.innerHTML = `
          Name: ${contact.name}<br>
          Email: ${contact.email}<br>
          Phone: ${contact.phone}<br>
          Tags: ${contact.tags.join(', ')}<br>
          <button @click="editContact(${contact.id}, '${contact.name}', '${contact.email}', '${contact.phone}', '${contact.tags.join(', ')}')">Edit</button>
          <button @click="deleteContact('${contact.phone}')">Delete</button>
        `;
        contactsList.appendChild(contactDiv);
      });
    },

    clearForm() {
      document.getElementById('contactId').value = '';
      document.getElementById('name').value = '';
      document.getElementById('email').value = '';
      document.getElementById('phone').value = '';
      document.getElementById('tags').value = '';
    },

    editContact(id, name, email, phone, tags) {
      document.getElementById('contactId').value = id;
      document.getElementById('name').value = name;
      document.getElementById('email').value = email;
      document.getElementById('phone').value = phone;
      document.getElementById('tags').value = tags;
    },

    async deleteContact(phone) {
      try {
        const response = await fetch(`http://localhost:8000/contacts/${phone}`, {
          method: 'DELETE',
        });

        if (response.ok) {
          alert('Contact deleted successfully');
          this.loadContacts();
        } else {
          alert('Error deleting contact');
        }
      } catch (error) {
        console.error('Error deleting contact:', error);
        alert('Error deleting contact');
      }
    },

    updateRecipients() {
      const checkboxes = document.querySelectorAll('#contactTableBody input[type="checkbox"]:checked');
      const recipients = Array.from(checkboxes).map(checkbox => checkbox.value);
      document.getElementById('phoneNumbers').value = recipients.join(', ');
    },

    addCheckboxListeners() {
      const checkboxes = document.querySelectorAll('#contactTableBody input[type="checkbox"]');
      checkboxes.forEach(checkbox => {
        checkbox.addEventListener('change', this.updateRecipients);
      });
    },
  },

  mounted() {
    this.setDefaultActiveSection();
    this.fetchTemplates();
    this.addCheckboxListeners();
    this.loadContacts();
  }
}
</script>



<style scoped>
body {
    font-family: Arial, sans-serif;
    margin: 0;
    display: flex;
    height: 100vh;
    overflow: hidden;
}

.navbar {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    display: flex;
    background-color: #f8f9fa;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    z-index: 1;
}

.logo {
    font-weight: 800;
    margin: 8px 0 8px 18px;
    padding-right: 30px;
    font-size: xx-large;
    color: #075e54;
}

.nav-item {
    padding: 15px;
    cursor: pointer;
    color: #333;
    text-align: center;
    margin: 8px 0;
    border-right: 1px solid #e9ecef;
    font-size: medium;
}

.nav-item i {
    padding-right: 9px;
}

.nav-item:hover {
    font-weight: 600;
    border-radius: 5px;
    color: #075e54;
}

.content {
    display: flex;
    flex: 1;
    margin-top: 50px; /* Height of the navbar */
}

.sidebar {
    position: fixed;
    top: 50px; /* Below the navbar */
    left: 0;
    width: 270px;
    height: calc(100% - 50px);
    background-color: #f1f1f1;
    display: none;
    flex-direction: column;
    padding: 10px;
    padding-top: 30px;
    box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
    overflow-y: auto;
}

.sidebar a {
    display: block;
    padding: 10px;
    color: #333;
    text-decoration: none;
    margin-bottom: 10px;
    border-radius: 5px;
    font-size: large;
}

.sidebar i {
    padding-right: 10px;
}

.sidebar a:hover {
    font-weight: 600;
    border-radius: 5px;
    color: #075e54;
    background-color: #e9ecef;
}

.sidebar a.active {
    background-color: #075e54;
    color: white;
}

.main-content {
    flex: 1;
    margin-left: 270px; /* Width of the sidebar */
    padding: 20px;
    height: calc(100% - 50px);
    overflow-y: auto;
    background-color: #ffffff;
    padding-left: 150px;
    padding-top: 50px;
}

.content-section {
    display: none;
}

.content-section.active {
    display: block;
}

.container, .container-contacts {
    background-color: #f5f6fa;
    border-radius: 12px;
    width: 90%;
    max-width: 900px;
    padding: 20px;
    text-align: left;
    box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
}

form {
    display: flex;
    flex-direction: column;
}

select {
    width: 100%;
    padding: 10px;
    margin: 8px 0 20px 0;
    border-radius: 5px;
    border: 1px solid #ccc;
    font-size: 16px;
}

h2 {
    color: #075e54;
    margin-bottom: 20px;
}

input {
    width: calc(100% - 20px);
    padding: 10px;
    border-radius: 5px;
    border: 1px solid #ccc;
    font-size: 16px;
    margin: 8px 0 20px 0;
}

button {
    background-color: #075e54;
    color: white;
    border: none;
    border-radius: 5px;
    padding: 10px;
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.3s;
}

button:hover {
    background-color: #1ebd5b;
}

#response {
    margin-top: 20px;
    font-size: 16px;
    color: #333;
}

.contact-table {
    width: 100%;
    border-collapse: collapse;
    display: block;
    overflow-y: auto;
    max-height: 400px;
    width: 600px;
}

.contact-table th,
.contact-table td {
    border: 1px solid #ccc;
    padding: 8px;
    text-align: left;
}

.contact-table thead th {
    position: sticky;
    top: 0;
    background-color: #f9f9f9;
}
</style>
