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

        <title>Business | Login</title>

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
                   <h3 class="text-center m-t-10"> Sign In to <strong>Business</strong> </h3>
                </div> 

                <div class="panel-body">
                    <form class="form-horizontal m-t-10 p-20 p-b-0" method="post">
                                            
                        <div class="form-group ">
                            <div class="col-xs-12">
                                <input class="form-control" type="email" value="<?php if(isset($_COOKIE["name"])) { echo $_COOKIE["BusinessUserEmail"]; } ?>" placeholder="User Email" required="" aria-required="true" name="txtbuemail">
                            </div>
                        </div>
                        <div class="form-group ">
                            
                            <div class="col-xs-12">
                                <input class="form-control" type="password" value="<?php if(isset($_COOKIE["pass"])) { echo $_COOKIE["BusinessUserPassword"]; } ?>" placeholder="User Password" required="" aria-required="true" name="txtbupass">
                            </div>
                        </div>

                        <div class="form-group ">
                            <div class="col-xs-12">
                                <label class="cr-styled">
                                    <input type="checkbox" name="chkrememberme" value="Remember me" <?php if(isset($_COOKIE["BusinessUserEmail"])) { ?> checked="checked" <?php } ?>>
                                    <i class="fa"></i> 
                                    Remember me
                                </label>
                            </div>
                        </div>
                        
                        <div class="form-group text-right">
                            <div class="col-xs-12">
                                <button class="btn btn-success w-md" type="submit" name="btnlogin">Log In</button>
                            </div>
                        </div>
                        <div class="form-group m-t-30">
                            <div class="col-sm-7">
                                <a href="ForgetPassword.php"><i class="fa fa-lock m-r-5"></i> Forgot your password?</a>
                            </div>
                        </div>
						<div class="form-group text-right">
                            <div class="col-xs-12">
                                <a href="SignUp.php">Sign Up</a>
                            </div>
                        </div>
                    </form>
					<?php
						if(isset($_REQUEST["btnlogin"]))
						{
							$res = $con->query("select * from business_user where BusinessUserEmail='".$_REQUEST["txtbuemail"]."' and BusinessUserPassword='".$_REQUEST["txtbupass"]."'") or die(mysqli_error($con));
							if(mysqli_num_rows($res) > 0)
							{
								$_SESSION["business"] = $_REQUEST["txtbuemail"];
								if(!empty($_REQUEST["chkrememberme"]))
								{
									setcookie ("name",$_REQUEST["txtbuemail"],time()+ (86400)); /*86400 = 1day*/ 
									setcookie ("pass",$_REQUEST["txtbupass"],time()+ (86400)); 
								}
								else  
								{  
									if(isset($_COOKIE["name"]))   
									{  
										setcookie("name","");  
									}  
									if(isset($_COOKIE["pass"]))   
									{  
										setcookie("pass","");  
									}  
								}
								echo "<script>window.location='home.php'</script>";
								
							}
							else
							{
								echo "<script>alert('Your email and password invalid..!!Try again..!!')</script>";
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
