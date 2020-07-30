<?php
	include 'connection.php';
	session_start();
	if(!isset($_SESSION["business"]))
	{
		echo "<script>window.location='index.php'</script>";
	}
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

        <title>Business | Profile</title>

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

	<table>
	
	<td></td>
	
	</table>
        <div class="wrapper-page">
            <div class="panel panel-color panel-inverse">
                <!--<div class="panel-heading"> 
                   <h3 class="text-center m-t-10"> Change Password to <strong>CityGuide</strong> </h3>
                </div>-->
	

                <div class="panel-body">
                    <form class="form-horizontal m-t-10 p-20 p-b-0" method="post" enctype="multipart/form-data">
					
					
					
					<?php
					
						 $up = $con->query("select * from business_user where BusinessUserEmail ='".$_SESSION['business']."'") or die(mysqli_error($con));
						 while($row = mysqli_fetch_array($up))
						 {
					?>
					
					<input type="hidden" name="txtimg" value="<?php echo $row["BusinessUserIcon"]; ?>">
					
						<input type="file" id="file1" name="image" style="display:none"/>
						<center><img src="BusinessUserIcons/<?php echo $row["BusinessUserIcon"]; ?>" id="upfile1" style="width: 100px; height: 100px; cursor:pointer;"></center>
					
                        <div class="form-group ">
                            
                            <div class="col-xs-12">
                                <input class="form-control" value="<?php echo $row["BusinessUserName"]; ?>" type="text" placeholder="Enter Your Name " name="txtname" required="" style="margin-top: 10px;">
                            </div>
                        </div>
						
						<div class="form-group ">
                            
                            <div class="col-xs-12">
                                <input class="form-control" type="text"  value="<?php echo $row["BusinessUserEmail"]; ?>" placeholder="Enter Your Email" name="txtemail" required="">
                            </div>
                        </div>
						
						<div class="form-group ">
                            
                            <div class="col-xs-12">
                                <input class="form-control" type="number"  value="<?php echo $row["BusinessUserMobile"]; ?>" placeholder="Enter Your Number" name="txtnumber" required="">
                            </div>
                        </div>
                        
                        <div class="form-group text-right">
                            <div class="col-xs-12">
                                <button class="btn btn-success w-md" type="submit" name="btnupdate">Update</button>
                            </div>
                        </div>
						 <?php } ?>
                    </form>
					<?php
						if(isset($_REQUEST["btnupdate"]))
						{
							$image="";
							if($_FILES["image"]["name"]=="")
							{
								$image=$_REQUEST["txtimg"];
							}	
							else
							{
								unlink("BusinessUserIcons//".$_REQUEST["txtimg"]);
								move_uploaded_file($_FILES["image"]["tmp_name"],"BusinessUserIcons/".$_FILES["image"]["name"]);
								$image=$_FILES["image"]["name"];
							}
							$qr = $con->query("update business_user set BusinessUserName='".$_REQUEST["txtname"]."',BusinessUserEmail='".$_REQUEST['txtemail']."',BusinessUserMobile='".$_REQUEST["txtnumber"]."',BusinessUserIcon='".$image."' where BusinessUserEmail='".$_SESSION['business']."'") or die(mysqli_error($con));
							if($qr == TRUE)
							{
								echo "<script>alert('Record updated..!!')window.location='index.php'</script>";
							}
							else
							{
								echo "<script>alert('Error while updating..!!')</script>";
							}
						}
					?>
                </div>

            </div>
        </div>

    


        <!-- js placed at the end of the document so the pages load faster -->
        <script src="js/jquery.js"></script>
		<script>
		$("#upfile1").click(function () {
			$("#file1").trigger('click');
		});
		</script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/pace.min.js"></script>
        <script src="js/wow.min.js"></script>
        <script src="js/jquery.nicescroll.js" type="text/javascript"></script>
            

        <!--common script for all pages-->
        <script src="js/jquery.app.js"></script>

    
    </body>

<!-- Mirrored from coderthemes.com/velonic_3.0/admin_4/pages-login.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 18 Jan 2018 10:49:20 GMT -->
</html>