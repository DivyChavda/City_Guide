<?php
	include 'connection.php';
	
	
	$query = "select * from favourite where BussinessIdFK='".$_REQUEST["BussinessIdFK"]."' and BusinessUserIdFK='".$_REQUEST["BusinessUserIdFK"]."'";
	$result = $con->query($query) or die(mysqli_error($con));

	$posts = array();
	if(mysqli_num_rows($result)) {
		while($post = mysqli_fetch_assoc($result)) {
			$posts[] = array('post'=>$post);
		}
	}
	
	header('Content-type: text/xml');
	echo '<posts>';
	foreach($posts as $index => $post) {
		if(is_array($post)) {
			foreach($post as $key => $value) {
				echo '<',$key,'>';
				if(is_array($value)) {
					foreach($value as $tag => $val) {
						echo '<',$tag,'>',htmlentities($val),'</',$tag,'>';
					}
				}
				echo '</',$key,'>';
			}
		}
	}
	echo '</posts>';

	@mysqli_close($con);
?>