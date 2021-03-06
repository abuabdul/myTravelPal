<%@ include file="/WEB-INF/jsp/includes/siteTags.jsp"%>
    <%-- TravelPlan Board --%>
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h1 class="board-head">Travel Plans</h1>
                <p class="lead sub-title">Make your travel plans. We will keep it safe for you.</p>
                <c:url var="travelPlanUrl" value="/secure/travel/makePlans.go"/>
                    <form:form id="myTravelPalPlan" class="form-horizontal" role="form" modelAttribute="myTravelPalPlan" action="${travelPlanUrl}" method="post">
		                   <div class="form-group">
		                       <div class="col-sm-3"></div>
		                       <div class="col-sm-6">
								    <c:if test="${travelPlanned}">
										<div class="alert alert-success">
											<a href="#" class="close" data-dismiss="alert">&times;</a> 
											Travel Planned!!!
										</div>
							        </c:if>
							   </div>
							   <div class="col-sm-3"></div>
						   </div>
						   <div class="form-group">
							   <div class="col-sm-3"></div>
							   <div class="col-sm-6">
							      <form:textarea rows="1" class="form-control" path="travelPlanDesc" placeholder="Travel Plan"
							      			  data-toggle="tooltip" data-placement="top"
								              title="What is your travel plan? Tell us!!"/>
							   </div>
							   <div class="col-sm-3"></div>
						   </div>
	  					   <div class="form-group">
		  					   <div class="col-sm-3"></div>
							   <div class="col-sm-6">
							     <div class="input-group">
								       <form:input type="text" class="form-control travel-datepicker" readonly="true" path="startDate" placeholder="Start Date" 
									               data-toggle="tooltip" data-placement="top"
									               title="When does it start?"/>
								       <div class="input-group-addon">
	        							  <span class="glyphicon glyphicon-calendar"></span>
	    							   </div>
							      </div>
							    </div>
								<div class="col-sm-3"></div>
						   </div>
	  					   <div class="form-group">
		  					   <div class="col-sm-3"></div>
							    <div class="col-sm-6">
							      <div class="input-group">
								       <form:input type="text" class="form-control travel-datepicker" readonly="true" path="endDate" placeholder="End Date"
								      			  data-toggle="tooltip" data-placement="top"
									              title="Does it end? When?"/>	
								       <div class="input-group-addon">
	        							  <span class="glyphicon glyphicon-calendar"></span>
	    							   </div>
							      </div>
							    </div>
								<div class="col-sm-3"></div>
						   </div>
						   <div class="form-group">
							   <div class="col-sm-3"></div>
							   <div class="col-sm-3">
								  <div class="input-group bootstrap-timepicker timepicker">
								       <form:input type="text" class="form-control travel-timepicker" path="startTime" placeholder="Start Time"
							      			  data-toggle="tooltip" data-placement="top"
								              title="Start time?"/>
								       <div class="input-group-addon">
	        							  <span class="glyphicon glyphicon-time"></span>
	    							   </div>
							      </div>            
							    </div>
							    <div class="col-sm-3">
								      <div class="input-group bootstrap-timepicker timepicker">
									       <form:input type="text" class="form-control travel-timepicker" path="endTime" placeholder="End Time"
	   							      			  data-toggle="tooltip" data-placement="top"
									              title="End time? Any time on start date or After 9am on any end date"/>
									       <div class="input-group-addon">
		        							  <span class="glyphicon glyphicon-time"></span>
		    							   </div>
								      </div>
							     </div> 
							   <div class="col-sm-3"></div>
						   </div>
						   <div class="form-group">
							   <div class="col-sm-3"></div>
							   <div class="col-sm-6">
							      <form:select class="form-control" path="travelMode"
							      			  data-toggle="tooltip" data-placement="top"
								              title="You can choose the mode of travel you want to make.">
								              <form:option value="">Select</form:option>
	                                          <form:options items="${travelModes}"></form:options>
	                              </form:select>            
							   </div>
							   <div class="col-sm-3"></div>
						   </div>
						   <div class="form-group">
							   <div class="col-sm-3"></div>
							   <div class="col-sm-6">
							      <form:select class="form-control" path="travelType"
							      			  data-toggle="tooltip" data-placement="top"
								              title="Do you want to take solo trip/family? Choose travel type here.">
								              <form:option value="">Select</form:option>
	                                          <form:options items="${travelTypes}"></form:options>
	                              </form:select>
							   </div>
							   <div class="col-sm-3"></div>
						   </div>
						   <div class="form-group">
							   <div class="col-sm-3"></div>
							   <div class="col-sm-6">
							      <form:input type="text" class="form-control" path="sideNote" placeholder="Side Note"
							      			  data-toggle="tooltip" data-placement="top"
								              title="Thinking about peculiar/special note about this travel?"/>
							   </div>
							   <div class="col-sm-3"></div>
						   </div>
						   <div class="form-group">
							   <div class="col-sm-3"></div>
							   <div class="col-sm-6">
							       <button type="button" id="resetButton" class="btn btn-sm btn-default">Clear</button>
							       &nbsp;
							       <button type="submit" class="btn btn-sm btn-primary">Plan</button>
							       &nbsp;
							       <a role="button" class="btn btn-sm btn-primary" href="<c:url value='/secure/travel/viewPlans.go'/>">View / Modify Plans</a>
							   </div>
							   <div class="col-sm-3"></div>
						   </div>
					</form:form>
            </div>
        </div>
        <!-- /.row -->
    </div>
    <!-- /.container -->