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

	var baseURL = '';
	/* Bootstrap editable */
	$('.editable').editable({
		type : 'text',
		url : baseURL + 'http://localhost:8697/myTravelPal/secure/travel/updatePlans.go',
		success : function(response, newValue) {
			if (response.status == 'error')
				return response.msg;
		}
	});

	$('.travel-start').datepicker({
		format : "mm/dd/yyyy",
		weekStart : 0,
		todayBtn : "linked",
		multidate : false,
		autoclose : true,
		todayHighlight : true
	});

	/* Full Calendar Plugin */
	$('#TravelBoard').fullCalendar({
		header : {
			left : 'prev,next today',
			center : 'title',
			right : 'month,basicWeek,basicDay'
		},
		defaultDate : '2016-01-12',
		editable : true,
		eventLimit : true, // allow "more" link when too many events
		events : [ {
			title : 'All Day Event',
			start : '2016-01-01'
		}, {
			title : 'Long Event',
			start : '2016-01-07',
			end : '2016-01-10'
		}, {
			id : 999,
			title : 'Repeating Event',
			start : '2016-01-09T16:00:00'
		}, {
			id : 999,
			title : 'Repeating Event',
			start : '2016-01-16T16:00:00'
		}, {
			title : 'Conference',
			start : '2016-01-11',
			end : '2016-01-13'
		}, {
			title : 'Meeting',
			start : '2016-01-12T10:30:00',
			end : '2016-01-12T12:30:00'
		}, {
			title : 'Lunch',
			start : '2016-01-12T12:00:00'
		}, {
			title : 'Meeting',
			start : '2016-01-12T14:30:00'
		}, {
			title : 'Happy Hour',
			start : '2016-01-12T17:30:00'
		}, {
			title : 'Dinner',
			start : '2016-01-12T20:00:00'
		}, {
			title : 'Birthday Party',
			start : '2016-01-13T07:00:00'
		}, {
			title : 'Click for Google',
			url : 'http://google.com/',
			start : '2016-01-28'
		} ]
	});

	// $(".fc-title").append("<i class='fa fa-motorcycle fa-sm'></i>");
});