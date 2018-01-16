package colorshare

class Color {
    User   creator
    String hexCode
    Date   dateCreated

    static hasMany   = [favoriters: User]
    static belongsTo = [User]

    static constraints = {
        hexCode size: 4..7, blank: false, nullable: false, unique: true, matches: "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})\$"
    }
}
