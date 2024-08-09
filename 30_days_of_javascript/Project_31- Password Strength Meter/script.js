const input = document.querySelector(".input");
const showBtn = document.querySelector(".showBtn");
const weak = document.querySelector(".weak");
const medium = document.querySelector(".medium");
const strong = document.querySelector(".strong");
const text = document.querySelector(".text");
const confirmPassword = document.getElementById("confirm-password");
const confirmText = document.querySelector(".confirm-text");
const generateBtn = document.querySelector(".generateBtn");

let regExpWeak = /[a-z]/;
let regExpMedium = /\d+/;
let regExpStrong = /.[!,@,#,$,%,^,&,*,(,)]/;

document.getElementById("password").addEventListener("keyup", function() {
  let no = 0;

  if (input.value.length <= 3 && (input.value.match(regExpWeak) || input.value.match(regExpMedium) || input.value.match(regExpStrong))) no = 1;

  if (input.value.length >= 6 && ((input.value.match(regExpWeak) && input.value.match(regExpMedium)) || (input.value.match(regExpMedium) && input.value.match(regExpStrong)) || (input.value.match(regExpWeak) && input.value.match(regExpStrong)))) no = 2;

  if (input.value.length >= 8 && input.value.match(regExpWeak) && input.value.match(regExpMedium) && input.value.match(regExpStrong)) no = 3;

  if (no == 1) {
    weak.classList.add("active");
    medium.classList.remove("active");
    strong.classList.remove("active");
    text.style.display = "block";
    text.textContent = "Your password is too weak";
    text.classList.add("weak");
    text.classList.remove("medium");
    text.classList.remove("strong");
  }

  if (no == 2) {
    weak.classList.add("active");
    medium.classList.add("active");
    strong.classList.remove("active");
    text.style.display = "block";
    text.textContent = "Your password is average";
    text.classList.remove("weak");
    text.classList.add("medium");
    text.classList.remove("strong");
  }

  if (no == 3) {
    weak.classList.add("active");
    medium.classList.add("active");
    strong.classList.add("active");
    text.style.display = "block";
    text.textContent = "Your password is strong";
    text.classList.remove("weak");
    text.classList.remove("medium");
    text.classList.add("strong");
  }

  document.getElementById("lengthReq").classList.toggle("active", input.value.length >= 8);
  document.getElementById("lowercaseReq").classList.toggle("active", /[a-z]/.test(input.value));
  document.getElementById("uppercaseReq").classList.toggle("active", /[A-Z]/.test(input.value));
  document.getElementById("numberReq").classList.toggle("active", /\d/.test(input.value));
  document.getElementById("specialReq").classList.toggle("active", /[!,@,#,$,%,^,&,*,(,)]/.test(input.value));
});

confirmPassword.addEventListener("keyup", function() {
  if (confirmPassword.value === input.value) {
    confirmText.style.display = "block";
    confirmText.textContent = "Passwords match";
    confirmText.style.color = "#23ad5c";
  } else {
    confirmText.style.display = "block";
    confirmText.textContent = "Passwords do not match";
    confirmText.style.color = "red";
  }
});

showBtn.style.display = "block";
showBtn.addEventListener("click", function() {
  if (input.type === "password") {
    input.type = "text";
    showBtn.textContent = "HIDE";
    showBtn.style.color = "#23ad5c";
  } else {
    input.type = "password";
    showBtn.textContent = "SHOW";
    showBtn.style.color = "#000";
  }
});

generateBtn.addEventListener("click", function() {
  const charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()";
  let newPassword = "";
  for (let i = 0; i < 12; i++) {
    const randomIndex = Math.floor(Math.random() * charset.length);
    newPassword += charset[randomIndex];
  }
  input.value = newPassword;
  input.dispatchEvent(new Event('keyup'));
});
