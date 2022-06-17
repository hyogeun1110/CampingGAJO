<? 
	session_start();
	if(!isset($_SESSION['username'])) {
		echo "<script>location.replace('admin_login.php');</script>";            
	}
	else {
		$username = $_SESSION['username'];
		$name = $_SESSION['name'];
	} 
?>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>공지사항</title>
	<link rel="stylesheet" href="./assets//bootstrap-5.1.1/css/bootstrap.min.css">
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

</head>
<body>
	<header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
	<a class="navbar-brand col-md-3 col-lg-2 me-0 px-3" 
			href="./main.php">CampingGAJO</a>
		<button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-bs-toggle="collapse" 
			data-bs-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
		  <span class="navbar-toggler-icon"></span>
		</button>
		<div class="navbar-nav">
		  <div class="nav-item text-nowrap">
			<a class="btn btn-primary btn-sm" href="./notice_write.php">글쓰기</a>
		  </div>
		</div>
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
				<a class="nav-link" href="./Camping_select.php">
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
				  <a class="nav-link active" href="./notice_list.php">
					<span data-feather="file-text"></span>
					공지사항
				  </a>
				</li>
			  </ul>
			</div>
		  </nav>
	  
		  <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
			<p></p>
			<h2><img class="mb-4" src="id.png" alt="" width="50" height="50">  공지사항</h2>
			<div class="table-responsive">
			  <table class="table table-striped table-sm">
				<thead>
				  <tr>
					<th width="150" scope="col">번호</th>
					<th width="900" scope="col">제목</th>
					<th width="150" scope="col">글쓴이</th>
					<th width="150" scope="col">작성일</th>
                    <th scope="col">조회수</th>
				  </tr>
				</thead>
				<tbody>
                <?
                    include("dbconn.php");
                    if (mysqli_connect_errno()){
                        echo "MySQL 연결 오류: " . mysqli_connect_error();
                        exit;
                    } else {
                    }

                    // 문자셋 설정, utf8.
                    mysqli_set_charset($conn,"utf8");

                    $sql = "SELECT *  FROM t_notice ORDER BY idx desc limit 0,10" ;

                    $result = mysqli_query($conn, $sql); //db.php내에 선언된 접속정보 ($conn변수)를
                                                // 이용해 데이터를 불러온다

                    $num = mysqli_num_rows($result);     //결과값의 총'열'수를 반환한다. 
                    while($row = mysqli_fetch_assoc($result)){

                ?>
                    <tr>
					<td><?= $row['idx'] ?> </td>
					<td><a href="notice_read.php?idx=<?php echo $row["idx"]; ?>"><?= $row['title']?> </td>
					<td><?= $row['writer']?> </td>
					<td><?= $row['date']?> </td>
                    <td><?= $row['hit']?> </td>
					
				</tr>
                    <? } ?>
				  
				</tbody>
			  </table>
			</div>
		  </main>
		</div>
	  </div>
	  <?
		mysqli_close($conn);
	  ?>
</body>
</html>
