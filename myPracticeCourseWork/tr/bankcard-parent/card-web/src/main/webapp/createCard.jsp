<%-- 
    Document   : createCard.jsp
    Created on : Nov 11, 2018, 3:25:57 PM
    Author     : cgallen
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="solent.ac.uk.ood.examples.cardvalidator.model.BankApi"%>
<%@page import="solent.ac.uk.ood.examples.cardvalidator.model.CardFactoryDAO"%>
<%@page import="solent.ac.uk.ood.examples.cardvalidator.model.ServiceObjectFactory"%>
<%@page import="solent.ac.uk.ood.examples.cardvalidator.model.TransactionApi"%>
<%@page import="solent.ac.uk.ood.examples.cardvalidator.cardservice.ServiceObjectFactoryImpl"%>
<%@page import="solent.ac.uk.ood.examples.cardvalidator.cardservice.web.WebObjectFactory"%>
<%@page import="solent.ac.uk.ood.examples.cardvalidator.model.Account"%>
<%@page import="java.util.List"%>

<%
    BankApi bankApi = (BankApi) session.getAttribute("bankApi");

    // If the user session has no bankApi, create a new one
    if (bankApi == null) {
        ServiceObjectFactory serviceObjectFactory = WebObjectFactory.getServiceObjectFactory();
        bankApi = serviceObjectFactory.getBankApi();
        session.setAttribute("bankApi", bankApi);
    }

    String bankProvider = (String) request.getParameter("bankProvider");
    String issuerIdentificationNumber = bankApi.getIssuerIdentifierNumberForName(bankProvider);
    String accountNumber = (String) request.getParameter("accountNumber");
    String accountName = (String) request.getParameter("accountName");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create Credit Card</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>

    <body>
        <h1>Newly Created Credit Card</h1>

        <table>
            <tr>
                <td>Card Name</td><td></td>               
            </tr>
            <tr>
                <td>Expiry Date</td><td></td>
            </tr>
            <tr>
                <td>Card Number</td><td></td>
            </tr>
            <tr>
                <td>cvv</td><td></td>
            </tr>
            <tr>
                <td>Issue Number</td><td></td>
            </tr>
        </table>
        <BR>
        <button onclick="window.location.href = 'accountList.jsp'">Return to Account List</button>
    </body>
</html>
