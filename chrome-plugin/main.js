var _USERID;
var _WORDS = ["tell", "google", "link", "class", "hello", "element"];

findANDHighlight(_WORDS);

chrome.runtime.onMessage.addListener(function (request, sender, sendResponse) {
    if (request.action === "register") {
        _USERID = request.myId;
    } else if (request.action === "update") {
        _WORDS = request.words;
    }
});
