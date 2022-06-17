<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>로그인</title>
<link rel="stylesheet" href="../assets/bootstrap-5.1.1/css/bootstrap.min.css">
<style>
    *{
        margin: 0px;
        padding: 0px;
    }
    .container{
        width: 1000px;
        margin: 0 auto;
    }
    .container div{
         text-align: center;
         display: table;
    }
    .container div span{
        display: table-cell;
        vertical-align: middle;
    }
    .idpass{
        text-align: center;
        position: relative;
        top: 30px;
        left: 30px;    
        opacity: 0.9;  
    }
    .Lbtn{
        width: 70px;
        height: 70px;
        text-align: center;
        position: relative;     
        left: 395px;      
        top: 3px; 
        opacity: 0.9;
    }
    .etc{
        position: relative;
        left: 100px;
        top: 23px;
    }
    .boxId{
        width: 250px;
        height: 42px;
        position: relative;
        left: 88px;
        top : 50px
    }
    .boxPs{
        width: 250px;
        height: 42px;
        position: relative;
        top: 57px;
        left: 88px;
    }
    div{
        text-align: center;
    }
    .top{
        margin-top: 20px;
        outline: 1px solid #9F9F9F;
        width: 1000px;
        height: 100px;
        display: table;
        background-color: #eed8cb;
        background-image:url('2.png');
        background-size: 80px 80px;
        background-repeat: no-repeat;
        background-position: 10px;
    }
    .topSP{
        font-weight: 900;
        font-size: 40px;
    }
    .middle{
        margin-top: 20px;
        width: 1000px;
        height: 500px;
        position: relative;       
    }
    .middle-left{
        outline: 1px solid #9F9F9F;
        position: absolute;
        top: 0px;
        width: 170px;
        height: 500px;
    }
    .middle-right{
        position: absolute;
        top: 0px;
        left: 190px;
        width: 810px;
        height: 500px;
        background-image:url('1.jpg');
        background-size: 810px 500px;
    }
    .bottom{
        margin-top: 20px;
        margin-bottom: 20px;
        outline: 1px solid #9F9F9F;
        width: 1000px;
        height: 100px;
        background-color: #5D5D5D;
        color: #fff;
    }
    .btn{
        width: 170px;
        height: 50px;
        color: #9F9F9F;
        border-radius: 0%;
        border: 1px;
        outline: 1px;
        outline-color: black;
    }
    
</style>
</head>
<body>
<div class="container">
<div class="top">
    <span class="topSP">교양(선택) 과목 수강신청 시스템
    </span>
</div>
<div class="middle">
    <div class="middle-left">
        <button type="button" class="btn btn-secondary">전공 (필수)</button>
        <button type="button" class="btn btn-secondary">전공 (선택)</button>
        <button type="button" class="btn btn-secondary">교양 (필수)</button>
        <button type="button" class="btn btn-secondary" autofocus>교양 (선택)</button>
    </div>
    <div class="middle-right">
        <form method="post" action="login2.jsp">
            <div class="idpass">
            <div>
                <input type="text" name="id" id="id" class="boxId" placeholder="아이디 입력" required>
            </div>
            <div>
                <input type="password"  name="passwd" id="passwd" class="boxPs" placeholder="비밀번호 입력" required>
            </div>
            </div>
            <div>
                <input type="submit" class="Lbtn btn-outline-success" value="로그인">
            </div>
        </form>
        <div class="etc">
        <a href="insertMemForm.jsp" class="btn btn-link" style="color: black;font-weight: 700;width: 100px;">회원가입</a>
        <a href="selectIdForm.jsp" class="btn btn-link" style="color: black;font-weight: 700;width: 180px;">아이디,비번 찾기</a>
        </div> 
        </div>
    </div>
<div class="bottom">
    <p align="left"></br>&nbsp ooooo TEL : 123-456-7890 </br>
        &nbsp www.ooo.com
    </p>
</div>
</div>
</body>
</html>