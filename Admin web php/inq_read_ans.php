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
    <script type="text/javascript">
var bDisplay = true;
function doDisplay(){
	var con = document.getElementById("ans");
	if(con.style.display=='none'){
		con.style.display = 'block';
	}else{
		con.style.display = 'none';
	}
}
</script>

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
				  <a class="nav-link active" href="./qna.php">
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
		</div>
	  </div>

      <?php
          $id = $_GET["id"];

          $query = "SELECT * FROM t_inquiry WHERE inquiry_id='$id'";
          $res   = mysqli_query($conn,$query);
          $row   = mysqli_fetch_array($res);
          if($row){
            $title = $row['inquiry_title'];
            $writer = $row['user_id'];
            $content = $row['inquiry_content'];
            $day = $row['inquiry_date'];
          }
          $hit = $hit['hit'] + 1;

          $query2 = "SELECT * FROM t_answer WHERE inquiry_id='$id'";
          $res2   = mysqli_query($conn,$query2);
          $row2   = mysqli_fetch_array($res2);

          if($row2){
            $content2 = $row2['answer_content'];
            $ans_id = $row2['answer_id'];
          }
        ?>

      <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
			<p></p>
			<h2><img class="mb-4" src="id.png" alt="" width="50" height="50"> 질문</h2>
                <div class="mb-3">
                <label for="id" class="form-label">제목</label>
                <input type="text" class="form-control" name="title" id="title"  value='<? echo $title?>' maxlength="50" readonly>
                </div>

                <div class="mb-3">
                <label for="name" class="form-label">작성자</label>
                <input type="text" class="form-control" name="writer" id="writer"  value='<? echo $writer?>' maxlength="300"  readonly>
                </div>

                <div class="mb-3">
                <label for="name" class="form-label" >내용</label><br>
                <textarea name="content" id="content" cols="150" rows="10" readonly> <? echo $content?> </textarea>
                </div>
                <br>
            <form method="post" action="ans_update.php?inq_id=<?php echo $id;?>&ans_id=<?php echo $ans_id;?>">
            <label for="name" class="form-label">답변내용</label>
                    <div class="input-group mb-3">
                        
                        <textarea name="content" id="content" cols="150" rows="12" aria-describedby="button-addon2" required><? echo $content2 ?></textarea>
                        <input type="submit" class="btn btn-outline-secondary" id="button-addon2" value="수정하기">
                    </div>
            </form>
		</main>
</body>
<?
		mysqli_close($conn);
	  ?>
</html>