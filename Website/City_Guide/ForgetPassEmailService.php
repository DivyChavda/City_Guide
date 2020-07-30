<?php
	include 'connection.php';
	
	$UserEmail = $_REQUEST["UserEmail"];
	
	function rendomcode($length = 4)
	{
		$characters = '0123456789';
		$charactersLength = strlen($characters);
		$randomString = '';
		for ($i = 0; $i < $length; $i++) {
			$randomString .= $characters[rand(0, $charactersLength - 1)];
		}
		return $randomString;
	}
	
	$rcode = rendomcode();
	$query = "select * from registration where UserEmail='".$_REQUEST["UserEmail"]."'";
	$result = $con->query($query) or die(mysqli_error($con));

	
	$posts = array();
	if(mysqli_num_rows($result))
	{
		$res=mysqli_fetch_array($result);
										
		require 'phpmail/PHPMailerAutoload.php';
										
		$mail = new PHPMailer();
		$mail->SMTPOptions = array(
		'ssl' => array(
		'verify_peer' => false,
		'verify_peer_name' => false,
		'allow_self_signed' => true
		)
		);
		$mail->IsSMTP();
		$mail->SMTPDebug = 0;
		$mail->SMTPAuth = TRUE;
		$mail->SMTPSecure = "tls";
		$mail->Port     = 587;  
		$mail->Username = "chavdadivy29@gmail.com";
		$mail->Password = "Chavdadivy29@";
		$mail->Host     = "smtp.gmail.com";
		$mail->Mailer   = "smtp";
		$mail->SetFrom("chavdadivy29@gmail.com", "Divy Chavda");
		$mail->AddAddress($UserEmail);
		$mail->Subject = "City Guide";
		$mail->Body = "Rendom Code is:".$rcode;
		if(!$mail->Send())
		{			
			header('Content-type: text/xml');
			echo '<posts><post><result>fail</result></post></posts>';
		}
		else 
		{
			$insert = $con->query("insert into forgotpassword(Email,Code) values('".$UserEmail."','".$rcode."')") or die(mysqli_error($con));
			header('Content-type: text/xml');
			echo '<posts><post><result>success</result></post></posts>';
		}
			
		
	}
	else{
		header('Content-type: text/xml');
			echo '<posts><post><result>fail</result></post></posts>';
	}
	

	@mysqli_close($con);
?>