<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원 삭제</title>
<link rel="stylesheet" href="../assets/bootstrap-5.1.1/css/bootstrap.min.css">
    
<head>
</head>

<? 
	session_start();
    include("dbconn.php");
	if(!isset($_SESSION['username'])) {
		echo "<script>location.replace('admin_login.php');</script>";            
	}

    $user_id = $_POST["user_id"];

    $query = "DELETE FROM t_user WHERE user_id='$user_id'";
    $res   = mysqli_query($conn,$query);

    echo "<script>alert('회원삭제완료')</script>";

    sleep(1.5);
   echo "<script>location.replace('member.php');</script>";

   
?>
	  <?
		mysqli_close($conn);
	  ?>