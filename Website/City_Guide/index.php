<?php
	include 'connection.php';
	session_start();
?>
<!DOCTYPE html>
<html lang="en">
<head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <link rel="shortcut icon" href="img/favicon_1.ico">

        <title>CityGuide | Login</title>

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
    </head>


    <body>

        <div class="wrapper-page">
            <div class="panel panel-color panel-inverse">
                <div class="panel-heading"> 
                   <h3 class="text-center m-t-10"> Sign In to <strong>CityGuide</strong> </h3>
                </div> 

                <div class="panel-body">
                    <form class="form-horizontal m-t-10 p-20 p-b-0" method="post">
                                            
                        <div class="form-group ">
                            <div class="col-xs-12">
                                <input class="form-control" type="email" value="<?php if(isset($_COOKIE["useremail"])) { echo $_COOKIE["useremail"]; } ?>" placeholder="User Email" name="txtuemail">
                            </div>
                        </div>
                        <div class="form-group ">
                            
                            <div class="col-xs-12">
                                <input class="form-control" type="password" value="<?php if(isset($_COOKIE["userpassword"])) { echo $_COOKIE["userpassword"]; } ?>" placeholder="User Password" name="txtpassword">
                            </div>
                        </div>

                        <div class="form-group ">
                            <div class="col-xs-12">
                                <label class="cr-styled">
                                    <input type="checkbox" name="chkrememberme" value="Remember me" <?php if(isset($_COOKIE["useremail"])) { ?> checked="checked" <?php } ?> >
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
                    </form>
					<?php
					/* Login Code Start */
						if(isset($_POST["btnlogin"]))   /* Login button click event */
						{
							$res=$con->query("select * from admin where AdminEmail='".$_REQUEST["txtuemail"]."' and AdminPassword='".$_REQUEST["txtpassword"]."'") or die(mysqli_error($con));
							if(mysqli_num_rows($res)> 0)
							{
								/*Checked that you checked Remember me checkbox or not*/
								$_SESSION["admin"] = $_REQUEST["txtuemail"];
								if(!empty($_REQUEST["chkrememberme"]))
								{
									setcookie ("useremail",$_REQUEST["txtuemail"],time()+ (86400)); /*86400 = 1day*/ 
									setcookie ("userpassword",$_REQUEST["txtpassword"],time()+ (86400)); 
								}
								else  
								{  
									if(isset($_COOKIE["useremail"]))   
									{  
										setcookie("useremail","");  
									}  
									if(isset($_COOKIE["userpassword"]))   
									{  
										setcookie("userpassword","");  
									}  
								}
								echo "<script>window.location='home.php'</script>";
							}
							else
							{
								echo "<script>alert('Invalid Email or Password')</script>";
							}
						}
					/* Login Code End */
					?>
                </div>

            </div>
        </div>

        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/pace.min.js"></script>
        <script src="js/wow.min.js"></script>
        <script src="js/jquery.nicescroll.js" type="text/javascript"></script>
        <script src="js/jquery.app.js"></script>
    </body>
</html>
