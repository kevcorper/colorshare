package colorshare

class ColorController {
    static defaultAction = "index"

    def colorService

    def index() {
        [colors: Color.getAll()]
    }

    def create() {
        def user = session.user
        if (!user) {
            response.sendError(404)
            redirect (controller: "login", action: 'login')
        }
    }

    def addColor() {
        try {
            def newColor = colorService.createColor(session.user.userName, params.hexCode)
            flash.message = "Success"
        } catch (ColorException pe) {
            flash.message = pe.message
        }

        if (flash.message == "Success") {
            redirect(action: 'index', id: params.id)
        } else {
            redirect(action: 'create', id: params.id)
        }
    }
}
