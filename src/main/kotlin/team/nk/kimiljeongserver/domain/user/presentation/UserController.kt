package team.nk.kimiljeongserver.domain.user.presentation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import team.nk.kimiljeongserver.domain.auth.presentation.dto.response.TokenResponse
import team.nk.kimiljeongserver.domain.user.presentation.dto.request.*
import team.nk.kimiljeongserver.domain.user.presentation.dto.response.QueryBooleanResponse
import team.nk.kimiljeongserver.domain.user.presentation.dto.response.QueryMyInfoResponse
import team.nk.kimiljeongserver.domain.user.service.*
import javax.validation.Valid

@Tag(name = "유저", description = "유저 관련 API입니다.")
@RequestMapping("/user")
@RestController
class UserController(
    private val signupUserService: SignupUserService,
    private val loginUserService: LoginUserService,
    private val modifyBirthdayService: ModifyBirthdayService,
    private val modifyPasswordService: ModifyPasswordService,
    private val queryMyInfoService: QueryMyInfoService,
    private val userExistsService: AlreadyUserExistsService,
    private val userCheckCodeService: UserCheckCodeService,
    private val modifyUserInfoService: ModifyUserInfoService
) {

    @Operation(summary = "회원가입")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun signup(@Valid @RequestBody request: SaveUserRequest) {
        signupUserService.execute(request)
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    fun login(@Valid @RequestBody request: LoginUserRequest): TokenResponse {
        return loginUserService.execute(request)
    }

    @Operation(summary = "생일 수정하기")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/birthday")
    fun modifyBirthday(@Valid @RequestBody request: ModifyBirthdayRequest) {
        modifyBirthdayService.execute(request);
    }

    @Operation(summary = "비밀번호 변경")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/password")
    fun modifyPassword(@Valid @RequestBody request: ModifyPasswordRequest) {
        modifyPasswordService.execute(request);
    }

    @Operation(summary = "자신의 정보 보기")
    @GetMapping
    fun queryMyInfo(): QueryMyInfoResponse {
        return queryMyInfoService.execute();
    }

    @Operation(summary = "아이디 중복 확인")
    @GetMapping("/check")
    fun checkAccountId(@RequestParam("account-id") accountId: String): QueryBooleanResponse {
        return userExistsService.execute(accountId);
    }

    @Operation(summary = "코드 확인")
    @GetMapping("/code")
    fun checkCode(@RequestParam("email") email: String, @RequestParam("code") code: String): QueryBooleanResponse {
        return userCheckCodeService.execute(email, code);
    }

    @Operation(summary = "자신의 정보 수정")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping
    fun modifyUserInfo(@Valid @RequestBody request: ModifyUserInfoRequest) {
        modifyUserInfoService.execute(request);
    }
}
