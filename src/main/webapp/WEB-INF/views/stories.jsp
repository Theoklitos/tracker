<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>	
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:parent_page title="Stories">

	<c:if test="${not empty stories}">
		<div id="story-button-panel">
				<button id="show-all-stories" onclick="window.location.href='${rootUrl}/stories?show=all'">Show All Stories</button>
				<button id="show-open-stories" onclick="window.location.href='${rootUrl}/stories?show=open'">Show Open Stories</button>
		</div>
		<table id="stories-table" class="table table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th>Creation Date</th>
					<th>Title</th>
					<th>Description</th>
					<th>Developer</th>
					<th>Story Status</th>
					<th>Points</th>
				</tr>
			</thead>
			<tbody>			
			<c:forEach items="${stories}" var="story">
				<tr>
					<td>${story.id}</td>
					<td>${story.prettyCreationDate}</td>
					<td>${story.title}</td>
					<td>${story.description}</td>
					<td>${story.developer}</td>
					<td>${story.storyStatus}</td>
					<td>${story.points}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</c:if>
	<c:if test="${empty stories}">
		<p class="error-message">No stories found.</p>
	</c:if>
	
	<p class="error-message"><c:out value="${errorMessage}" /></p>
	
	<form id="stories-form" role="form" action="${rootUrl}/stories" method="post">
		<div class="form-group">
			<label for="storyId">ID</label>
			<input type="text" class="form-control" name="id" id="storyId" placeholder="Leave empty to create new story, or enter existing ID to edit story...">
		</div>
		<div class="form-group">
			<label for="storyTitle">Title</label>
			<input type="text" class="form-control" name="title" id="storyTitle" placeholder="Enter a title for your story...">
		</div>
		<div class="form-group">
			<label for="storyDesc">Description</label>
			<textarea rows="5" cols="50" class="form-control" name="description" id="storyDesc" placeholder="Describe this story..."></textarea>
		</div>
		<div class="form-group">
			<label for="storyDeveloper">Developer</label>
			<select class="form-control" id="storyDeveloper" name="developer">
			<option value="NONE">No-one</option>
			<c:forEach items="${developers}" var="developer">
				<option value="${developer.name}">${developer.name}</option>
			</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<label for="storyStatus">Status</label>
			<select class="form-control" id="storyStatus" name="status">			
			<option value="NEW">New</option>
			<option value="ESTIMATED">Estimated</option>
			<option value="COMPLETED">Completed</option>
			</select>
		</div>
		<div class="form-group">
			<label for="storyPoints">Story Points</label>
			<input type="text" class="form-control" name="points" id="storyPoints" placeholder="Enter your story point estimation...">
		</div>		
		<input type="submit" class="btn btn-default" name="action" value="Create or Edit Story" />
	</form>
</t:parent_page>
