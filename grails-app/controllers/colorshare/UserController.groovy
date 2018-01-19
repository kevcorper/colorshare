package colorshare

class UserController {

    def show() { }

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

    def addToFavorites() {
        if (session.user && params.id) {
            def user = User.findById(session.user.id)
            def color = Color.findById(params.id)
            if (user && color) {
                user.addToFavoriteColors(color)
                user.save(flush: true)
                render(text: '', contentType: "text/html", encoding: "UTF-8")
            } else {
                flash.message = "Error in adding color to favorites"
                redirect(uri: "/")
            }
        } else {
            flash.message = "Error in adding color to favorites"
            redirect(uri: "/")
        }
    }

    def removeFromFavorites() {
        if (session.user && params.id) {
            def user = User.findById(session.user.id)
            def color = Color.findById(params.id)
            if (user && color) {
                user.favoriteColors.find{it.hexCode==color.hexCode}.delete(flush:true)
//                user.removeFromFavoriteColors(color)
                user.save(flush: true)
                render(text: '', contentType: "text/html", encoding: "UTF-8")
            } else {
                flash.message = "Error in removing color from favorites"
                redirect(uri: "/")
            }
        } else {
            flash.message = "Error in removing color from favorites"
            redirect(uri: "/")
        }
    }
}
