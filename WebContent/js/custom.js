function loaddata(){
	$.getJSON("/Sampleproject/User",function( data ){
		var htmlToAppend = '<table class="table table-striped" id="table"><thead><tr><th>First Name</th><th>Last Name</th><th>Email</th><th></th></tr></thead>';
		for(person of data){
			htmlToAppend += "<tr>";
			htmlToAppend = htmlToAppend + "<td>" + person.fname + "</td>";
			htmlToAppend = htmlToAppend + "<td>" + person.lname + "</td>";
			htmlToAppend = htmlToAppend + "<td>" + person.email + "</td>";
			htmlToAppend = htmlToAppend + "<td>" + "<a href = 'EditForm.html'><img src='img/edit-icon-small.jpg' class='imagecontain'></a>" + "</td></tr>";
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