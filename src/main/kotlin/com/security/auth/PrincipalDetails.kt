package com.security.auth

import com.security.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class PrincipalDetails(
    user: User
): UserDetails {

    private var user: User = user

    // 해당 유저의 권한을 리턴하는 곳
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val collect = mutableListOf<GrantedAuthority>()
        collect.add(GrantedAuthority { user.role?.name })
        return collect
    }

    override fun getPassword(): String {
        return user.password!!
    }

    override fun getUsername(): String {
        return user.userName!!
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        // ex) 1년동안 회원이 로그인을 안하면 휴면계정을 하기로 할 때 사용
        return true
    }
}