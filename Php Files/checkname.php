<?php
$con=mysqli_connect("localhost","a2546156_shraddh","Shraddha94!","a2546156_users");
if (mysqli_connect_errno())
  {
  	echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }


$username=$_POST["username"]

$statement=mysqli_prepare($con,"SELECT * FROM usertable WHERE username= ?");
mysqli_stmt_bind_param($statement, "s",$username);
mysqli_stmt_execute($statement);

mysqli_stmt_store_result($statement);
mysqli_stmt_bind_result($statement,$name,$username,$password,$email,$mobile);

$user=array();

while(mysqli_stmt_fetch($statement))
{
$user[name]=$name;
$user[username]=$username;
$user[password]=$password;
$user[mobile]=$mobile;
$user[email]=$email;
}

echo json_encode($user);

mysqli_stmt_close($statement);
mysqli_close($con);
?>
