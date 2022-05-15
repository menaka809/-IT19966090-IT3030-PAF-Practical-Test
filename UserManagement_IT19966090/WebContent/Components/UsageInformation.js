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


//CLIENT-MODEL================================================================
function validateUsageForm()
{
	// user name
	if ($("#userName").val().trim() == "")
	{
	return "Insert User Name.";
	}
	// address
	if ($("#address").val().trim() == "")
	{
	return "Insert User Address.";
	
    }
	// no of unit
	if ($("#noOfUnit").val().trim() == "")
	{
	return "Insert No of Units.";
	}
	// month
	if ($("#month").val().trim() == "")
	{
	return "Insert Month.";
	
    }

	return true;
}

//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
		{ 
		$("#hideUsageInformationIDSave").val($(this).data("usageid")); 
		 $("#userName").val($(this).closest("tr").find('td:eq(0)').text()); 
		 $("#address").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#noOfUnit").val($(this).closest("tr").find('td:eq(2)').text()); 
		 $("#month").val($(this).closest("tr").find('td:eq(3)').text()); 
		 
		});

//Delete
$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "UsageInformationAPI", 
		 type : "DELETE", 
		 data : "usageID=" + $(this).data("usageid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
			 onUsageInformationDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});