<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>정보 수정</title>
<link rel="stylesheet" href="../assets/bootstrap-5.1.1/css/bootstrap.min.css">
    
<head>
</head>

<? 
	session_start();
    include("dbconn.php");
	if(!isset($_SESSION['username'])) {
		echo "<script>location.replace('admin_login.php');</script>";            
	}
	else {
		$username = $_SESSION['username'];
		$level = $_SESSION['admin_level'];
	} 

    $user_id = $_POST["user_id"];
    $user_name =  $_POST["user_name"];
    $user_number =  $_POST["user_number"];

    $query = "UPDATE t_user SET user_name='$user_name', user_number='$user_number' WHERE user_id = '$user_id'";
    $res   = mysqli_query($conn,$query);

    echo "<script>alert('정보수정완료')</script>";

    sleep(1.5);
   echo "<script>location.replace('member.php');</script>";
   
?>
	  <?
		mysqli_close($conn);
	  ?>