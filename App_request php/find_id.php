<?php
    include "DB_conn.php";

    $arr = array();
    if ($_SERVER["REQUEST_METHOD"]=="POST") {
        
        $user_number    = trim($_POST['user_number']);
    
        $user_number    = addslashes($user_number);

        $query = "SELECT user_id FROM t_user WHERE user_number = '$user_number' ";
        $res   = mysqli_query($conn,$query);
        $row   = mysqli_fetch_array($res);
        if ($row['user_id'] == null) {
            $arr["success"] = "-1";        
        } else {
            $arr["success"] = $row['user_id'];        
        }
    
    } else {
        $arr["success"] = "error";
    }
echo json_encode($arr,JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
?>