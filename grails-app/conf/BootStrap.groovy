import colorshare.*

class BootStrap {

    def init = { servletContext ->
        if (!Color.count()) {
            createSampleData()
        }
    }

    private createSampleData() {
        def now = new Date()

        def chuck = new User(
                userName: "chuck_norris",
                password: "password123",
                dateCreated: now).save(failOnError: true)
        def david = new User(
                userName: "davidwu",
                password: "password123",
                dateCreated: now).save(failOnError: true)
        def kevin = new User(
                userName: "kevin",
                password: "password123",
                dateCreated: now).save(failOnError: true)
        def scott = new User(
                userName: "scott",
                password: "password123",
                dateCreated: now).save(failOnError: true)
        def kat = new User(
                userName: "kat",
                password: "password123",
                dateCreated: now).save(failOnError: true)

        chuck.addToColors(hexCode: "#adadad", dateCreated: now)
        chuck.addToColors(hexCode: "#1d23ff", dateCreated: now)
        chuck.addToColors(hexCode: "#ddd", dateCreated: now)
        chuck.save(failOnError: true)
        david.addToColors(hexCode: "#babadd", dateCreated: now)
        david.addToColors(hexCode: "#4f4fdd", dateCreated: now)
        david.addToColors(hexCode: "#aaa", dateCreated: now)
        david.save(failOnError: true)
        kevin.addToColors(hexCode: "#8e8e8e", dateCreated: now)
        kevin.addToColors(hexCode: "#876534", dateCreated: now)
        kevin.save(failOnError: true)
        scott.addToColors(hexCode: "#333", dateCreated: now)
        scott.addToColors(hexCode: "#564656", dateCreated: now)
        scott.save(failOnError: true)
        kat.addToColors(hexCode: "#7e7d7a", dateCreated: now)
        kat.addToColors(hexCode: "#b4b4cc", dateCreated: now)
        kat.save(failOnError: true)

        chuck.addToFavoriteColors(scott.colors[0])
        chuck.addToFavoriteColors(scott.colors[1])
        david.addToFavoriteColors(scott.colors[1])
        david.addToFavoriteColors(kevin.colors[1])
        kat.addToFavoriteColors(kevin.colors[0])
        kat.addToFavoriteColors(kevin.colors[1])
        scott.addToFavoriteColors(kat.colors[1])
        scott.addToFavoriteColors(chuck.colors[1])
        kat.save(failOnError: true)
        scott.save(failOnError: true)
        david.save(failOnError: true)
        chuck.save(failOnError: true)
    }

}
