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
?>