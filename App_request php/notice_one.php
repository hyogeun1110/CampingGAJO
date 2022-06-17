<?php
    include "DB_conn.php";

    $arr = array();
    if ($_SERVER["REQUEST_METHOD"]=="POST") {

        $query = "SELECT * FROM t_notice ORDER by date desc";
        $res   = mysqli_query($conn,$query);
        $row   = mysqli_fetch_array($res);
        if ($row == null) {
            $arr["success"] = "-1";        
        } else {
            $arr["success"] = $row['title'];   
            $arr["content"] = $row['content'];  
        }
    
    } else {
        $arr["success"] = "error";
    }
echo json_encode($arr,JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
?>