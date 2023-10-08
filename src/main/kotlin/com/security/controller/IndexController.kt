package com.security.controller

import com.security.entity.User
import com.security.enums.UserRoleEnum
import com.security.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import kotlin.math.log

@Controller
class IndexController(
    val userRepository: UserRepository,
    val passwordEncoder: BCryptPasswordEncoder
) {

    @GetMapping("/")
    fun home(): String {
        return "index"
    }

    @GetMapping("/user")
    @ResponseBody
    fun user(): String {
        return "user"
    }

    @GetMapping("/admin")
    @ResponseBody
    fun admin(): String {
        return "admin"
    }

    @GetMapping("/manager")
    @ResponseBody
    fun manager(): String {
        return "manager"
    }
    @GetMapping("/loginForm")
    fun loginForm(): String {
        return "loginForm"
    }

    @GetMapping("/joinForm")
    fun joinForm(): String {
        return "joinForm"
    }

    @PostMapping("/join")
    fun join(request: User): String {
        val encodePassword = passwordEncoder.encode(request.password)
        val user = User(request.userName, encodePassword, request.email)
        user.role = UserRoleEnum.ROLE_USER
        userRepository.save(user)
        return "redirect:/loginForm"
    }

}