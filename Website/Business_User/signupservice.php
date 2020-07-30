<?php
	include 'connection.php'; 
	
	$UserName=$_REQUEST["UserName"];
	$UserEmail=$_REQUEST['UserEmail'];
	$UserPassword=$_REQUEST['UserPassword'];
	
	$query = "insert into admin(AdminName,AdminEmail,AdminPassword) values('".$UserName."','".$UserEmail."','".$UserPassword."')";
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