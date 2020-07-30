<?php
	include 'connection.php'; 
	
	$UserId = $_REQUEST['UserId'];
	$UserName=$_REQUEST["UserName"];
	$UserEmail=$_REQUEST['UserEmail'];
	$UserMobileNumber=$_REQUEST['UserMobileNumber'];
	$UserGender=$_REQUEST['UserGender'];
	
	$query = "update registration set UserName='".$_REQUEST["UserName"]."',UserEmail='".$_REQUEST["UserEmail"]."',UserMobileNumber='".$_REQUEST["UserMobileNumber"]."',UserGender='".$_REQUEST["UserGender"]."' where UserId='".$_REQUEST["UserId"]."'";
	$result = $con->query($query) or die(mysqli_error($con));

	if(!$result)
	{
		header('Content-type: text/xml');
		echo '<posts><post>fail</post></posts>';
	}
	else
	{
		header('Content-type: text/xml');
		echo '<posts><post>success</post></posts>';
	}
	
	@mysqli_close($con);
?>