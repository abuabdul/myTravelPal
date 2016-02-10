<%@ include file="/WEB-INF/jsp/includes/siteTags.jsp"%>
<%-- TravelPlan View --%>
<div class="container">
	<div class="row">
		<div class="col-lg-12 text-center">
			<h1 class="board-head">Travel Plans</h1>
			<p class="lead sub-title">Modify or Remove your travel plans.</p>
			<div class="form-group">
		          <div class="col-sm-3"></div>
		          <div class="col-sm-6">
					 <c:if test="${travelPlanRemoved && not empty allTravelPlans}">
						<div class="alert alert-success">
				     		<a href="#" class="close" data-dismiss="alert">&times;</a> 
										Travel Plan Cancelled!!!
						</div>
					</c:if>
			 	  </div>
			      <div class="col-sm-3"></div>
			</div>
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
									<td>
									    <a href="#" class="editable" id="travelPlanDesc" data-pk="${plan.id}">${plan.travelPlanDesc}</a>
									</td>
									<td>
									   <a href="#" class="editable" id="startDate" data-pk="${plan.id}">${plan.startDate}</a>
									     &nbsp;
									   <a href="#" class="editable" id="startTime" data-pk="${plan.id}">${plan.startTime}</a>
									</td>
									<td>
									   <a href="#" class="editable" id="endDate" data-pk="${plan.id}">${plan.endDate}</a>
									     &nbsp;
									   <a href="#" class="editable" id="endTime" data-pk="${plan.id}">${plan.endTime}</a>
									</td>
									<td>
									    <a href="#" class="editable-travelmode" id="travelMode" data-value="${plan.travelMode}" data-pk="${plan.id}">${plan.travelMode}</a>
									</td>
									<td>
									    <a href="#" class="editable-traveltype" id="travelType" data-value="${plan.travelType}" data-pk="${plan.id}">${plan.travelType}</a>
									</td>
									<td>
									    <a href="#" class="editable" id="sideNote" data-pk="${plan.id}">${plan.sideNote}</a>
									</td>
									<td>
									   <a href="<c:url value='/secure/travel/${plan.id}/removePlans.go'/>">
											<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
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
