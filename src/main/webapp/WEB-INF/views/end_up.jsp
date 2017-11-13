<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fieldset class="floating-box">
    <legend>Generisi dokument</legend>
        <table>
            <tr>
                <td>Dokument ce biti generisan i prikazan u prozoru pretrazivaca</td>
            </tr>
            <tr>
                <td colspan="1">
                    <input type="submit" name="buttonAction" value="Generisi" class="button"
                    onclick="location.href='<%=request.getContextPath()%>/generate_and_show_pdf_file/';"/>
                </td>
            </tr>
        </table>
    </legend>
</fieldset>


