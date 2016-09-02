<?php
$con=mysqli_connect(" 	mysql12.000webhost.com 	","a2546156_shraddh","Shraddha","a2546156_users");
if (mysqli_connect_errno())
  {
  	echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }
if(isset($_POST['name']))
	$name=$_POST['name'];

if(isset($_POST['username']))
	$username=$_POST['username'];

if(isset($_POST['password']))
	$password=$_POST['password'];

if(isset($_POST['mobile']))
	$mobile=$_POST['mobile'];

if(isset($_POST['email']))
	$email=$_POST['email'];

$statement=mysqli_prepare($con,"INSERT INTO usertable(name,username,password,
mobile,email) VALUES (?,?,?,?,?)");
mysqli_stmt_bind_param($statement, "sssss",$name,$username,$password,
$mobile,$email);
mysqli_stmt_execute($statement);
mysqli_stmt_close($statement);
mysqli_close($con);
?>
