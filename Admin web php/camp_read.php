<? 
	session_start();
    include("dbconn.php");
	if(!isset($_SESSION['username'])) {
		echo "<script>location.replace('admin_login.php');</script>";            
	}

  $idx = $_GET["idx"];
?>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>회원정보 수정</title>
  <link rel="stylesheet" href="./assets//bootstrap-5.1.1/css/bootstrap.min.css"
	<meta name="theme-color" content="#7952b3">
  <style>
    body {
      font-size: .875rem;
      }

      .feather {
      width: 16px;
      height: 16px;
      vertical-align: text-bottom;
      }

      /*
      * Sidebar
      */

      .sidebar {
      position: fixed;
      top: 0;
      /* rtl:raw:
      right: 0;
      */
      bottom: 0;
      /* rtl:remove */
      left: 0;
      z-index: 100; /* Behind the navbar */
      padding: 48px 0 0; /* Height of navbar */
      box-shadow: inset -1px 0 0 rgba(0, 0, 0, .1);
      }

      @media (max-width: 767.98px) {
      .sidebar {
        top: 5rem;
      }
      }

      .sidebar-sticky {
      position: relative;
      top: 0;
      height: calc(100vh - 48px);
      padding-top: .5rem;
      overflow-x: hidden;
      overflow-y: auto; /* Scrollable contents if viewport is shorter than content. */
      }

      .sidebar .nav-link {
      font-weight: 500;
      color: #333;
      }

      .sidebar .nav-link .feather {
      margin-right: 4px;
      color: #727272;
      }

      .sidebar .nav-link.active {
      color: #2470dc;
      }

      .sidebar .nav-link:hover .feather,
      .sidebar .nav-link.active .feather {
      color: inherit;
      }

      .sidebar-heading {
      font-size: .75rem;
      text-transform: uppercase;
      }

      /*
      * Navbar
      */

      .navbar-brand {
      padding-top: .75rem;
      padding-bottom: .75rem;
      font-size: 1rem;
      background-color: rgba(0, 0, 0, .25);
      box-shadow: inset -1px 0 0 rgba(0, 0, 0, .25);
      }

      .navbar .navbar-toggler {
      top: .25rem;
      right: 1rem;
      }

      .navbar .form-control {
      padding: .75rem 1rem;
      border-width: 0;
      border-radius: 0;
      }

      .form-control-dark {
      color: #fff;
      background-color: rgba(255, 255, 255, .1);
      border-color: rgba(255, 255, 255, .1);
      }

      .form-control-dark:focus {
      border-color: transparent;
      box-shadow: 0 0 0 3px rgba(255, 255, 255, .25);
      }
          .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
          }

          @media (min-width: 768px) {
            .bd-placeholder-img-lg {
              font-size: 3.5rem;
      }
    }
  </style>
</head>
<body>
  <header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
  <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3" 
			href="./main.php">CampingGAJO</a>
	 </header>

  <div class="container-fluid">
    <div class="row">
    <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
			<div class="position-sticky pt-3">
	  
			  <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
				<span>MENU</span>
				<a class="link-secondary" href="#" aria-label="Add a new report">
				  <span data-feather="plus-circle"></span>
				</a>
			  </h6>
			  <ul class="nav flex-column mb-2">
				<li class="nav-item">
				  <a class="nav-link" href="./member.php">
					<span data-feather="file-text"></span>
					회원정보
				  </a>
				</li>
				<li class="nav-item">
				<a class="nav-link active" href="./Camping_select.php">
					<span data-feather="file-text"></span>
					캠핑장 정보
				  </a>
				</li>
				<li class="nav-item">
				  <a class="nav-link" href="./qna.php">
					<span data-feather="file-text"></span>
					질문
				  </a>
				</li>
				<li class="nav-item">
				  <a class="nav-link" href="./notice_list.php">
					<span data-feather="file-text"></span>
					공지사항
				  </a>
				</li>
			  </ul>
			</div>
		  </nav>

      <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
        <?php
          $user_id = $_GET["user_id"];

          $query = "select * from camp_all where num='$idx'";
          $res   = mysqli_query($conn,$query);
          $row   = mysqli_fetch_array($res);
          if($row){
            $name = $row['name'];
            $lat = $row['lat'];
            $lon = $row['lon'];
            $kind = $row['camping_kind'];
            $addr = $row['address'];
            $toilet = $row['toilet'];
            $shower = $row['shower'];
            $sink = $row['sink'];
            $etc = $row['etc'];
            $count = $row['count'];
          }

        ?>
        <p></p>
        <h2><img class="mb-4" src="idcard.png" alt="" width="50" height="50">  캠핑장 정보</h2>
          <form method="post" action="camp_update.php?idx=<?php echo $idx; ?>">
            <div class="mb-3">
              <label for="id" class="form-label">캠핑장 이름</label>
              <input type="text" class="form-control" name="name" value='<? echo $name?>' placeholder='<? echo $name?>' maxlength="50" readonly>
            </div>

            <div class="mb-3">
              <label for="name" class="form-label">위도</label>
              <input type="text" class="form-control" name="lat" value="<? echo $lat?>" placeholder="<? echo $lat?>" maxlength="10" readonly>
            </div>

            <div class="mb-3">
              <label for="name" class="form-label">경도</label>
              <input type="text" class="form-control" name="lon" value="<? echo $lon?>" placeholder="<? echo $lon?>" maxlength="10" readonly>
            </div>

            <div class="mb-3">
              <label for="name" class="form-label">종류</label>
              <input type="text" class="form-control" name="kind" value="<? echo $kind?>" placeholder="<? echo $kind?>" maxlength="10" readonly>
            </div>

            <div class="mb-3">
              <label for="name" class="form-label">주소</label>
              <input type="text" class="form-control" name="addr" value="<? echo $addr?>" placeholder="<? echo $addr?>" maxlength="10" readonly>
            </div>

            <div class="mb-3">
              <label for="name" class="form-label">화장실</label>
              <input type="text" class="form-control" name="toilet" value="<? echo $toilet?>" placeholder="<? echo $toilet?>" maxlength="10" readonly>
            </div>

            <div class="mb-3">
              <label for="name" class="form-label">샤워실</label>
              <input type="text" class="form-control" name="shower" value="<? echo $shower?>" placeholder="<? echo $shower?>" maxlength="10" readonly>
            </div>

            <div class="mb-3">
              <label for="name" class="form-label">싱크대</label>
              <input type="text" class="form-control" name="sink" value="<? echo $sink?>" placeholder="<? echo $sink?>" maxlength="10" readonly>
            </div>

            <div class="mb-3">
              <label for="name" class="form-label">기타 정보</label>
              <input type="text" class="form-control" name="etc" value="<? echo $etc?>" placeholder="<? echo $etc?>" maxlength="10" readonly>
            </div>

            <div class="mb-3">
              <label for="name" class="form-label">찜 개수</label>
              <input type="text" class="form-control" name="count" value="<? echo $count?>" placeholder="<? echo $count?>" maxlength="10" readonly>
            </div>

            <div class="mb-3">
            <input type="submit" value="정보수정모드"></p>
            <a href="camp_delete.php?idx=<?php echo $idx; ?>" class="btn btn-danger btn-sm">삭제</a>
            </div>
          </form>
        </div>
      </main>
    </div>
  </div>
</body>
<?
		mysqli_close($conn);
	  ?>
</html>