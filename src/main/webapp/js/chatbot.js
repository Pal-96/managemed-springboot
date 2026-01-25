document.addEventListener("DOMContentLoaded", () => {
    const fab = document.getElementById("chat-fab");
    const chatBody = document.querySelector(".chat-body");
    const chatInput = document.getElementById("chat-input");
    const sendBtn = document.getElementById("send-chat");
    const chatContainer = document.querySelector(".chat-container");

    let isChatOpen = false;

    function toggleChat() {
        isChatOpen = !isChatOpen;
        chatContainer.style.display = isChatOpen ? "flex" : "none";
    }

    fab.addEventListener("click", toggleChat);

    function addMessage(text, sender) {
        const div = document.createElement("div");
        div.className = "chat-msg " + sender;
        div.textContent = text;
        chatBody.appendChild(div);
        chatBody.scrollTop = chatBody.scrollHeight;
    }

    function sendMessage() {
        const message = chatInput.value.trim();
        if (!message) return;

        addMessage(message, "user");
        chatInput.value = "";

        fetch("http://localhost:8081/api/openai/", {
            method: "POST",
            headers: { "Content-Type": "text/plain" },
            body: message
        })
            .then(res => res.text())
            .then(reply => addMessage(reply, "bot"))
            .catch(() => addMessage("AI service is temporarily unavailable.", "bot"));
    }

    sendBtn.addEventListener("click", sendMessage);
    chatInput.addEventListener("keypress", e => {
        if (e.key === "Enter") sendMessage();
    });
});