function onTelegramAuth(user) {
    // alert('Logged in as ' + user.first_name + ' ' + user.last_name +
    //     ' (' + user.id + (user.username ? ', @' + user.username : '') + ')');

    console.log("user print:");
    console.log(JSON.stringify(user));

    $.ajax({
        url: "/authorization/telegramAuth",
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(user)
    }).done((msgOk) => {
        console.log("success!!!");
        console.log(JSON.stringify(msgOk));
    })
}

