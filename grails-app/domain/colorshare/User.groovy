package colorshare

class User {
    String userName
    String password
    Date   dateCreated

    static hasMany = [colors: Color, favoriteColors: Favorite]

    static constraints = {
        userName size: 3..20, unique: true, nullable: false
        password size: 6..20, nullable: false
    }
}
