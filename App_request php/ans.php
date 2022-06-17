<?php
include "DB_conn.php";
    $num    = trim($_POST['num']);
    $num    = addslashes($num);
    $arr = array();
    $query = "SELECT * from t_answer where inquiry_id='$num'";
    $res   = mysqli_query($conn,$query);
    $arr["success"] = "-1";
    while($row = mysqli_fetch_array($res)){
        $arr["success"] = $row['answer_content'];
    }

echo json_encode($arr,JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
?>