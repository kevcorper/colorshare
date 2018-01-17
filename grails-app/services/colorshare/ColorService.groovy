package colorshare

import grails.transaction.Transactional

class ColorException extends RuntimeException {
    String message
    Color color
}

@Transactional
class ColorService {

    Color createColor(String userName, String hexCode) {
        def user = User.findByUserName(userName)
        if (user) {
            def color = new Color(hexCode: hexCode)
            user.addToColors(color)
            if(color.validate() && user.save()) {
                return color
            } else {
                throw new ColorException(message: "Invalid, non-unique or empty color", color: color)
            }
        }
        throw new ColorException(message: "Invalid User Name")
    }
}
