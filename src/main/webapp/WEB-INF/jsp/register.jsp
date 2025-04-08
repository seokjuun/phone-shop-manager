<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <title>회원가입</title>
</head>
<style>
    /* 모든 input의 placeholder 크기 줄이기 */
    input::placeholder {
        font-size: 0.85rem;
        color: #aaa;
        position: relative;
        top: -4px;  /* 위로 올리기 */
    }
</style>
<body>

<div class="container" style="max-width: 600px;">
    <div class="mb-5 mt-5 d-flex justify-content-center">
        <h1 class="display-4">회원가입</h1>
    </div>
<%--    <div class="mb-3">--%>
<%--        <h2>회원 가입</h2>--%>
<%--    </div>--%>
    <form novalidate>
        <div class="mb-3">
            <label for="userName" class="form-label">이름 </label>
            <input type="text" class="form-control form-control-lg rounded-1" id="userName" name="userName" placeholder="김길동">
<%--            <div class="valid-feedback">Valid</div>--%>
            <div class="invalid-feedback">이름은 2글자 이상!</div>
        </div>
        <div class="mb-3">
            <label for="userPassword" class="form-label">비밀번호 </label>
            <input type="password" class="form-control form-control-lg rounded-1" id="userPassword" name="userPassword" placeholder="8자 이상의 비밀번호">
<%--            <div class="valid-feedback">Valid</div>--%>
            <div class="invalid-feedback">비밀번호는 1개 이상 특수문자, 알파벳, 숫자 포함!</div>
        </div>
        <div class="mb-3">
            <label for="userPassword2" class="form-label">비밀번호 확인 </label>
            <input type="password" class="form-control form-control-lg rounded-1" id="userPassword2" name="userPassword2" placeholder="8자 이상의 비밀번호">
<%--            <div class="valid-feedback">Valid</div>--%>
            <div class="invalid-feedback">비밀번호가 일치하지 않음!</div>
        </div>
        <div class="mb-3">
            <label for="userEmail" class="form-label">이메일 </label>
            <input type="email" class="form-control form-control-lg rounded-1" id="userEmail" name="userEmail" placeholder="abc@email.com">
<%--            <div class="valid-feedback">Valid</div>--%>
            <div class="invalid-feedback">올바르지 않은 이메일 형식!</div>
        </div>
    </form>
    <div>
        <button type="button" id="btnRegister"
                style="height: 60px;
                   color: black;
                   background-color: transparent;
                   border: 1px solid black;"
                class="btn mt-3 form-control-lg rounded-pill w-100">
            가입하기
        </button>
    </div>
</div>

<script>
    window.onload = function (){
        //btnRegister 처리
        document.querySelector("#btnRegister").onclick = function (){
            // validation check
            if(document.querySelectorAll("form .is-invalid").length > 0){
                alert("입력이 올바르지 않습니다.");
            } else {
                register();
            }

        }
        // focus 를 잃을 때  <= 입력 완료 후 다른 입력으로 넘어 갈 때
        document.querySelector("#userName").onblur = function (){
            if (validateUserName(this.value)){
                this.classList.remove("is-invalid");
                this.classList.add("is-valid");
            } else {
                this.classList.remove("is-valid");
                this.classList.add("is-invalid");
            }
        }
        document.querySelector("#userPassword").onblur = function (){
            if (validateUserPassword(this.value)){
                this.classList.remove("is-invalid");
                this.classList.add("is-valid");
            } else {
                this.classList.remove("is-valid");
                this.classList.add("is-invalid");
            }
        }
        document.querySelector("#userPassword2").onblur = function (){
            if (validateUserPassword2(this.value)){
                this.classList.remove("is-invalid");
                this.classList.add("is-valid");
            } else {
                this.classList.remove("is-valid");
                this.classList.add("is-invalid");
            }
        }
        document.querySelector("#userEmail").onblur = function (){
            if (validateUserEmail(this.value)){
                this.classList.remove("is-invalid");
                this.classList.add("is-valid");
            } else {
                this.classList.remove("is-valid");
                this.classList.add("is-invalid");
            }
        }
    }

    function validateUserName(userName){
        // 2글자 이상
        if(userName.length >= 2) return true;
        return false;
    }

    function validateUserPassword(userPassword){
        let patternEngAtListOne = new RegExp(/[a-zA-Z]+/);// 알파벳 적어도 1개 이상
        let patternSpeAtListOne = new RegExp(/[~!@#$%^&*()_+|<>?:{}]+/);// 특수문자 적어도 1개 이상
        let patternNumAtListOne = new RegExp(/[0-9]+/);// 숫자 적어도 1개 이상

        if (patternEngAtListOne.test(userPassword) &&
            patternSpeAtListOne.test(userPassword) &&
            patternNumAtListOne.test(userPassword)
        ) return true;
        return false;
    }

    function validateUserPassword2(userPassword2){
        // userPass 와 일치
        if(userPassword2 == document.querySelector("#userPassword").value) return true;
        return false;
    }

    function validateUserEmail(userEmail){
        // @ .
        let regexp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
        if(regexp.test(userEmail)) return true;
        return false;
    }


    // 회원 가입 처리
    async function register(){
        let name = document.querySelector("#userName").value;
        let password = document.querySelector("#userPassword").value;
        let email = document.querySelector("#userEmail").value;

        //x-www-form-urlencoded
        let urlParams = new URLSearchParams({
            name : name,
            password : password,
            email : email,
        });

        //fetch Options
        let fetchOptions = {
            method: "POST",
            body: urlParams
        }

        let response = await fetch("/user/register", fetchOptions);
        console.log(response);
        let data = await response.json();

        if(data.result == "success"){
            alert("가입 성공")
            // 로그인 페이지 이동
            window.location.href="/pages/login"
        } else {
            alert("가입 실패")
        }
    }

</script>
</body>
</html>