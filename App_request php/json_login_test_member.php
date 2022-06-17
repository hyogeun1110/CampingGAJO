<?
include "DB_conn.php";
 
$arr = array();
if ($_SERVER["REQUEST_METHOD"]=="POST") {
    
    $user_id    = trim($_POST['user_id']);
    $user_pw    = trim($_POST['user_pw']);
 
    $user_id    = addslashes($user_id);
    $user_pw    = addslashes($user_pw);
 
    $query = "select user_id from test_member where user_id='$user_id' and user_pw='$user_pw'";
    $res   = mysqli_query($conn,$query);
    $row   = mysqli_fetch_array($res);
    if ($row['user_id']) {
        $arr["success"] = "1";        // 로그인 성공
    } else {
        $arr["success"] = "-1";        // 로그인 실패
    }
 
} else {
    $arr["success"] = "error";
}
echo json_encode($arr,JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
?>
