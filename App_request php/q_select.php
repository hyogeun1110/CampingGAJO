<?php
include "DB_conn.php";
    $user_id    = trim($_POST['user_id']);
    $user_id    = addslashes($user_id);
    $arr = array();
    $query = "select * from t_inquiry where user_id='qwerasdf'";
    $arr["success"] = "-1";
    while($row = mysqli_fetch_array($res)){
        $arr["success"] = "1";
        $arr[] = $row;
    }

echo json_encode($arr,JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
?>

  
