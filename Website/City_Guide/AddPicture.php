<?php
	include 'connection.php';
	session_start();
	if(!isset($_SESSION["admin"]))
	{
		echo "<script>window.location='index.php'</script>";
	}
?>
<!DOCTYPE html>
<html lang="en">
    
<!-- Mirrored from coderthemes.com/velonic_3.0/admin_4/form-validation.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 18 Jan 2018 10:48:02 GMT -->
<head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <link rel="shortcut icon" href="img/favicon_1.ico">

        <title>City_Guide | AddPicture</title>

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

       
    </head>


    <body>

        <!-- Aside Start-->
        <?php
			include'sidebar.php';
		?>
        <!-- Aside Ends-->


        <!--Main Content Start -->
        <section class="content">
            
            <!-- Header -->
            <?php
				include'header.php';
			?>
            <!-- Header Ends -->


            <!-- Page Content Start -->
            <!-- ================== -->

            <div class="wraper container-fluid">
                <div class="page-title"> 
                    <h3 class="title">Place Images</h3> 
                </div>

                <div class="row">
                    <div class="col-sm-12">
                        
                    </div> <!-- col -->

                </div> <!-- End row -->


                <!-- Form-validation -->
                <div class="row">
                    <div class="col-sm-12">
                        <div class="panel panel-default">
                            <div class="panel-heading"><h3 class="panel-title">Place Images</h3></div>
                            <div class="panel-body">
                                <div class=" form p-20">
                                    <?php
										if(isset($_REQUEST["btnupdate"]))
										{
											$image="";
											if($_FILES["imgpic"]["name"]=="")
											{
												$image=$_REQUEST["txtimg"];
											}	
											else
											{
												unlink("Advertisement_Image//".$_REQUEST["txtimg"]);
												move_uploaded_file($_FILES["imgpic"]["tmp_name"],"Advertisement_Image/".$_FILES["imgpic"]["name"]);
												$image=$_FILES["imgpic"]["name"];
											}
											$qr = $con->query("update advertisement set AdvertisementImage='".$image."' where AdvertisementId='".$_REQUEST["txtid"]."'") or die(mysqli_error($con));
											if($qr == TRUE)
											{
												echo "<script>alert('Record updated..!!');window.location='ViewPicture.php'</script>";
											}
											else
											{
												echo "<script>alert('Error while updating..!!')</script>";
											}
										}
									?>
									<?php
										if(isset($_REQUEST["update"]))
										{
											$rs = $con->query("select * from advertisement where AdvertisementId='".$_REQUEST["update"]."'") or die(mysqli_error($con));
											while($row = mysqli_fetch_array($rs))
											{
									?>
									<form class="cmxform form-horizontal tasi-form" id="commentForm" method="post" enctype="multipart/form-data" novalidate="novalidate">
										<input type="hidden" name="txtid" value="<?php echo $row["AdvertisementId"]; ?>">
										<input type="hidden" name="txtimg" value="<?php echo $row["AdvertisementImage"]; ?>">
										
										<div class="form-group ">
                                            <label for="cname" class="control-label">Picture</label></br></br>
                                            <div class="col-lg-15">
                                                <input class=" form-control" name="imgpic" type="file" required="" aria-required="true" style="margin-top: -15px;">
												<img src="Advertisement_Image/<?php echo $row["AdvertisementImage"]; ?>" height="100" width="100">
											</div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-lg-offset-2 col-lg-10">
                                                <button class="btn btn-success" name="btnupdate" type="submit">Update</button>
                                                <button class="btn btn-default" name="btncancel" type="submit">Cancel</button>
                                            </div>
                                        </div>
                                    </form>
									
									<?php
										}}else{
									?>
									<form class="cmxform form-horizontal tasi-form" id="commentForm" enctype="multipart/form-data" method="post" novalidate="novalidate">
                                        
										<div class="form-group ">
                                            <label for="cname" class="control-label">Picture</label></br></br>
                                            <div class="col-lg-15">
                                                <input class=" form-control" name="imgpic[]" type="file" multiple="multiple" required="" aria-required="true" style="margin-top: -15px;">
                                            </div>
                                        </div>
                                        
										<div class="form-group">
                                            <div class="col-lg-offset-2 col-lg-10">
                                                
                                                <button class="btn btn-default" name="btncancel" type="button" style="float: right;">Cancel</button>
												<button class="btn btn-success" name="btnadd" type="submit" style="float: right;">Add</button>
                                            </div>
                                        </div>
                                    </form>
										<?php } ?>
									<?php
										if(isset($_REQUEST["btnadd"]))
										{
											for($i=0; $i<count($_FILES['imgpic']['name']); $i++)
											{
												move_uploaded_file($_FILES["imgpic"]["tmp_name"][$i],"Advertisement_Image/".$_FILES["imgpic"]["name"][$i]);
												$image = $_FILES["imgpic"]["name"][$i];
												
												$res = $con->query("insert into advertisement(AdvertisementImage) values('".$image."')") or die(mysqli_error($con));
											}
											if($res==true)
											{
												echo "<script>alert('Record inserted..!!')</script>";
											}
											else
											{
												echo "<script>alert('Error..!!')</script>";
											}
										}
									?>
                                </div> <!-- .form -->
                            </div> <!-- panel-body -->
                        </div> <!-- panel -->
                    </div> <!-- col -->

                </div> <!-- End row -->
                

            </div>
            <!-- Page Content Ends -->
            <!-- ================== -->

            <!-- Footer Start -->
            <footer class="footer">
                2018 © CityGuide.
            </footer>
            <!-- Footer Ends -->


        </section>
        <!-- Main Content Ends -->
    


        <!-- js placed at the end of the document so the pages load faster -->
        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/pace.min.js"></script>
        <script src="js/wow.min.js"></script>
        <script src="js/jquery.nicescroll.js" type="text/javascript"></script>


        <!--form validation-->
        <script type="text/javascript" src="assets/jquery.validate/jquery.validate.min.js"></script>

        <!--form validation init-->
        <script src="assets/jquery.validate/form-validation-init.js"></script>

        <script src="js/jquery.app.js"></script>


    </body>

<!-- Mirrored from coderthemes.com/velonic_3.0/admin_4/form-validation.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 18 Jan 2018 10:48:03 GMT -->
</html>
