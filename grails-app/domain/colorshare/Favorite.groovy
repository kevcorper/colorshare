package colorshare

class Favorite {

    static belongsTo = [favoriter: User, color: Color]

    static constraints = {
    }
}
