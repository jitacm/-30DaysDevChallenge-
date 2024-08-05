let currentSlide = 0;

function showSlide(index) {
    const slides = document.querySelector('.slides');
    const totalSlides = document.querySelectorAll('.slide').length;
    currentSlide = (index + totalSlides) % totalSlides;
    slides.style.transform = `translateX(-${currentSlide * 100}%)`;
    updateIndicators(); // Update indicators on slide change
}

function changeSlide(step) {
    showSlide(currentSlide + step);
}

function createIndicators() {
    const totalSlides = document.querySelectorAll('.slide').length;
    const indicatorsContainer = document.querySelector('.indicators');
    indicatorsContainer.innerHTML = ''; // Clear existing indicators

    for (let i = 0; i < totalSlides; i++) {
        const indicator = document.createElement('span');
        indicator.classList.add('indicator');
        indicator.addEventListener('click', () => showSlide(i));
        indicatorsContainer.appendChild(indicator);
    }
}

function updateIndicators() {
    const indicators = document.querySelectorAll('.indicator');
    indicators.forEach((indicator, index) => {
        indicator.classList.toggle('active', index === currentSlide);
    });
}

document.addEventListener('DOMContentLoaded', () => {
    createIndicators(); // Create indicators on load
    showSlide(currentSlide);
});
