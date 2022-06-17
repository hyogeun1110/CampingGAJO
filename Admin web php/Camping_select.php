<? 
	session_start();
	if(!isset($_SESSION['username'])) {
		echo "<script>location.replace('admin_login.php');</script>";            
	}
	if($_SESSION['level'] > 2 ){
		echo "<script>alert('보안등급이 맞지 않습니다.')</script>";
		echo "<script>location.replace('main.php');</script>";    
	}
?>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>캠핑장 정보</title>
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
	  .hidden_td{
		  display: none;
	  }
    </style>
  </head>

</head>
<body>
	<header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
		<a class="navbar-brand col-md-2 col-lg-2 me-0 px-2" 
			href="./main.php"> CampingGAJO</a>
		<button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-bs-toggle="collapse" 
			data-bs-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
		  <span class="navbar-toggler-icon"></span>
		</button>
		<div class="navbar-nav">
		  <div class="nav-item text-nowrap">
			<a class="btn btn-primary btn-sm" href="insertCampForm.php">캠핑장 추가</a>
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
	<p></p>
	
		<h2><img class="mb-4" src="id.png" alt="" width="50" height="50">  캠핑장 정보</h2>
		<form method="post" action="camp_search.php">	
			<div class="input-group mb-3">
				<select class="btn btn-outline-secondary dropdown-toggle" name="s_kind">
				<option value="all" selected>전체</option>
					<option value="name">이름</option>
					<option value="address">주소</option>
					<option value="etc">기타 정보</option>
				</select>
					<input type="text" class="form-control"  name="s_text" autocomplete='off' placeholder="검색어를 입력하세요">       
					<input class="btn btn-outline-secondary" type="submit" value="검색">
			</div>
		</form>
		<div class="table-responsive">
			<table class="table table-striped table-sm">
			    <thead>
				  <tr>
					<th scope="col">캠핑장</th>
					<th scope="col">캠팡장 종류</th>
					<th scope="col">주소</th>
					<th scope="col" style="display:none;">위도</th>
					<th scope="col" style="display:none;">경도</th>
                    <th scope="col" style="display:none;">화장실</th>
					<th scope="col" style="display:none;">샤워실</th>
					<th scope="col" style="display:none;">싱크대</th>
					<th scope="col" style="display:none;" >기타 정보</th>
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

                    $sql = "SELECT *  FROM camp_all";

                    $result = mysqli_query($conn, $sql); //db.php내에 선언된 접속정보 ($conn변수)를
                                                // 이용해 데이터를 불러온다

                    $num = mysqli_num_rows($result);     //결과값의 총'열'수를 반환한다. 
                    while($row = mysqli_fetch_assoc($result)){

                ?>
				<tr>
					<td><a href="camp_read.php?idx=<?php echo $row["num"]; ?>"><?= $row['name'] ?> </td>
					<td><?= $row['camping_kind']?> </td>
					<td><?= $row['address']?></td>
					<td style="display:none;"><?= $row['lat']?> </td>
					<td style="display:none;"><?= $row['lon']?> </td>
                    <td style="display:none;"><?= $row['toilet']?></td>
					<td style="display:none;"><?= $row['shower']?></td>
					<td style="display:none;"><?= $row['sink']?></td>
					<td style="display:none;"><?= $row['etc']?></td>
				</tr>
                    <? }
					
					mysqli_close($conn); ?>
				  
				</tbody>
			  </table>
		</div>
	</main>
</body>
</html>