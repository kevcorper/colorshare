package colorshare

class UserController {

    def show() { }

    def register(String userName, String password) {
        def user = new User(userName: userName, password: password)
        if (user && user.validate()) {
            user.save()
            flash.message = "Successfully created user"
            session.user = user
            redirect(uri: "/")
        } else {
            flash.message = "Error Registering User"
            return [ user: user ]
        }
    }
}
