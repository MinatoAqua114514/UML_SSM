
//加载页面
function loadPage(url) {
    document.getElementById('content-frame').src = url;
}
//返回按钮
function goBack() {
    window.history.back();
}