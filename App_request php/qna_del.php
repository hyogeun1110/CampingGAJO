<?php
include "DB_conn.php";
    $num    = trim($_POST['num']);
    $arr = array();

    $query = "Delete From t_inquiry where inquiry_id='$num'";
    $arr["del"] = "-1";
    if($res = mysqli_query($conn,$query)){
        $arr["del"] = "1";

    }

echo json_encode($arr,JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
?>