var USERID;

function getRandomToken() {
    var randomPool = new Uint8Array(32);//32*8 256 bit tokenize
    var hex = "";

    crypto.getRandomValues(randomPool);
    for (var i = 0; i < randomPool.length; i++) {
        hex += randomPool[i].toString(16);
    }

    return hex;
}

chrome.runtime.onInstalled.addListener(function () {

  chrome.runtime.onMessage.addListener(function (request, sender, sendResponse) {
    sendResponse({action: "update", words: gWords().data});
  });
    chrome.storage.sync.get("userId", function (items) {
        var userId = items.userId;

        if (!items.userId) {
            var id = getRandomToken();
            chrome.storage.sync.set({userId: id}, function () {
                console.log(id);
                USERID = id;
            });
        } else {
            USERID = userId;
        }
    });

    var selectCopy = chrome.contextMenus.create({
        "title": "Spell",
        "contexts": ["selection"],
        "onclick": function (data, tab) {
            var response = spellIt(data.selectionText);

            if (response.err) {
                alert(response.msg);
            }
        }
    });
});
