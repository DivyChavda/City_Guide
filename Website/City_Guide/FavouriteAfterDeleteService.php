<?php
	include 'connection.php'; 
	
	$BussinessIdFK=$_REQUEST["BussinessIdFK"];
	$BusinessUserIdFK=$_REQUEST['BusinessUserIdFK'];
	
	$query = "delete from favourite where BussinessIdFK='".$_REQUEST["BussinessIdFK"]."' and BusinessUserIdFK='".$_REQUEST["BusinessUserIdFK"]."'";
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