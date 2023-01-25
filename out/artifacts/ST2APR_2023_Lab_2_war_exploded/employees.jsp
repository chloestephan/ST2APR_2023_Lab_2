<%-- 
    Purpose  : APR course - 2023
    Author   : JAA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <form>
            <table>
                <tr>
                    <th><b>First name</b></th>
                    <th><b>Last name</b></th>
                    <th><b>Address</b></th>
                    <th><b>Postal code</b></th>
                    <th><b>City</b></th>
                    <th><b>Home phone</b></th>
                    <th><b>Mobile phone</b></th>
                    <th><b>Work phone</b></th>
                    <th><b>Email</b></th>
                </tr>

                <c:forEach items="${employeesKey}" var="employee">
                    <tr> 

                        <td>  ${employee.firstname}    </td>
                        <td>  ${employee.name}</td>
                        <td>  ${employee.address}</td>
                        <td>  ${employee.postalcode}</td>
                        <td>  ${employee.city}    </td>
                        <td>  ${employee.homephone}</td>
                        <td>  ${employee.mobilephone}</td>
                        <td>  ${employee.workphone}  </td>
                        <td>  ${employee.email}  </td>

                    </tr>
                </c:forEach>
            </table>
            <input type='submit' name="action" value="Detail"/>
        </form>
    </body>
</html>
