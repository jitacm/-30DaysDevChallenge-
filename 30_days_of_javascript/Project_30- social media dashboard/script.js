// script.js
const socialMediaData = {
    twitter: {
        posts: 100,
        likes: 500,
        comments: 250
    },
    facebook: {
        posts: 150,
        likes: 700,
        comments: 300
    }
};

document.addEventListener('DOMContentLoaded', function() {
    updateMetrics('twitter', socialMediaData.twitter);
    updateMetrics('facebook', socialMediaData.facebook);
});

function updateMetrics(platform, data) {
    const postsElement = document.getElementById(`${platform}-posts-count`);
    const likesElement = document.getElementById(`${platform}-likes-count`);
    const commentsElement = document.getElementById(`${platform}-comments-count`);

    postsElement.textContent = data.posts;
    likesElement.textContent = data.likes;
    commentsElement.textContent = data.comments;
}
