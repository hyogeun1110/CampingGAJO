<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>공지사항 수정</title>
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
		$name = $_SESSION['name'];
	} 

    $idx = $_POST["idx"];
    $title = $_POST["title"];
    $content =  $_POST["content"];
    $user_number =  $_POST["user_number"];

    $query = "UPDATE t_notice SET title='$title', content='$content' WHERE idx = '$idx'";
    $res   = mysqli_query($conn,$query);

    echo "<script>alert('수정완료')</script>";

    sleep(1.5);
   echo "<script>location.replace('notice_list.php');</script>";
?>
	  <?
		mysqli_close($conn);
	  ?>
