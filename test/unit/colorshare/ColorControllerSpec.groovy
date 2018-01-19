package colorshare

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.mixin.Mock

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(ColorController)
@Mock([User,Color])
class ColorControllerSpec extends Specification {

    def "Get all colors from all users when visiting home aka color-index"() {

        given: "Several users with created colors"
        def jess = new User(
                userName: "jess",
                password: "password123").save(failOnError: true)
        def david = new User(
                userName: "david",
                password: "password123").save(failOnError: true)
        def kevin = new User(
                userName: "kevin",
                password: "password123").save(failOnError: true)
        jess.addToColors(new Color(hexCode: "#dddd00"))
        jess.addToColors(new Color(hexCode: "#eee"))
        jess.addToColors(new Color(hexCode: "#fff"))
        jess.save(flush: true)
        david.addToColors(new Color(hexCode: "#222"))
        david.addToColors(new Color(hexCode: "#333"))
        david.addToColors(new Color(hexCode: "#444"))
        david.save(flush: true)
        kevin.addToColors(new Color(hexCode: "#dedede"))
        kevin.save(flush: true)

        when: "the color index is invoked"
        def model = controller.index()

        then: "all colors created above are returned"
        model.colors.size() == 7
    }

    def "Check that session is empty when visiting color index without being logged in"() {
        given: "several users each with created colors"
        def jess = new User(
                userName: "jess",
                password: "password123").save(failOnError: true)
        def david = new User(
                userName: "david",
                password: "password123").save(failOnError: true)
        def kevin = new User(
                userName: "kevin",
                password: "password123").save(failOnError: true)
        jess.addToColors(new Color(hexCode: "#dddd00"))
        jess.addToColors(new Color(hexCode: "#eee"))
        jess.addToColors(new Color(hexCode: "#fff"))
        jess.save(flush: true)
        david.addToColors(new Color(hexCode: "#222"))
        david.addToColors(new Color(hexCode: "#333"))
        david.addToColors(new Color(hexCode: "#444"))
        david.save(flush: true)
        kevin.addToColors(new Color(hexCode: "#dedede"))
        kevin.save(flush: true)

        and: "no session user is defined"
        session.invalidate()

        when: "the color index is invoked"
        def model = controller.index()

        then: "the defined colors above will be returned with an empty session user"
        model.colors.size() == 7
        !session.user
    }

    def "Get a user's created colors when viewing 'My Colors'"() {

        given: "A user with created colors"
        def jess = new User(
                userName: "jess",
                password: "password123").save(failOnError: true)
        jess.addToColors(new Color(hexCode: "#dddd00"))
        jess.addToColors(new Color(hexCode: "#eee"))
        jess.addToColors(new Color(hexCode: "#fff"))
        jess.save(flush: true)

        and: "a defined session user"
        session.user = jess

        when: "the user's favorites list is invoked"
        def model = controller.userColors()

        then: "the favorite colors initiated above are returned"
        model.colors.size() == 3
    }

    def "check that an attempt to see created colors without logged in user returns an error"() {

        given: "No users"

        and: "no defined session user"
        session.invalidate()

        when: "the user's favorites list is invoked"
        def model = controller.userColors()

        then: "a 404 is sent to the browser"
        response.status == 404
    }

    def "Get a users favorite colors list given they are signed in"() {

        given: "Two users, one with created colors and the other with those colors favorited"
        def jess = new User(
                userName: "jess",
                password: "password123").save(failOnError: true)
        def david = new User(
                userName: "david",
                password: "password123").save(failOnError: true)
        jess.addToColors(new Color(hexCode: "#dddd00"))
        jess.addToColors(new Color(hexCode: "#eee"))
        jess.addToColors(new Color(hexCode: "#fff"))
        jess.save(flush: true)
        david.addToFavoriteColors(jess.colors[0])
        david.addToFavoriteColors(jess.colors[1])
        david.addToFavoriteColors(jess.colors[2])
        david.save(flush: true)

        and: "a defined session user"
        session.user = david

        when: "the user's favorites list is invoked"
        def model = controller.favorites()

        then: "the favorite colors initiated above are returned"
        model.colors.size() == 3
    }

    def "check that an attempt to see favorited colors without logged in user returns an error"() {

        given: "No users"

        and: "no defined session user"
        session.invalidate()

        when: "the user's favorites list is invoked"
        def model = controller.favorites()

        then: "a 404 is sent to the browser"
        response.status == 404
    }
}
