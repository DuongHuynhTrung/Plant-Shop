<%-- 
    Document   : VerifyMaiil
    Created on : Mar 11, 2022, 2:51:54 PM
    Author     : Trung Duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="mycss.css" type="text/css" />
        <title>JSP Page</title>
    </head>

    <body>
        <input id="OTP" type="hidden" value="${requestScope.SendOTP}" />
        <input id="MESSAGE" type="hidden" value="${requestScope.MESSAGE}" />
        <script>
            if (document.getElementById("OTP").value !== "") {
                alert("PLEASE, check your email: " + document.getElementById("OTP").value + ", a confirm code is send to you!");
            }
            if (document.getElementById("MESSAGE").value !== "") {
                alert(document.getElementById("MESSAGE").value);
            }
            
        </script>
        <header>

        </header>
        <section style="width: 100%; display: flex; justify-content: space-between">
            <form action="mainController" method="POST"  style="width: 50%">
                <div style="text-align: center; margin: auto 0;">
                    <input type="text" value="" required="" name="codeInput" placeholder="Enter Code to Verify"/> 
                    <input type="hidden" value="${requestScope.code}" name="codeCheck"/>
                    <input type="hidden" value="${requestScope.txtEmail}" name="txtEmail"/>
                    <input type="hidden" value="${requestScope.txtFullName}" name="txtFullName"/>
                    <input type="hidden" value="${requestScope.txtPassWord}" name="txtPassWord"/>
                    <input type="hidden" value="${requestScope.txtPhone}" name="txtPhone"/>
                    <input type="submit" value="Verify" name="btAction"/>  <br/>
                    <input type="hidden" value="${requestScope.time}" name="time"/>
                    <input style="background-color: red; color: white" type="text" value="You have ${requestScope.time} time to verify!" readonly=""/>
                </div>
            </form>
            <div style="width: 50%">
                <img src="images/gmail.png" />
            </div>
        </section>
        <footer>

        </footer>
    </body>
</html>
