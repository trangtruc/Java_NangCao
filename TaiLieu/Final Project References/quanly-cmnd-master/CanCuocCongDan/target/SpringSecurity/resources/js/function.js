			function kiemTraDoDai(id, vlue){
				if(vlue.length > 15) {
					var new_vlue = vlue.slice(0,15);
					$("#"+id).val(new_vlue);
				}
			}
			function chenGachNgang(id, vlue){
				var new_value = vlue;
				if(vlue.length == 3){
					new_value += "-";
				}
				if(vlue.length == 7){
					new_value += "-";
				}
				if(vlue.length == 11){
					new_value += "-";
				}
				$("#"+id).val(new_value);
			}
function xacNhan(message){
	if(!confirm(message)){
		return false;
	}
}
function getThongTin(id){ // ViDu id = soCha_1
	var x = id.split("_"); // ket qua x[0] la soCha,  x[1] la 1
	var s = x[0].split("so"); // ket qua s[0] la null, s[1] la Cha
	if(s[1] != ""){
	var resultID = "hoTen"+s[1];
	} else {
		resultID = "tonTaiCC";
	}
	var vlue = "";
	for(var i = 1; i <= 12; i++){
		vlue += $("#so"+s[1]+"_"+i).val();
	}
	var soCC = "soCC="+vlue;
	$.ajax({url: 'get-thong-tin',
		type: 'GET',
		data:soCC,
	  	success: function(result){
	  		$("#"+resultID).val(result);
	  		if(result == "Không tìm thấy"){
	  				$("#"+resultID).attr("aria-value", "0");
	  		} else {
	  				$("#"+resultID).attr("aria-value", "1");
	  		}
        }});
}
function getHuyenFocus(id_huyen){
	var id_tinh = id_huyen.split("Huyen");
	id_tinh = id_tinh[0]+"Tinh";
	var tinh = "tinh="+$("#"+id_tinh).val();
	$.ajax({url: 'get-huyen',
		type: 'GET',
		data:tinh,
	  	success: function(result){
	  		$("#"+id_huyen).html(result);
        }});
	getXa(id_huyen,val);
}
function getHuyen(id_tinh, val){
	var tinh = "tinh="+val;
	var data = id_tinh.split("Tinh");
	var id_huyen = data[0]+"Huyen";
	$.ajax({url: 'get-huyen',
		type: 'GET',
		data:tinh,
	  	success: function(result){
	  		$("#"+id_huyen).html(result);
        }});
	getXa(id_huyen,val);
}
function getXa(id_huyen, val){
	var huyen = "huyen="+val;
	var data = id_huyen.split("Huyen");
	var id_xa = data[0]+"Xa";
	$.ajax({url: 'get-xa',
		type: 'GET',
		data:huyen,
	  	success: function(result){
	  		$("#"+id_xa).html(result);
        }});
}
$(document).ready(function(){
	$("#open-menu-1").click(function() {
    	$("#menu-user-1").show();
	});
});
$(document).ready(function(){
	$("#open-menu-2").click(function() {
    	$("#menu-user-2").show();
	});
});
$(document).ready(function(){
	$("#open-menu-3").click(function() {
    	$("#menu-user-3").show();
	});
});
$(document).ready(function(){
	$("#open-setting-1").click(function() {
    	$("#setting-user-1").show();
	});
});
$(document).ready(function(){
	$("#open-setting-2").click(function() {
    	$("#setting-user-2").show();
	});
});
$(document).ready(function(){
	$("#open-setting-3").click(function() {
    	$("#setting-user-3").show();
	});
});
$(document).mouseup(function (e)
		{
		    var container = $("#menu-user-1");

		    if (!container.is(e.target) // if the target of the click isn't the container...
		        && container.has(e.target).length == 0) // ... nor a descendant of the container
		    {	
			   container.hide('fast');
		    }
		    var container = $("#menu-user-2");

		    if (!container.is(e.target) // if the target of the click isn't the container...
		        && container.has(e.target).length == 0) // ... nor a descendant of the container
		    {	
			   container.hide('fast');
		    }
		    var container = $("#menu-user-3");

		    if (!container.is(e.target) // if the target of the click isn't the container...
		        && container.has(e.target).length == 0) // ... nor a descendant of the container
		    {	
			   container.hide('fast');
		    }
		    var container = $("#setting-user-1");

		    if (!container.is(e.target) // if the target of the click isn't the container...
		        && container.has(e.target).length == 0) // ... nor a descendant of the container
		    {	
			   container.hide('fast');
		    }
		    var container = $("#setting-user-2");

		    if (!container.is(e.target) // if the target of the click isn't the container...
		        && container.has(e.target).length == 0) // ... nor a descendant of the container
		    {	
			   container.hide('fast');
		    }
		    var container = $("#setting-user-3");

		    if (!container.is(e.target) // if the target of the click isn't the container...
		        && container.has(e.target).length == 0) // ... nor a descendant of the container
		    {	
			   container.hide('fast');
		    }
		});
