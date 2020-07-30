<?php
	include 'connection.php'; 
	
	$BusinessIdFK=$_REQUEST["BusinessIdFK"];
	$BusinessUserIdFK=$_REQUEST["BusinessUserIdFK"];
	$Rating=$_REQUEST['Rating'];
	$Review=$_REQUEST['Review'];
	
	$query = "insert into rating_review(BusinessIdFK,BusinessUserIdFK,Rating,Review) values('".$BusinessIdFK."','".$BusinessUserIdFK."','".$Rating."','".$Review."')";
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