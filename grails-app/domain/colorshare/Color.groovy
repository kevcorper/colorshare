package colorshare

class Color {
    String hexCode
    Date   dateCreated

    static belongsTo = [user: User]
    static hasMany   = [favoriters: Favorite]

    static constraints = {
        hexCode size: 4..7, blank: false, nullable: false, unique: true, matches: "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})\$"
    }
}
