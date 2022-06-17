<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <title></title>
</head>
<body>
   <?php
   include("dbconn.php");
   session_start();
      
      //admin_login.php에서 입력받은 id, password
      $username = $_POST['id'];
      $userpass = $_POST['pw'];
      
      $q = "SELECT * FROM t_admin WHERE admin_id = '$username' AND admin_pw = '$userpass'";
      $result = $conn->query($q);
      $row = $result->fetch_array(MYSQLI_ASSOC);
      
      //결과가 존재하면 세션 생성
      if ($row != null) {
         $_SESSION['username'] = $row['admin_id'];
         $_SESSION['level'] = $row['admin_level'];
         $_SESSION['name'] = $row['admin_name'];
         echo "<script>location.replace('main.php');</script>";
         exit;
      }
      
      //결과가 존재하지 않으면 로그인 실패
      if($row == null){
         echo "<script>alert('Invalid username or password')</script>";
         echo "<script>location.replace('admin_login.php');</script>";
         exit;
      }
      mysqli_close($conn);
      ?>
   </body>