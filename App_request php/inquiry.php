<?
include "DB_conn.php";
 
$arr = array();
if ($_SERVER["REQUEST_METHOD"]=="POST") {
    
    $id    = trim($_POST['id']);
    $title    = $_POST['title'];
    $content    = $_POST['content'];
 
    $query = "insert into t_inquiry (inquiry_title, user_id, inquiry_content) values ('$title', '$id', '$content')";
    if (mysqli_query($conn, $query)) {
        $arr["success"] = "1";
    } else {
        $arr["success"] = "-1";
    }
 
} else {
    $arr["success"] = "error";
}
echo json_encode($arr,JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
?>
