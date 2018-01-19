package colorshare

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.mixin.Mock

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(LoginController)
@Mock([User])
class LoginControllerSpec extends Specification {

    def "A session user is defined when a user signs in"() {
        given: "A created user"
        def jess = new User(
                userName: "jess",
                password: "password123").save(failOnError: true)

        when: "The user attempts to log in"
        def model = controller.logIn(jess.userName, jess.password)

        then: "The session user is set as the correct user"
        session.user == jess
    }

    def "A session user is not defined when a user signs in with incorrect information"() {
        given: "A created user"
        def jess = new User(
                userName: "jess",
                password: "password123").save(failOnError: true)

        when: "The user attempts to log in"
        def model = controller.logIn(jess.userName, "incorrectPassword")

        then: "The session user is set as the correct user"
        flash.error == "Unknown username or password"
        !session.user
    }

    def "A session user is cleared when logging out"() {
        given: "A created user"
        def jess = new User(
                userName: "jess",
                password: "password123").save(failOnError: true)

        and: "The user is logged in"
        session.user == jess

        when: "The user logs out"
        controller.logOut()

        then: "The session user is set as the correct user"
        !session.user
    }

}
