<?php
    include "DB_conn.php";


    $arr = array();
    if ($_SERVER["REQUEST_METHOD"]=="POST") {
        
        $user_id    = trim($_POST['user_id']);
        $user_name    = trim($_POST['user_name']);
        $user_number    = trim($_POST['user_number']);
    
        $user_id    = addslashes($user_id);
        $user_name    = addslashes($user_name);
        $user_number    = addslashes($user_number);

        $query = "SELECT user_password FROM t_user WHERE user_id = '$user_id' and user_name = '$user_name' and user_number = '$user_number' ";
        $res   = mysqli_query($conn,$query);
        $row   = mysqli_fetch_array($res);
        if ($row['user_password'] == null) {
            $arr["success"] = "-1";        
        } else {
            $arr["success"] = $row['user_password'];        
        }
    
    } else {
        $arr["success"] = "error";
    }
echo json_encode($arr,JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
?>