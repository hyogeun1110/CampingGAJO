<?php
    include "DB_conn.php";
    
    $arr = array();
    if ($_SERVER["REQUEST_METHOD"]=="POST") {
        
        $user_id    = trim($_POST['user_id']);
        $user_password    = trim($_POST['user_password']);
    
        $user_id    = addslashes($user_id);
        $user_password    = addslashes($user_password);
    
        $query = "SELECT user_id FROM t_user WHERE user_id = '$user_id' ";
        $res   = mysqli_query($conn,$query);
        $row   = mysqli_fetch_array($res);
        if ($row['user_id'] == null) {
            $arr["success"] = "1";        // 아이디 중복안댐 성공
        } else {
            $arr["success"] = "-1";        // 중복
        }
    
    } else {
        $arr["success"] = "error";
    }
echo json_encode($arr,JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
?>