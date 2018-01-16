package colorshare



import spock.lang.*

/**
 *
 */
class FavoriteIntegrationSpec extends Specification {

    def "Add a color to a user's favorite"() {
        given: "Two new users"
        def user = new User(userName: "Kevin", password: "password123")
        user.save(failOnError: true)
        def favoriteColor = new Color(hexCode: "#fdfdfd")
        user.addToColors(favoriteColor)
        def user2 = new User(userName: "David", password: "password123")
        user2.save(failOnError: true)

        when: "One user favorites another's color"
        user2.addToFavoriteColors(favoriteColor)

        then: "The favorite object saves to the database"
        user2.favoriteColors.size() == 1
    }

    def "Ensure that a user as both a cerator and favoriter and the actual color can be correctly retrieved"() {
        given: "Two users each with colors attached"
        def user = new User(userName: "Kevin", password: "password123")
        user.save(failOnError: true)
        def favoriteColor = new Color(hexCode: "#fdfdfd")
        user.addToColors(favoriteColor)
        def user2 = new User(userName: "David", password: "password123")
        user2.save(failOnError: true)

        when: "One user favorites another's color"
        user2.addToFavoriteColors(favoriteColor)

        then: "The user and color can be retrieved correctly"
        user2.favoriteColors[0].creator.userName == "Kevin"
        user2.favoriteColors[0].hexCode == "#fdfdfd"
        favoriteColor.favoriters[0].userName == "David"
    }
}
