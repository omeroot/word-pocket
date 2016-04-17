function spellIt(text) {
    var result = {
        err: true,
        msg: "",
        data: ""
    };

    $.ajax({
        url: "http://localhost:8080/frequency",
        type: "POST",
        dataType: "json",
        async: false,
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({
            sentence: text,
            userId: USERID
        }),
        success: function (response) {
            result.err = false;
            result.data = response;
        },
        error: function (jqxhr, textStatus, errorThrown) {
            result.msg = errorThrown;
        }
    });

    return result;
};

function gWords() {
    var result = {
        err: false,
        msg: "",
        data: []
    };

    $.ajax({
        url: "http://localhost:8080/myfrequency",
        type: "POST",
        dataType: "json",
        async: false,
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({
            userId: USERID
        }),
        success: function (response) {
            result.data = response.words;
        }
    });

    return result;
};
