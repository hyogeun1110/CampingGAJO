<?php
    include "DB_conn.php";

    mysqli_set_charset($conn, "utf-8");

    $res = mysqli_query($conn, "SELECT idx, title, writer FROM t_notice");

    $result = array();

    while($row = mysqli_fetch_array($res)) {

        array_push($result, array("idx"=>$row[0], "title"=>$row[1], "writer"=>$row[2]));
    
    }
    echo json_encode(array("result"=>$result),JSON_UNESCAPED_UNICODE);
    

    mysqli_close($conn);
?>
