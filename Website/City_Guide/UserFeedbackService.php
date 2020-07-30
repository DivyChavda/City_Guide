<?php
	include 'connection.php'; 
	
	$UserName=$_REQUEST['UserName'];
	$UserEmail=$_REQUEST['UserEmail'];
	$UserNumber=$_REQUEST['UserNumber'];
	$UserReview=$_REQUEST['UserReview'];
	
	
	$query = "insert into feedback(UserName,UserEmail,UserNumber,UserReview) values('".$UserName."','".$UserEmail."','".$UserNumber."','".$UserReview."')";
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