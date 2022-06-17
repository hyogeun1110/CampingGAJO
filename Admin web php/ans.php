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

    $inq_id = $_REQUEST['id'];
    $ans_cont = $_REQUEST['content'];

    $query = "INSERT INTO t_answer(answer_content, inquiry_id) VALUES ('$ans_cont','$inq_id')";
    $res   = mysqli_query($conn,$query);

    $query2 = "UPDATE t_inquiry SET ans=1 WHERE inquiry_id='$inq_id'";
    $res2   = mysqli_query($conn,$query2);

    echo "<script>alert('답변작성 완료')</script>";

    sleep(1.5);
   echo "<script>location.replace('qna.php');</script>";
   
?>