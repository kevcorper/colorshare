package colorshare

class UserController {

    def userService

    def show() { }

    def register() {
        if (session.user) {
            redirect(controller: "color", action: "index")
        }
    }

    def addUser(String userName, String password) {
        try {
            def user = userService.createUser(userName, password)
            flash.message = "Successfully created user"
            session.user = user
            redirect(controller: "color", action: "index")
        } catch(UserException ue) {
            flash.message = ue.message
            render(view: "register", model: ue.user)
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
                user.removeFromFavoriteColors(color)
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
