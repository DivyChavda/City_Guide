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
    
<!-- Mirrored from coderthemes.com/velonic_3.0/admin_4/tables-datatable.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 18 Jan 2018 10:48:22 GMT -->
<head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <link rel="shortcut icon" href="img/favicon_1.ico">

        <title>CityGuide | ViewBusiness</title>

        <!-- DataTables -->
        <link href="assets/datatables/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />
        <link href="assets/datatables/buttons.bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="assets/datatables/fixedHeader.bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="assets/datatables/responsive.bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="assets/datatables/scroller.bootstrap.min.css" rel="stylesheet" type="text/css" />

        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/bootstrap-reset.css" rel="stylesheet">

        <!--Animation css-->
        <link href="css/animate.css" rel="stylesheet">

        <!--Icon-fonts css-->
        <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
        <link href="assets/ionicon/css/ionicons.min.css" rel="stylesheet" />
        <link href="assets/material-design-iconic-font/css/material-design-iconic-font.min.css" rel="stylesheet" />

        <!-- DataTables -->
        <link href="assets/datatables/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />

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
				include 'header.php';
			?>
            <!-- Header Ends -->


            <!-- Page Content Start -->
            <!-- ================== -->

            <div class="wraper container-fluid">
                <div class="page-title"> 
                    <h3 class="title">View Business</h3> 
                </div>


                <div class="row">
                            <div class="col-md-12">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">View Business</h3>
                                    </div>
                                    <div class="panel-body">
                                        <div class="row" style="overflow:scroll;">
                                            <div class="col-md-12 col-sm-12 col-xs-12">
                                                <table id="datatable" class="table table-striped table-bordered">
                                                    <thead>
                                                        <tr>
                                                            <th>Business Title</th>
															<th>Business Address</th>
															<th>Business Facility</th>
															<th>Business Description</th>
															<th>Business PhoneNo</th>
															<th>Business LandlineNo</th>
															<th>Business Website</th>
															<th>Business Icon</th>
															<th>Business Latitude</th>
															<th>Business Longitude</th>
															<th>Business Category</th>
															<th>Business Status</th>
															<th>Action</th>
                                                        </tr>
                                                    </thead>
													<tbody> 
													<?php
													$res = $con->query("select * from business") or die(mysqli_error($con));
													while($row = mysqli_fetch_array($res))
													{
													?>
                                                        <tr>
                                                            <td><?php echo $row["BusinessTitle"]; ?></td>
															<td><?php echo $row["BusinessAddress"]; ?></td>
															<td><?php echo $row["BusinessFacility"]; ?></td>
															<td><?php echo $row["BusinessDescription"]; ?></td>
															<td><?php echo $row["BusinessPhoneNo"]; ?></td>
															<td><?php echo $row["BusinessLandlineNo"]; ?></td>
															<td><?php echo $row["BusinessWebsite"]; ?></td>
															<td><img src="../Business_User/BusinessIcons/<?php echo $row["BusinessProfilePicture"]; ?>" height="100" width="100"></td>
															<td><?php echo $row["BusinessLatitude"]; ?></td>
															<td><?php echo $row["BusinessLongitude"]; ?></td>
															<td>
																<?php
																	$rs = $con->query("select * from category") or die(mysqli_error($con));
																	while($rdata = mysqli_fetch_array($rs))
																	{
																		if($rdata["CategoryId"] == $row["BusinessCategoryIdFK"])
																			echo $rdata["CategoryName"];
																	}
																?>
															</td>
															<td><?php echo $row["BusinessStatus"]; ?></td>
															<td><a href="?approve=<?php echo $row["BusinessId"]; ?>">Approve</a> | <a href="?reject=<?php echo $row["BusinessId"]; ?>">Reject</a></td>
                                                        </tr>
													<?php } ?>
                                                    </tbody>
													
                                                </table>
													<?php
														if(isset($_REQUEST["approve"]))
														{
															$qr = $con->query("update business set BusinessStatus='Approve' where BusinessId='".$_REQUEST["approve"]."'") or die(mysqli_error($con));
															if($qr == TRUE)
															{
																echo "<script>alert('Business Approve..!!');window.location='ViewBusiness.php'</script>";
															}
														}
														if(isset($_REQUEST["reject"]))
														{
															$qr = $con->query("update business set BusinessStatus='Reject' where BusinessId='".$_REQUEST["reject"]."'") or die(mysqli_error($con));
															if($qr == TRUE)
															{
																echo "<script>alert('Business Reject..!!');window.location='ViewBusiness.php'</script>";
															}
														}
													?>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div> <!-- End Row -->


                        <div class="row">
                            <div class="col-md-12">
                                
                            </div>

                        </div> <!-- End Row -->


                        <div class="row">
                            <div class="col-md-12">
                                
                            </div>
                        </div> <!-- End Row -->



                        <div class="row">
                            <div class="col-md-12">
                                
                            </div>
                        </div> <!-- End Row -->


                        <div class="row">
                            <div class="col-md-12">
                                
                            </div>

                        </div> <!-- End Row -->



                        <div class="row">
                            <div class="col-md-12">
                               
                            </div>

                        </div> <!-- End Row -->

                

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


        <script src="js/jquery.app.js"></script>

        <!-- Datatables-->
        <script src="assets/datatables/jquery.dataTables.min.js"></script>
        <script src="assets/datatables/dataTables.bootstrap.js"></script>
        <script src="assets/datatables/dataTables.buttons.min.js"></script>
		<script src="assets/datatables/dataTables.fixedHeader.min.js"></script>
        <script src="assets/datatables/dataTables.keyTable.min.js"></script>
        <script src="assets/datatables/dataTables.responsive.min.js"></script>
        <script src="assets/datatables/responsive.bootstrap.min.js"></script>
        <script src="assets/datatables/dataTables.scroller.min.js"></script>
        <script src="assets/datatables/buttons.bootstrap.min.js"></script>
        <script src="assets/datatables/jszip.min.js"></script>
        <script src="assets/datatables/pdfmake.min.js"></script>
        <script src="assets/datatables/vfs_fonts.js"></script>
        <script src="assets/datatables/buttons.html5.min.js"></script>
        <script src="assets/datatables/buttons.print.min.js"></script>
        

        <!-- Datatable init js -->
        <script src="js/datatables.init.js"></script>


        <script type="text/javascript">
            $(document).ready(function() {
                $('#datatable').dataTable();
                $('#datatable-keytable').DataTable( { keys: true } );
                $('#datatable-responsive').DataTable();
                $('#datatable-scroller').DataTable( { ajax: "assets/datatables/json/scroller-demo.json", deferRender: true, scrollY: 380, scrollCollapse: true, scroller: true } );
                var table = $('#datatable-fixed-header').DataTable( { fixedHeader: true } );
            } );
            TableManageButtons.init();
        </script>

    </body>

<!-- Mirrored from coderthemes.com/velonic_3.0/admin_4/tables-datatable.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 18 Jan 2018 10:48:44 GMT -->
</html>
