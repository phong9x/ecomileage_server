$(document.body).on('click', '#button-check-serial-number', function(){
	var url ="/ajax/check-serial-number"
	var serialNumber = $('input[name=serialNumber]').val();
	var typeVehicle = $('input[name=typeVehicle]').val();
	$.ajax({
		type: "POST",
		url: url,
		data: {serialNumber: serialNumber, typeVehicle: typeVehicle},
		success: function(response)
		{
			console.log("ajx running");
			console.log("response data: "+response.code);
			$('input[name=checkSerialNumber]').val(true);
			if(response.code == 200){
				$('#serial-message-error').hide();
				$('#serial-message-success').show();
				$('input[name=existsSerialNumber]').val(false);
			}else{
				$('#serial-message-error').show();
				$('#serial-message-success').hide();
				$('input[name=existsSerialNumber]').val(true);
			}
		},
		error : function(e) {
			alert("ERROR: "+ e);
			}
		});
	
});

$(document.body).on('click', '#button-search-user', function(){
	var usernameSearch = $('#usernameSearch').val();
	callApiFindUser(usernameSearch)
});

$(document.body).on('click', '#button-popup-search-user', function(){
	var usernameSearch = $('#popup-username').val();
	callApiFindUser(usernameSearch)
});

function callApiFindMemberGroup(groupId, username) {
	var url ="/ajax/find-member-group";
	console.log("groupId:"+groupId);
	console.log("username:"+username);
	$.ajax({
			type: "POST",
			url: url,
			data: {groupId: groupId, username:username},
			success: function(response)
			{
				console.log("ajx running");
				console.log("response data: "+response.data);
				if(response.code == 200){
					$('#popup-find-member-group').modal('show');
					var html= "";
					for (var i = 0; i < response.data.length; i++) {
						html+="<tr>";
							if(response.data[i].username != null){
								html+="<td class='td-find-id'>"+response.data[i].username+"</td>";
							}else{
								html+="<td class='td-find-id'>"+response.data[i].fullname+"</td>";
							}
							html+="<td>"+response.data[i].fullname+"</td>";
							html+="<input type='hidden' id='userId' value='"+response.data[i].id+"'>";
							if(response.data[i].username != null){
								html+="<input type='hidden' id='username' value='"+response.data[i].username+"'>";
							}else{
								html+="<input type='hidden' id='username' value='"+response.data[i].fullname+"'>";
							}
							html+="<input type='hidden' id='fullname' value='"+response.data[i].fullname+"'>";
							html+="<input type='hidden' id='phone' value='"+response.data[i].phone+"'>";
							html+="<input type='hidden' id='postcode' value='"+response.data[i].postcode+"'>";
							html+="<input type='hidden' id='address' value='"+response.data[i].address+"'>";
						html+="<tr>";
					}
					$('#popup-find-member-group tbody').html(html);
				}
			},
			error : function(e) {
				alert("ERROR: "+ e);
				}
			});
}

function callApiFindGroupMember(usernameSearch) {
	var url ="/ajax/find-user"
		$.ajax({
			type: "POST",
			url: url,
			data: {username: usernameSearch},
			success: function(response)
			{
				console.log("ajx running");
				console.log(response.data);
				if(response.code == 200){
					$('#popup-username-group').val(usernameSearch);
					$('#popup-add-member-group').modal('show');
					var html= "";
					for (var i = 0; i < response.data.length; i++) {
						html+="<tr>";
							if(response.data[i].username != null){
								html+="<td class='td-find-id'>"+response.data[i].username+"</td>";
							}else{
								html+="<td class='td-find-id'>"+response.data[i].fullname+"</td>";
							}
							html+="<td class='td-find-id'>"+response.data[i].fullname+"</td>";
							html+="<input type='hidden' id='userId' value='"+response.data[i].id+"'>";
							if(response.data[i].username != null){
								html+="<input type='hidden' id='username' value='"+response.data[i].username+"'>";
							}else{
								html+="<input type='hidden' id='username' value='"+response.data[i].fullname+"'>";
							}
							html+="<input type='hidden' id='fullname' value='"+response.data[i].fullname+"'>";
							html+="<input type='hidden' id='phone' value='"+response.data[i].phone+"'>";
							html+="<input type='hidden' id='postcode' value='"+response.data[i].postcode+"'>";
							html+="<input type='hidden' id='address' value='"+response.data[i].address+"'>";
						html+="<tr>";
					}
					$('#popup-add-member-group tbody').html(html);
				}
			},
			error : function(e) {
				alert("ERROR: "+ e);
				}
			});
}


