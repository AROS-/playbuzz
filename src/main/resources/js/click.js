try {
return arguments[0].click();
} catch(e) {
var click_ev =  document.createEvent("MouseEvent");
click_ev.initEvent("click", true, false, window, 0, 0, 0);
return arguments[0].dispatchEvent(click_ev);
};