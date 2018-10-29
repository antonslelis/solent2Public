<%-- 
    Document   : DoorLockPage.jsp
    Created on : Oct 20, 2018, 6:34:33 PM
    Author     : cgallen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="solent.ac.uk.ood.examples.hotellock.model.CardKey"%>
<%@page import="solent.ac.uk.ood.examples.hotellock.model.HotelRoomLockService"%>
<%@page import="solent.ac.uk.ood.examples.hotellock.model.SecretKeyProvider"%>
<%@page import="solent.ac.uk.ood.examples.hotellock.secretkey.SecretKeyProviderImpl"%>
<%@page import="solent.ac.uk.ood.examples.hotellock.roomlock.HotelRoomLockServiceImpl"%>

<%
    HotelRoomLockService hotelRoomLockService = (HotelRoomLockService) session.getAttribute("hotelRoomLockService");
    if (hotelRoomLockService == null) {
        hotelRoomLockService = new HotelRoomLockServiceImpl();
        SecretKeyProvider secretKeyProvider = new SecretKeyProviderImpl();
        hotelRoomLockService.setSecretKeyProvider(secretKeyProvider);
        session.setAttribute("hotelRoomLockService", hotelRoomLockService);
        
    }
    
    String roomNumber = (String) request.getParameter("roomNumber");
    if (roomNumber == null) {
        roomNumber = (String) session.getAttribute("sessionRoomNumber");
        if (roomNumber == null) {
            roomNumber = "";
        }
    } else {
        session.setAttribute("sessionRoomNumber", roomNumber);
    }
    
    String cardCode = (String) request.getParameter("cardCode");
    if (cardCode == null) {
        cardCode = (String) session.getAttribute("sessionCardCode");
        if (cardCode == null) {
            cardCode = "";
        }
    } else {
        session.setAttribute("sessionCardCode", cardCode);
    }
    boolean errorUnlockingLock=false;
    boolean errorMissingData =true;
    if(roomNumber.isEmpty()){
        errorMissingData = true;
    } else {
        hotelRoomLockService.setRoomNumber(roomNumber);
        if(cardCode.isEmpty()){
            errorMissingData = true;
        }else{
            
            try {
                errorUnlockingLock=hotelRoomLockService.unlockDoor(cardCode);
            } catch (Exception ex) {
                errorMissingData = true;
            }
        }
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Door Lock Page</title>
    </head>
    <body>
        <h1>Door Lock</h1>

        <form action="./DoorLockPage.jsp">
            Enter Room Number:<br>
            <input type="text" name="roomNumber" value="<%=roomNumber%>">
            <% if (roomNumber.isEmpty()) { %>
            Room Number must not be empty
            <% }%>
            <br>
            Enter Card Code:<br>
            <input type="text" name="cardCode" value="<%=cardCode%>">
            <% if (cardCode.isEmpty()) { %>
            Card Code must not be empty
            <% }%>
            <br>
            <input type="submit" value="Unlock Door">
            <%if (errorUnlockingLock) { %>
            <p><%=errorUnlockingLock%></p>
        
            <% } else {%>
            <p><%=errorUnlockingLock%></p>
            <% }%>
        </form> 
         <br>
        <div id="result"></div>

    </body>
</html>
