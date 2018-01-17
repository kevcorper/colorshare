package colorshare

class LoginController {
    static defaultAction = "loginForm"

    def loginForm() {
        if (session.user) {
            redirect(uri: "/")
        }
    }

    def logout() {
        session.invalidate()
        redirect(uri: "/")
    }

    def logIn(String userName, String password) {
        def user = User.findByUserName(userName)
        if (user && user.password == password) {
            session.user = user
            redirect(uri: "/")
        } else {
            flash.error = "Unknown username or password"
            redirect(action: "loginForm")
        }
    }
}
