package aryumka.app1

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService
) {
    @GetMapping
    fun findAll(): List<User> =
        this.userService.findAll()

    @PostMapping
    fun save(@RequestBody body: CreateUser): User =
        this.userService.save(User(name = body.name))
}

data class CreateUser(val name: String)