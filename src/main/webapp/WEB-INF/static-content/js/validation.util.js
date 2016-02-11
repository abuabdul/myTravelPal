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
 * This file contains the code for the global validation rules.
 *
 * @project   myTravelPal App
 * @date      15-Dec-2015
 * @author    Abubacker Siddik A, Chennai, India <abuabdul86@hotmail.com>
 * @licensor  Apache License 2.0
 * @site      
 *
 */

/*global validation rules and messages */
var MYTRAVELPAL = window.MYTRAVELPAL || {};

(function(window, document, $, MYTRAVELPAL) {
    "use strict";
    MYTRAVELPAL.validationUtil = (function() {

        var validationRules = function() {
        	
            $('#myTravelPalPlan').bootstrapValidator({
                live: 'enabled',
                message: 'This value is not valid',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    /* for mytravelpal form */
                	travelPlanDesc: {
                        validators: {
                            notEmpty: {
                                message: 'Travel plan title is important'
                            }
                        }
                    },
                    startDate: {
                        validators: {
                            notEmpty: {
                                message: 'The start date is important for travel plan'
                            }
                        }
                    },
                    travelMode:{
                    	validators: {
                            notEmpty: {
                                message: 'Please select a travel mode'
                            }
                        }
                    },
                    travelType:{
                    	validators: {
                            notEmpty: {
                                message: 'Please select a travel type'
                            }
                        }
                    }
                }
            });
            
            $('#resetButton').on('click touchstart', function() {
            	bootstrapValidatorObj('#myTravelPalPlan').resetForm(true);
            	$("#startTime,#endDate,#endTime,#sideNote").val('');      	
            });
            
	       	 var bootstrapValidatorObj = function(formName){
	     	   	return $(formName).data('bootstrapValidator');
	     	 }
            
        };
        
        /* Initiate Function */
        var initFunction = function() {
            validationRules(); 
        };

        /* Return public properties/methods */
        return {
            initFunction: initFunction
        };

    }());

}(window, document, jQuery, MYTRAVELPAL));

/* Bind the validation utilities function to document load */
jQuery(MYTRAVELPAL.validationUtil.initFunction);