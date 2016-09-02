<?php
$con=mysqli_connect("localhost","a2546156_shraddh","Shraddha94!","a2546156_users");
if (mysqli_connect_errno())
{
	echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

$email=$_POST["email"];

$statement=mysqli_prepare($con,"SELECT premium,selectplan FROM applyonline WHERE email= ?");
mysqli_stmt_bind_param($statement,"s",$email);
mysqli_stmt_execute($statement);

mysqli_stmt_store_result($statement);
mysqli_stmt_bind_result($statement,$premium,$selectplan);

$user=array();

while(mysqli_stmt_fetch($statement))
{
$user[premium]=$premium;
$user[selectplan]=$selectplan;
}
echo json_encode($user);

mysqli_stmt_close($statement);
mysqli_close($con);
?>
