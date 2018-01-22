package colorshare

import grails.transaction.Transactional

class UserException extends RuntimeException {
    String message
    User user
}

@Transactional
class UserService {

    def createUser(String userName, String password) {
        def user = new User(userName: userName, password: password)
        if (user.validate() && user.save()) {
            return user
        } else {
            throw new UserException(
                    message: "Invalid username or password", user: user
            )
        }
    }
}
