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
    
<!-- Mirrored from coderthemes.com/velonic_3.0/admin_4/tables-datatable.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 18 Jan 2018 10:48:22 GMT -->
<head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <link rel="shortcut icon" href="img/favicon_1.ico">

        <title>Business | ViewBusiness</title>

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
															<th>Business Images</th>
															<th>Action</th>
                                                        </tr>
                                                    </thead>
													<tbody> 
													<?php
													$data = $con->query("select * from business_user where BusinessUserEmail='".$_SESSION["business"]."'") or die(mysqli_error($con));
													$rw = mysqli_fetch_array($data);
													$uid = $rw["BusinessUserId"];
													
													
													$res = $con->query("select * from business,business_images where business.BusinessId=business_images.BusinessIdFK and business.BusinessUserIdFK='".$uid."'") or die(mysqli_error($con));
													while($row = mysqli_fetch_array($res))
													{
													?>
                                                        <tr>
                                                            <td><?php echo $row["BusinessTitle"]; ?></td>
															<td><img src="BusinessImages/<?php echo $row["BusinessImages"]; ?>" height="100" width="100"></td>
															<td><a href="AddPicture.php?update=<?php echo $row["BusinessImageId"]; ?>">Edit</a> | <a href="?delete=<?php echo $row["BusinessImageId"]; ?>">Delete</a></td>
                                                        </tr>
													<?php } ?>
                                                    </tbody>
													
                                                </table>
													<?php
														if(isset($_REQUEST["delete"]))
														{
															$result=$con->query("select BusinessImages from business_images where BusinessImageId='".$_REQUEST["delete"]."'");
															while($row1=mysqli_fetch_array($result))
															{
																$image1=$row1["BusinessImages"];
															}
															unlink("BusinessImages//".$image1);
															$con->query("delete from business_images where BusinessImageId='".$_REQUEST["delete"]."'") or die(mysqli_error($con));
															echo "<script>alert('Record deleted..!!');window.location='ViewPicture.php'</script>";
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
