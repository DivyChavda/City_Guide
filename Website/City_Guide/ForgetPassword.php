<?php
	include 'connection.php';
?>
<!DOCTYPE html>
<html lang="en">
    
<!-- Mirrored from coderthemes.com/velonic_3.0/admin_4/pages-recoverpw.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 18 Jan 2018 10:49:20 GMT -->
<head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <link rel="shortcut icon" href="img/favicon_1.ico">

        <title>CityGuide | ForgetPassword</title>

        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/bootstrap-reset.css" rel="stylesheet">

        <!--Animation css-->
        <link href="css/animate.css" rel="stylesheet">

        <!--Icon-fonts css-->
        <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
        <link href="assets/ionicon/css/ionicons.min.css" rel="stylesheet" />
        <link href="assets/material-design-iconic-font/css/material-design-iconic-font.min.css" rel="stylesheet" />

        <!-- Custom styles for this template -->
        <link href="css/style.css" rel="stylesheet">
        <link href="css/helper.css" rel="stylesheet">
        

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->
        <!--[if lt IE 9]>
          <script src="js/html5shiv.js"></script>
          <script src="js/respond.min.js"></script>
        <![endif]-->

        <script>
          (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
          (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
          m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
          })(window,document,'script','../../../www.google-analytics.com/analytics.js','ga');

          ga('create', 'UA-62751496-1', 'auto');
          ga('send', 'pageview');

        </script>

    </head>


    <body>

        <div class="wrapper-page">
            <div class="panel panel-color panel-inverse">

                <div class="panel-heading"> 
                   <h3 class="text-center m-t-10"> Recover Password </h3>
                </div> 

                <div class="panel-body">
                    <form method="post" role="form" class="text-center"> 
                        <div class="alert alert-info alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                            Enter your <b>Email</b> and instructions will be sent to you!
                        </div>
                        <div class="form-group m-b-0"> 
                            <div class="input-group"> 
                                <input type="email" name="txtemail" class="form-control" placeholder="Enter Email"> 
                                <span class="input-group-btn"> <button type="submit" class="btn btn-success" name="btnforgot">Send</button> </span> 
                            </div> 
                        </div> 
                        
                    </form>
					<?php
							if(isset($_REQUEST["btnforgot"]))
							{
								$email=$_REQUEST["txtemail"];
								$query=$con->query("select * from registration where UserEmail='".$email."'") or die(mysqli_error());
								
								if(mysqli_num_rows($query) > 0)
								{
									$res=mysqli_fetch_array($query);
									
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
									$mail->AddAddress($email);
									$mail->Subject = "City Guide";
									$mail->Body = 'Dear '.$res["UserName"].' your password is: '.$res["UserPassword"];
									$mail->IsHTML(true);
									if(!$mail->Send()) 
										echo "<script>alert('Something getting wrong while sending mail...')</script>";
									else 
										echo "<script>alert('Your password send to your mailbox...');;window.location='index.php'</script>";
								}
								else
								{
									echo "<script>alert('Enter valid email...')</script>";
								}
								
							}
						?>
                </div>

                                        
                
            </div>
        </div>

        




        <!-- js placed at the end of the document so the pages load faster -->
        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/pace.min.js"></script>
        <script src="js/wow.min.js"></script>
        <script src="js/jquery.nicescroll.js" type="text/javascript"></script>
            

        <!--common script for all pages-->
        <script src="js/jquery.app.js"></script>

    
    </body>

<!-- Mirrored from coderthemes.com/velonic_3.0/admin_4/pages-recoverpw.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 18 Jan 2018 10:49:20 GMT -->
</html>
