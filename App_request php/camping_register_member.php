<?
header("Content-Type:application/json");

$db_host = "localhost"; 

$db_user = "campinggajo"; 

$db_passwd = "ysucamgajo1!";

$db_name = "campinggajo";



// MySQL - DB 접속.

$conn = mysqli_connect($db_host,$db_user,$db_passwd,$db_name);

if (mysqli_connect_errno()){
    echo "MySQL 연결 오류: " . mysqli_connect_error();
    exit;
} else {

// echo "DB : \"$db_name\"에 접속 성공.<br/>";

}

// 문자셋 설정, utf8.

mysqli_set_charset($conn,"utf8"); 
 
$arr = array();
if ($_SERVER["REQUEST_METHOD"]=="POST") {
    
    $user_id    = trim($_POST['user_id']);
    $user_password    = trim($_POST['user_password']);
    $user_name        = trim($_POST['user_name']);
    $user_number    = trim($_POST['user_number']);
 
    $user_id    = addslashes($user_id);
    $user_password    = addslashes($user_password);
    $user_name    = addslashes($user_name);
    $user_number    = addslashes($user_number);
    
    $sql = "SELECT * FROM t_user WHERE user_number='$user_number'";
    $res = mysqli_query($conn, $sql);  
    if ($row   = mysqli_fetch_array($res)) {
        $arr["success"] = "-2";
    } else {     
        $query = "insert into t_user values ('$user_id','$user_password','$user_name','$user_number')";
        if (mysqli_query($conn, $query)) {
            $arr["success"] = "1";
        } else {
            $arr["success"] = "-1";
        }
    } 
 
} else {
    $arr["success"] = "error";
}
echo json_encode($arr,JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
?>
