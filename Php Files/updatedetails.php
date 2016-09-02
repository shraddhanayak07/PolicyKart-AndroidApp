<?php
$con=mysqli_connect("mysql12.000webhost.com","a2546156_shraddh","Shraddha","a2546156_users");
if (mysqli_connect_errno())
  {
  	echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }
if(isset($_POST['username']))
	$username=$_POST['username'];

if(isset($_POST['email']))
	$email=$_POST['email'];

if(isset($_POST['mobile']))
	$mobile=$_POST['mobile'];

$statement=mysqli_prepare($con,"UPDATE usertable SET mobile=? AND email=? WHERE username=?");
mysqli_stmt_bind_param($statement, "sss",,$mobile,$email,$username);
mysqli_stmt_execute($statement);
mysqli_stmt_close($statement);
mysqli_close($con);
?>
