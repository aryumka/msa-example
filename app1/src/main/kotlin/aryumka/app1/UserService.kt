package aryumka.app1

import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun save(user: User): User {
        return userRepository.save(user)
    }

    fun findAll(): List<User> {
        return userRepository.findAll()
    }

    fun findById(id: String): User {
        return userRepository.findById(id).orElseThrow()
    }

    fun deleteById(id: String) {
        userRepository.deleteById(id)
    }

    fun deleteAll() {
        userRepository.deleteAll()
    }

}