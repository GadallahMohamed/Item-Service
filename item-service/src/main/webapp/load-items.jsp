

<%@page import="com.item.model.ItemDetails"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.item.model.Item"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Show Items</title>
    <link rel="stylesheet" href="css/show-items.css">
</head>
<body>
<div class="layer">
    <h1>Items</h1>
    <table>
        <thead>
        <tr>
            <th>ID</th> 
            <th>NAME</th>
            <th>PRICE</th>
            <th>TOTAL_NUMBER</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Item> items = (List<Item>) request.getAttribute("itemsData");

            if (items != null && !items.isEmpty()) {
                for (Item item : items) {
        %>
        <tr> 
            <td><strong><%= item.getId() %></strong></td>
            <td><%= item.getName() %></td>
            <td><%= item.getPrice() %></td>
            <td><%= item.getTotalNumber() %></td>
            <td>
                <a href='itemController?action=load-item&id= <%=item.getId() %>'>Update</a>
                <a href='itemController?action=remove-item&id= <%=item.getId() %>' >Delete</a>
                
                  <% if (!item.hasDetails()) { %>
        <a href='ItemDetailsController?action=load-item-details&id=<%= item.getId() %>'>Add Details</a>
    <% } %>
                 <% if (item.hasDetails()) { %>
        <a href='ItemDetailsController?action=show-item-details&id=<%= item.getId() %>'>Show Details</a>
            <% } %>  
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="5" style="text-align:center;">No items found.</td>
        </tr>
        <%
            }
        %>

        </tbody>
    </table>

    <button class="f"><a href="add-item.html">Add Item</a></button>
</div>
</body> 
</html>






<%--

<%@page import="com.item.model.ItemDetails"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.item.model.Item"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Show Items</title>
    <link rel="stylesheet" href="css/show-items.css">
</head>
<body>
<div class="layer">
    <h1>Items</h1>
    <table>
        <thead>
        <tr>
            <th>ID</th> 
            <th>NAME</th>
            <th>PRICE</th>
            <th>TOTAL_NUMBER</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Item> items = (List<Item>) request.getAttribute("itemsData");

            if (items != null && !items.isEmpty()) {
                for (Item item : items) {
        %>
        <tr> 
            <td><strong><%= item.getId() %></strong></td>
            <td><%= item.getName() %></td>
            <td><%= item.getPrice() %></td>
            <td><%= item.getTotalNumber() %></td>
            <td>
                <a href='itemController?action=load-item&id= <%=item.getId() %>'>Update</a>
                <a href='itemController?action=remove-item&id= <%=item.getId() %>' >Delete</a>
                <a href='ItemDetailsController?action=load-item-details&id=<%= item.getId() %>'>Add Details</a>
                
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="5" style="text-align:center;">No items found.</td>
        </tr>
        <%
            }
        %>

        </tbody>
    </table>

    <button class="f"><a href="add-item.html">Add Item</a></button>
</div>
</body> 
</html>





--%>



















