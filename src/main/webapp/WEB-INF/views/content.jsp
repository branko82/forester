<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<td>
<h3>
	<c:choose>
		<c:when test="${not empty page}">
			<c:choose>
				<c:when test="${page == 'start_up'}">
					<%@ include file ="start_up.jsp" %>
				</c:when>
				<c:when test="${page == 'first_point'}">
					<%@ include file ="first_point.jsp" %>
				</c:when>
				<c:when test="${page == 'second_point'}">
					<%@ include file ="second_point.jsp" %>
				</c:when>
				<c:when test="${page == 'third_point'}">
					<%@ include file ="third_point.jsp" %>
				</c:when>
				<c:when test="${page == 'fourth_point'}">
					<%@ include file ="fourth_point.jsp" %>
				</c:when>
				<c:when test="${page == 'fifth_point'}">
					<%@ include file ="fifth_point.jsp" %>
				</c:when>
				<c:when test="${page == 'sixth_point'}">
					<%@ include file ="sixth_point.jsp" %>
				</c:when>
				<c:when test="${page == 'seventh_point'}">
					<%@ include file ="seventh_point.jsp" %>
				</c:when>
				<c:when test="${page == 'eight_point'}">
					<%@ include file ="eight_point.jsp" %>
				</c:when>
				<c:when test="${page == 'end_up'}">
					<%@ include file ="end_up.jsp" %>
				</c:when>
				<c:when test="${page == 'error'}">
					<%@ include file ="error.jsp" %>
				</c:when>
				<c:otherwise>
					No page attribute is set
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
            <%@ include file ="welcome.jsp" %>
   		</c:otherwise>
	</c:choose>
</h3>
</td>