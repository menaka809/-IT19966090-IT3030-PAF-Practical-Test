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

//create function to check information correctly save or not
function onUsageInformationSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divUsageInformationGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 }
$("#hideUsageInformationIDSave").val(""); 
$("#formUsageInformation")[0].reset(); 
}

