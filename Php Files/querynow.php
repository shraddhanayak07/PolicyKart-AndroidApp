<?php
$con=mysqli_connect("mysql12.000webhost.com","a2546156_shraddh","Shraddha94","a2546156_users");
if (mysqli_connect_errno())
  {
  	echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }
if(isset($_POST['firstname']))
	$firstname=$_POST['firstname'];

if(isset($_POST['lastname']))
	$lastname=$_POST['lastname'];

if(isset($_POST['mobile']))
	$mobile=$_POST['mobile'];

if(isset($_POST['email']))
	$email=$_POST['email'];

if(isset($_POST['policyn']))
	$policyn=$_POST['policyn'];

if(isset($_POST['comments']))
	$comments=$_POST['comments'];

if(isset($_POST['ifreceivenews']))
	$ifreceivenews=$_POST['ifreceivenews'];

$statement=mysqli_prepare($con,"INSERT INTO querynow(firstname,lastname,
mobile,email,policyn,comments,isreceivenews) VALUES (?,?,?,?,?,?,?)");
mysqli_stmt_bind_param($statement, "sssssss",$firstname,$lastname,
$mobile,$email,$policyn,$comments,$isreceivenews);
mysqli_stmt_execute($statement);
mysqli_stmt_close($statement);
mysqli_close($con);
?>
