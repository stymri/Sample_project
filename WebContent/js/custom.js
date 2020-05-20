function loaddata(){
	$.getJSON("/Sampleproject/User",function( data ){
		var htmlToAppend = '<table class="table table-striped" id="table"><thead><tr><th>First Name</th><th>Last Name</th><th>Email</th><th></th></tr></thead>';
		for(person of data){
			htmlToAppend += "<tr>";
			htmlToAppend = htmlToAppend + "<td>" + person.fname + "</td>";
			htmlToAppend = htmlToAppend + "<td>" + person.lname + "</td>";
			htmlToAppend = htmlToAppend + "<td>" + person.email + "</td>";
			htmlToAppend = htmlToAppend + "<td>" + "<button type='button' class='editbutton btn btn-primary' data-toggle='modal' data-target='#formModal' data-email="+person.email+" data-fname="+person.fname+" data-lname="+person.lname+"><img src='img/edit-icon.png' class='imagecontain'></button>" + "</td></tr>";
		}
		htmlToAppend += "</table>";
		$("#table").append(htmlToAppend);
	});
};
function addfunction(){
	$("#addbutton").hide();
	$("#addbuttonloader").show();
	var fname = $("#fname").val();
	var lname = $("#lname").val();
	var email = $("#email").val();
	$.ajax({
		url : "/Sampleproject/User",
	    dataType : "json",
	    method: "POST",
	    data : JSON.stringify( {
	    	'fname' : fname,
	    	'lname' : lname,
	    	'email' : email
	    })
	  }).done(function(data){
		  $("#addbuttonloader").hide();
		  $("#addbutton").show();
		  if(data.id == 0){
			  /* success*/
			  location.reload();
		  }else {
			  $("#add_alert").html(data.message);
			  $("#add_alert").show();
		  }
	  });
} ;
$('#formModal').on('show.bs.modal', function (event) {
	  var button = $(event.relatedTarget); 
	  var email = button.data('email');
	  var fname = button.data('fname');
	  var lname = button.data('lname');
	  console.log(email);
	  var modal = $(this);
	  if(email == null){
		  // add case
		  $('#email').removeAttr("value");
		  $('#fname').removeAttr("value");
		  $('#lname').removeAttr("value");
		  $('#email').removeAttr("disabled");
		  $('#titleheader').html("Add New User");
		  $('#addbutton').html("Add");
	  }else{
		  // update case
		  $('#email').attr({value : email , disabled : "true"});
		  $('#fname').attr("value",fname);
		  $('#lname').attr("value",lname);
		  $('#titleheader').html("Update User");
		  $('#addbutton').html("Update");
	  }	
});
