document.addEventListener("DOMContentLoaded", () => {
    const button = document.querySelector(".glow-effect");

    button.addEventListener("click", function(event) {
        const ripple = document.createElement("span");
        ripple.classList.add("ripple");

        const rect = this.getBoundingClientRect();
        const size = Math.max(rect.width, rect.height);
        ripple.style.width = ripple.style.height = `${size}px`;

        const x = event.clientX - rect.left - size / 2;
        const y = event.clientY - rect.top - size / 2;
        ripple.style.left = `${x}px`;
        ripple.style.top = `${y}px`;

        this.appendChild(ripple);

        setTimeout(() => {
            ripple.remove();
        }, 600);
    });
});

document.addEventListener("DOMContentLoaded", function () {
    const loginBtn = document.querySelector("button");

    loginBtn.addEventListener("click", async function (event) {
        event.preventDefault();

        const email = document.querySelector("input[type='text']").value;
        const password = document.querySelector("input[type='password']").value;

        if (!email || !password) {
            alert("Please enter email and password.");
            return;
        }

        try {
            const response = await fetch("http://localhost:8080/authenticate", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ email, password })
            });

            const data = await response.json();

            if (data.success) {
                alert("Login successful!");
                window.location.href = "/dashboard.html";  // Redirect on success
            } else {
                alert("Invalid credentials. Please try again.");
            }
        } catch (error) {
            console.error("Error:", error);
            alert("Something went wrong. Please try again later.");
        }
    });
});
