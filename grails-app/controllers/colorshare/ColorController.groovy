package colorshare

class ColorController {
    static defaultAction = "index"

    def colorService

    def index() {
        [colors: Color.getAll()]
    }

    def userColors() {
        def currentUser = session.user
        if (!currentUser) {
            response.sendError(404)
        } else {
            def user = User.findById(currentUser.id)
            [ colors: user.colors ]
        }
    }

    def favorites() {
        def currentUser = session.user
        if (!currentUser) {
            response.sendError(404)
        } else {
            def user = User.findById(currentUser.id)
            [colors: user.favoriteColors]
        }
    }

    def create() {
        def user = session.user
        if (!user) {
            response.sendError(404)
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
            redirect(action: 'index')
        } else {
            redirect(action: 'create')
        }
    }
}
