var addMembersCount = 1;
var members = {"groupName":"", "members":[{}]};
var memberRowHtml = '<div id="newmem" class="form-group"><div class="form-group form-inline" id="newmemrow"><input type="text" name="mname" placeholder="Name" class="form-control"/><span> </span><input type="email" name="memail" placeholder="Email" class="form-control col-md-offset-1" required="true"/><span> </span><button type="button" class="btn btn-default glyphicon glyphicon-remove-circle" id="removemem" onclick="removeNewMemberRow(this);"></button></div></div>';
var isSignedIn = false;
var signOutHtml = '<li><a href="#" onclick="signOut();">Logout</a></li>';

var addNewMemberRow = function(e){	
	e.preventDefault();
	if (addMembersCount < 5)
	{
		$('#newmem').append(memberRowHtml);
		addMembersCount++;
	}
	
};

var removeNewMemberRow = function(obj){	
		if(addMembersCount>1){
			$(obj).parents('div#newmem').get(0).remove();
		}
		addMembersCount--;
}

var createGroup = function(e){			
	getNewMembersInfo();	
	$.ajax({
		  method: "POST",
		  url: "http://localhost/webtracker/webapi/group",
		  //url: "#",
		  data: JSON.stringify(members)
		})
		  .done(function( msg, txtStatus, jqXHR) {	
			  displaySuccess();
			  clearCGForm();
			  console.log("show succ");
		  });
	e.preventDefault();	
};

$('#amb').click(addNewMemberRow);
$('#createGroupForm').submit(createGroup);

var clearCGForm = function(){
	$('input[name=mname]').each(function(){$(this).val('')});
	$('input[name=memail]').each(function(){$(this).val('')});
	$('input[name=gname]').each(function(){$(this).val('')});
}

var displaySuccess = function(){
	$('.displaySuccess').css("display","block");
	  $('#successMessage').text("Group created successfully");
	  $('#newmem').find('div#newmemrow').remove();
	  $('#newmem').append(memberRowHtml);
}

var hideSuccess = function(){
	$('.displaySuccess').css("display","none");	 
}

$(function(){
	addMembersCount = 1;
	clearCGForm();
	hideSuccess();
	onSignIn();	
})

var getNewMembersInfo = function (){	
	var count=0;
	members.groupName= $('input[name=gname]').val();
	$('#newmem').find('div#newmemrow').each(function(){
		var member = { "name":$(this).children('input[name=mname]').val(), "email": $(this).children('input[name=memail]').val()};
		members.members[count++] = member;
		});	
};

function onSignIn(googleUser) {
	  isSignedIn = true;
	  $('.g-signin2').css('display','none');
	  if ($('ul.nav.nav-justified li:last').text() != 'Logout')
	  {
	      $('ul.nav.nav-justified').append(signOutHtml);
	  } 
	}

function signOut(){
	 var auth2 = gapi.auth2.getAuthInstance();
	    auth2.signOut().then(function () {
	    	$('.g-signin2').css('display','block');
	    	$('ul.nav.nav-justified li:last').remove();    	
	  	  
	    });
	    $.ajax({
  		  method: "POST",
  		  url: "http://localhost/webtracker/logout",
  		  //url: "#",
  		  data: {"token":""}
  		})
  		  .done(function( msg, txtStatus, jqXHR) {	
  			window.location.href = "http://localhost/webtracker/login.html"
  		  });       

}
