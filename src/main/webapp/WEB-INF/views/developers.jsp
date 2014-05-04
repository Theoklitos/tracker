<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>	
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:parent_page title="Developers">

	<c:if test="${not empty developers}">		
		<table id="developer-table" class="table table-hover">			
			<thead><tr><th>ID</th><th>Name</th></tr></thead>
			<tbody>			
			<c:forEach items="${developers}" var="developer">
				<tr>
					<td>${developer.id}</td>
					<td>${developer.name}</td>					
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</c:if>
	<c:if test="${empty developers}">
		<p class="error-message">No developers found.</p>
	</c:if>		

	<p class="error-message"><c:out value="${errorMessage}" /></p>

	<form id="developer-form" role="form" action="${rootUrl}/developers" method="post">
		<div class="form-group">
			<label for="developerName">Developer Name</label>
			<input type="text" class="form-control" name="name" id="developerName" placeholder="Enter a developer name...">
		</div>
		<input type="submit" class="btn btn-default" name="action" value="Create"/>
		<input type="submit" class="btn btn-default" name="action" value="Delete"/>
	</form>	
	
</t:parent_page>

  