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
	<title>Main</title>
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
	  .user-box {
		  	position: absolute;
			width: 350px ;
			height : 150px;
			border:2px solid; 
			padding:10px;
			margin : 10 10 10 10;
			text-align: center;
		}
		.camp-box {
			position: relative;
			left : 380px;
			width: 350px ;
			height : 150px;
			border:2px solid; 
			padding:10px;
			margin : 10 10 10 10;
			text-align: center;
		}
		.ans-box {
			position: relative;
			top : -150px;
			left : 757px;
			width: 250px ;
			height : 150px;
			border:2px solid; 
			padding:10px;
			text-align: center;
		}
		.rank-box {		
			position: relative;
			top : -120px;
			width: 1007px ;
			height : 420px;
			border:2px solid; 
			padding:10px;
			margin : 10 10 10 10;
			text-align: center;
		}
</style>
<?
include("dbconn.php");
if (mysqli_connect_errno()){
	echo "MySQL 연결 오류: " . mysqli_connect_error();
	exit;
} else {
}

// 문자셋 설정, utf8.
mysqli_set_charset($conn,"utf8");

$sql_rank = "SELECT name,count FROM camp_all order by count desc";

$result_rank = mysqli_query($conn, $sql_rank); //db.php내에 선언된 접속정보 ($conn변수)를
												// 이용해 데이터를 불러온다;
$data_n = array();
$data_c = array();
for($i=0; $i<10; $i++){ 
	$row_rank = mysqli_fetch_array($result_rank);

	array_push($data_n,$row_rank['name']);
	array_push($data_c,$row_rank['count']);
	
}


$dataPoints = array(
	array("label"=> "$data_n[0]", "y"=> $data_c[0]),
	array("label"=> "$data_n[1]", "y"=> $data_c[1]),
	array("label"=> "$data_n[2]", "y"=> $data_c[2]),
	array("label"=> "$data_n[3]", "y"=> $data_c[3]),
	array("label"=> "$data_n[4]", "y"=> $data_c[4]),
	array("label"=> "$data_n[5]", "y"=> $data_c[5]),
	array("label"=> "$data_n[6]", "y"=> $data_c[6]),
	array("label"=> "$data_n[7]", "y"=> $data_c[7]),
	array("label"=> "$data_n[8]", "y"=> $data_c[8]),
	array("label"=> "$data_n[9]", "y"=> $data_c[9])
);
?>
<script>
	window.onload = function () {
		var chart = new CanvasJS.Chart("chartContainer", {
			animationEnabled: true,
			theme: "light2", // "light1", "light2", "dark1", "dark2"
			title: {
				text: "캠핑장 Ranking Top10"
			},
			axisY: {
				title: "찜수"
			},
			data: [{
				type: "column",
				dataPoints: <?php echo json_encode($dataPoints, JSON_NUMERIC_CHECK+JSON_UNESCAPED_UNICODE); ?>
			}]
		});
		chart.render();
	}
</script>
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
			<a class="btn btn-primary btn-sm" href="logout.php">로그아웃</a>
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
				<a class="nav-link" href="./notice_list.php">
					<span data-feather="file-text"></span>
					공지사항
				  </a>
				</li>
			  </ul>
			</div>
		  </nav>
	  
		  <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
			<p></p>
			<h2><img class="mb-4" src="id.png" alt="" width="50" height="50"> CampingGAJO 관리자 웹사이트 (로그인 : <?echo $_SESSION['name'] ?>)</h2>
			<p></p>
			<?
			$sql_user = "SELECT count(*)  FROM t_user";

			$result_user = mysqli_query($conn, $sql_user); //db.php내에 선언된 접속정보 ($conn변수)를
										// 이용해 데이터를 불러온다
			$row_user = mysqli_fetch_assoc($result_user)
			?>
			<div class="user-box">
			<h3>캠핑가조 유저수</h3>
			<hr/>
			<h1><? echo $row_user['count(*)']."명"?></h1>
			</div>

			<?
			$sql_camp = "SELECT count(*)  FROM camp_all";

			$result_camp = mysqli_query($conn, $sql_camp); //db.php내에 선언된 접속정보 ($conn변수)를
										// 이용해 데이터를 불러온다
			$row_camp = mysqli_fetch_assoc($result_camp)
			?>
			<div class="camp-box">
			<h3>등록된 캠핑장수</h3>
			<hr/>
			<h1><? echo $row_camp['count(*)']."개"?></h1>
			</div>

			<?
			$sql_ans = "SELECT count(*)  FROM t_inquiry WHERE ans=0";

			$result_ans = mysqli_query($conn, $sql_ans); //db.php내에 선언된 접속정보 ($conn변수)를
										// 이용해 데이터를 불러온다
			$row_ans = mysqli_fetch_assoc($result_ans)
			?>
			<div class="ans-box">
			<h3>답변안한 문의수</h3>
			<hr/>
			<h1><? echo $row_ans['count(*)']."개"?></h1>
			</div>

			<div class="rank-box">
				<div id="chartContainer" style="height: 370px; width: 100%;"></div> 
			</div>
			
		  </main>
		</div>
	  </div>
	  <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
	  <?
	 	mysqli_close($conn); 
	  ?>
</body>
</html>