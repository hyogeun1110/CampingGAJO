<? 
include "DB_conn.php";

$arr = array();
if ($_SERVER["REQUEST_METHOD"]=="POST") {
    
    $user_id    = trim($_POST['user_id']);
    $user_password    = trim($_POST['user_password']);
 
    $user_id    = addslashes($user_id);
    $user_password    = addslashes($user_password);
 
    $query = "select user_id, user_name, user_number from t_user where user_id='$user_id' and user_password='$user_password'";
    $res   = mysqli_query($conn,$query);
    $row   = mysqli_fetch_array($res);
    if ($row['user_id']) {
        $arr["success"] = "1";
        $arr["name"] = $row['user_name'];
        $arr["number"] = $row['user_number'];        // 성공
    } else {
        $arr["success"] = "-1";        // 실패
    }

} else {
    $arr["success"] = "error";
}


echo json_encode($arr,JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);

?>