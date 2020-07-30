<?php
	include 'connection.php'; 
	
	$query = $con->query("select * from forgotpassword where Email ='".$_REQUEST["Email"]."' and Code ='".$_REQUEST["Code"]."'") or die(mysqli_error($con));
	
	if(mysqli_num_rows($query) > 0)
	{
			if(!$query)
			{
				header('Content-type: text/xml');
				echo '<posts><post><result>fail</result></post></posts>';
			}
			else
			{
				
				$query1=$con->query("select * from registration where UserEmail='".$_REQUEST["Email"]."'") or die(mysqli_error());
				
				if(mysqli_num_rows($query1) > 0)
				{
					$res=mysqli_fetch_array($query1);
					
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
					$mail->AddAddress($_REQUEST["Email"]);
					$mail->Subject = "City Guide";
					$mail->Body = 'Dear '.$res["UserName"].' your password is: '.$res["UserPassword"];
					$mail->IsHTML(true);
					$mail->Send();
					
				}
				$query2=$con->query("delete from forgotpassword where Email='".$_REQUEST["Email"]."'") or die(mysqli_error());
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