package colorshare



import spock.lang.*

/**
 *
 */
class UserIntegrationSpec extends Specification {

    def "Saving user to the database"() {
        given: "A brand new user"
        def kevin = new User(userName: "Kevin", password: "password123")

        when: "the user is saved"
        kevin.save()

        then: "save is successful and user can be found in database"
        kevin.errors.errorCount == 0
        kevin.id != null
        User.get(kevin.id).userName == "Kevin"
    }

    def "Updating a saved user changes its properties"() {
        given: "An existing user"
        def existingUser = new User(userName: "Kevin", password: "password123")
        existingUser.save(failOnError: true)

        when: "A property is changed"
        def foundUser = User.get(existingUser.id)
        foundUser.password = "newpassword"
        foundUser.save(failOnError: true)

        then: "The change is reflected in the database"
        User.get(existingUser.id).password == "newpassword"
    }

    def "Deleting a user removes it from the database"() {
        given: "An existing user"
        def existingUser = new User(userName: "Kevin", password: "password123")
        existingUser.save(failOnError: true)

        when: "The user is deleted"
        def foundUser = User.get(existingUser.id)
        foundUser.delete(flush: true)

        then: "The user removed from the database"
        !User.exists(foundUser.id)
    }
}
