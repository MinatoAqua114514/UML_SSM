
//加载页面
function loadPage(url) {
    document.getElementById('content-frame').src = url;
}
//返回按钮
function goBack() {
    window.history.back();
}

// const contextPath = "${pageContext.request.contextPath}";
//
// document.getElementById('provinceSelect').onchange = function() {
//     const province = this.value;
//     fetch(`/location/get_cities/${province}`)
//         .then(response => response.json())
//         .then(cities => {
//             const citySelect = document.getElementById('citySelect');
//             citySelect.innerHTML = '<option value="">市</option>'; // 清空
//             cities.forEach(city => {
//                 citySelect.innerHTML += `<option value="${city}">${city}</option>`;
//             });
//         })
//         .catch(error => console.error('Error fetching cities:', error));
// };
//
// document.getElementById('citySelect').onchange = function() {
//     const city = this.value;
//     fetch(`${contextPath}/location/get_districts/${city}`)
//         .then(response => response.json())
//         .then(districts => {
//             const districtSelect = document.getElementById('districtSelect');
//             districtSelect.innerHTML = '<option value="">区</option>'; // 清空
//             districts.forEach(district => {
//                 districtSelect.innerHTML += `<option value="${district}">${district}</option>`;
//             });
//         })
//         .catch(error => console.error('Error fetching districts:', error));
// };


// //获取点击省名
// document.querySelectorAll('#provincesList li').forEach(function(item) {
//     item.addEventListener('click', function() {
//         // 获取省名
//         const provinceName = this.dataset.provinceName;
//         // 调用控制器
//         cityController(provinceName);
//     });
// });

// function cityController(provinceName) {
//     fetch('${pageContext.request.contextPath}/location/get_cities/' + provinceName, { // 替换为你的控制器 URL
//         method: 'GET'
//     })
//         .then(response => {
//             if (!response.ok) {
//                 throw new Error('Network response was not ok');
//             }
//             return response.json(); // 解析 JSON 响应，如果返回的是 JSON 格式
//         })
//         .then(data => {
//             console.log(data); // 处理返回的数据
//         })
//         .catch(error => {
//             console.error('Error:', error); // 捕获并处理错误
//         });
// }
// function districtController(cityName) {
//     fetch('${pageContext.request.contextPath}/location/get_districts/' + cityName, { // 替换为你的控制器 URL
//         method: 'GET'
//     })
//         .then(response => {
//             if (!response.ok) {
//                 throw new Error('Network response was not ok');
//             }
//             return response.json(); // 解析 JSON 响应，如果返回的是 JSON 格式
//         })
//         .then(data => {
//             console.log(data); // 处理返回的数据
//         })
//         .catch(error => {
//             console.error('Error:', error); // 捕获并处理错误
//         });
// }