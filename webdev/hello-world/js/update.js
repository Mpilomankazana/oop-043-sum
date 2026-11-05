// js/update.js
// Q5.1.1: when the user clicks one of the languages in the list,
// show "You chose <language>" in the paragraph with id "selection".

var ul = document.getElementById("languages");

ul.onclick = function (event) {
    // event.target is the element that was actually clicked (an <li>)
    if (event.target.tagName === "LI") {
        document.getElementById("selection").textContent =
            "You chose " + event.target.textContent;
    }
};
