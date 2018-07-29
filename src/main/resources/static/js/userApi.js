function $(selector) {
    return document.querySelector(selector);
}

document.addEventListener("DOMContentLoaded", () => {
    initEvents(".join_form_box .btn_area .btn",registerUserHandler);
    initEvents("#login_btn",loginHandler);
    initEvents("#kakao_rest",kakaoLoginHandler);
})

function initEvents(btnSelector,handler) {
    const initBtn = $(btnSelector);
    if(initBtn === null) return;
    initBtn.addEventListener("click", handler);
}

function fetchManager({ url, method, body, headers,callback}) {
    fetch(url, {method,body,headers,credentials: "same-origin"})
        .then((response) => {
        console.log(response);
        callback(response);
    })
}

function registerUserHandler(evt) {
    evt.preventDefault();
    const email = $("#email_id").value+"@"+$("#email_domain").value;
    const password = $("#pw1").value;
    const passwordConfirm = $("#pw2").value;
    const name = $("#name").value;
    const phoneNo = $("#cell1").value+$("#cell2").value+$("#cell3").value;
    
    $("#pw1").value = "";
    $("#pw2").value = "";
    
    fetchManager({
        url: '/api/users',
        method: 'POST',
        headers: { 'content-type': 'application/json'},
        body: JSON.stringify({email,password,passwordConfirm,name,phoneNo}),
        callback: joinCallback
    })
}

function loginHandler(evt) {
    evt.preventDefault();
    const email = $("#member_id").value;
    const password = $("#pwd").value;
    
    $("#pwd").value = "";

    fetchManager({
        url: '/api/users/login',
        method: 'POST',
        headers: { 'content-type': 'application/json'},
        body: JSON.stringify({email,password}),
        callback: loginCallback
    })
}



function kakaoLoginHandler(evt){
    evt.preventDefault();
    window.location.href='https://kauth.kakao.com/oauth/authorize?client_id={client_id}&redirect_uri=http://localhost:8080/api/users/kakao_auth    &response_type=code'
  
}


function joinCallback(response){
    clearJoinError();
    if(response.status === 201){
        window.location.replace("http://localhost:8080/")
        return
    }
    response.json().then((result) => {
        if(result.message === "valid_error"){
            result.data.validationErrorList.forEach(error => {
                $("#"+error.fieldName+"_valid").style.display = "block";
            });
            return;
        }
        alert("이미 존재하는 이메일입니다.");
        $("#duplicate_email").style.display = "block";
    })
} 

function clearJoinError(){
    document.querySelectorAll(".tb_join span.tb_in_txt.caution").forEach(element =>{
        element.style.display = "none";
    })
    $("#duplicate_email").style.display = "none";
}

function loginCallback(response){
    if(response.status === 200){
        window.location.href = "http://localhost:8080/"
        return
    }
    alert("로그인에 실패하였습니다.");
}


function goNext(evt){

}