package com.security.auth

import com.security.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

// 시큐리티 설정에서 loginProcessingUrl("/login");
// login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC 되어있는 loadUserByUsername 함수가 실행
@Service
class PrincipalDetailsService(
    val userRepository: UserRepository
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails? {
        val user = userRepository.findByUserName(username!!)
        return PrincipalDetails(user)
    }
}