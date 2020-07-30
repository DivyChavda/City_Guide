<?php
	include 'connection.php'; 
	
	$UserId = $_REQUEST['UserId'];
	
	$query = "delete from admin where AdminId='".$_REQUEST["UserId"]."'";
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