<?php
	include 'connection.php'; 
	
	$UserId = $_REQUEST['UserId'];
	$UserPassword=$_REQUEST['UserPassword'];
	$UserNewPassword=$_REQUEST['UserNewPassword'];
	
	$query = $con->query("select * from registration where UserId ='".$_REQUEST["UserId"]."' and UserPassword ='".$_REQUEST["UserPassword"]."'") or die(mysqli_error($con));
	
	if(mysqli_num_rows($query) > 0)
	{
		$update = "update registration set UserPassword = '".$_REQUEST["UserNewPassword"]."' where UserId='".$_REQUEST["UserId"]."'";
		$result = $con->query($update) or die(mysqli_error($con));

			if(!$result)
			{
				header('Content-type: text/xml');
				echo '<posts><post><result>fail</result></post></posts>';
			}
			else
			{
				header('Content-type: text/xml');
				echo '<posts><post><result>success</result></post></posts>';
			}
	}
	else
		{
			header('Content-type: text/xml');
			echo '<posts><post><result>fail</result></post></posts>';
		}
	
	@mysqli_close($con);
?>