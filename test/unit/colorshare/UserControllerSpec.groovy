package colorshare

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.mixin.Mock
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(UserController)
@Mock([User, Color])
class UserControllerSpec extends Specification {

    def "adding a valid new user"() {
        given: "a mock user service"
        def mockUserService = Mock(UserService)
        1 * mockUserService.createUser(_,_) >> new User (userName: "jess", password: "password123")
        controller.userService = mockUserService

        when: "controller is invoked"
        def result = controller.addUser("jess", "password123")

        then: "redirected to home/color-index, flash message shows succesful creation, user is set to session user and logged in"
        flash.message == "Successfully created user"
        session.user.userName == "jess"
        response.redirectedUrl == '/'
    }

    def "adding favorite colors to users will populate the user's FavoriteColors"() {
        given: "two new users and a new color"
        def jess = new User(
                userName: "jess",
                password: "password123").save(failOnError: true)
        def david = new User(
                userName: "david",
                password: "password123").save(failOnError: true)
        jess.addToColors(new Color(hexCode: "#dddd00"))
        jess.save(flush: true)

        and: "the param id and session id are properly set"
        params.id = jess.colors[0].id
        session.user = david

        when: "the addToFavorites action is inititated"
        controller.addToFavorites()

        then: "the selected color is properly added to the session user's favoriteColors"
        david.favoriteColors.size() == 1
    }


    def "adding favorite colors when no session user is set results in an error"() {
        given: "two new users and a new color"
        def jess = new User(
                userName: "jess",
                password: "password123").save(failOnError: true)
        def david = new User(
                userName: "david",
                password: "password123").save(failOnError: true)
        jess.addToColors(new Color(hexCode: "#dddd00"))
        jess.save(flush: true)

        and: "the param id is properly set but the session user has not been"
        params.id = jess.colors[0].id
        session.invalidate()

        when: "the addToFavorites action is inititated"
        controller.addToFavorites()

        then: "a flash error message is returned"
        flash.message == "Error in adding color to favorites"
    }


    def "removing favorite colors when no session user is set results in an error"() {
        given: "two new users, a new color, and a favorited color between the users"
        def jess = new User(
                userName: "jess",
                password: "password123").save(failOnError: true)
        def david = new User(
                userName: "david",
                password: "password123").save(failOnError: true)
        jess.addToColors(new Color(hexCode: "#dddd00"))
        jess.save(flush: true)
        david.addToFavoriteColors(jess.colors[0])
        david.save(flush: true)

        and: "the param id and session id are properly set"
        params.id = jess.colors[0].id
        session.invalidate()

        when: "the removeFromFavorites action is inititated"
        controller.removeFromFavorites()

        then: "the selected color is properly removed from the session user's favoriteColors"
        flash.message == "Error in removing color from favorites"
    }

    @Unroll
    def "Registration command command object validates correctly"() {
        given: "a mocked command object"
        def urc = mockCommandObject(UserRegistrationCommand)

        and: "a set of initial values from the spock test"
        urc.userName = userName
        urc.password = password
        urc.passwordRepeat = passwordRepeat

        when: "the validator is invoked"
        def isValidRegistration = urc.validate()

        then: "the appropriate fields are flagged as errors"
        isValidRegistration == anticipatedValid
        urc.errors.getFieldError(fieldInError)?.code == errorCode

        where:
        userName | password | passwordRepeat | anticipatedValid | fieldInError | errorCode
        "glen" | "password" | "no-match" | false | "passwordRepeat" | "validator.invalid"
        "peter" | "password" | "password" | true | null | null
    }

    def "Invoking the new addUser2 action via a command object"() {
        given: "A configured command object and mock user service"
        def urc = mockCommandObject(UserRegistrationCommand)
        urc.with {
            userName = "jess"
            password = "password"
            passwordRepeat = "password"
        }
        def mockUserService = Mock(UserService)
        1 * mockUserService.createUser(_,_) >> new User (userName: "jess", password: "password")
        controller.userService = mockUserService

        and: "which has been validated"
        urc.validate()

        when: "the register action is invoked"
        controller.addUser2(urc)

        then: "the user is registered and browser redirected"
        !urc.hasErrors()
        response.redirectedUrl == '/'
        User.count() == 1
    }
}
