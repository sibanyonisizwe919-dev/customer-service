<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customers</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script>
        // Evaluated server-side by JSP EL -> e.g. "/customer-service".
        const CTX = "${pageContext.request.contextPath}";

        async function loadCustomers() {
            const tbody = document.getElementById("rows");
            const status = document.getElementById("status");
            try {
                const res = await fetch(CTX + "/api/customer-details/customers");
                if (res.status === 404) {
                    status.textContent = "No customers found.";
                    return;
                }
                if (!res.ok) throw new Error("HTTP " + res.status);
                const list = await res.json();
                tbody.innerHTML = list.map(function (c) {
                    return "<tr>"
                        + "<td>" + c.id + "</td>"
                        + "<td>" + escapeHtml(c.name) + "</td>"
                        + "<td>" + escapeHtml(c.surname) + "</td>"
                        + "<td>" + escapeHtml(c.email) + "</td>"
                        + "<td>" + escapeHtml(c.contact) + "</td>"
                        + "<td><button class='btn' onclick='openCard(" + c.id + ")'>View</button></td>"
                        + "</tr>";
                }).join("");
            } catch (e) {
                status.textContent = "Failed to load customers: " + e.message;
            }
        }

        function openCard(id) {
            window.open(CTX + "/customer.jsp?id=" + id, "customerCard" + id,
                "width=440,height=560,menubar=no,toolbar=no,location=no,status=no");
        }

        function escapeHtml(s) {
            return (s == null ? "" : String(s))
                .replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;")
                .replace(/"/g, "&quot;").replace(/'/g, "&#39;");
        }
    </script>
</head>
<body onload="loadCustomers()">
    <h1>Customers</h1>
    <p class="subtitle"><a href="${pageContext.request.contextPath}/index.jsp">&larr; Home</a></p>

    <table>
        <thead>
            <tr><th>ID</th><th>Name</th><th>Surname</th><th>Email</th><th>Contact</th><th></th></tr>
        </thead>
        <tbody id="rows"></tbody>
    </table>
    <p id="status" class="subtitle"></p>
</body>
</html>
