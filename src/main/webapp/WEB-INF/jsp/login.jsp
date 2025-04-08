<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.4/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-DQvkBjpPgn7RC31MCQoOeC9TI2kdqa4+BSgNMNj8v77fdC77Kj5zpWFTJaaAoMbC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.4/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YUe2LzesAfftltw+PEaao2tjU/QATaW/rOitAq67e0CT0Zi2VVRL0oC4+gAaeBKu"
            crossorigin="anonymous"></script>
    <title>로그인</title>
    <style>
        input::placeholder {
            font-size: 0.85rem;
            color: #aaa;
            position: relative;
            top: -4px;
        }
    </style>
</head>
<body>

<div class="container" style="max-width: 600px;">

    <div class="mb-5 mt-5 d-flex justify-content-center">
        <h1 class="display-4">로그인</h1>
    </div>

    <form id="loginForm" novalidate>
        <div class="mb-3">
            <label for="userEmail" class="form-label">이메일</label>
            <input type="email" class="form-control form-control-lg rounded-1"
                   id="userEmail" name="email" placeholder="abc@email.com">
        </div>
        <div class="mb-3">
            <label for="userPassword" class="form-label">비밀번호</label>
            <input type="password" class="form-control form-control-lg rounded-1"
                   id="userPassword" name="password" placeholder="6자 이상의 비밀번호">
        </div>
    </form>

    <div>
        <button type="button" id="btnLogin"
                style="height: 60px;
                       color: black;
                       background-color: transparent;
                       border: 1px solid black;"
                class="btn mt-3 form-control-lg rounded-pill w-100">
            로그인
        </button>
    </div>

    <script>
        window.onload = function () {
            // btnLogin 처리
            document.querySelector("#btnLogin").onclick = function () {
                // validation check
                if (document.querySelector("#userEmail").value == '' ||
                    document.querySelector("#userPassword").value == '') {
                    alert("안돼 돌아가.");
                } else {
                    login();
                }
            }
        }


        async function login() {
            let userEmail = document.querySelector("#userEmail").value;
            let userPassword = document.querySelector("#userPassword").value;

            // x-www-form-urlencoded
            let urlParams = new URLSearchParams({
                // 변수명과 프로퍼티가 같으면 변수명만 써도 됨
                email: userEmail,
                password: userPassword
            });

            // fetch Options
            let fetchOptions = {
                method: "POST",
                body: urlParams
            }

            let response = await fetch("/auth/login", fetchOptions);
            let data = await response.json();

            if (data.result == "success") {
                // 게시판 페이지 이동
                window.location.href = "/pages/board"
            } else {
                alert("이메일 또는 비밀번호가 올바르지 않습니다.");
            }
        }

    </script>
</div>
</body>
</html>