<?php
include "DB_conn.php";

    $arr = array();
    $query = "SELECT * from camp_all order by count desc limit 30";   
    $res   = mysqli_query($conn,$query);
    $arr["success"] = "-1";
    while($row = mysqli_fetch_array($res)){
        $arr["success"] = "1";
        $arr[] = $row;
    }

echo json_encode($arr,JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
?>