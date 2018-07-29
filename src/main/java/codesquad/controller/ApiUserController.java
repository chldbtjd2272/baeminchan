package codesquad.controller;

import codesquad.domain.user.User;
import codesquad.dto.user.JoinUserDto;
import codesquad.dto.user.LoginUserDto;
import codesquad.service.UserService;
import codesquad.util.SessionUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class ApiUserController {

    @Resource(name = "userService")
    UserService userService;

    @ApiOperation(value = "회원가입", notes = "회원정보를 입력하여 회원가입 시도 ")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "삽입 성공"),
            @ApiResponse(code = 400, message = "잘못된 요청")
    })
    @PostMapping("")
    public ResponseEntity<User> create(@Valid @RequestBody JoinUserDto joinUserDto, HttpSession session) {
        session.setAttribute(SessionUtil.SESSTION_KEY, userService.add(joinUserDto));
        return ResponseEntity.created(URI.create("/api/users")).body(null);
    }


    @ApiOperation(value = "로그인", notes = "아이디, 비밀번호를 입력하여 로그인하였습니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "삽입 성공"),
            @ApiResponse(code = 400, message = "잘못된 요청")
    })
    @PostMapping("/login")
    public ResponseEntity<User> login(HttpSession session, @RequestBody LoginUserDto loginUserDto) {
        session.setAttribute(SessionUtil.SESSTION_KEY, userService.login(loginUserDto));
        return ResponseEntity.ok(null);
    }

    @GetMapping("/kakao_auth")
    public ResponseEntity<String> kakaoLogin(HttpSession httpSession,String code){
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> responseEntity;
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String,String>();
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+code);
        map.add("grant_type", "authorization_code");
        map.add("client_id","0f434146ffdb8d3f8c73d543ff4425d3");
        map.add("redirect_uri", "http://localhost:8080/api/users/kakao_auth");
        map.add("code", code);
        responseEntity = template.postForEntity("https://kauth.kakao.com/oauth/token",map,String.class);
        return responseEntity;
    }
}
