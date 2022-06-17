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

    $title = $_POST["title"];
    $writer =  $_POST["writer"];
    $content =  $_POST["content"];
    $date = date('Y-m-d');

    $query = "INSERT INTO t_notice VALUES (null, '$title' ,'$content', '$writer','$date', '0')";
    $res   = mysqli_query($conn,$query);

    echo "<script>alert('글쓰기완료')</script>";

    sleep(1.5);
   echo "<script>location.replace('notice_list.php');</script>";
   
?>