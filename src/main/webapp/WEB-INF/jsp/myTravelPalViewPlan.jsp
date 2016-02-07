<%@ include file="/WEB-INF/jsp/includes/siteTags.jsp"%>
<%-- TravelPlan View --%>
<div class="container">
	<div class="row">
		<div class="col-lg-12 text-center">
			<h1 class="board-head">Travel Plans</h1>
			<p class="lead sub-title">Modify or Remove your travel plans.</p>
			<c:if test="${empty allTravelPlans}">
				<div>
					<h2>
						<small>No Travel Plans Found</small>
					</h2>
				</div>
			</c:if>
			<c:if test="${not empty allTravelPlans}">
				<div class="table-responsive-travel">
					<table id="TravelPlans" class="table table-hover table-bordered">
						<thead>
							<tr>
								<th>#</th>
								<th>Travel Plan</th>
								<th>Start</th>
								<th>End</th>
								<th>Mode</th>
								<th>Type</th>
								<th>Note</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${allTravelPlans}" var="plan">
								<tr>
									<td></td>
									<td>${plan.travelPlanDesc}</td>
									<td>${plan.startDate}&nbsp;${plan.startTime}</td>
									<td>${plan.endDate}&nbsp;${plan.endTime}</td>
									<td>${plan.travelMode}</td>
									<td>${plan.travelType}</td>
									<td>${plan.sideNote}</td>
									<td>
									   <a href=""><span	class="glyhpicon glyphicon glyphicon-edit"></span></a>
										&nbsp;&nbsp;&nbsp;&nbsp; 
									   <a href="<c:url value='/secure/travel/${plan.id}/removePlans.go'/>">
											<span class="glyhpicon glyphicon glyphicon-trash"></span>
									   </a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
		</div>
	</div>
	<!-- /.row -->
</div>
<!-- /.container -->
