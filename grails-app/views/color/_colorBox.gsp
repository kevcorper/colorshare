<div class="color-box" >
    <div class="color-display" style="background:${color.hexCode}; width: 200px; height: 100px;"></div>
    <div class="color-info">
        <div class="color-hex">${color.hexCode}</div>
        <div class="color-creator">by: ${color.creator.userName}</div>
        <div class="color-date">${color.dateCreated.toString().substring(0,10)}</div>
        <div class="color-favorite">
            <g:if test="${session.user && session.user.userName in (color.favoriters.collect { it.userName})}" >
                <g:link controller="user" action="removeFromFavorites" id="${color.id}"><i class="fa fa-heart"></i></g:link>
            </g:if>
            <g:elseif test="${session.user}">
                <g:link controller="user" action="addToFavorites" id="${color.id}"><i class="fa fa-heart-o"></i></g:link>
                <g:remoteLink controller="user" action="addToFavorites" id="${color.id}"><i class="fa fa-heart-o"></i></g:remoteLink>
            </g:elseif>
        </div>
    </div>
</div>