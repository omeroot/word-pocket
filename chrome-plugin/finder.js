function findANDHighlight(words) {
    var html = document.body.innerHTML;
    var last = html;
    var ex = ["div", "class", "p", "header",
        "footer", "h1", "h2", "h3", "h4", "h5", "h6",
        "div", "nav", "section", "li", "ul", "ol",
        "br", "em", "video", "img", "audio", "canvas",
        "table", "tbody", "td", "th", "tr", "button",
        "input", "textarea", "hr", "style", "link", "a"]

    for (var i = 0; i < words.length; i++) {
        var word = words[i];

        if (!(new RegExp('\\b' + ex.join('\\b|\\b') + '\\b') ).test(word)) {
            last = last.replace(new RegExp(word, 'gi'), function (match) {
                console.log("match", match);
                var el = "<mark>" + match + "</mark>";
                return el;
            });
        }
    }

    document.body.innerHTML = last;
};
