package colorshare

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(LoginController)
class LoginControllerSpec extends Specification {

    def "A session user is defined when a user signs in"() {
        given: "A created user"

        when: "The user attempts to log in"

        then: "The session user is set as the correct user"
    }

}
