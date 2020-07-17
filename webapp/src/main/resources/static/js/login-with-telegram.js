
    function onTelegramAuth(user) {
        alert('Logged in as ' + user.first_name + ' ' + user.last_name +
            ' (' + user.id + (user.username ? ', @' + user.username : '') + ')');
        // $.ajax({
        //     url: "/authorization/telegramAuth",
        //     type: "POST",
        //     contentType: "text; charset=utf-8",
        //     dataType: "text",
        //     data: JSON.stringify(user)
        // }).done((msgSave) => {
        //
        // })
    }

