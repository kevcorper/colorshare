package colorshare

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.mixin.Mock
/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(UserController)
@Mock([User, Color])
class UserControllerSpec extends Specification {

    def "registering a user creates them within the database"() {
        given: "a new user with accurate information"
        def userName = "jess"
        def password = "password123"

        when: "the user is registered"
        controller.register(userName, password)

        then: "the user should appear within the database"
        User.findByUserName("jess")
    }

    def "registering a user with invalidated data returns an error"() {
        given: "a new user with inaccurate information"
        def userName = "jess"
        def password = "bad"

        when: "the user is registered"
        controller.register(userName, password)

        then: "a flash error message is thrown and the user should not appear within the database"
        flash.message == "Error Registering User"
        !User.findByUserName("jess")
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
}
