<?php
	include 'connection.php'; 
	
	$UserName=$_REQUEST['UserName'];
	$UserEmail=$_REQUEST['UserEmail'];
	$UserPassword=$_REQUEST['UserPassword'];
	$UserMobileNumber=$_REQUEST['UserMobileNumber'];
	$UserGender=$_REQUEST['UserGender'];
	
	
	$query = "insert into registration(UserName,UserEmail,UserPassword,UserMobileNumber,UserGender) values('".$UserName."','".$UserEmail."','".$UserPassword."','".$UserMobileNumber."','".$UserGender."')";
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