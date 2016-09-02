<?php
$con=mysqli_connect("mysql12.000webhost.com","a2546156_shraddh","Shraddha","a2546156_users");
if (mysqli_connect_errno())
  {
  	echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }
if(isset($_POST['password']))
	$password=$_POST['password'];

if(isset($_POST['email']))
	$email=$_POST['email'];


$statement=mysqli_prepare($con,"UPDATE usertable SET password=? WHERE email=?");
mysqli_stmt_bind_param($statement, "ss",$email,$password);
mysqli_stmt_execute($statement);
mysqli_stmt_close($statement);
mysqli_close($con);
?>
