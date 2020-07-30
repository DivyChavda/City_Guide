<?php
	include 'connection.php'; 
	
	$BussinessIdFK=$_REQUEST["BussinessIdFK"];
	$BusinessUserIdFK=$_REQUEST['BusinessUserIdFK'];
	$Favourite=1;
	
	$query = "insert into favourite(BussinessIdFK,BusinessUserIdFK,Favourite) values('".$BussinessIdFK."','".$BusinessUserIdFK."','".$Favourite."')";
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