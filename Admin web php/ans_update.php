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

    $inq_id = $_REQUEST['inq_id'];
    $ans_id = $_REQUEST['ans_id'];
    $ans_cont = $_REQUEST['content'];

    $query = "UPDATE t_answer SET answer_content='$ans_cont' WHERE answer_id='$ans_id'";
    $res   = mysqli_query($conn,$query);

    echo "<script>alert('답변수정 완료')</script>";

    sleep(1.5);
   echo "<script>location.replace('qna.php');</script>";
   
?>