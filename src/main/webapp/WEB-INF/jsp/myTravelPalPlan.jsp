<%@ include file="/WEB-INF/jsp/includes/siteTags.jsp"%>
    <%-- TravelPlan Board --%>
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h1 class="board-head">Travel Plans</h1>
                <p class="lead"></p>
                <ul class="list-unstyled">
                    <li></li>
                </ul>
                <c:url var="travelPlanUrl" value="/secure/travel/makePlans.go"/>
                    <form:form id="myTravelPalPlan" class="form-horizontal" role="form" modelAttribute="myTravelPalPlan" action="${travelPlanUrl}" method="post">
		                   <div class="form-group">
		                       <div class="col-sm-3"></div>
		                       <div class="col-sm-6">
								    <c:if test="${travelPlanned}">
										<div class="alert alert-success">
											<a href="#" class="close" data-dismiss="alert">&times;</a> 
											Travel is Planned!!!
										</div>
							        </c:if>
							   </div>
							   <div class="col-sm-3"></div>
						   </div>
						   <div class="form-group">
							   <div class="col-sm-3"></div>
							   <div class="col-sm-6">
							      <form:input type="textarea" class="form-control no-border" path="travelPlanDesc" placeholder="Travel Plan"
							      			  data-toggle="tooltip" data-placement="top"
								              title="You can also tell us elaborately anything with this keynote message?"/>
							   </div>
							   <div class="col-sm-3"></div>
						   </div>
	  					   <div class="form-group">
		  					   <div class="col-sm-3"></div>
							   <div class="col-sm-6">
								   <form:input type="text" class="form-control no-border" path="startDate" placeholder="Start Date" 
								               data-toggle="tooltip" data-placement="top"
								               title="Category just helps to classify notes"/>
							    </div>
								<div class="col-sm-3"></div>
						   </div>
						   <div class="form-group">
							  <div class="col-sm-3"></div>
							  <div class="col-sm-6">
							      <form:input type="text" class="form-control no-border" path="startTime" placeholder="Start Time"
							      			  data-toggle="tooltip" data-placement="top"
								              title="Tell us about note, what is it all about?"/>
							  </div>
							  <div class="col-sm-3"></div>
						   </div>
						   <div class="form-group">
							   <div class="col-sm-3"></div>
							   <div class="col-sm-6">
							      <form:input type="text" class="form-control no-border" path="endDate" placeholder="End Date"
							      			  data-toggle="tooltip" data-placement="top"
								              title="You could have done something, why is it you want a note?"/>							      
							   </div>
							   <div class="col-sm-3"></div>
						   </div>
						   <div class="form-group">
							   <div class="col-sm-3"></div>
							   <div class="col-sm-6">
							      <form:input type="text" class="form-control no-border" path="endTime" placeholder="End Time"
   							      			  data-toggle="tooltip" data-placement="top"
								              title="Place your key note text here"/>
							   </div>
							   <div class="col-sm-3"></div>
						   </div>
						   <div class="form-group">
							   <div class="col-sm-3"></div>
							   <div class="col-sm-6">
							      <form:select class="form-control no-border" path="travelMode"
							      			  data-toggle="tooltip" data-placement="top"
								              title="You can also tell us elaborately anything with this keynote message?"/>
							   </div>
							   <div class="col-sm-3"></div>
						   </div>
						   <div class="form-group">
							   <div class="col-sm-3"></div>
							   <div class="col-sm-6">
							      <form:select class="form-control no-border" path="travelType"
							      			  data-toggle="tooltip" data-placement="top"
								              title="You can also tell us elaborately anything with this keynote message?"/>
							   </div>
							   <div class="col-sm-3"></div>
						   </div>
						   <div class="form-group">
							   <div class="col-sm-3"></div>
							   <div class="col-sm-6">
							      <form:input type="text" class="form-control no-border" path="sideNote" placeholder="Any Side Note about this Plan"
							      			  data-toggle="tooltip" data-placement="top"
								              title="You can also tell us elaborately anything with this keynote message?"/>
							   </div>
							   <div class="col-sm-3"></div>
						   </div>
						   <div class="form-group">
							   <div class="col-sm-3"></div>
							   <div class="col-sm-6">
							       <button type="button" id="resetButton" class="btn btn-sm btn-default">Clear</button>
							       &nbsp;
							      <button type="submit" class="btn btn-sm btn-primary">Plan</button>
							   </div>
							   <div class="col-sm-3"></div>
						   </div>
					</form:form>
            </div>
        </div>
        <!-- /.row -->
    </div>
    <!-- /.container -->
