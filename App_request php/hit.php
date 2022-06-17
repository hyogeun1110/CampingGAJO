<?
include "DB_conn.php";

$arr = array();
if ($_SERVER["REQUEST_METHOD"]=="POST") {
    
    $idx   = trim($_POST['idx']);

 
    $sql = "UPDATE t_notice SET hit=hit+1 WHERE idx = '$idx'";

    if(mysqli_query($conn,$sql)){
        $arr["success"] = "1";        // 성공
    } else {
        $arr["success"] = "-1";        // 실패
    }

} else {
    $arr["success"] = "error";
}


echo json_encode($arr,JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);

?>