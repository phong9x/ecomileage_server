(function($) {
	  var current = null;

	  var nc = window.tramsNC = window.tramsNC || {};

	  nc.confirm = function() {
	    current.data("confirmed", true);
	    current.trigger("click");
	  };

	  $(document).on('click', '.trams-need-confirm', function(e) {
	    var self = $(this);
	    var confirmed = self.data("confirmed");

	    if (confirmed) return true;

	    var popupId = self.attr("data-trams-confirm-popup");
	    current = self;

	    $(popupId).modal("show");

	    return false;
	  });
	})(jQuery);

$("#button-goal-user-show").on("click", function(event){
      $('#goal-user-show').hide();
      $('#goal-user-edit').show();
  });
  
  $("#button-goal-user-delete").on("click", function(event){
	  $('#goal-user-show').show();
      $('#goal-user-edit').hide();
  });
  
  $("#button-goal-co2-show").on("click", function(event){
      $('#goal-co2-show').hide();
      $('#goal-co2-edit').show();
  });
  
  $("#button-goal-co2-delete").on("click", function(event){
	  $('#goal-co2-show').show();
      $('#goal-co2-edit').hide();
  });
  
  $("#button-edit-show").on("click", function(event){
      $('#div-show').hide();
      $('#div-edit').show();
  });
  
  $("#button-edit-delete").on("click", function(event){
	  $('#div-show').show();
      $('#div-edit').hide();
  });
  
  function formatDate(date){
	  var yy = date.getFullYear();
	  var dd = date.getDate();
	  var mm = date.getMonth()+1; //January is 0!
	  var yyyy = date.getFullYear();
	    if(dd<10){
	        dd='0'+dd;
	    } 
	    if(mm<10){
	        mm='0'+mm;
	    } 
	    return yy+"/"+mm+"/"+dd;
  }
  
  function formatDateTime(date){
	  var yy = date.getFullYear();
	  var dd = date.getDate();
	  var mm = date.getMonth()+1; //January is 0!
	  var yyyy = date.getFullYear();
	    if(dd<10){
	        dd='0'+dd;
	    } 
	    if(mm<10){
	        mm='0'+mm;
	    } 
	    var h = date.getHours();
	    if(h<10){
	        h='0'+h;
	    } 
	    var m = date.getMinutes();
	    if(m<10){
	        m='0'+m;
	    } 
	    return yy+"/"+mm+"/"+dd+" "+h+":"+m;
  }
  
  function validateFormVehicleCode(){
		var checkSerialNumber = $('input[name=checkSerialNumber]').val();
		var existsSerialNumber = $('input[name=existsSerialNumber]').val();
		console.log("checkSerialNumber:"+checkSerialNumber);
		console.log('existsSerialNumber: '+existsSerialNumber);
		if(checkSerialNumber === 'false'){
			$('#popupNotCheckSerialNumber').modal('show');
		}else if(existsSerialNumber === 'true'){
			console.log('existsSerialNumber: '+existsSerialNumber);
			$('#popupExistsSerialNumber').modal('show');
		}else{
			$('#form-vehicle-code').submit()
		}
		
	}
  
  $(document).on('keyup', 'input[name=serialNumber]', function(){
		var serialNumberOld = $('input[name=serialNumberOld]').val();
		$('#serial-message-error').hide();
		$('#serial-message-success').hide();
		if(serialNumberOld == $(this).val()){
			$('input[name=checkSerialNumber]').val(true);
			$('#button-check-serial-number').attr('disabled', true);
		}else{
			$('input[name=checkSerialNumber]').val(false);
			$('#button-check-serial-number').attr('disabled', false);
		}
	});
  
  $(document).on('keyup', '#usernameSearch', function(){
		var usernameSearch = $('input[name=usernameSearch]').val();
		if(usernameSearch == ''){
			$('#button-search-user').attr('disabled', true);
		}else{
			$('#button-search-user').attr('disabled', false);
		}
	});
  
  $(document.body).on('click', '#popup-find-user tr', function(){
	  	$('input[name=username]').val($(this).find('#username').val());
		$('input[name=fullname]').val($(this).find('#fullname').val());
		$('input[name=postCode]').val($(this).find('#postcode').val());
		$('input[name=postcode]').val($(this).find('#postcode').val());
		$('input[name=address]').val($(this).find('#address').val());
		$('input[name=addressDetail]').val($(this).find('#addressDetail').val());
		$('input[name=location]').val($(this).find('#address').val());
		$('input[name=phone]').val($(this).find('#phone').val());
		$('input[name=userId]').val($(this).find('#userId').val());
		$('input[name=email]').val($(this).find('#email').val());
		$('#popup-find-user').modal('hide');
	});
  
  $(document.body).on('click', '#popup-add-member-group tr', function(){
		$('input[name=addMemberId]').val($(this).find('#userId').val());
		$('#form-add-member').submit();
	});
  
  $(document.body).on('click', '#popup-find-member-group tr', function(){
	  	
		
		
		var url="/ajax/update-group-manager";
		var groupId = $('input[name=groupId]').val();
		var username = $(this).find('#username').val();
		var fullname = $(this).find('#fullname').val();
		var userId =   $(this).find('#userId').val();	
		$.ajax({
			type: "POST",
			url: url,
			data: {groupManagerId: userId,groupId:groupId},
			success: function(response)
			{
				if(response.code == 200){
					$('#groupManagerFullname').text(fullname);
					$('#groupManagerUsername').text(username);
					$('input[name=groupManagerId]').val(userId);
					$('#popup-find-member-group').modal('hide');
				}
			},
			error : function(e) {
				alert("ERROR: "+ e);
				}
			});
	});
  
  function validateFormBikeCode(){
		var checkSerialNumber = $('input[name=checkSerialNumber]').val();
		var existsSerialNumber = $('input[name=existsSerialNumber]').val();
		if(checkSerialNumber === 'false'){
			$('#popupNotCheckSerialNumber').modal('show');
		}else if(existsSerialNumber === 'true'){
			$('#popupExistsSerialNumber').modal('show');
		}else {
			var url ="/ajax/find-bike";
			var userId =$('input[name=userId]').val();
			var serialNumber = $('input[name=serialNumber]').val();
			console.log(userId);
			console.log(serialNumber);
				$.ajax({
					type: "POST",
					url: url,
					data: {userId: userId, serialNumber:serialNumber},
					success: function(response)
					{
						console.log("ajx running");
						console.log("response data: "+response.code);
						if(response.code == 200){
							$('#popupMatchSerialNumber').modal('show');
						}else{
							$('#form-bike-code').submit()
						}
					},
					error : function(e) {
						alert("ERROR: "+ e);
						}
					});
		}
	}
  
  $(document.body).on('click', '#popup-postcode-member tr', function(){
	  	console.log(11111111);
	  	$('input[name=postcode]').val($(this).find('#postcode').val());
		$('input[name=address]').val($(this).find('#address').val());
		$('input[name=addressDetail]').val($(this).find('#addressDetail').val());
		$('input[name=city]').val($(this).find('#city').val());
		$('input[name=district]').val($(this).find('#district').val());
		$('#popup-postcode-member').modal('hide');
	});
  
  
  function validateFormMemberCreated(){
		var password = $('input[name=password]').val();
		if(password.length < 4){
			alert("비밀번호는 4자 이상 입력해야 합니다.")
		} else {
			$('#form-member-creat').submit();
				
		}
	}
  
  $(document).ready(function(){
	  $('input.timepicker').timepicker({
		  showMeridian:false
	    });
		
	})
