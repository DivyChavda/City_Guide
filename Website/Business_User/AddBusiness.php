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
    
<!-- Mirrored from coderthemes.com/velonic_3.0/admin_4/form-validation.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 18 Jan 2018 10:48:02 GMT -->
<head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <link rel="shortcut icon" href="img/favicon_1.ico">

        <title>Business | AddBusiness</title>

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
                    <h3 class="title">Place Details</h3> 
                </div>

                <div class="row">
                    <div class="col-sm-12">
                        
                    </div> <!-- col -->

                </div> <!-- End row -->


                <!-- Form-validation -->
                <div class="row">
                    <div class="col-sm-12">
                        <div class="panel panel-default">
                            <div class="panel-heading"><h3 class="panel-title">Place Details</h3></div>
                            <div class="panel-body">
                                <div class=" form p-20">
                                    <?php
										if(isset($_REQUEST["btnupdate"]))
										{
											$image="";
											if($_FILES["imgicon"]["name"]=="")
											{
												$image=$_REQUEST["txtimg"];
											}	
											else
											{
												unlink("BusinessIcons//".$_REQUEST["txtimg"]);
												move_uploaded_file($_FILES["imgicon"]["tmp_name"],"BusinessIcons/".$_FILES["imgicon"]["name"]);
												$image=$_FILES["imgicon"]["name"];
											}
											$qr = $con->query("update business set BusinessCategoryIdFK='".$_REQUEST["drpCategory"]."',BusinessTitle='".$_REQUEST["txtbname"]."',BusinessAddress='".$_REQUEST["txtbaddress"]."',BusinessFacility='".$_REQUEST["txtbfacility"]."',BusinessDescription='".$_REQUEST["txtbdescription"]."',BusinessPhoneNo='".$_REQUEST["txtbphoneno"]."',BusinessLandlineNo='".$_REQUEST["txtblandlineno"]."',BusinessWebsite='".$_REQUEST["txtbwebsite"]."',BusinessProfilePicture='".$image."',BusinessLatitude='".$_REQUEST["txtblatitude"]."',BusinessLongitude='".$_REQUEST["txtblongitude"]."' where BusinessId='".$_REQUEST["txtid"]."'") or die(mysqli_error($con));
											if($qr == TRUE)
											{
												echo "<script>alert('Record updated..!!');window.location='ViewBusiness.php'</script>";
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
											$rs = $con->query("select * from business where BusinessId='".$_REQUEST["update"]."'") or die(mysqli_error($con));
											while($row = mysqli_fetch_array($rs))
											{
									?>
									<form class="cmxform form-horizontal tasi-form" id="commentForm" method="post" enctype="multipart/form-data" novalidate="novalidate">
										<input type="hidden" name="txtid" value="<?php echo $row["BusinessId"]; ?>">
										<input type="hidden" name="txtimg" value="<?php echo $row["BusinessProfilePicture"]; ?>">
										
                                        
										<div class="form-group ">
                                            <label for="cname" class="control-label">Title</label></br></br>
                                            <div class="col-lg-15">
                                                <input class=" form-control" value="<?php echo $row["BusinessTitle"]; ?>" id="bname" name="txtbname" type="text" required="" aria-required="true" style="margin-top: -15px;">
                                            </div>
                                        </div>
										<div class="form-group ">
                                            <label for="cname" class="control-label">Address</label></br></br>
                                            <div class="col-lg-15">
                                                <input class=" form-control" value="<?php echo $row["BusinessAddress"]; ?>" id="baddress" name="txtbaddress" type="text" required="" aria-required="true" style="margin-top: -15px;">
                                            </div>
                                        </div>
										<div class="form-group ">
                                            <label for="cname" class="control-label">Facility</label></br></br>
                                            <div class="col-lg-15">
                                                <input class=" form-control" value="<?php echo $row["BusinessFacility"]; ?>" id="bfacility" name="txtbfacility" type="text" required="" aria-required="true" style="margin-top: -15px;">
                                            </div>
                                        </div>
										<div class="form-group ">
                                            <label for="cname" class="control-label">Description</label></br></br>
											<textarea rows="4" cols="140"  name="txtbdescription" required="" aria-required="true" style="margin-top: -15px;">
                                            <?php echo $row["BusinessDescription"]; ?>
											</textarea>
                                        </div>
										<div class="form-group ">
                                            <label for="cname" class="control-label">Phone No</label></br></br>
                                            <div class="col-lg-15">
                                                <input class=" form-control" value="<?php echo $row["BusinessPhoneNo"]; ?>" id="bphoneno" name="txtbphoneno" type="text" required="" aria-required="true" style="margin-top: -15px;">
                                            </div>
                                        </div>
										<div class="form-group ">
                                            <label for="cname" class="control-label">Landline No</label></br></br>
                                            <div class="col-lg-15">
                                                <input class=" form-control" value="<?php echo $row["BusinessLandlineNo"]; ?>" id="blandlineno" name="txtblandlineno" type="text" required="" aria-required="true" style="margin-top: -15px;">
                                            </div>
                                        </div>
										<div class="form-group ">
                                            <label for="cname" class="control-label">Website</label></br></br>
                                            <div class="col-lg-15">
                                                <input class=" form-control" value="<?php echo $row["BusinessWebsite"]; ?>" id="bwebsite" name="txtbwebsite" type="text" required="" aria-required="true" style="margin-top: -15px;">
                                            </div>
                                        </div>
										<div class="form-group ">
                                            <label for="cname" class="control-label">Profile</label></br></br>
                                            <div class="col-lg-15">
                                                <input class=" form-control" name="imgicon" type="file" style="margin-top: -15px;">
												<img src="BusinessIcons/<?php echo $row["BusinessProfilePicture"]; ?>" height="100" width="100">
                                            </div>
                                        </div>
                                        
										<div class="form-group ">
                                            <label for="cname" class="control-label">Latitude</label></br></br>
                                            <div class="col-lg-15">
                                                <input class=" form-control" value="<?php echo $row["BusinessLatitude"]; ?>" id="blatitude" name="txtblatitude" type="text" required="" aria-required="true" style="margin-top: -15px;">
                                            </div>
                                        </div>
										<div class="form-group ">
                                            <label for="cname" class="control-label">Longitude</label></br></br>
                                            <div class="col-lg-15">
                                                <input class=" form-control" value="<?php echo $row["BusinessLongitude"]; ?>" id="blongitude" name="txtblongitude" type="text" required="" aria-required="true" style="margin-top: -15px;">
                                            </div>
                                        </div>
										<div class="form-group ">
                                            <label for="cname" class="control-label">Please Check Atleast One Category</label></br></br>
                                            <div class="col-lg-15">
                                                <select  class=" form-control" name="drpCategory">
												
												<option>Select Category</option>
												<?php 
												 $rs = $con->query("select * from category") or die(mysqli_error($con));
												while($rw = mysqli_fetch_array($rs))
												{
												?>
												<option value="<?php echo $rw["CategoryId"]; ?>" <?php if($rw["CategoryId"] == $row["BusinessCategoryIdFK"]) { ?> selected="selected" <?php } ?>><?php echo $rw["CategoryName"]; ?></option>
												<?php } ?>
												</select>
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
                                            <label for="cname" class="control-label">Title</label></br></br>
                                            <div class="col-lg-15">
                                                <input class=" form-control" id="bname" name="txtbtitle" type="text" required="" aria-required="true" style="margin-top: -15px;">
                                            </div>
                                        </div>
										<div class="form-group ">
                                            <label for="cname" class="control-label">Address</label></br></br>
											<textarea class="form-control" name="txtbaddress" required="" aria-required="true" style="margin-top: -15px;">
                                            
											</textarea>
                                        </div>
										<div class="form-group ">
                                            <label for="cname" class="control-label">Facility</label></br></br>
                                            <div class="col-lg-15">
                                                <input class=" form-control" id="bfacility" name="txtbfacility" type="text" required="" aria-required="true" style="margin-top: -15px;">
                                            </div>
                                        </div>
										<div class="form-group ">
                                            <label for="cname" class="control-label">Description</label></br></br>
											<textarea class="form-control" name="txtbdescription" required="" aria-required="true" style="margin-top: -15px;">
                                            
											</textarea>
                                        </div>
										<div class="form-group ">
                                            <label for="cname" class="control-label">Phone No</label></br></br>
                                            <div class="col-lg-15">
                                                <input class=" form-control" id="bphoneno" name="txtbphoneno" type="number" required="" aria-required="true" style="margin-top: -15px;">
                                            </div>
                                        </div>
										<div class="form-group ">
                                            <label for="cname" class="control-label">Landline No</label></br></br>
                                            <div class="col-lg-15">
                                                <input class=" form-control" id="blandlineno" name="txtblandlineno" type="number" required="" aria-required="true" style="margin-top: -15px;">
                                            </div>
                                        </div>
										<div class="form-group ">
                                            <label for="cname" class="control-label">Website</label></br></br>
                                            <div class="col-lg-15">
                                                <input class=" form-control" id="bwebsite" name="txtbwebsite" type="text" required="" aria-required="true" style="margin-top: -15px;">
                                            </div>
                                        </div>
										<div class="form-group ">
                                            <label for="cname" class="control-label">Profile</label></br></br>
                                            <div class="col-lg-15">
                                                <input class=" form-control" name="imgicon" type="file" required="" aria-required="true" style="margin-top: -15px;">
                                            </div>
                                        </div>
                                        
										<div class="form-group ">
                                            <label for="cname" class="control-label">Latitude</label></br></br>
                                            <div class="col-lg-15">
                                                <input class=" form-control" id="blatitude" name="txtblatitude" type="text" required="" aria-required="true" style="margin-top: -15px;">
                                            </div>
                                        </div>
										<div class="form-group ">
                                            <label for="cname" class="control-label">Longitude</label></br></br>
                                            <div class="col-lg-15">
                                                <input class=" form-control" id="blongitude" name="txtblongitude" type="text" required="" aria-required="true" style="margin-top: -15px;">
                                            </div>
                                        </div>
										<div class="form-group ">
                                            <label for="cname" class="control-label">Please Check Atleast One Category</label></br></br>
                                            <div class="col-lg-15">
                                                <select  class=" form-control" name="drpCategory">
												
												<option>Select Category</option>
												<?php 
												 $rs = $con->query("select * from category") or die(mysqli_error($con));
												while($row = mysqli_fetch_array($rs))
												{
												?>
												<option value="<?php echo $row["CategoryId"]; ?>"><?php echo $row["CategoryName"]; ?></option>
												<?php } ?>
												</select>
                                            </div>
                                        </div>
										
										<div class="form-group ">
                                            <label for="cname" class="control-label">Please Check Atleast One City</label></br></br>
                                            <div class="col-lg-15">
                                                <select  class=" form-control" name="drpCity">
												
												<option>Select City</option>
												<?php 
												 $rs = $con->query("select * from city") or die(mysqli_error($con));
												while($row = mysqli_fetch_array($rs))
												{
												?>
												<option value="<?php echo $row["CityId"]; ?>"><?php echo $row["CityName"]; ?></option>
												<?php } ?>
												</select>
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
											$data = $con->query("select * from business_user where BusinessUserEmail='".$_SESSION["business"]."'") or die(mysqli_error($con));
											$rw = mysqli_fetch_array($data);
											$uid = $rw["BusinessUserId"];
											
											move_uploaded_file($_FILES["imgicon"]["tmp_name"],"BusinessIcons/".$_FILES["imgicon"]["name"]);
											$image = $_FILES["imgicon"]["name"];
											
											$res = $con->query("insert into business(BusinessUserIdFK,BusinessCategoryIdFK,CityIdFK,BusinessTitle,BusinessAddress,BusinessFacility,BusinessDescription,BusinessPhoneNo,BusinessLandlineNo,BusinessWebsite,BusinessProfilePicture,BusinessLatitude,BusinessLongitude,BusinessStatus) 
											values('".$uid."','".$_REQUEST["drpCategory"]."','".$_REQUEST["drpCity"]."','".$_REQUEST["txtbtitle"]."','".$_REQUEST["txtbaddress"]."','".$_REQUEST["txtbfacility"]."','".$_REQUEST["txtbdescription"]."','".$_REQUEST["txtbphoneno"]."','".$_REQUEST["txtblandlineno"]."','".$_REQUEST["txtbwebsite"]."','".$image."','".$_REQUEST["txtblatitude"]."','".$_REQUEST["txtblongitude"]."','Pandding')") or die(mysqli_error($con));
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
