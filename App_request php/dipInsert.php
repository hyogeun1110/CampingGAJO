<?php
include "DB_conn.php";

    $arr = array();
    if ($_SERVER["REQUEST_METHOD"]=="POST") {
        $id = trim($_POST['id']);
        $name = trim($_POST['name']);
        $choice = trim($_POST['choice']);
        if($choice=="1"){
            $query = "insert into t_dip values ('$id','$name')";
            $query1 = "update camp_all set count = count+1 where name like '$name%'";
            if (mysqli_query($conn, $query)&&mysqli_query($conn,$query1)) {
                $arr["success"] = "1";
            }
        }
        elseif($choice=="0"){
            $query = "delete from t_dip where user_id = '$id' and name = '$name' ";
            $query1 = "update camp_all set count = count-1 where name like '$name%'";
            if (mysqli_query($conn, $query)&&mysqli_query($conn, $query1)) {
                $arr["success"] = "-1";
            }
        }
    } else {
        $arr["success"] = "error";
    }
echo json_encode($arr,JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
?>