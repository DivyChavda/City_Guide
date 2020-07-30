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
    
<!-- Mirrored from coderthemes.com/velonic_3.0/admin_4/index.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 18 Jan 2018 10:46:40 GMT -->
<head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="A fully featured admin theme which can be used to build CRM, CMS, etc.">
        <meta name="author" content="Coderthemes">

        <link rel="shortcut icon" href="img/favicon_1.ico">

        <title>Business | Dashboard</title>

        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/bootstrap-reset.css" rel="stylesheet">

        <!--Animation css-->
        <link href="css/animate.css" rel="stylesheet">

        <!--Icon-fonts css-->
        <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
        <link href="assets/ionicon/css/ionicons.min.css" rel="stylesheet" />
        <link href="assets/material-design-iconic-font/css/material-design-iconic-font.min.css" rel="stylesheet" />

        <!--Morris Chart CSS -->
        <link rel="stylesheet" href="assets/morris/morris.css">

        <!-- sweet alerts -->
        <link href="assets/sweet-alert/sweet-alert.min.css" rel="stylesheet">

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

        <!-- Aside Start-->
        <?php
			include 'sidebar.php';
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
                    <h3 class="title">Welcome !</h3> 
                </div>


                


                


                <div class="row">
                    <div class="col-lg-3 col-sm-6">
                        <div class="tile-stats white-bg"> 
                            <div class="status">
                                <h3 class="m-t-0"><span class="counter">
								
								<?php
								
								$data = $con->query("select * from business_user where BusinessUserEmail='".$_SESSION["business"]."'") or die(mysqli_error($con));
								$rw = mysqli_fetch_array($data);
								$uid = $rw["BusinessUserId"];
													
								$rs=$con->query("select * from business where BusinessUserIdFK = '".$uid."'") or die(mysqli_error($con));
								echo mysqli_num_rows($rs);
								?>
								
								</span></h3> 
                                <p>Total Number of Business</p>
                            </div> 
                            <div class="chart-inline">
                                <span class="inlinesparkline"></span> 
                            </div>
                        </div> 
                    </div>

                    <!--<div class="col-lg-3 col-sm-6">
                        <div class="tile-stats white-bg"> 
                            <div class="status">
                                <h3 class="m-t-0 counter">49</h3> 
                                <p>Avg. Sales per day</p>
                            </div> 
                            <span class="dynamicbar-big"></span> 
                        </div> 
                    </div>

                    <div class="col-lg-3 col-sm-6">
                        <div class="tile-stats white-bg"> 
                            <div class="status">
                                <h3 class="m-t-0 counter">0.9</h3> 
                                <p>Stock Market</p>
                            </div> 
                            <span id="compositeline"></span> 
                        </div>
                    </div>

                    <div class="col-lg-3 col-sm-6">
                        <div class="tile-stats white-bg"> 
                            <div class="col-sm-8">
                                <div class="status">
                                <h3 class="m-t-15"><span class="counter">91.5</span>%</h3> 
                                <p>US Dollar Share</p>
                            </div> 
                            </div>
                            <div class="col-sm-4 m-t-20">
                                <span class="sparkpie-big"></span> 
                            </div>
                        </div> 
                    </div>-->
                </div>


                <!-- WEATHER -->
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
        <script src="js/modernizr.min.js"></script>
        <script src="js/pace.min.js"></script>
        <script src="js/wow.min.js"></script>
        <script src="js/jquery.scrollTo.min.js"></script>
        <script src="js/jquery.nicescroll.js" type="text/javascript"></script>

        <!-- Counter-up -->
        <script src="js/waypoints.min.js" type="text/javascript"></script>
        <script src="js/jquery.counterup.min.js" type="text/javascript"></script>

         <!-- sparkline --> 
        <script src="assets/sparkline-chart/jquery.sparkline.min.js" type="text/javascript"></script>
        <script src="assets/sparkline-chart/chart-sparkline.js" type="text/javascript"></script> 

        <!-- skycons -->
        <script src="js/skycons.min.js" type="text/javascript"></script>
    
        <!--Morris Chart-->
        <script src="assets/morris/morris.min.js"></script>
        <script src="assets/morris/raphael.min.js"></script>


        <script src="js/jquery.app.js"></script>
        
        <!-- Dashboard -->
        <script src="js/jquery.dashboard.js"></script>


        <script type="text/javascript">
            jQuery(document).ready(function($) {
                /* Counter Up */
                $('.counter').counterUp({
                    delay: 100,
                    time: 1200
                });
            });
            /* BEGIN SVG WEATHER ICON */
            if (typeof Skycons !== 'undefined'){
            var icons = new Skycons(
                {"color": "#fff"},
                {"resizeClear": true}
                ),
                    list  = [
                        "clear-day", "clear-night", "partly-cloudy-day",
                        "partly-cloudy-night", "cloudy", "rain", "sleet", "snow", "wind",
                        "fog"
                    ],
                    i;

                for(i = list.length; i--; )
                icons.set(list[i], list[i]);
                icons.play();
            };
        </script>

    

    </body>

<!-- Mirrored from coderthemes.com/velonic_3.0/admin_4/index.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 18 Jan 2018 10:47:06 GMT -->
</html>