function callApiFindUser(usernameSearch) {
	var url ="/ajax/find-user"
		$.ajax({
			type: "POST",
			url: url,
			data: {username: usernameSearch},
			success: function(response)
			{
				console.log("ajx running");
				console.log(response.data);
				if(response.code == 200){
					$('#popup-username').val(usernameSearch);
					$('#popup-find-user').modal('show');
					var html= "";
					for (var i = 0; i < response.data.length; i++) {
						html+="<tr>";
							if(response.data[i].username != null){
								html+="<td class='td-find-id'>"+response.data[i].username+"</td>";
							}else{
								html+="<td class='td-find-id'>"+response.data[i].fullname+"</td>";
							}
							html+="<td class='td-find-id'>"+response.data[i].fullname+"</td>";
							html+="<input type='hidden' id='userId' value='"+response.data[i].id+"'>";
							if(response.data[i].username != null){
								html+="<input type='hidden' id='username' value='"+response.data[i].username+"'>";
							}else{
								html+="<input type='hidden' id='username' value='"+response.data[i].fullname+"'>";
							}
							html+="<input type='hidden' id='fullname' value='"+response.data[i].fullname+"'>";
							html+="<input type='hidden' id='phone' value='"+response.data[i].phone+"'>";
							html+="<input type='hidden' id='postcode' value='"+response.data[i].postcode+"'>";
							html+="<input type='hidden' id='address' value='"+response.data[i].address+"'>";
							html+="<input type='hidden' id='addressDetail' value='"+response.data[i].addressDetail+"'>";
							html+="<input type='hidden' id='email' value='"+response.data[i].email+"'>";
						html+="<tr>";
					}
					$('#popup-find-user tbody').html(html);
				}
			},
			error : function(e) {
				alert("ERROR: "+ e);
				}
			});
}

$(document.body).on('click', '#popup-postcode-member #button-search-address', function(){
	var url ="http://www.juso.go.kr/addrlink/addrLinkApiJsonp.do";
	var confmKey ="U01TX0FVVEgyMDE3MTAxNDEzMjgzMzEwNzQwMzY=";
	var resultType ="json";
	var currentPage ="1";
	var countPerPage ="10";
	var keyword =$('#popup-postcode-member input[name=key]').val();
	$.ajax({
		type: "POST",
		url: url,
		dataType: 'text',
		contentType: 'application/x-www-form-urlencoded',
		data: {confmKey: confmKey, resultType:resultType, currentPage:currentPage, countPerPage:countPerPage, keyword:keyword},
		success: function(response)
		{
			console.log("ajx running");
			var myjson = response.substring(1,response.length-1);
			var data= $.parseJSON(myjson);
			var html= "";
			if(data.results.common.totalCount > 0){
				
				for (var i = 0; i < data.results.juso.length; i++) {
					html+="<tr style='cursor: pointer;'>";
						html+="<td class='width75'>"+data.results.juso[i].roadAddr+"</td>";
						html+="<td>"+data.results.juso[i].zipNo+"</td>";
						html+="<input type='hidden' id='postcode' value='"+data.results.juso[i].zipNo+"'>";
						html+="<input type='hidden' id='address' value='"+data.results.juso[i].roadAddr+"'>";
						html+="<input type='hidden' id='addressDetail' value='"+data.results.juso[i].jibunAddr+"'>";
						html+="<input type='hidden' id='city' value='"+data.results.juso[i].sggNm+"'>";
						html+="<input type='hidden' id='district' value='"+data.results.juso[i].siNm+"'>";
					html+="<tr>";
				}
				
			}else{
				html+="<div class='alert text-center'><strong>검색 결과값이 없습니다.</strong></div>";
			}
			$('#popup-postcode-member tbody').html(html);
		},
		error: function (xhr, ajaxOptions, thrownError) {
			 alert(xhr.status);
		     alert(thrownError);
			}
		});
	
});

$(document.body).on('click', '#button-edit-group-name', function(){
	var url ="/ajax/edit-group-name";
	var groupId = $('input[name=groupId]').val();
	var groupName = $('input[name=groupName]').val();
	
		$.ajax({
			type: "POST",
			url: url,
			data: {groupId: groupId, groupName: groupName},
			success: function(response)
			{
				console.log("ajx running");
				console.log("response data: "+response.data);
				if(response.code == 200){
					$('#group-name').text(response.data);
					$('#div-show').show();
				    $('#div-edit').hide();
				}
			},
			error : function(e) {
				alert("ERROR: "+ e);
				}
			});
	
});
