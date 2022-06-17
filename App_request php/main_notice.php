<?php
    include "DB_conn.php";


    $arr = array();
    if ($_SERVER["REQUEST_METHOD"]=="POST") {
        

        $query = "SELECT notice_title FROM t_notice";
        $res   = mysqli_query($conn,$query);
        $row   = mysqli_fetch_array($res);
        if ($row['notice_title'] == null) {
            $arr["success"] = "-1";        
        } else {
            $arr["success"] = $row['notice_title'];        
        }
    
    } else {
        $arr["success"] = "error";
    }
echo json_encode($arr,JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
?>