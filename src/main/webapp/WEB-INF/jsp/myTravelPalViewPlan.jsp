<%@ include file="/WEB-INF/jsp/includes/siteTags.jsp"%>
<%-- TravelPlan Board --%>
<div class="container">
	<div class="row">
		<div class="col-lg-12 text-center">
			<h1 class="board-head">Travel Plans</h1>
			<p class="lead sub-title">Modify or Remove your travel plans.</p>
			<c:url var="travelPlanUrl" value="/secure/travel/makePlans.go" />
			<div class="form-group">
				<div class="col-sm-3"></div>
				<div class="col-sm-6">
					<c:if test="${travelRemoved}">
						<div class="alert alert-success">
							<a href="#" class="close" data-dismiss="alert">&times;</a> Your
							Selected Travel Plan(s) Removed!!!
						</div>
					</c:if>
				</div>
				<div class="col-sm-3"></div>
			</div>
			<c:if test="${empty allTravelPlans}">
				<div class="table-responsive padding-3px">
					<div class="page-header">
						<h2>
							<small>No Travel Plans Found</small>
						</h2>
					</div>
				</div>
			</c:if>
			<c:if test="${not empty allTravelPlans}">
				<div class="table-responsive-travel">
					<table id="travelPlans" class="table table-hover table-bordered">
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
						<tfoot>
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
						</tfoot>
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
									<td><span class="glyhpicon glyphicon glyphicon-edit"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="glyhpicon glyphicon glyphicon-trash"></span></td>
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
