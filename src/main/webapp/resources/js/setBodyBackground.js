function backgroundBody() {
    var idx = Math.floor((new Date().getHours()));
    var body = document.getElementsByTagName("body")[0];
    body.classList.add("heaven-" + idx);
}