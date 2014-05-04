<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>	
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="count" value="1" scope="page" />

<t:parent_page title="Planning">

	<c:if test="${not empty weeks}">		
		<h3 class="center">Project Plan, auto-generated at ${prettyDateTime}:</h3>		
		<c:forEach items="${weeks}" var="week">
			<div class="week">
				<div id="week-header">
					<div class="week-name">Week ${count}</div>
					<div class="week-story-points">${week.totalPoints} of ${week.maxPoints} Story Points</div>
				</div>
				<table class="week-table">
					<c:forEach items="${week.stories}" var="story">				
						<tr>
							<td>Story "${story.title}"</td>
							<td>${story.points} Points</td>
						</tr>				
					</c:forEach>
				</table>
			</div>			
			<c:set var="count" value="${count + 1}" scope="page"/>
		</c:forEach>
	</c:if>
	<c:if test="${empty weeks}">
		<p class="error-message">Planning failed.</p>
	</c:if>
		
	<div id="audit-trail">
		<h3>Audit Trail</h3>		
		<ol>
			<c:forEach items="${auditTrailLines}" var="line">
				<li>${line}</li>
  			</c:forEach>  			
		</ol>
	</div>
	
</t:parent_page>