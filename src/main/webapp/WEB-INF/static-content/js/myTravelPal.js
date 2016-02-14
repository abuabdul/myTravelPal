/*
 * Copyright 2013-2016 abuabdul.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

/*
 *
 * @project   myTravelPal App
 * @date      15-Dec-2015
 * @author    Abubacker Siddik A, Chennai, India <abuabdul86@hotmail.com>
 * @licensor  Apache License 2.0
 * @site      
 *
 */

//Inline mode
$.fn.editable.defaults.mode = 'inline';
$(function() {

	/* Bootstrap tooltip */
	$('[data-toggle="tooltip"]').tooltip({
		trigger : 'focus'
	});

	/* Bootstrap datatable */
	var travelPlans = $('#TravelPlans').DataTable({
		"columnDefs" : [ {
			"searchable" : false,
			"orderable" : false,
			"targets" : [ 0, 7 ]
		} ],
		"order" : [ [ 1, 'asc' ] ]
	});

	travelPlans.on('order.dt search.dt', function() {
		travelPlans.column(0, {
			search : 'applied',
			order : 'applied'
		}).nodes().each(function(cell, i) {
			cell.innerHTML = i + 1;
		});
	}).draw();

	/* Bootstrap editable */
	$('.editable').editable({
		type : 'text',
		url : baseURL + '/secure/travel/updatePlans.go',
		success : function(response, newValue) {
			if (response.status == 'error')
				return response.msg;
		}
	});
	
	$('.editable-travelmode').editable({
		type : 'select',
		source: baseURL + '/secure/travel/loadTravelMode.go',
		url : baseURL + '/secure/travel/updatePlans.go',
		success : function(response, newValue) {
			if (response.status == 'error')
				return response.msg;
		}
	});
	
	$('.editable-traveltype').editable({
		type : 'select',
		source: baseURL + '/secure/travel/loadTravelType.go',
		url : baseURL + '/secure/travel/updatePlans.go',
		success : function(response, newValue) {
			if (response.status == 'error')
				return response.msg;
		}
	});

	$('.travel-datepicker').datepicker({
		format : "yyyy-mm-dd",
		weekStart : 0,
		multidate : false,
		autoclose : true,
		todayHighlight: true,
		assumeNearbyYear: true
	});

	/* Full Calendar Plugin */
	var title = '';
	$('#TravelBoard').fullCalendar({
		header : {
			left : 'prev,next today',
			center : 'title',
			right : 'month,basicWeek,basicDay'
		},
		defaultDate : moment().format('YYYY-MM-DD'),
		eventLimit : true, // allow "more" link when too many events
	    events: baseURL + '/secure/travel/planLoad.go',
	    eventMouseover: function(calEvent, jsEvent, view) {
	    	title = $(this).html();
	        $(this).html(getGlyph(calEvent.travelMode)+' ['+calEvent.travelType+'] '+calEvent.title);
	    },
	    eventMouseout: function(calEvent, jsEvent, view) {
	    	$(this).html(title);
	    } 
	});
	
	var getGlyph = function(mode){
		switch (mode) {
		    case 'Motor Bike':
		        return '<i class="fa fa-motorcycle"></i>';
		    case 'Bi Cycle':
		        return '<i class="fa fa-bicycle"></i>';
		    case 'Car':
		    	return '<i class="fa fa-car"></i>';
		    case 'Bus':
		    	return '<i class="fa fa-bus"></i>';
		    case 'Train':
		    	return '<i class="fa fa-train"></i>';
		    case 'Flight':
		    	return '<i class="fa fa-plane"></i>';
		    case 'Ship':
		    	return '<i class="fa fa-ship"></i>';
		    default:
		    	return '';
		}
	}
});