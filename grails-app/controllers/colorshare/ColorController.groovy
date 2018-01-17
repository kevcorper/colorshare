package colorshare

class ColorController {
    static defaultAction = "index"

    def index() {
        [colors: Color.getAll()]
    }

    def create() {
        def user = User.findByLoginId(params.id)
        if (!user) {
            response.sendError(404)
        } else {
            [ user: user ]
        }
    }

    def addColor() {

    }
}
