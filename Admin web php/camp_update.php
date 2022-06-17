<? 
	session_start();
    include("dbconn.php");
	if(!isset($_SESSION['username'])) {
		echo "<script>location.replace('admin_login.php');</script>";            
	}
  $idx = $_GET["idx"];

  $name = $_POST["name"];
  $lat = $_POST["lat"];
  $lon = $_POST["lon"];
  $kind1 = $_POST["kind"];
  $kind = explode(',', $kind1);
  $addr = $_POST["addr"];
  $toilet = $_POST["toilet"];
  $shower = $_POST["shower"];
  $sink= $_POST["sink"];
  $etc = $_POST["etc"];
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
        <p></p>
        <h2><img class="mb-4" src="idcard.png" alt="" width="50" height="50">  캠핑장 수정</h2>
          <form method="post" action="camp_updatePro.php?idx=<?php echo $idx; ?>">
            <div class="mb-3">
              <label for="id" class="form-label">캠핑장 이름</label>
              <input type="text" class="form-control" name="name" value='<? echo $name?>' placeholder='<? echo $name?>' maxlength="50" required>
            </div>

            <div class="input-group mb-3">
                <button class="btn btn-outline-secondary" type="button" id="button-addon1" onclick="sample5_execDaumPostcode()">주소검색</button>
                <input type="text" class="form-control" id="sample5_address" name="address" value="<? echo $addr?>" placeholder="주소" aria-label="Example text with button addon" aria-describedby="button-addon1" autocomplete='off' required readonly>
                <div id="map" style="width:100%;height:350px;"></div>
            </div>

            <div class="mb-3">
              <input type="text" name="lat" id="lat" placeholder="위도" maxlength="10" value="<? echo $lat?>" readonly>
              <input type="text" name="lon" id="lon" placeholder="경도" maxlength="10" value="<? echo $lon?>" readonly>
            </div>

            <label for="id" class="form-label">캠핑장 유형 - </label>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="normal" name="kind[]" value="일반야영장" <? if(in_array("일반야영장", $kind)){?> checked<?}?> >
                <label class="form-check-label" for="inlineCheckbox1">일반 야영장</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="karaban" name="kind[]" value="카라반" <? if(in_array("카라반", $kind)){?> checked<?}?> >
                <label class="form-check-label" for="inlineCheckbox2">카라반</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="glam" name="kind[]" value="글램핑" <? if(in_array("글램핑", $kind)){?> checked<?}?> >
                <label class="form-check-label" for="inlineCheckbox3">글램핑</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="car" name="kind[]" value="자동차야영장" <? if(in_array("자동차야영장", $kind)){?> checked<?}?> >
                <label class="form-check-label" for="inlineCheckbox3">자동차 야영장</label>
            </div>

            <div class="mb-3">
                <label for="toilet" class="form-label">화장실 </label>
                <input type="number" min="0", max="5" class="number" value="<? echo $toilet?>" name="toilet" id="toilet" maxlength="10" required>

                <label for="shower" class="form-label">샤워실 </label>
                <input type="number" min="0", max="5" class="number" value="<? echo $shower?>" name="shower" id="shower" maxlength="10" required>

                <label for="sink" class="form-label">싱크대 </label>
                <input type="number" min="0", max="5" class="number" value="<? echo $sink?>" name="sink" id="sink" maxlength="10" required>
            </div>

            <div class="mb-3">
              <label for="name" class="form-label">기타 정보</label>
              <input type="text" class="form-control" name="etc" value="<? echo $etc?>" placeholder="<? echo $etc?>" maxlength="10">
            </div>

            <div class="mb-3">
            <input type="submit" value="수정하기"></p>
            </div>
          </form>
        </div>
      </main>
    </div>
  </div>
  <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4d514c4c55ca24151d49f1db9431ee3d&libraries=services"></script>
<script>

    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center: new daum.maps.LatLng(<?=$lat?>, <?=$lon?>), // 지도의 중심좌표
            level: 2 // 지도의 확대 레벨
        };
    //지도를 미리 생성
    var map = new daum.maps.Map(mapContainer, mapOption);
    //주소-좌표 변환 객체를 생성
    var geocoder = new daum.maps.services.Geocoder();
    //마커를 미리 생성
    var marker = new daum.maps.Marker({
        position: new daum.maps.LatLng(<?=$lat?>, <?=$lon?>),
        map: map
    });

    function sample5_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                var addr = data.address; // 최종 주소 변수

                // 주소 정보를 해당 필드에 넣는다.
                document.getElementById("sample5_address").value = addr;
                // 주소로 상세 정보를 검색
                geocoder.addressSearch(data.address, function(results, status) {
                    // 정상적으로 검색이 완료됐으면
                    if (status === daum.maps.services.Status.OK) {

                        var result = results[0]; //첫번째 결과의 값을 활용

                        // 해당 주소에 대한 좌표를 받아서
                        var coords = new daum.maps.LatLng(result.y, result.x);
                        // 지도를 보여준다.
                        mapContainer.style.display = "block";
                        map.relayout();

                        //위도 경도 설정
                        document.getElementById("lat").value = result.y;
                        document.getElementById("lon").value = result.x;

                        // 지도 중심을 변경한다.
                        map.setCenter(coords);
                        // 마커를 결과값으로 받은 위치로 옮긴다.
                        marker.setPosition(coords)
                    }
                });
            }
        }).open();
    }
</script>
</body>
<?
		mysqli_close($conn);
	  ?>
</html>