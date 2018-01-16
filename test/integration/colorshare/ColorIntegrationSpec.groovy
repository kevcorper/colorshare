package colorshare



import spock.lang.*

/**
 *
 */
class ColorIntegrationSpec extends Specification {

    def "Adding colors to user links the color to the user"() {
        given: "A new user"
        def user = new User(userName: "Kevin", password: "password123")
        user.save(failOnError: true)

        when: "Several colors are added to the user"
        user.addToColors(new Color(hexCode: "#fdfdfd"))
        user.addToColors(new Color(hexCode: "#666"))
        user.addToColors(new Color(hexCode: "#cdcdcd"))

        then: "The user has a list of colors attached"
        User.get(user.id).colors.size() == 3
    }

    def "Ensure that colors linked to a user can be retrieved"() {
        given: "A user with several colors"
        def user = new User(userName: "Kevin", password: "password123")
        user.save(failOnError: true)
        user.addToColors(new Color(hexCode: "#fdfdfd"))
        user.addToColors(new Color(hexCode: "#666"))
        user.addToColors(new Color(hexCode: "#cdcdcd"))

        when: "The user is retrieved by ID"
        def foundUser = User. get(user.id)
        def sortedColors = foundUser.colors.collect {it.hexCode}.sort()

        then: "The colors appear on the retrieved user"
        sortedColors == ["#666", "#cdcdcd", "#fdfdfd"]
    }
}
