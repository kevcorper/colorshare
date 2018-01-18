package colorshare

class UserController {

    def show() { }

    def addToFavorites() {
        try {
            def user = User.findById(session.user.id)
            def color = Color.findById(params.id)
            if (user && color) {
                user.addToFavoriteColors(color)
                user.save(flush: true)
            }
            render(text: '', contentType: "text/html", encoding: "UTF-8")
        } catch (ColorException ce) {
            redirect(uri: "/")
        }
    }

    def removeFromFavorites() {
        try {
            def user = User.findById(session.user.id)
            def color = Color.findById(params.id)
            if (user && color) {
                user.removeFromFavoriteColors(color)
                user.save(flush: true)
            }
            render(text: '', contentType: "text/html", encoding: "UTF-8")
        } catch (ColorException ce) {
            redirect(uri: "/")
        }
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
