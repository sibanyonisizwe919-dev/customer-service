<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Service</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h1>Customer Service</h1>
    <p class="subtitle">A Jakarta EE demo running on GlassFish.</p>

    <p>This home page is a JSP served from <code>/index.jsp</code>. The REST API lives under <code>/api</code>:</p>

    <ul>
        <li><a href="${pageContext.request.contextPath}/api/hello">GET /api/hello</a> &mdash; sanity check</li>
        <li><a href="${pageContext.request.contextPath}/api/customer-details/customers">GET /api/customer-details/customers</a> &mdash; list all customers</li>
        <li><a href="${pageContext.request.contextPath}/api/customer-details/1">GET /api/customer-details/1</a> &mdash; fetch customer by id</li>
    </ul>

    <p><a href="${pageContext.request.contextPath}/customers.jsp">Browse customers &rarr;</a> &mdash; table view that opens a card popup per customer.</p>

    <p>Rendered on the server at <%= new java.util.Date() %>.</p>

    <footer>customer-service &middot; Jakarta EE 10</footer>
</body>
</html>
