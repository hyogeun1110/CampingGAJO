<?php
include "DB_conn.php";

    $arr = array();
    if ($_SERVER["REQUEST_METHOD"]=="POST") {
        $id = trim($_POST['id']);
        $name = trim($_POST['name']);
        $query = "select name from t_dip where user_id='$id' and name='$name' ";
        $res   = mysqli_query($conn,$query);
        $row   = mysqli_fetch_array($res);
        if ($row['name'] == null) {
            $arr["success"] = "1";
        } else {
            $arr["success"] = "-1";
        }
            
    } else {
        $arr["success"] = "error";
    }
echo json_encode($arr,JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
?>