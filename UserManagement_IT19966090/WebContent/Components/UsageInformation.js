$(document).ready(function()
{ 

 $("#alertSuccess").hide(); 
 $("#alertError").hide(); 
});

//save
//add a click event handler for the Save button
$(document).on("click", "#btnSave", function(event)
{ 
	
//Clear alerts---------------------
$("#alertSuccess").text(""); 
$("#alertSuccess").hide(); 
$("#alertError").text(""); 
$("#alertError").hide(); 


//Form validation-------------------
var status = validateUsageForm(); 

//If not valid
if (status != true) 
{ 
$("#alertError").text(status); 
$("#alertError").show(); 
return; 
} 


//If valid------------------------
var type = ($("#hideUsageInformationIDSave").val() == "") ? "POST" : "PUT"; 
$.ajax( 
{ 
url : "UsageInformationAPI", 
type : type, 
data : $("#formUsageInformation").serialize(), 
dataType : "text", 
complete : function(response, status) 
{ 
onUsageInformationSaveComplete(response.responseText, status); 
} 
}); 
});