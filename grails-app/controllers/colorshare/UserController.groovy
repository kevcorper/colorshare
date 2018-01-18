package colorshare

class UserController {

    def show() { }

    def addToFavorites() {
        def user  = User.findById(session.user.id)
        def color = Color.findById(params.id)
        if (user && color) {
            user.addToFavoriteColors(color)
            user.save(failOnError: true)
        }
        redirect(uri: "/")
    }

    def removeFromFavorites() {

    }

    def register(String userName, String password) {
        def user = new User(userName: userName, password: password)
        if (user && user.validate()) {
            user.save(failOnError: true)
            flash.message = "Successfully created user"
            session.user = user
            redirect(uri: "/")
        } else {
            flash.message = "Error Registering User"
            return [ user: user ]
        }
    }
}
