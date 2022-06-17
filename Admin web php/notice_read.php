<? 
	session_start();
    include("dbconn.php");
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
	<title>공지사항 글쓰기</title>
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

</head>
<body>
	<header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
		<a class="navbar-brand col-md-3 col-lg-2 me-0 px-3" 
			href="./main.php">CampingGAJO</a>
		<button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-bs-toggle="collapse" 
			data-bs-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
		  <span class="navbar-toggler-icon"></span>
		</button>
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
		</div>
	  </div>

      <?php
          $idx = $_GET["idx"];

          $query = "SELECT title, writer, content FROM t_notice WHERE idx='$idx'";
          $res   = mysqli_query($conn,$query);
          $row   = mysqli_fetch_array($res);
          if($row){
            $title = $row['title'];
            $writer = $row['writer'];
            $content = $row['content'];
          }
          $hit = $hit['hit'] + 1;
        ?>

      <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
			<p></p>
			<h2><img class="mb-4" src="id.png" alt="" width="50" height="50"> 공지사항</h2>
			<form method="post" action="notice_update.php">
                <div class="mb-3">
                <label for="id" class="form-label">ID</label>
                <input type="text" class="form-control" name="idx" id="idx"  value='<? echo $idx?>' maxlength="50" readonly>
                </div>

                <div class="mb-3">
                <label for="id" class="form-label">제목</label>
                <input type="text" class="form-control" name="title" id="title"  value='<? echo $title?>' maxlength="50">
                </div>

                <div class="mb-3">
                <label for="name" class="form-label">작성자</label>
                <input type="text" class="form-control" name="writer" id="writer"  value='<? echo $writer?>' maxlength="300"  readonly>
                </div>

                <div class="mb-3">
                <label for="name" class="form-label">내용</label>
                </div>

                <div class="mb-3">
                    <textarea name="content" id="content" cols="150" rows="20" id="content" required> <? echo $content?> </textarea>
                </div>
                <input type="submit" class="btn btn-warning btn-sm" value="글 수정">
                <a href="notice_delete.php?idx=<?php echo $idx; ?>" class="btn btn-danger btn-sm">삭제</a>
            </form>
                
		</main>
</body>
<?
		mysqli_close($conn);
	  ?>
</html>