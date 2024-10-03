
//加载页面
function loadPage(url) {
    document.getElementById('content-frame').src = url;
}
//返回按钮
function goBack() {
    window.history.back();
}

// //获取点击省名
// document.querySelectorAll('#provincesList li').forEach(function(item) {
//     item.addEventListener('click', function() {
//         // 获取省名
//         const provinceName = this.dataset.provinceName;
//         // 调用控制器
//         cityController(provinceName);
//     });
// });

function cityController(provinceName) {
    fetch('${pageContext.request.contextPath}/location/get_cities/' + provinceName, { // 替换为你的控制器 URL
        method: 'GET'
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // 解析 JSON 响应，如果返回的是 JSON 格式
        })
        .then(data => {
            console.log(data); // 处理返回的数据
        })
        .catch(error => {
            console.error('Error:', error); // 捕获并处理错误
        });
}
function districtController(cityName) {
    fetch('${pageContext.request.contextPath}/location/get_districts/' + cityName, { // 替换为你的控制器 URL
        method: 'GET'
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // 解析 JSON 响应，如果返回的是 JSON 格式
        })
        .then(data => {
            console.log(data); // 处理返回的数据
        })
        .catch(error => {
            console.error('Error:', error); // 捕获并处理错误
        });
}