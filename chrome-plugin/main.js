var _USERID;
var _WORDS = [];

chrome.runtime.sendMessage(JSON.stringify({action: "get"}), function (response) {
  findANDHighlight(response.words);
});
