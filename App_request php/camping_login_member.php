

<?
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
// header("Content-Type:application/json");
// include "../inc/config_noHeader.php";
 
$arr = array();
if ($_SERVER["REQUEST_METHOD"]=="POST") {
    
    $user_id    = trim($_POST['user_id']);
    $user_password    = trim($_POST['user_password']);
 
    $user_id    = addslashes($user_id);
    $user_password    = addslashes($user_password);
 
    $query = "select user_id, user_name from t_user where user_id='$user_id' and user_password='$user_password'";
    $res   = mysqli_query($conn,$query);
    $row   = mysqli_fetch_array($res);
    if ($row['user_id']) {
        $arr["success"] = "1";        // 로그인 성공
    } else {
        $arr["success"] = "-1";        // 로그인 실패
    }
 
    if ($row['user_name']) {
        $arr["name"] = $row['user_name'];        // 이름값 가져오기
    } else {
        $arr["name"] = "";   
    }


} else {
    $arr["success"] = "error";
}


echo json_encode($arr,JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
?>
