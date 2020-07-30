<header class="top-head container-fluid">
                <button type="button" class="navbar-toggle pull-left">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                
                
                
                <!-- Left navbar -->
                <nav class=" navbar-default" role="navigation">
                    <!-- Right navbar -->
                    <ul class="nav navbar-nav navbar-right top-menu top-right-menu">  
                        <!-- mesages -->  
                        
                        <!-- /messages -->
                        <!-- Notification -->
                        
                        <!-- /Notification -->

                        <!-- user login dropdown start-->
						
                        <li class="dropdown text-center">
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <img alt="" src="AdminIcons/user.png" class="img-circle profile-img thumb-sm">
                                <span class="username">
								<?php
								if(isset($_SESSION["admin"]))
								{
									$res = $con->query("select * from admin where AdminEmail='".$_SESSION["admin"]."'") or die(mysqli_error($con));
									$row = mysqli_fetch_array($res);
									echo $row["AdminName"];
								}
								?>
								</span> <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu pro-menu fadeInUp animated" tabindex="5003" style="overflow: hidden; outline: none;">
                                <li><a href="profile.php"><i class="fa fa-briefcase"></i>Profile</a></li>
                                <li><a href="changepassword.php"><i class="fa fa-lock"></i> ChangePassword </a></li>
                                <li><a href="logout.php"><i class="fa fa-sign-out"></i> Log Out</a></li>
                            </ul>
                        </li>
                        <!-- user login dropdown end -->       
                    </ul>
                    <!-- End right navbar -->
                </nav>
                
            </header>