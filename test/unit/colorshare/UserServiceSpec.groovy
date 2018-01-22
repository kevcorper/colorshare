package colorshare

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.mixin.Mock

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(UserService)
@Mock([User])
class UserServiceSpec extends Specification {
    def "registering a user creates them within the database"() {

        given: "a new user with accurate information"
        def userName = "jess"
        def password = "password123"

        when: "the user is registered"
        service.createUser(userName,password)

        then: "the user should appear within the database"
        User.findByUserName("jess")
    }

    def "registering a user with invalidated data returns an error"() {
        given: "a new user with inaccurate information"
        def userName = "jess"
        def password = "a"

        when: "the user is registered"
        service.createUser(userName,password)

        then: "a flash error message is thrown and the user should not appear within the database"
        thrown(UserException)
    }
}
