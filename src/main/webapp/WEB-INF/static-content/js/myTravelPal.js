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
 * @project   Note-Dovn App
 * @date      18-Dec-2015
 * @author    Abubacker Siddik A, Chennai, India <abuabdul86@hotmail.com>
 * @licensor  Apache License 2.0
 * @site      
 *
 */

//Inline mode
$.fn.editable.defaults.mode = 'inline';

$(function () {
	  
	  $('[data-toggle="tooltip"]').tooltip({
		  trigger: 'focus' 
	  });
	  
	  //Editable fields
	  $('.editable').editable({
	        type: 'text',
	        url: baseURL + '/secure/scratch/updateNote.go',
	        success: function(response, newValue){
	            if(response.status == 'error') return response.msg;
	        }
	  });
	  
	  //Removing notes
	  $(".remove").click(function(){
          $.post(this.id);
          $(this).parent().fadeOut("slow");
       });
});