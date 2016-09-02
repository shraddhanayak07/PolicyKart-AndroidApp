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

if(isset($_POST['city']))
	$city=$_POST['city'];

if(isset($_POST['address']))
	$address=$_POST['address'];

if(isset($_POST['pincode']))
	$pincode=$_POST['pincode'];

if(isset($_POST['age']))
	$age=$_POST['age'];

if(isset($_POST['gender']))
	$gender=$_POST['gender'];

if(isset($_POST['plan']))
	$plan=$_POST['plan'];

if(isset($_POST['ifreceivenews']))
	$ifreceivenews=$_POST['ifreceivenews'];

$statement=mysqli_prepare($con,"INSERT INTO applyonline(firstname,lastname,
mobile,email,city,address,pincode,age,gender,plan,ifreceivenews) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
mysqli_stmt_bind_param($statement, "sssssssssss",$firstname,$lastname,
$mobile,$email,$city,$address,$pincode,$age,$gender,$plan,$ifreceivenews);
mysqli_stmt_execute($statement);
mysqli_stmt_close($statement);
mysqli_close($con);
?>
