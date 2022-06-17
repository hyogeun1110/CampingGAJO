<?php
include "DB_conn.php";

    $arr = array();
    if ($_SERVER["REQUEST_METHOD"]=="POST") {
        $location = trim($_POST['location']);
        $kind = trim($_POST['kind']);
        $shower = trim($_POST['shower']);
        $sink = trim($_POST['sink']);
        if($location=="all"){
            if($shower=="0" && $sink=="1"){
                $query = "SELECT * FROM camp_all where camping_kind like '%$kind%' and sink='$sink' ";
            }
            elseif($sink=="0" && $shower=="1"){
                $query = "SELECT * FROM camp_all where camping_kind like '%$kind%' and shower='$shower' ";
            }
            elseif($sink=="0" && $shower=="0"){
                $query = "SELECT * FROM camp_all where camping_kind like '%$kind%'";
            }
            else{
                $query = "SELECT * FROM camp_all where camping_kind like '%$kind%' and shower='$shower' and sink='$sink' ";
            }
        }
        else{
            if($shower=="0" && $sink=="1"){
                $query = "SELECT * FROM `camp_$location` as cp JOIN camp_all as ca ON cp.name = ca.name where camping_kind like '%$kind%' and sink='$sink' ";
            }
            elseif($sink=="0" && $shower=="1"){
                $query = "SELECT * FROM `camp_$location` as cp JOIN camp_all as ca ON cp.name = ca.name where camping_kind like '%$kind%' and shower='$shower' ";
            }
            elseif($sink=="0" && $shower=="0"){
                $query = "SELECT * FROM `camp_$location` as cp JOIN camp_all as ca ON cp.name = ca.name where camping_kind like '%$kind%'";
            }
            else{
                $query = "SELECT * FROM `camp_$location` as cp JOIN camp_all as ca ON cp.name = ca.name where camping_kind like '%$kind%' and shower='$shower' and sink='$sink' ";
            }
        }
        $res   = mysqli_query($conn,$query);
        $arr["success"] = "-1";
        while($row = mysqli_fetch_array($res)){
            $arr["success"] = "1";
            $arr[] = $row;
        }
    } else {
        $arr["success"] = "error";
    }
echo json_encode($arr,JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
?>