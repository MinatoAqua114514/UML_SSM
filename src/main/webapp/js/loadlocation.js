let data = {};

// 读取 pca.json 数据
function loadData() {
    fetch('/UML_SSM_war_exploded/resource/pca.json')
        .then(response => response.json())
        .then(jsonData => {
            data = jsonData;
            loadProvinces();
        })
        .catch(error => console.error('Error loading data:', error));
}

// 加载省数据
function loadProvinces() {
    const provinceSelect = document.getElementById('provinceSelect');
    for (const province in data) {
        const option = document.createElement('option');
        option.value = province;
        option.textContent = province;
        provinceSelect.appendChild(option);
    }
}

// 加载市数据
function loadCities() {
    const provinceSelect = document.getElementById('provinceSelect');
    const citySelect = document.getElementById('citySelect');
    citySelect.innerHTML = '<option value="">市</option>'; // 清空市下拉框

    const selectedProvince = provinceSelect.value;
    if (selectedProvince) {
        const cities = Object.keys(data[selectedProvince]);
        cities.forEach(city => {
            const option = document.createElement('option');
            option.value = city;
            option.textContent = city;
            citySelect.appendChild(option);
        });
    }
}

// 加载区数据
function loadDistricts() {
    const provinceSelect = document.getElementById('provinceSelect');
    const citySelect = document.getElementById('citySelect');
    const districtSelect = document.getElementById('districtSelect');
    districtSelect.innerHTML = '<option value="">区</option>'; // 清空区下拉框

    const selectedProvince = provinceSelect.value;
    const selectedCity = citySelect.value;
    if (selectedProvince && selectedCity) {
        const districts = data[selectedProvince][selectedCity];
        districts.forEach(district => {
            const option = document.createElement('option');
            option.value = district;
            option.textContent = district;
            districtSelect.appendChild(option);
        });
    }
}

// 初始化加载数据
window.onload = loadData;