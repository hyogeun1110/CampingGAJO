<?
include "DB_conn.php";
 
$arr = array();
if ($_SERVER["REQUEST_METHOD"]=="POST") {
    
    $user_id    = trim($_POST['user_id']);
    $user_pw    = trim($_POST['user_pw']);
    $user_name    = trim($_POST['user_name']);
    $user_age        = trim($_POST['user_age']);
 
    $user_id    = addslashes($user_id);
    $user_pw    = addslashes($user_pw);
    $user_name    = addslashes($user_name);
    $user_age    = addslashes($user_age);
 
    $reg_date = time();
 
    $query = "insert into test_member (user_id,user_pw,user_name,user_age,reg_date) values ('$user_id','$user_pw','$user_name','$user_age','$reg_date')";
    if (mysqli_query($conn, $query)) {
        $arr["success"] = "1";
    } else {
        $arr["success"] = "-1";
    }
 
} else {
    $arr["success"] = "error";
}
echo json_encode($arr,JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
?>
