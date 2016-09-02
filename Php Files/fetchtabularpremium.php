<?php
$con=mysqli_connect("localhost","a2546156_shraddh","Shraddha94!","a2546156_users");
if (mysqli_connect_errno())
{
	echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

$plan=$_POST["plan"]
$term=$_POST["term"]
$age=$_POST["age"]


$statement=mysqli_prepare($con,"SELECT tabular FROM ? WHERE term= ? AND 
age=?");
mysqli_stmt_bind_param($statement, "sss",$plan,$term,$age);
mysqli_stmt_execute($statement);

mysqli_stmt_store_result($statement);
mysqli_stmt_bind_result($statement,$tabular);

$user=array();

while(mysqli_stmt_fetch($statement))
{
$user[tabularpremium]=$tabular;

echo json_encode($user);

mysqli_stmt_close($statement);
mysqli_close($con);
?>
