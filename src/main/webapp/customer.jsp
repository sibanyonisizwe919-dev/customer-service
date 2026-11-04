<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script>
        // Evaluated server-side by JSP EL -> e.g. "/customer-service".
        const CTX = "${pageContext.request.contextPath}";

        async function loadCustomer() {
            const id = new URLSearchParams(location.search).get("id");
            const card = document.getElementById("card");
            const status = document.getElementById("status");
            document.getElementById("titleId").textContent = id || "";
            if (!id) {
                status.textContent = "No customer id provided.";
                return;
            }
            try {
                const res = await fetch(CTX + "/api/customer-details/" + encodeURIComponent(id));
                if (res.status === 404) {
                    status.textContent = "Customer not found.";
                    return;
                }
                if (!res.ok) throw new Error("HTTP " + res.status);
                const data = await res.json();
                const c = data.customer;
                card.innerHTML =
                    "<h2>" + escapeHtml(c.name) + " " + escapeHtml(c.surname) + "</h2>" +
                    "<dl>" +
                      "<dt>Customer ID</dt><dd>" + escapeHtml(String(c.id)) + "</dd>" +
                      "<dt>Email</dt><dd>" + escapeHtml(c.email) + "</dd>" +
                      "<dt>Contact</dt><dd>" + escapeHtml(c.contact) + "</dd>" +
                    "</dl>";
            } catch (e) {
                status.textContent = "Failed to load customer: " + e.message;
            }
        }

        function escapeHtml(s) {
            return (s == null ? "" : String(s))
                .replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;")
                .replace(/"/g, "&quot;").replace(/'/g, "&#39;");
        }
    </script>
</head>
<body onload="loadCustomer()">
    <h1>Customer <span id="titleId">#</span></h1>
    <p class="subtitle">Card view</p>

    <div id="card" class="card"></div>
    <p id="status" class="subtitle"></p>

    <p><button class="btn" onclick="window.close()">Close</button></p>
</body>
</html>
