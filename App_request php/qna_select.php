<?php
include "DB_conn.php";
    $user_id    = trim($_POST['user_id']);
    $user_id    = addslashes($user_id);
    $arr = array();
    $query = "SELECT * from t_inquiry where user_id='$user_id' order by inquiry_date desc";
    $res   = mysqli_query($conn,$query);
    $arr["success"] = "-1";
    while($row = mysqli_fetch_array($res)){
        $arr["success"] = "1";
        $arr[] = $row;
    }

echo json_encode($arr,JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
?>