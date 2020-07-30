<?php
	include 'connection.php'; 
	
	$UserName=$_REQUEST["UserName"];
	$UserEmail=$_REQUEST['UserEmail'];
	$UserPassword=$_REQUEST['UserPassword'];
	
	$UserId = $_REQUEST['UserId'];
	
	$query = "update admin set AdminName='".$_REQUEST["UserName"]."',AdminEmail='".$_REQUEST["UserEmail"]."',AdminPassword='".$_REQUEST["UserPassword"]."' where AdminId='".$_REQUEST["UserId"]."'";
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