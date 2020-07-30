<?php
	include 'connection.php';
	session_start();
?>
<!DOCTYPE html>
<html lang="en">
    
<!-- Mirrored from coderthemes.com/velonic_3.0/admin_4/pages-login.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 18 Jan 2018 10:49:20 GMT -->
<head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <link rel="shortcut icon" href="img/favicon_1.ico">

        <title>Business | SignUp</title>

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
                   <h3 class="text-center m-t-10"> Sign Up to <strong>Business</strong> </h3>
                </div> 

                <div class="panel-body">
                    <form class="form-horizontal m-t-10 p-20 p-b-0" enctype="multipart/form-data" method="post">
                        <div class="form-group ">
                            <div class="col-xs-12">
                                <input class="form-control" type="text" name="txtbuname" required="" aria-required="true" placeholder="User Name" >
                            </div>
                        </div>                   
                        <div class="form-group ">
                            <div class="col-xs-12">
                                <input class="form-control" type="email" name="txtbuemail" required="" aria-required="true" placeholder="User Email" >
                            </div>
                        </div>
						<div class="form-group ">
                            <div class="col-xs-12">
                                <input class="form-control" type="number_format" name="txtbumo" required="" aria-required="true" placeholder="User MobileNUmber" ">
                            </div>
                        </div>
                        <div class="form-group ">
                            
                            <div class="col-xs-12">
                                <input class="form-control" type="password" name="txtbupass" aria-required="true" placeholder="User Password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required="">
                            </div>
                        </div>
						<div class="form-group ">
                            <div class="col-xs-12">
                                <input class=" form-control" name="imgicon" type="file" required="" aria-required="true" required="" aria-required="true">
                            </div>
                        </div>  
                        <div class="form-group text-right">
                            <div class="col-xs-12">
                                <button class="btn btn-success w-md" type="submit" name="btnsingup">SignUp</button>
                            </div>
                        </div>
                    </form>
					<?php
						if(isset($_REQUEST["btnsingup"]))
						{
							move_uploaded_file($_FILES["imgicon"]["tmp_name"],"BusinessUserIcons/".$_FILES["imgicon"]["name"]);
							$image = $_FILES["imgicon"]["name"];
							
							$res = $con->query("insert into business_user(BusinessUserName,BusinessUserEmail,BusinessUserMobile,BusinessUserPassword,BusinessUserIcon) values('".$_REQUEST["txtbuname"]."','".$_REQUEST["txtbuemail"]."','".$_REQUEST["txtbumo"]."','".$_REQUEST["txtbupass"]."','".$image."')") or die(mysqli_error($con));
							if($res==true)
							{
								echo "<script>window.location='index.php'</script>";
								
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

<!-- Mirrored from coderthemes.com/velonic_3.0/admin_4/pages-login.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 18 Jan 2018 10:49:20 GMT -->
</html>
