const repoOwner = 'jitacm';
const repoName = '-30DaysDevChallenge-';
let projects = [];

async function fetchProjects() {
    try {
        const response = await fetch(`https://api.github.com/repos/${repoOwner}/${repoName}/pulls?state=closed`);
        projects = await response.json();
        displayProjects(projects);
    } catch (error) {
        console.error('Error fetching projects:', error);
    }
}

function displayProjects(projects) {
    const projectsDiv = document.getElementById('projects');
    projectsDiv.innerHTML = '';

    projects.forEach(pull => {
        const projectDiv = document.createElement('div');
        projectDiv.classList.add('project');

        const title = document.createElement('h2');
        title.textContent = pull.title;

        const description = document.createElement('p');
        description.textContent = pull.body.split('\n')[0]; // Display only the first line as description

        const link = document.createElement('a');
        link.href = pull.html_url;
        link.textContent = 'View Pull Request';

        // Container for image/video
        const mediaDiv = document.createElement('div');
        mediaDiv.classList.add('project-media');

        // Display image or video if present in the body
        const imageUrl = pull.body.match(/!\[.*\]\((.*)\)/);
        if (imageUrl) {
            const image = document.createElement('img');
            image.src = imageUrl[1];
            mediaDiv.appendChild(image);
        }

        projectDiv.appendChild(title);
        projectDiv.appendChild(mediaDiv);
        projectDiv.appendChild(description);
        projectDiv.appendChild(link);

        projectsDiv.appendChild(projectDiv);
    });
}

function filterProjects() {
    const query = document.getElementById('search').value.toLowerCase();
    const filteredProjects = projects.filter(pull => pull.title.toLowerCase().includes(query));
    displayProjects(filteredProjects);
}

function sortProjects() {
    const sortOrder = document.getElementById('sort').value;
    let sortedProjects = [...projects];

    if (sortOrder === 'newest') {
        sortedProjects.sort((a, b) => new Date(b.created_at) - new Date(a.created_at));
    } else {
        sortedProjects.sort((a, b) => new Date(a.created_at) - new Date(b.created_at));
    }

    displayProjects(sortedProjects);
}

document.addEventListener('DOMContentLoaded', () => {
    fetchProjects();
});
