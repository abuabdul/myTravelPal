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
 * @project   Note-Dovn App
 * @date      18-Dec-2015
 * @author    Abubacker Siddik A, Chennai, India <abuabdul86@hotmail.com>
 * @licensor  Apache License 2.0
 * @site      
 *
 */

/*global validation rules and messages */

var NOTEDOVN = window.NOTEDOVN || {};

(function(window, document, $, NOTEDOVN) {
    "use strict";
    NOTEDOVN.validationUtil = (function() {

        var validationRules = function() {
        	
            $('#scratchPadForm').bootstrapValidator({
                live: 'enabled',
                message: 'This value is not valid',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    /* for note-dovn form */
                	aboutNote: {
                        validators: {
                            notEmpty: {
                                message: 'Please write something to tell about this note'
                            }
                        }
                    },
                    noteMsg: {
                        validators: {
                            notEmpty: {
                                message: 'The key note can never be empty by any chance'
                            }
                        }
                    }
                }
            });
            
            $('#resetButton').on('click touchstart', function() {
            	bootstrapValidatorObj('#scratchPadForm').resetForm(true);
            	$("#category,#reasonNote,#sideNote").val('');
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

}(window, document, jQuery, NOTEDOVN));

/* Bind the validation utilities function to document load */
jQuery(NOTEDOVN.validationUtil.initFunction);