<?
include "DB_conn.php";

$arr = array();
if ($_SERVER["REQUEST_METHOD"]=="POST") {
    
    $user_id    = trim($_POST['user_id']);
    $user_password    = trim($_POST['user_password']);
    $user_name    = trim($_POST['user_name']);
    $user_number    = trim($_POST['user_number']);
 
    $user_id    = addslashes($user_id);
    $user_password    = addslashes($user_password);
    $user_name    = addslashes($user_name);
    $user_number    = addslashes($user_number);
 
    $sql = "UPDATE t_user SET user_password='$user_password', user_name='$user_name', user_number='$user_number' WHERE user_id = '$user_id'";

    if(mysqli_query($conn,$sql)){
        $arr["update_suc"] = "1";        // 성공
    } else {
        $arr["update_suc"] = "-1";        // 실패
    }

} else {
    $arr["update_suc"] = "error";
}


echo json_encode($arr,JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);

?>