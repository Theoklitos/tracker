<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>	

<t:parent_page title="Overview">	
	<div id="app-overview">
		<p><strong>Currently tracking ${developerNo} developer(s) and ${issueNo} issue(s) in total.</strong></p>
		<p>Open stories: ${openStoriesNo}</p>
		<p>Non-resolved bugs: ${openBugsNo}</p><br/>
		<p>Please use the menu on top to navigate.</p>	
	</div>	
</t:parent_page>

