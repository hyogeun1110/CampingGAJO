<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>정보 수정</title>
<link rel="stylesheet" href="../assets/bootstrap-5.1.1/css/bootstrap.min.css">
    
<head>
</head>

<? 
	session_start();
    include("dbconn.php");
	if(!isset($_SESSION['username'])) {
		echo "<script>location.replace('admin_login.php');</script>";            
	}

    $name = $_POST["name"];
    $address =  $_POST["address"];
    $lat =  $_POST["lat"];
    $lon =  $_POST["lon"];

    $normal =  $_POST["normal"];
    $karaban =  $_POST["karaban"];
    $glam =  $_POST["glam"];
    $car =  $_POST["car"];

    $kind = implode(",",$_POST["kind"]);

    $toilet =  $_POST["toilet"];
    $shower =  $_POST["shower"];
    $sink =  $_POST["sink"];
    $etc =  $_POST["etc"];

    $query = "insert into camp_all (`name`, `lat`, `lon`, `camping_kind`, `address`, `toilet`, `shower`, `sink`, `etc`) values ('$name','$lat','$lon','$kind','$address','$toilet','$shower','$sink','$etc')";
    
    if($res = mysqli_query($conn,$query)){
        echo "<script>alert('캠핑장 추가 완료')</script>";
        sleep(1.5);
        echo "<script>location.replace('Camping_select.php');</script>";
    }
    else {
        echo "<script>alert('캠핑장 추가 실패')</script>";

        sleep(2);

        $prevPage = $_SERVER['HTTP_REFERER'];
        // 변수에 이전페이지 정보를 저장

        header('location:'.$prevPage);
        // 페이지 이동
    }
    
?>
	  <?
		mysqli_close($conn);
	  ?>